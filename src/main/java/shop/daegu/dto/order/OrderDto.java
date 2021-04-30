package shop.daegu.dto.order;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import shop.daegu.domain.Order;
import shop.daegu.domain.OrderItem;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public OrderDto(Order order, List<OrderItem> orderItems) {
        this.orderId = order.getId();
        this.clientName = order.getClient().getName();
        this.orderDate = order.getOrderDate();
        this.orderItems = orderItems.stream()
                .map(orderItem -> new OrderItemDto(orderItem))
                .collect(Collectors.toList());
    }
}
