package shop.daegu.repository.filter;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.daegu.domain.Group;
import shop.daegu.domain.filter.ItemFilter;

public interface FilterRepository extends JpaRepository<ItemFilter,Long> {
}
