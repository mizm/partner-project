package shop.daegu.repository.filtergroup;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import shop.daegu.domain.filter.ItemFilterGroup;
import shop.daegu.domain.filter.QFilterGroup;
import shop.daegu.domain.filter.QItem;
import shop.daegu.domain.filter.QItemFilterGroup;

import javax.persistence.EntityManager;
import java.util.List;

import static shop.daegu.domain.filter.QFilterGroup.*;
import static shop.daegu.domain.filter.QItem.*;
import static shop.daegu.domain.filter.QItemFilterGroup.*;

@Repository
public class ItemFilterGroupSearchRepository {

    private final JPAQueryFactory queryFactory;

    public ItemFilterGroupSearchRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<ItemFilterGroup> findByGroupIds(List<Long> ids) {
        return queryFactory
                .select(itemFilterGroup)
                .from(itemFilterGroup)
                .leftJoin(itemFilterGroup.filterGroup, filterGroup).fetchJoin()
                .leftJoin(itemFilterGroup.item, item).fetchJoin()
                .where(itemFilterGroup.filterGroup.id.in(ids))
                .fetch();
    }


}
