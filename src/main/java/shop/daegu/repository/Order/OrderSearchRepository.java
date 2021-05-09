package shop.daegu.repository.Order;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import shop.daegu.domain.*;
import shop.daegu.dto.order.*;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Supplier;

import static org.springframework.util.StringUtils.hasText;
import static shop.daegu.domain.QClient.client;
import static shop.daegu.domain.QOrder.*;
import static shop.daegu.domain.QOrderItem.*;
import static shop.daegu.domain.QTotalOrder.*;

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
                .leftJoin(order.totalOrder, totalOrder).fetchJoin()
                .where(
                        clientNameEq(cond.getClientName()),
                        orderDateEq(cond.getOrderDate()),
                        totalOrderIdEq(cond.getTotalOrderId())
                ).fetch();
    }

    public List<Order> findByFilter(OrderSearch cond, OrderFilter filter) {
        return queryFactory
                .select(order)
                .distinct()
                .from(order)
                .leftJoin(order.totalOrder, totalOrder).fetchJoin()
                .leftJoin(order.client, client).fetchJoin()
                .leftJoin(order.orderItems, orderItem).fetchJoin()
                .where(
                        totalOrderIdEq(cond.getTotalOrderId()),
                        includeFilter(filter.getIncludeItems()),
                        excludeFilter(filter.getExcludeItems())
                )
                .fetch();
    }

    public List<OrderItem> findOrderItemByFilter(OrderSearch cond, OrderFilter filter) {
        return queryFactory
                .select(orderItem)
                .from(orderItem)
                .leftJoin(orderItem.order, order).fetchJoin()
                .leftJoin(order.client, client).fetchJoin()
                .leftJoin(order.totalOrder, totalOrder)
                .where(
                        totalOrderIdEq(cond.getTotalOrderId()),
                        includeFilter(filter.getIncludeItems()),
                        excludeFilter(filter.getExcludeItems())
                ).fetch();
    }


    public List<TotalOrder> findTotalOrders(TotalOrderSearch totalOrderSearch) {
        return queryFactory
                .select(totalOrder)
                .from(totalOrder)
                .where(
                        totalOrderIdEq(totalOrderSearch.getTotalOrderId()),
                        totalOrderDateEq(totalOrderSearch.getOrderDate())
                ).fetch();
    }

    private BooleanExpression totalOrderDateEq(String orderDate) {
        return hasText(orderDate) ? totalOrder.orderDate.eq(LocalDate.parse(orderDate, DateTimeFormatter.ISO_DATE)) : null;
    }

    private BooleanExpression totalOrderIdEq(Long id) {
        return id != null ? totalOrder.id.eq(id) : null;
    }

    private BooleanExpression orderDateEq(String orderDate) {
        return hasText(orderDate) ? order.orderDate.eq(LocalDate.parse(orderDate, DateTimeFormatter.ISO_DATE)) : null;
    }

    private BooleanExpression clientNameEq(String clientName) {
        return hasText(clientName) ? client.name.eq(clientName) : null;
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
            booleanBuilder.or(nameNotStartWith(name));
        }
        return booleanBuilder;
    }
    private BooleanBuilder nameStartWith(String name) {
        return nullSafeBuilder(() -> orderItem.name.startsWith(name));
    }
    private BooleanBuilder nameNotStartWith(String name) {
        return nullSafeBuilder(() -> orderItem.name.notLike(name + "%"));
    }

    public static BooleanBuilder nullSafeBuilder(Supplier<BooleanExpression> f) {
        try {
            return new BooleanBuilder(f.get());
        } catch (IllegalArgumentException e) {
            return new BooleanBuilder();
        }
    }

}
