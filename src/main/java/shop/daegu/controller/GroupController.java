package shop.daegu.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.daegu.dto.group.GroupForm;
import shop.daegu.service.GroupService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/groups")
    public String list(Model model) {
        model.addAttribute("groups", groupService.findGroups());
        return "groups/groupList";
    }

    @GetMapping("/groups/new")
    public String createForm(Model model) {
        model.addAttribute("groupForm", new GroupForm());
        return "groups/createGroupForm";
    }

    @PostMapping("/groups/new")
    public String create(@Valid GroupForm form, BindingResult result) {
        if(result.hasErrors()) {
            return "groups/createGroupForm";
        }
        groupService.add(form);
        return "redirect:/groups";
    }

    @PostMapping("/group/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        groupService.delete(id);
        return "redirect:/groups";
    }
}
