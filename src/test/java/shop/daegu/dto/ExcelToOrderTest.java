package shop.daegu.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;


class ExcelToOrderTest {

    @Test
    void 날짜변환테스트() {
        String text = "2021-04-24";
        LocalDate date = LocalDate.parse(text, DateTimeFormatter.ISO_DATE);
        System.out.println("date = " + date);
    }
}