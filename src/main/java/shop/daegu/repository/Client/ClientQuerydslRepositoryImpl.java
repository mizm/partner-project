package shop.daegu.repository.Client;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import shop.daegu.domain.Client;
import shop.daegu.dto.client.ClientSearchCondition;

import javax.persistence.EntityManager;
import java.util.List;

import static org.springframework.util.StringUtils.*;
import static shop.daegu.domain.QClient.*;
import static shop.daegu.domain.QGroup.*;

public class ClientQuerydslRepositoryImpl implements ClientQuerydslRepository{

    private final JPAQueryFactory queryFactory;

    public ClientQuerydslRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Client> findAllByQuerydsl(ClientSearchCondition cond) {
         return queryFactory
                .select(client)
                .from(client)
                .leftJoin(client.group, group).fetchJoin()
                 .where(
                         clientNameEq(cond.getClientName()),
                         groupNameEq(cond.getGroupName())
                 )
                .fetch();
    }

    private BooleanExpression groupNameEq(String groupName) {
        return hasText(groupName) ? group.name.eq(groupName) : null;
    }

    private BooleanExpression clientNameEq(String clientName) {
        return hasText(clientName) ? client.name.eq(clientName) : null;
    }
}
