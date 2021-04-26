package shop.daegu.repository.filtergroup;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import shop.daegu.domain.filter.FilterGroup;
import shop.daegu.domain.filter.QFilterGroup;

import javax.persistence.EntityManager;
import java.util.List;

import static shop.daegu.domain.filter.QFilterGroup.*;

@Repository
public class FilterGroupSearchRepository {
    private final JPAQueryFactory queryFactory;

    public FilterGroupSearchRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }



}
