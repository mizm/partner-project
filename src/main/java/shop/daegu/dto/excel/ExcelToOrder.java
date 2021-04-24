package shop.daegu.dto.excel;

import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
public class ExcelToOrder {

    private LocalDate orderDate;
    private List<ExcelData> dataList = new ArrayList<>();

    public ExcelToOrder(String date, List<ExcelData> dataList) {
        this.dataList = dataList;
        orderDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
    }
}
