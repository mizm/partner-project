package shop.daegu.service;

import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import shop.daegu.dto.excel.ExcelData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExcelService {

    public List<ExcelData> readExcel(MultipartFile file, String extension) {
        List<ExcelData> dataList = new ArrayList<>();

        Workbook workbook = null;

        try {
            if (extension.equals("xlsx")) {
                workbook = new XSSFWorkbook(file.getInputStream());
            } else if (extension.equals("xls")) {
                workbook = new HSSFWorkbook(file.getInputStream());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Sheet worksheet = workbook.getSheetAt(0);
        String prevClientName = "";
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) { // 4

            Row row = worksheet.getRow(i);

            ExcelData data = new ExcelData();

            String clientName = (String) getCellData(row.getCell(1)).orElse(prevClientName);
            prevClientName = clientName;
            data.setClientName(clientName);

            data.setItemName((String)getCellData(row.getCell(2)).orElse("blank"));
            data.setCount((int) getCellData(row.getCell(3)).orElse(0));
            data.setSize((String) getCellData(row.getCell(4)).orElse("blank"));
            data.setPrice((int) getCellData(row.getCell(6)).orElse(0));

            dataList.add(data);

        }

        return dataList;
    }

    private Optional getCellData(Cell cell) {
        if(cell == null) return Optional.empty();
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return Optional.ofNullable(cell.getStringCellValue());
            case Cell.CELL_TYPE_NUMERIC:
                return Optional.ofNullable((int) cell.getNumericCellValue());
        }
        return Optional.empty();
    }
}
