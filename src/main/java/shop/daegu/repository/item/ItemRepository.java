package shop.daegu.repository.filter;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.daegu.domain.Group;
import shop.daegu.domain.filter.FilterGroup;
import shop.daegu.domain.filter.ItemFilter;

import java.util.Collection;
import java.util.List;

public interface FilterRepository extends JpaRepository<ItemFilter,Long> {

    List<ItemFilter> findByFilterGroup_IdIn(List<Long> ids);
}
