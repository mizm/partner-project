package shop.daegu.dto.excel;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

@Data
public class ExcelForm {

    private MultipartFile file;

    @NotEmpty(message = "날짜를 입력하세요.")
    private String today;
}
