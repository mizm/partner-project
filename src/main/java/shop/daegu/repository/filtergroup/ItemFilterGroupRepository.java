package shop.daegu.repository.filtergroup;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.daegu.domain.filter.ItemFilterGroup;

import java.util.List;

public interface ItemFilterGroupRepository extends JpaRepository<ItemFilterGroup, Long> {
//    List<ItemFilterGroup> findByFilterGroup_IdIn(List<Long> ids);
}
