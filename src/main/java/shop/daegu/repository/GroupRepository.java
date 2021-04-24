package shop.daegu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.daegu.domain.Group;
import shop.daegu.domain.Order;

public interface GroupRepository extends JpaRepository<Group,Long> {
}
