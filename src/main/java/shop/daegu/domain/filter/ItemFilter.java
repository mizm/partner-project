package shop.daegu.domain.filter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.daegu.domain.filter.FilterGroup;
import shop.daegu.domain.filter.FilterType;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemFilter {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private FilterType filterType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filter_group_id")
    private FilterGroup filterGroup;


}
