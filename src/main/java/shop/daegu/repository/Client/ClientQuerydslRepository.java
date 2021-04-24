package shop.daegu.repository.Client;

import shop.daegu.domain.Client;
import shop.daegu.dto.client.ClientSearchCondition;

import java.util.List;

public interface ClientQuerydslRepository {

    List<Client> findAllByQuerydsl(ClientSearchCondition cond);
}
