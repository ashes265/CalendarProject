package demo.calendar.controller;

import demo.calendar.model.AppRole;
import demo.calendar.model.User;
import demo.calendar.repository.AppRoleRepository;
import demo.calendar.repository.UserRepository;
import demo.calendar.service.SecurityService;
import org.aspectj.weaver.ast.Instanceof;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/users")
public class AccountController {

    private UserRepository userRepository;
    private AppRoleRepository appRoleRepository;
    @Autowired
    private SecurityService securityService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AccountController(UserRepository userRepository,
                             AppRoleRepository appRoleRepository) {
        this.userRepository = userRepository;
        this.appRoleRepository = appRoleRepository;
    }

    //for login
    @GetMapping("/login")
    public String login() {
        if (securityService.isAuthenticated()) {

            return "redirect:/calendars";
        }
        return "accounts/login";
    }

//    @PostMapping("/getIn")
//    public String login(@RequestParam String username,
//                        @RequestParam String password,
//                        HttpSession session) {
//        if(securityService.isAuthenticated()){
//            return "redirect:/calendars";
//        }
//        for (int i = 0; i < 10; i++) {
//            System.out.println(i);
//        }
//        return "redirect:/accounts/helloLogin";
////        }
////        return "redirect:/accounts/helloLogin";
//    }

    //for register
    @GetMapping("/register")
    public String registration(Model model) {
        List<AppRole> roles = appRoleRepository.findAll();
        model.addAttribute("roles", roles);
        model.addAttribute("userForm", new User());
        return "accounts/register";
    }

    @PostMapping("/register")
    public String registration(@ModelAttribute("userForm") User userForm,
                               BindingResult bindingResult,
                               @RequestParam String roles[],
                               ModelMap model) {
        //check error validate
        if (bindingResult.hasErrors()) {
            model.addAttribute("userForm", userForm);
            return "accounts/register";
        }
        //setting role for user
        Set<AppRole> appRoleSet = new HashSet<>();
        for (int i = 0; i < roles.length; i++) {
            AppRole appRole = appRoleRepository.getById(Long.parseLong(roles[i]));
            appRoleSet.add(appRole);
        }
        userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));
        userForm.setRoles(appRoleSet);
        userRepository.save(userForm);
        return "redirect:/users";
    }

    @GetMapping(value="/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/users";
    }

    //get error
    @GetMapping("error")
    public String errorLogin() {
        return "accounts/error";
    }
}

