package shop.daegu.dto.order;

import lombok.Data;
import shop.daegu.domain.filter.ItemFilterGroup;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderFilter {

    List<String> excludeItems = new ArrayList<>();
    List<String> includeItems = new ArrayList<>();

    public OrderFilter(List<ItemFilterGroup> filters) {
        for (ItemFilterGroup filter : filters) {
            if (filter.getFilterGroup().checkFilterType()) {
                includeItems.add(filter.getItem().getName());
            } else {
                excludeItems.add(filter.getItem().getName());
            }
        }
    }
}
