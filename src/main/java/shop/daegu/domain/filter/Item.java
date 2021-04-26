package shop.daegu.domain.filter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import shop.daegu.domain.filter.FilterGroup;
import shop.daegu.domain.filter.FilterType;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","name"})
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    public Item(String name) {
        this.name = name;
    }
}
