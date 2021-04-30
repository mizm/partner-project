package shop.daegu.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import shop.daegu.dto.excel.ExcelData;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of={"id","name","price","count","size"})
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;
    private String name;
    private int price;
    private int count;
    private String size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oredr_id")
    private Order order;

    //order편의 매서드 전용
    public void addOrder(Order order) {
        this.order = order;
    }

    public OrderItem(ExcelData excelData) {
        this.name = excelData.getItemName();
        this.price = excelData.getPrice();
        this.count = excelData.getCount();
        this.size = excelData.getSize();
    }

}
