package shop.daegu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.daegu.dto.client.ClientForm;
import shop.daegu.dto.client.ClientSearchCondition;
import shop.daegu.service.ClientService;
import shop.daegu.service.GroupService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class FilterGroupController {

    private final ClientService clientService;
    private final GroupService groupService;

    @GetMapping("/filters")
    public String list(Model model, ClientSearchCondition cond) {
        model.addAttribute("filters", clientService.findAllBySearchCondition(cond));
        return "filters/filterList";
    }

    @GetMapping("/filters/new")
    public String createForm(Model model) {
        model.addAttribute("filterForm", new ClientForm());
        model.addAttribute("groups", groupService.findGroups());
        return "clients/createClientForm";
    }

    @PostMapping("/filters/new")
    public String create(@Valid ClientForm form, BindingResult result) {
        if(result.hasErrors()) {
            return "clients/createClientForm";
        }
        clientService.add(form);
        return "redirect:/clients";
    }

    @GetMapping("/clients/{id}")
    public String update(Model model, @PathVariable Long id) {
        ClientForm clientForm = new ClientForm(clientService.findById(id));
        model.addAttribute("clientForm", clientForm);
        model.addAttribute("groups", groupService.findGroups());
        return "clients/updateClientForm";
    }

    @PostMapping("/clients/{id}")
    public String create(@Valid ClientForm form, @PathVariable Long id, BindingResult result) {
        if(result.hasErrors()) {
            return "clients/createClientForm";
        }
        clientService.update(form);
        return "redirect:/clients";
    }

    @PostMapping("/clients/{id}/delete")
    public String delete(@PathVariable Long id) {
        clientService.remove(id);
        return "redirect:/clients";
    }
}
