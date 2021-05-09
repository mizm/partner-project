package shop.daegu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.daegu.domain.Client;
import shop.daegu.domain.Order;
import shop.daegu.domain.OrderItem;
import shop.daegu.domain.TotalOrder;
import shop.daegu.dto.excel.ExcelData;
import shop.daegu.dto.excel.ExcelToOrder;
import shop.daegu.dto.order.*;
import shop.daegu.repository.Client.ClientRepository;
import shop.daegu.repository.Order.OrderRepository;
import shop.daegu.repository.Order.OrderSearchRepository;
import shop.daegu.repository.Order.TotalOrderRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final OrderSearchRepository orderSearchRepository;
    private final TotalOrderRepository totalOrderRepository;
    private final FilterService filterService;

    @Transactional
    public Long saveExcelToOrder(ExcelToOrder excelToOrder) {
        LocalDate orderDate = excelToOrder.getOrderDate();
        List<ExcelData> dataList = excelToOrder.getDataList();
        List<Client> clients = clientRepository.findByNameIn(getNames(dataList));

        TotalOrder totalOrder = createTotalOrder(orderDate);

        HashMap<String, Order> orders = new HashMap<>();
        for (ExcelData excelData : dataList) {
            Optional<Client> optClient = findClient(clients, excelData.getClientName());
            Client client = null;
            try {
                client = optClient.orElseThrow(() -> new IllegalArgumentException("고객목록에 존재하지 않습니다."));
            } catch (IllegalArgumentException e) {
                continue;
            }

            if(orders.containsKey(client.getName())) {
                orders.get(client.getName()).addOrderItem(new OrderItem(excelData));
            } else {
                Order order = Order.createOrder(client, orderDate, totalOrder, new OrderItem(excelData));
                orderRepository.save(order);
                orders.put(client.getName(),order);
            }
        }
        return totalOrder.getId();
    }

    private TotalOrder createTotalOrder(LocalDate orderDate) {
        TotalOrder totalOrder = new TotalOrder(orderDate);
        Long count = totalOrderRepository.countByOrderDate(orderDate);
        totalOrder.changeOrd(count);
        totalOrderRepository.save(totalOrder);
        return totalOrder;
    }

    private Optional<Client> findClient(List<Client> clients, String name) {
        for (Client client : clients) {
            if(client.getName().equals(name)) {
                return Optional.of(client);
            }
        }
        return Optional.empty();
    }

    private List<String> getNames(List<ExcelData> dataList) {
        Set<String> nameSet = new HashSet<>();
        for (ExcelData excelData : dataList) {
            nameSet.add(excelData.getClientName());
        }

        return new ArrayList<>(nameSet);
    }

    public List<OrderDto> findBySearch(OrderSearch orderSearch) {
        List<Order> result = orderSearchRepository.findBySearch(orderSearch);
        return result.stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }

    public List<Order> findOrderItemByFilter(OrderSearch orderSearch) {
        OrderFilter filters = filterService.findFilters(orderSearch.getFilterGroupIds());
        return orderSearchRepository.findByFilter(orderSearch, filters);
//        return result.stream()
//                .map(o -> new OrderDto(o,orderSearchRepository.findOrderItemByFilter(orderSearch,filters)))
//                .collect(Collectors.toList());
//        return result.stream()
//                .map(OrderDto::new)
//                .collect(Collectors.toList());
    }

    public List<TotalOrderDto> findTotalOrders(TotalOrderSearch totalOrderSearch) {
        List<TotalOrder> result = orderSearchRepository.findTotalOrders(totalOrderSearch);
        return result.stream()
                .map(o -> new TotalOrderDto(o.getId(),o.getOrderDate(),o.getCreateDate(),o.getOrd()))
                .collect(Collectors.toList());
    }

    public TotalOrder findTotalOrder(Long id) {
        return totalOrderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("주문서가 없습니다."));
    }

}
