package shop.daegu.repository.Order;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import shop.daegu.domain.Order;
import shop.daegu.dto.order.OrderSearch;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;
import static shop.daegu.domain.QClient.client;
import static shop.daegu.domain.QOrder.*;

@Repository
public class OrderSearchRepository {

    private final JPAQueryFactory queryFactory;

    public OrderSearchRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<Order> findBySearch(OrderSearch cond) {
        return queryFactory
                .select(order)
                .from(order)
                .leftJoin(order.client, client).fetchJoin()
                .where(
                        clientNameEq(cond.getClientName()),
                        orderDateEq(cond.getOrderDate())
                ).fetch();
    }

    private Predicate orderDateEq(String orderDate) {
        return hasText(orderDate) ? order.orderDate.eq(LocalDate.parse(orderDate, DateTimeFormatter.ISO_DATE)) : null;
    }

    private BooleanExpression clientNameEq(String clientName) {
        return hasText(clientName) ? client.name.eq(clientName) : null;
    }
}
