package org.pp.petionary.repository.order;

import org.pp.petionary.entity.order.Orders;
import org.pp.petionary.entity.user.Users;
import org.pp.petionary.type.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    Optional<Orders> findByOrderIdAndUsers(Long orderId, Users user);

    @Modifying
    @Transactional
    @Query("UPDATE Orders o SET o.orderStatus = :orderStatus WHERE o.orderId = :orderId")
    void updateOrderStatus(@Param("orderId") Long orderId, @Param("orderStatus") OrderStatus orderStatus);

    List<Orders> findAllByOrderStatusAndOrderDateBefore(OrderStatus orderStatus, LocalDateTime dateTime);


    // userId에 해당하는 order
    @Query("SELECT o " +
            "FROM Orders o " +
            "LEFT JOIN o.users u " +
            "WHERE u.userId = :userId ")
    List<Orders> findOrdersByUsersId(Long userId);

}
