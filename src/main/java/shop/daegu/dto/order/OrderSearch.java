package shop.daegu.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderSearch {
    private String clientName;
    private String orderDate;
    private Long totalOrderId;
    private List<Long> filterGroupIds;
}
