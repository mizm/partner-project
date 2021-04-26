package shop.daegu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.daegu.domain.filter.Item;
import shop.daegu.domain.filter.ItemFilterGroup;
import shop.daegu.dto.order.OrderFilter;
import shop.daegu.repository.filtergroup.ItemFilterGroupRepository;
import shop.daegu.repository.filtergroup.ItemFilterGroupSearchRepository;
import shop.daegu.repository.item.ItemRepository;
import shop.daegu.repository.filtergroup.FilterGroupRepository;
import shop.daegu.repository.filtergroup.FilterGroupSearchRepository;

import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FilterService {

    private final ItemFilterGroupSearchRepository itemFilterGroupSearchRepository;

    public OrderFilter findFilters(List<Long> idList) {
        return new OrderFilter(itemFilterGroupSearchRepository.findByGroupIds(idList));
    }
}
