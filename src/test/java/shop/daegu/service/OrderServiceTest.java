package shop.daegu.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import shop.daegu.domain.OrderItem;
import shop.daegu.domain.TotalOrder;
import shop.daegu.domain.filter.FilterGroup;
import shop.daegu.domain.filter.FilterType;
import shop.daegu.domain.filter.Item;
import shop.daegu.domain.filter.ItemFilterGroup;
import shop.daegu.dto.excel.ExcelData;
import shop.daegu.dto.excel.ExcelToOrder;
import shop.daegu.dto.order.OrderDto;
import shop.daegu.dto.order.OrderSearch;
import shop.daegu.repository.filtergroup.FilterGroupRepository;
import shop.daegu.repository.filtergroup.ItemFilterGroupRepository;
import shop.daegu.repository.item.ItemRepository;

import javax.persistence.EntityManager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    ExcelService excelService;

    @Autowired
    FilterService filterService;
    @Autowired
    EntityManager em;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    FilterGroupRepository filterGroupRepository;

    @Autowired
    ItemFilterGroupRepository itemFilterGroupRepository;
    private ExcelToOrder excelToOrder;

    @BeforeEach
    void beforeEach() throws IOException {
        ClassPathResource cpr = new ClassPathResource("test.xlsx");
        MultipartFile file = new MockMultipartFile("text.xlsx", cpr.getInputStream());
        System.out.println("file = " + file);
        List<ExcelData> data = excelService.readExcel(file, "xlsx");
        excelToOrder = new ExcelToOrder("2021-04-27", data);
    }
    @Test
    void 오더Ord생성테스트() throws IOException {
        //given
        Long firstTotalOrder = orderService.saveExcelToOrder(excelToOrder);
        Long secondTotalOrder = orderService.saveExcelToOrder(excelToOrder);
        em.flush();
        em.clear();
        //when
        TotalOrder totalOrder1 = orderService.findTotalOrder(firstTotalOrder);
        TotalOrder totalOrder2 = orderService.findTotalOrder(secondTotalOrder);
        
        //then
        assertThat(totalOrder1.getOrd()).isEqualTo(0);
        assertThat(totalOrder2.getOrd()).isEqualTo(1);
    }
    
    @Test
    void 오더_생성_테스트() {
        //given
        Long firstTotalOrder = orderService.saveExcelToOrder(excelToOrder);
        em.flush();
        em.clear();
        //when
        TotalOrder totalOrder1 = orderService.findTotalOrder(firstTotalOrder);
        //then
        assertThat(totalOrder1.getOrders().size()).isEqualTo(8);
    }

    @Test
    @Commit
    void 필터_테스트() {
        //given
        Long firstTotalOrder = orderService.saveExcelToOrder(excelToOrder);
        OrderSearch orderSearch = new OrderSearch();
        orderSearch.setTotalOrderId(firstTotalOrder);


        Item item1 = new Item("냉장)");
        Item item2 = new Item("냉동)");
        FilterGroup filterGroup = new FilterGroup("테스트", FilterType.INCLUDE);
        itemRepository.save(item1);
        itemRepository.save(item2);
        filterGroupRepository.save(filterGroup);

        ItemFilterGroup itemFilterGroup1 = new ItemFilterGroup(item1,filterGroup);
        ItemFilterGroup itemFilterGroup2 = new ItemFilterGroup(item2,filterGroup);
        itemFilterGroupRepository.save(itemFilterGroup1);
        itemFilterGroupRepository.save(itemFilterGroup2);

        //when
        List<Long> filterIds = new ArrayList<>();
        filterIds.add(filterGroup.getId());
        orderSearch.setFilterGroupIds(filterIds);


        List<OrderItem> orders = orderService.findOrderItemByFilter(orderSearch);

        //then
        for (OrderItem order : orders) {
            System.out.println("order = " + order);
            System.out.println("order.getOrder().getTotalOrder().getId() = " + order.getOrder().getTotalOrder().getId());
            System.out.println("order.getOrder().getClient().getName() = " + order.getOrder().getClient().getName());
            assertThat(order.getName()).satisfiesAnyOf(
                ase -> assertThat(order.getName()).startsWith("냉장)"),
                    ase -> assertThat(order.getName()).startsWith("냉동)")
            );
        }


    }

}