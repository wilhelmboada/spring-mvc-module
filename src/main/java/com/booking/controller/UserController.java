package com.booking.controller;

import com.booking.facade.BookingFacade;
import com.booking.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    private final BookingFacade bookingFacade;

    public UserController(BookingFacade bookingFacade) {
        this.bookingFacade = bookingFacade;
    }

    @PostMapping("/")
    public String createUser(
                             @RequestParam String name,
                             @RequestParam String email, Model model) {
        User user = bookingFacade.createUser(new User(name, email));
        model.addAttribute("user", user);
        return "userDetails";
    }

    @GetMapping("/all")
    public ModelAndView getAllUsers() {
        List<User> users = bookingFacade.getAllUsers();
        ModelAndView mav = new ModelAndView("allUsers");
        mav.addObject("users", users);
        return mav;
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id, Model model) {
        User user = bookingFacade.getUserById(id);
        model.addAttribute("user", user);
        return "userDetails";  // Thymeleaf template
    }
}
