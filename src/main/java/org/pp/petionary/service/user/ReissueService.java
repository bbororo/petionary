package org.pp.petionary.service.user;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.pp.petionary.entity.user.RefreshEntity;
import org.pp.petionary.repository.user.RefreshRepository;
import org.pp.petionary.security.jwt.JWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ReissueService {

    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    public ResponseEntity<?> reissueToken(HttpServletRequest request, HttpServletResponse response) {

        //get refresh token
        String refresh = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {

            if (cookie.getName().equals("refresh")) {

                refresh = cookie.getValue();
            }
        }

        if (refresh == null) {

            //response status code
            return new ResponseEntity<>("refresh token null", HttpStatus.BAD_REQUEST);
        }

        //expired check
        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {

            //response status code
            return new ResponseEntity<>("refresh token expired", HttpStatus.BAD_REQUEST);
        }

        // 토큰이 refresh인지 확인 (발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(refresh);

        if (!category.equals("refresh")) {

            //response status code
            return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
        }

        //DB에 저장되어 있는지 확인
        Boolean isExist = refreshRepository.existsByRefresh(refresh);
        if (!isExist) {

            //response body
            return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
        }

        String email = jwtUtil.getUsername(refresh);
        String role = jwtUtil.getRole(refresh);

        //make new JWT
        String newAccess = jwtUtil.createJwt("access", email, role, 600000L);
        String newRefresh = jwtUtil.createJwt("refresh", email, role, 86400000L);

        //Refresh 토큰 저장 DB에 기존의 Refresh 토큰 삭제 후 새 Refresh 토큰 저장
        refreshRepository.deleteByRefresh(refresh);
        addRefreshEntity(email, newRefresh, 86400000L);

        //response
        response.setHeader("access", newAccess);
        response.addCookie(createCookie("refresh", newRefresh));

        return new ResponseEntity<>(HttpStatus.OK);

    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        //cookie.setSecure(true);
        //cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }

    private void addRefreshEntity(String email, String refresh, Long expiredMs) {

        Date date = new Date(System.currentTimeMillis() + expiredMs);

        RefreshEntity refreshEntity = new RefreshEntity();
        refreshEntity.setEmail(email);
        refreshEntity.setRefresh(refresh);
        refreshEntity.setExpiration(date.toString());

        refreshRepository.save(refreshEntity);
    }
}

//TODO
// Refresh 토큰 저장소에서 기한이 지난 토큰 삭제
//TTL 설정을 통해 자동으로 Refresh 토큰이 삭제되면 무방하지만 계속해서 토큰이 쌓일 경우 용량 문제가 발생할 수 있다.
//따라서 스케줄 작업을 통해 만료시간이 지난 토큰은 주기적으로 삭제하는 것이 올바르다.