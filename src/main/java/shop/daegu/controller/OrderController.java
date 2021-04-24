package shop.daegu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import shop.daegu.dto.excel.ExcelForm;
import shop.daegu.dto.excel.ExcelToOrder;
import shop.daegu.dto.order.OrderDto;
import shop.daegu.dto.order.OrderSearch;
import shop.daegu.service.ExcelService;
import shop.daegu.service.OrderService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final ExcelService excelService;
    private final OrderService orderService;
    @GetMapping("/order/new")
    public String createForm(Model model) {
        model.addAttribute("excelForm", new ExcelForm());
        return "order/excelForm";
    }

    @PostMapping("/order/new")
    public String readExcel(@Valid ExcelForm form, BindingResult bindingResult) {
        String extension = FilenameUtils.getExtension(form.getFile().getOriginalFilename());
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            FieldError fieldError = new FieldError("form", "file", "엑셀파일만 업로드 해주세요.");
            bindingResult.addError(fieldError);
            return "order/excelForm";
        }

        ExcelToOrder excelToOrder = new ExcelToOrder(form.getToday(),excelService.readExcel(form.getFile(), extension));
        orderService.saveExcelToOrder(excelToOrder);

        return "redirect:/orders?orderDate=" + form.getToday();
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model){

        List<OrderDto> orders = orderService.findBySearch(orderSearch);
        model.addAttribute("orders", orders);

        return "order/orderList";
    }
}
