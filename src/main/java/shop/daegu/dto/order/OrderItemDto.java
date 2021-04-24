package shop.daegu.dto.order;

import lombok.Getter;
import shop.daegu.domain.OrderItem;

@Getter
public class OrderItemDto {
    private String itemName;
    private int price;
    private int count;
    private String size;
    public OrderItemDto(OrderItem orderItem) {
        this.itemName = orderItem.getName();
        this.price = orderItem.getPrice();
        this.count = orderItem.getCount();
        this.size = orderItem.getSize();
    }
}
