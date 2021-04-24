package shop.daegu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shop.daegu.dto.client.ClientForm;
import shop.daegu.dto.client.ClientSearchCondition;
import shop.daegu.service.ClientService;
import shop.daegu.service.GroupService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    private final ClientService clientService;
    private final GroupService groupService;

    @GetMapping("/clients")
    public String list(Model model, ClientSearchCondition cond) {
        model.addAttribute("clients", clientService.findAllBySearchCondition(cond));
        return "clients/clientList";
    }

    @GetMapping("/clients/new")
    public String createForm(Model model) {
        model.addAttribute("clientForm", new ClientForm());
        model.addAttribute("groups", groupService.findGroups());
        return "clients/createClientForm";
    }

    @PostMapping("/clients/new")
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
