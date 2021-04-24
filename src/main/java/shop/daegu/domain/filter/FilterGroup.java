package shop.daegu.domain.filter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "Groups")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FilterGroup {

    @Id @GeneratedValue
    @Column(name = "filter_group_id")
    private Long id;
    private String name;

    public FilterGroup(String name) {
        this.name = name;
    }
}
