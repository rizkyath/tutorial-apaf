package apap.tutorial.pergipergi.controller;

import apap.tutorial.pergipergi.model.RoleModel;
import apap.tutorial.pergipergi.model.UserModel;
import apap.tutorial.pergipergi.service.RoleService;
import apap.tutorial.pergipergi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/add")
    public String addUserFormPage(Model model) {
        UserModel user = new UserModel();
        List<RoleModel> listRole = roleService.findAll();
        model.addAttribute("user", user);
        model.addAttribute("listRole", listRole);
        return "form-add-user";
    }

    @PostMapping(value="/add")
    public String addUserSubmit(@ModelAttribute UserModel user, Model model) {
        if (userService.checkPassword(user.getPassword())) {
            userService.addUser(user);
        } else {
            model.addAttribute("msg", 0);
            List<RoleModel> listRole = roleService.findAll();
            model.addAttribute("listRole", listRole);
            model.addAttribute("user", user);
            return "form-add-user";
        }
        model.addAttribute("user", user);

        return "redirect:/";
    }

    @GetMapping(value="/view-users")
    public String viewListUser(Model model) {
        List<UserModel> listUser = userService.getListUser();
        model.addAttribute("listUser", listUser);
        return "view-users";
    }

    @RequestMapping(value="/delete/{userId}")
    public String deleteUser(@PathVariable(value="userId") String userId, Model model) {
        UserModel user = userService.deleteById(userId);
        return "redirect:/user/view-users";
    }

    @GetMapping(value="/change-password/{username}")
    public String changePassForm(@PathVariable(value = "username") String username,
                                 Model model) {
        UserModel user = userService.getUserByUsername(username);
        return "update-pass";
    }

    @PostMapping(value="/change-password/{username}")
    public String changePassSubmit(@PathVariable(value="username") String username,
                                   @RequestParam(value="passLama") String passLama,
                                   @RequestParam(value="passBaru") String passBaru,
                                   @RequestParam(value="passKonf") String passKonf,
                                   Model model) {
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        UserModel user = userService.getUserByUsername(username);
        String password = user.getPassword();
        if (!b.matches(passLama, password)) {
            model.addAttribute("msg", 0);
            return "update-pass";
        } else if (!passBaru.equals(passKonf)) {
            model.addAttribute("msg", 1);
            return "update-pass";
        } else if (!userService.checkPassword(passBaru)) {
            model.addAttribute("msg", 2);
            return "update-pass";
        }
        user.setPassword(passKonf);
        userService.addUser(user);
        model.addAttribute("msg", 3);
        return "update-pass";
    }
}
