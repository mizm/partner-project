package shop.daegu.dto.excel;

import lombok.Data;

@Data
public class ExcelData {
    private String clientName;
    private String itemName;
    private int count;
    private String size;
    private int price;
}
