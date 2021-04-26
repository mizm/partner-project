package shop.daegu.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.daegu.domain.filter.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {

//
}
