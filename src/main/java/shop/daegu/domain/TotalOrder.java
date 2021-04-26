package shop.daegu.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "TotalOrders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TotalOrder {

    @Id @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "totalOrder")
    private List<Order> orders = new ArrayList<>();

    private LocalDate orderDate;

    private LocalDateTime createDate;

    private Long ord;

    public TotalOrder(LocalDate orderDate) {
        this.orderDate = orderDate;
        this.createDate = LocalDateTime.now();
    }

    public void changeOrd(Long ord) {
        this.ord = ord;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }
}
