package shop.daegu.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "Orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "total_order_id")
    private TotalOrder totalOrder;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.addOrder(this);
    }
    public Order(Client client, LocalDate orderDate, TotalOrder totalOrder) {
        this.client = client;
        this.orderDate = orderDate;
        this.totalOrder = totalOrder;
        totalOrder.addOrder(this);
    }
    public static Order createOrder(Client client, LocalDate orderDate, TotalOrder totalOrder,  OrderItem... orderItems) {
        Order order = new Order(client,orderDate,totalOrder);
        Arrays.stream(orderItems).forEach(order::addOrderItem);
        return order;
    }

}
