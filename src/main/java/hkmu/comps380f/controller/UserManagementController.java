package hkmu.comps380f.controller;

import hkmu.comps380f.dao.TicketUserService;
import hkmu.comps380f.dao.UserManagementService;
import hkmu.comps380f.exception.BookNotFound;
import hkmu.comps380f.exception.UserNotFound;
import hkmu.comps380f.model.Book;
import hkmu.comps380f.model.TicketUser;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.IOException;
import java.security.Principal;


@Controller
@RequestMapping("/user")
public class UserManagementController {
    @Resource
    UserManagementService umService;

    @GetMapping({"", "/", "/list"})
    public String list(ModelMap model) {
        model.addAttribute("ticketUsers", umService.getTicketUsers());
        return "listUser";
    }

    public static class Form {
        @NotEmpty(message="Please enter your user name.")
        private String username;

        @NotEmpty(message="Please enter your password.")
        @Size(min=6, max=15, message="Your password length must be between {min} and {max}.")
        private String password;
        private String confirm_password;

        @NotEmpty(message="Please enter your email.")
        private String email;

        @NotEmpty(message="Please enter your delivery.")
        private String delivery;

        @NotEmpty(message="Please select at least one role.")
        private String[] roles;
        // getters and setters for all properties

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getConfirm_password() {
            return confirm_password;
        }

        public void setConfirm_password(String confirm_password) {
            this.confirm_password = confirm_password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getDelivery() {
            return delivery;
        }

        public void setDelivery(String delivery) {
            this.delivery = delivery;
        }

        public String[] getRoles() {
            return roles;
        }

        public void setRoles(String[] roles) {
            this.roles = roles;
        }
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("addUser", "ticketUser", new Form());
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("ticketUser") @Valid Form form, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            return "addUser";
        }
        umService.createTicketUser(form.getUsername(),
                form.getPassword(), form.getEmail(), form.getDelivery(),form.getRoles());
        return "redirect:/login";
    }

    @GetMapping("/delete/{username}")
    public String deleteTicket(@PathVariable("username") String username) {
        umService.delete(username);
        return "redirect:/user/list";
    }

    @GetMapping("/edituser/{username}")
    public ModelAndView showEditUser(@PathVariable("username") String username,
                                 Principal principal, HttpServletRequest request)
            throws UserNotFound {
        TicketUser user = umService.getTicketUsers(username);
        if (user == null
                || (!request.isUserInRole("ROLE_ADMIN")
                && !principal.getName().equals(user.getUsername()))) {
            return new ModelAndView(new RedirectView("/user/", true));
        }
        ModelAndView modelAndView = new ModelAndView("edituser");
        modelAndView.addObject("user", user);
        TicketUser ticketUser = new TicketUser();
        ticketUser.setUsername(ticketUser.getUsername());
        ticketUser.setPassword(ticketUser.getPassword());
        ticketUser.setEmail(ticketUser.getEmail());
        ticketUser.setDelivery(ticketUser.getDelivery());
        ticketUser.setRoles(ticketUser.getRoles());
        modelAndView.addObject("ticketUser", ticketUser);
        return modelAndView;
    }

    @PostMapping("/edituser/{username}")
    public String editUser(@PathVariable("username")String username, UserManagementController.Form form,
                       Principal principal, HttpServletRequest request)
            throws UserNotFound {
        TicketUser user = umService.getTicketUsers(username);
        if (user == null
                || (!request.isUserInRole("ROLE_ADMIN")
                && !principal.getName().equals(user.getUsername()))) {
            return "redirect:/user/";
        }
        umService.updateUser(form.getUsername(), form.getPassword(), form.getEmail(), form.getDelivery());
        return "redirect:/user/" + username;
    }
}
