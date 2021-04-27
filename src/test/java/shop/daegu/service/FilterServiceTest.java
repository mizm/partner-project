package shop.daegu.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import shop.daegu.domain.filter.FilterGroup;
import shop.daegu.domain.filter.FilterType;
import shop.daegu.domain.filter.Item;
import shop.daegu.domain.filter.ItemFilterGroup;
import shop.daegu.dto.order.OrderFilter;
import shop.daegu.repository.filtergroup.ItemFilterGroupRepository;
import shop.daegu.repository.filtergroup.ItemFilterGroupSearchRepository;
import shop.daegu.repository.item.ItemRepository;
import shop.daegu.repository.filtergroup.FilterGroupRepository;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class FilterServiceTest {

    @Autowired FilterService filterService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemFilterGroupRepository itemFilterGroupRepository;
    @Autowired
    FilterGroupRepository filterGroupRepository;
    @Autowired
    ItemFilterGroupSearchRepository itemFilterGroupSearchRepository;

    @Autowired
    EntityManager em;
    @Test
    void findFilter() {
        FilterGroup filterGroup1 = new FilterGroup("test1", FilterType.INCLUDE);
        FilterGroup filterGroup2 = new FilterGroup("test2", FilterType.EXCLUDE);
        filterGroupRepository.save(filterGroup1);
        filterGroupRepository.save(filterGroup2);
        Item item1 = new Item("filter1");
        Item item2 = new Item("filter2");
        Item item3 = new Item("filter3");
        Item item4 = new Item("filter4");
        Item item5 = new Item("filter5");
        Item item6 = new Item("filter6");
        Item item7 = new Item("filter7");

        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
        itemRepository.save(item4);
        itemRepository.save(item5);
        itemRepository.save(item6);
        itemRepository.save(item7);

        ItemFilterGroup ifg1 = new ItemFilterGroup(item1, filterGroup1);
        ItemFilterGroup ifg2 = new ItemFilterGroup(item2, filterGroup1);
        ItemFilterGroup ifg3 = new ItemFilterGroup(item3, filterGroup1);
        ItemFilterGroup ifg4 = new ItemFilterGroup(item4, filterGroup1);
        ItemFilterGroup ifg5 = new ItemFilterGroup(item5, filterGroup2);
        ItemFilterGroup ifg6 = new ItemFilterGroup(item6, filterGroup2);
        ItemFilterGroup ifg7 = new ItemFilterGroup(item7, filterGroup2);
        itemFilterGroupRepository.save(ifg1);
        itemFilterGroupRepository.save(ifg2);
        itemFilterGroupRepository.save(ifg3);
        itemFilterGroupRepository.save(ifg4);
        itemFilterGroupRepository.save(ifg5);
        itemFilterGroupRepository.save(ifg6);
        itemFilterGroupRepository.save(ifg7);

        List<Long> idList = new ArrayList<>();
        idList.add(filterGroup1.getId());

        List<ItemFilterGroup> filters = itemFilterGroupSearchRepository.findByGroupIds(idList);
        //
        assertThat(filters).contains(ifg1,ifg2,ifg3,ifg4);
        idList.add(filterGroup2.getId());
        OrderFilter orderFilter = filterService.findFilters(idList);
        assertThat(orderFilter.getExcludeItems())
                .contains(ifg5.getItem().getName(),ifg6.getItem().getName(),ifg7.getItem().getName());
        assertThat(orderFilter.getIncludeItems())
                .contains(
                        ifg1.getItem().getName(),
                        ifg2.getItem().getName(),
                        ifg3.getItem().getName(),
                        ifg4.getItem().getName()
                );

    }

}