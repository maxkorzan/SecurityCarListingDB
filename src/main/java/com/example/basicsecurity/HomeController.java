package com.example.basicsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.utils.ObjectUtils;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    CloudinaryConfig cloudc;
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String index(Model model, Principal principal, Authentication authentication){
        //pull all categories from repo --> template
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("cars", carRepository.findAll());
        String username = null;
        try {
            username = principal.getName();
            model.addAttribute("car_user_id", userRepository.findByUsername(principal.getName()).getId());
            return "index";
        } catch (Exception e){
            model.addAttribute("car_user_id", 0);
            return "index";
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/addCategory")
    public String formCategory(Model model){
        model.addAttribute("category", new Category());
        return "formCategory";
    }

    @PostMapping("/processCategory")
    public String processForm(@Valid Category category, BindingResult result){
        if (result.hasErrors()){
            return "formCategory";
        }
        categoryRepository.save(category);
        return "redirect:/addCar";
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/addCar")
    public String formCar(Model model, Principal principal){
        model.addAttribute("car", new Car());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("user_id",userRepository.findByUsername(principal.getName()).getId());
        return "formCar";
    }

    @PostMapping("/processCar")
    public String processForm(@Valid @ModelAttribute Car car, BindingResult result, /*@RequestParam("pic") String pic,*/
                              @RequestParam("category") long id, @RequestParam("file") MultipartFile file,
                              @RequestParam("car_user_id") long car_user_id, Principal principal){
        if (result.hasErrors()){
            return "formCar";
        }

        if (file.isEmpty()){
//            car.setImage(pic);
        }
        else {
            try {
                Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
                car.setImage(uploadResult.get("url").toString());
                carRepository.save(car);
            } catch (IOException e){
                e.printStackTrace();
                return "redirect:/addCar";
            }
        }
/////////////////////////////////////////
//        if (car_user_id == 0) {
//            String username = principal.getName();
//            User user = userRepository.findByUsername(username);
//            car.setUser(user);
//        }

//        else{

                User user = userRepository.findById(car_user_id).get();
                car.setUser(user);
//            }
/////////////////////////////////////////

        Category category = categoryRepository.findById(id).get();
        car.setCategory(category);
        carRepository.save(car);
        return "redirect:/";

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping("/detail/{id}")
    public String showCar(@PathVariable("id") long id, Model model) {
        model.addAttribute("car", carRepository.findById(id).get());
        model.addAttribute("categories", categoryRepository.findAll());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateCar(@PathVariable("id") long id, Model model, Principal principal) {
        model.addAttribute("car", carRepository.findById(id).get());
        model.addAttribute("user_id", userRepository.findByUsername(principal.getName()).getId());

        Car car=carRepository.findById(id).get();
//        String pic=car.getImage();                //THE HARD WAY USING REQUESTPARAM
//        model.addAttribute("pic",pic);            //THE HARD WAY USING REQUESTPARAM
        model.addAttribute("car", car);

        model.addAttribute("categories", categoryRepository.findAll());
        return "formCar";
    }

    @RequestMapping("/delete/{id}")
    public String delCar(@PathVariable("id") long id, Model model) {
        carRepository.deleteById(id);
        return "redirect:/";
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping("/category/{id}")
    public String category(@PathVariable("id") long id, Model model) {
        model.addAttribute("category", categoryRepository.findById(id).get());
        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "category";
    }


    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////

    //4.06 - USER REGISTRATION
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
        model.addAttribute("user", user);
        if (result.hasErrors()){
            return "registration";
        } else {
            userService.saveUser(user);
            model.addAttribute("message", "User Account Created");
        }
        return "index";
    }

    ///////////////////////////////////////////////////////

//    @RequestMapping("/")
//    public String index(){
//        return "index-original";
//    }

    //4.02 REDIRECT TO CUSTOM "LOGIN.HTML" PAGE
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    //4.03 USING ROLES FOR PAGE PERMISSIONS
    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    //////////////////////////////////////////////////////

    //4.05 PERSISTING CURRENT USER INFO
//    @Autowired
//    UserRepository userRepository;

    @RequestMapping("/secure")
//    public String secure(){
//        return "secure";
//    }
    public String secure(Principal principal, Model model){
        String username = principal.getName();  /* Principal.getName <-- this gets you the current user's "username" */
        model.addAttribute("user", userRepository.findByUsername(username));
        return "secure";
    }

}
