package shop.daegu.dto.order;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class TotalOrderDto {

    private Long id;
    private LocalDate orderDate;
    private LocalDateTime createDate;
    private List<OrderDto> orderDtos;
    private Long ord;

    public TotalOrderDto(Long id, LocalDate orderDate, LocalDateTime createDate, Long ord) {
        this.id = id;
        this.orderDate = orderDate;
        this.createDate = createDate;
        this.ord = ord;
    }

    public TotalOrderDto(Long id, LocalDate orderDate, LocalDateTime createDate, List<OrderDto> orderDtos, Long ord) {
        this.id = id;
        this.orderDate = orderDate;
        this.createDate = createDate;
        this.orderDtos = orderDtos;
        this.ord = ord;
    }

}
