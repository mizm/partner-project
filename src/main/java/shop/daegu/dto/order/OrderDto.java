package shop.daegu.dto.order;

import lombok.Getter;
import shop.daegu.domain.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrderDto {

    private Long orderId;
    private String clientName;
    private LocalDate orderDate;
    private List<OrderItemDto> orderItems;

    public OrderDto(Order order) {
        this.orderId = order.getId();
        this.clientName = order.getClient().getName();
        this.orderDate = order.getOrderDate();
        this.orderItems = order.getOrderItems().stream()
                .map(orderItem -> new OrderItemDto(orderItem))
                .collect(Collectors.toList());
    }
}
