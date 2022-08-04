package com.gpch.login.controller;

import java.util.List;

import javax.validation.Valid;

import com.gpch.login.model.Incident;
import com.gpch.login.model.User;
import com.gpch.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
    
    @RequestMapping(value={"/login"}, method = RequestMethod.POST)
    public ModelAndView userlogin(){
    	System.out.println("hi");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        
        String email = user.getEmail();
        if(email.equals("admin@demo.com"))
        {
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        List<Incident> listInc =  userService.listIncident();
    	modelAndView.addObject("lists", listInc);
        modelAndView.setViewName("admin/home");
        }
        else
        {
        	Incident incident = new Incident();
            modelAndView.addObject("incident", incident);
        	modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        	modelAndView.setViewName("admin/user");	
        }
        return modelAndView;
    }
    @RequestMapping(value = "/saveIncident", method = RequestMethod.POST)
    public ModelAndView createInc(@Valid Incident incident, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
            userService.saveIncident(incident);
            modelAndView.addObject("successMessage", "Request has been registered successfully");
            modelAndView.addObject("incident", new Incident());
            modelAndView.setViewName("admin/user");
            return modelAndView;
    }
    @RequestMapping(value="/contact", method = RequestMethod.GET)
    public ModelAndView contact(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("contact");
        return modelAndView;
    }
    @RequestMapping(value="/about", method = RequestMethod.GET)
    public ModelAndView about(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("about");
        return modelAndView;
    }
    
}
