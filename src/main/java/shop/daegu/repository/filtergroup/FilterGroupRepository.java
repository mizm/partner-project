package shop.daegu.repository.filtergroup;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.daegu.domain.Group;
import shop.daegu.domain.filter.FilterGroup;

public interface FilterGroupRepository extends JpaRepository<FilterGroup,Long> {

}
