package shop.daegu.repository.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.daegu.domain.TotalOrder;

import java.time.LocalDate;
import java.util.List;

public interface TotalOrderRepository extends JpaRepository<TotalOrder, Long> {

    Long countByOrderDate(LocalDate orderDate);
}
