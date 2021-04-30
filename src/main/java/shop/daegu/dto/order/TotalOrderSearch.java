package shop.daegu.dto.order;

import lombok.Data;

import java.util.List;

@Data
public class TotalOrderSearch {

    private Long totalOrderId;
    private String orderDate;
    private List<Long> filterGroupIds;
}
