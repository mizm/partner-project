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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.daegu.domain.TotalOrder;
import shop.daegu.dto.excel.ExcelForm;
import shop.daegu.dto.excel.ExcelToOrder;
import shop.daegu.dto.order.OrderDto;
import shop.daegu.dto.order.OrderSearch;
import shop.daegu.dto.order.TotalOrderDto;
import shop.daegu.dto.order.TotalOrderSearch;
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
    @GetMapping("/totalOrder/new")
    public String createForm(Model model) {
        model.addAttribute("excelForm", new ExcelForm());
        return "totalOrder/excelForm";
    }

    @PostMapping("/totalOrder/new")
    public String readExcel(@Valid ExcelForm form, BindingResult bindingResult) {
        String extension = FilenameUtils.getExtension(form.getFile().getOriginalFilename());
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            FieldError fieldError = new FieldError("form", "file", "엑셀파일만 업로드 해주세요.");
            bindingResult.addError(fieldError);
            return "totalOrder/excelForm";
        }

        ExcelToOrder excelToOrder = new ExcelToOrder(form.getToday(),excelService.readExcel(form.getFile(), extension));
        Long totalOrderId = orderService.saveExcelToOrder(excelToOrder);
        return "redirect:/totalOrders";
    }

    @GetMapping("/totalOrders")
    public String totalOrderList(@ModelAttribute("totalOrderSearch") TotalOrderSearch totalOrderSearch, Model model) {

        List<TotalOrderDto> totalOrders = orderService.findTotalOrders(totalOrderSearch);
        model.addAttribute("totalOrders",totalOrders);
        return "totalOrder/totalOrderList";
    }
    @GetMapping("/totalOrders/{id}")
    public String orderList(@PathVariable("id") Long id, Model model){
        OrderSearch orderSearch = new OrderSearch();
        orderSearch.setTotalOrderId(id);
        TotalOrder totalOrder = orderService.findTotalOrder(id);
        List<OrderDto> orders = orderService.findBySearch(orderSearch);
        TotalOrderDto totalOrderDto = new TotalOrderDto(
                totalOrder.getId(),
                totalOrder.getOrderDate(),
                totalOrder.getCreateDate(),
                orders,
                totalOrder.getOrd()
        );
        model.addAttribute("order", totalOrderDto);

        return "totalOrder/orderList";
    }
}
