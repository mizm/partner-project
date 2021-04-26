package shop.daegu;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static shop.daegu.domain.QClient.client;
import static shop.daegu.domain.QOrder.order;
import static shop.daegu.domain.QOrderItem.orderItem;

@SpringBootTest
@Transactional
public class queryDslTest {

    @Autowired
    EntityManager em;

    //동시성 문제 상관없음
    JPAQueryFactory queryFactory;
    @BeforeEach
    void before() {
        queryFactory = new JPAQueryFactory(em);
    }

    @Test
    void builderTest() {
        List<String> items2 = new ArrayList<>();
        items2.add("hi");
        List<String> items = new ArrayList<>();
        items.add("test");
        items.add("start");
        items.add("go");
        queryFactory
                .select(order)
                .from(order)
                .leftJoin(order.client, client).fetchJoin()
                .leftJoin(order.orderItems, orderItem).fetchJoin()
                .where(
                        includeFilter(items2),
                        excludeFilter(items)
                ).fetch();
    }

    private BooleanBuilder includeFilter(List<String> filter) {
        if(filter.size() == 0) return null;

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        for (String name : filter) {
            booleanBuilder.or(nameStartWith(name));
        }
        return booleanBuilder;
    }

    private BooleanBuilder excludeFilter(List<String> filter) {
        if(filter.size() == 0) return null;

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        for (String name : filter) {
            booleanBuilder.or(nameStartWithIgnore(name));
        }
        return booleanBuilder;
    }

    private BooleanBuilder nameStartWithIgnore(String name) {
        return nullSafeBuilder(() -> orderItem.name.notLike(name+"%"));
    }
    private BooleanBuilder nameStartWith(String name) {
        return nullSafeBuilder(() -> orderItem.name.startsWith(name));
    }

    public static BooleanBuilder nullSafeBuilder(Supplier<BooleanExpression> f) {
        try {
            return new BooleanBuilder(f.get());
        } catch (IllegalArgumentException e) {
            return new BooleanBuilder();
        }
    }
}
