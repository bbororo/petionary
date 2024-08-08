package org.pp.petionary.product.service;

import lombok.RequiredArgsConstructor;
import org.pp.petionary.global.exception.BadRequestException;
import org.pp.petionary.global.service.CommonService;
import org.pp.petionary.global.type.ErrorCode;
import org.pp.petionary.order.service.OrderService;
import org.pp.petionary.product.entity.Stock;
import org.pp.petionary.product.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockService {

    private final RedisTemplate<String, Stock> redisTemplate;
    private final StockRepository stockRepository;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(StockService.class);

    public Stock getStock(Long stockId) {
        var key = "stock:%d".formatted(stockId);
        Stock stock = redisTemplate.opsForValue().get(key);
        // 레디스에서 가져올때
        if (stock != null){
            logger.info("레디스에서 get, stockId = ", stockId);
            return stock;
        }

        // 레디스에 없음 -> db에서 가져옴 , cache set
        logger.info("db에서 get, 레디스 set,  stockId = ", stockId);
        stock = stockRepository.findById(stockId).orElseThrow(() -> new BadRequestException(ErrorCode.OUT_OF_STOCK));
        redisTemplate.opsForValue().set(key, stock);
        return stock;

    }

    @Async
    @Transactional
    public void updateStock(Stock stock){
        stockRepository.save(stock);
    }

}
