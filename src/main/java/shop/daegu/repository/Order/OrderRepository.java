package shop.daegu.repository.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.daegu.domain.Client;
import shop.daegu.domain.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
