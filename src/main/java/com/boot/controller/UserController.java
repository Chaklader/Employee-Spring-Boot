package com.boot.controller;

import com.boot.entity.User;
import com.boot.service.UserService;
import com.boot.validator.UserFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by Chaklader on Oct, 2017
 */
@Controller
public class UserController {

    // ConstraintName.CommandName.propertyName=validation error message
    // NotEmpty.userForm.email=Please enter your e-mail

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

//    @Autowired
//    private UserFormValidator userFormValidator;

    @Autowired
    private UserService userService;

//    @InitBinder
//    protected void initBinder(WebDataBinder webDataBinder) {
////        webDataBinder.setValidator(userFormValidator);
//    }

    @GetMapping(value = "/")
    public String index() {
        return "redirect:/users";
    }

    @GetMapping(value = "/users")
    public String showAllUsers(Model model) {

        // save the users
//        List<User> users = generateFakeUsers();
//        users.forEach(user -> {
//            userService.save(user);
//        });

        model.addAttribute("users", userService.findAll());
        return "list";
    }

    @GetMapping(value = "/users/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {

        User user = userService.findById(id);

        if (user == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "User not found");
        }

        model.addAttribute("user", user);
        return "show";
    }

    @GetMapping(value = "/users/{id}/update")
    public String showUpdateUserForm(@PathVariable("id") Long idx, Model model) {

        logger.debug("Show updated user form for {} : ", idx);

        User user = userService.findById(idx);

        model.addAttribute("updateUser", user);
        populateDefaultModel(model);
        return "userform";
    }

    @DeleteMapping(value = "/users/{id}/delete")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteUser(@PathVariable("id") Long idx, final RedirectAttributes redirectAttributes) {

        logger.debug("Delete user with Id {}", idx);

        redirectAttributes.addFlashAttribute("css", "Success");
        redirectAttributes.addFlashAttribute("msg", "The user is deleted");

        // delete the user
        userService.delete(idx);
        return "redirect:/users/";
    }

    @PostMapping(value = "/users")
    public String saveOrUpdateUser(@ModelAttribute("updateUser") @Validated User user,
                                   Model model, BindingResult bindingResult,
                                   final RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            populateDefaultModel(model);
            return "userform";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if (user.isNew()) {
                redirectAttributes.addFlashAttribute("msg", "User added successfully!");
            } else {
                redirectAttributes.addFlashAttribute("msg", "User updated successfully!");
            }

            userService.save(user);
            return "redirect:/users/" + user.getId();
        }
    }

    /*
    * add new user to the app
    * */
    @GetMapping(value = "/users/add")
    public String addNewUser(Model model) {

        User user = new User();

        model.addAttribute("updateUser", user);

        populateDefaultModel(model);
        return "userform";
    }

    /*
    * populate the user form with the default values
    * */
    private void populateDefaultModel(Model model) {

        List<String> frameworks = new ArrayList<String>();
        frameworks.add("Spring MVC");
        frameworks.add("Struts 2");
        frameworks.add("JSF 2");
        frameworks.add("GWT");
        frameworks.add("Play");
        frameworks.add("Apache Wicket");
        model.addAttribute("frameworks", frameworks);

        Map<String, String> skills = new LinkedHashMap<String, String>();
        skills.put("Hibernate", "Hibernate");
        skills.put("Spring", "Spring");
        skills.put("Struts", "Struts");
        skills.put("Groovy", "Groovy");
        skills.put("Grails", "Grails");
        model.addAttribute("skills", skills);

        List<Integer> numbers = new ArrayList<Integer>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        model.addAttribute("numbers", numbers);

        Map<String, String> countries = new LinkedHashMap<String, String>();
        countries.put("US", "United Stated");
        countries.put("CN", "China");
        countries.put("SG", "Singapore");
        countries.put("MY", "Malaysia");
        model.addAttribute("countries", countries);
    }

    private List<User> generateFakeUsers() {

        List<User> users = new ArrayList<>();

        User user = new User();
        user.setName("Bella");
        user.setEmail("bella.bezay@gmail.com");
        user.setPassword("Hansahouse11");
        user.setFramework(Arrays.asList("Java, Hibernate, GWT".split("\\s*,\\s*")));
        users.add(user);

        user = new User();
        user.setName("Ella");
        user.setEmail("ella.kerr@gmail.com");
        user.setPassword("Ellakerr123");
        user.setFramework(Arrays.asList("Java, Play, Scala".split("\\s*,\\s*")));
        users.add(user);


        user = new User();
        user.setName("Monica");
        user.setEmail("monica.bundeltag@live.com");
        user.setPassword("Monicaxyz12");
        user.setFramework(Arrays.asList("JSP, Haskell, Clojure".split("\\s*,\\s*")));
        users.add(user);


        user = new User();
        user.setName("Arefe");
        user.setEmail("arefe.bezay@gmail.com");
        user.setPassword("Hansahouse11");
        user.setFramework(Arrays.asList("Java, Hibernate, GWT".split("\\s*,\\s*")));
        users.add(user);

        user = new User();
        user.setName("Tom Hanricks");
        user.setEmail("tom@gmail.com");
        user.setPassword("Ellakerr123");
        user.setFramework(Arrays.asList("Java, Play, Scala".split("\\s*,\\s*")));
        users.add(user);


        user = new User();
        user.setName("Helloiii");
        user.setEmail("jelloiii@live.com");
        user.setPassword("Helloiii2367");
        user.setFramework(Arrays.asList("JSP, Haskell, Clojure".split("\\s*,\\s*")));
        users.add(user);

        return users;
    }


    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ModelAndView handleEmptyData(HttpServletRequest req, Exception ex) {

        logger.debug("handleEmptyData()");
        logger.error("Request: {}, error ", req.getRequestURL(), ex);

        ModelAndView model = new ModelAndView();
        model.setViewName("show");
        model.addObject("msg", "user not found");

        return model;
    }
}
