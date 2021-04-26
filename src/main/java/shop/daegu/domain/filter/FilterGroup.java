package shop.daegu.domain.filter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "Filter_groups")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","name","filterType"})
public class FilterGroup {

    @Id @GeneratedValue
    @Column(name = "filter_group_id")
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private FilterType filterType;

    public FilterGroup(String name, FilterType filterType) {
        this.name = name;
        this.filterType = filterType;
    }

    public boolean checkFilterType() {
        return filterType.equals(FilterType.INCLUDE);
    }
}
