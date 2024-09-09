package com.example.UserManagement_portal.controller;

import com.example.UserManagement_portal.dto.*;
import com.example.UserManagement_portal.model.UserEntity;
import com.example.UserManagement_portal.service.DashboardService;
import com.example.UserManagement_portal.service.UserService;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Controller
public class UserController {

    private UserService userService;

    private DashboardService dashboardService;

    public UserController(UserService userService,DashboardService dashboardService){
        this.userService=userService;
        this.dashboardService=dashboardService;
    }
    @GetMapping("/")
    public String login(Model model){
        LoginFormDTO loginFormDTO=new LoginFormDTO();
        model.addAttribute("user", loginFormDTO);
        return "login";
    }

   @PostMapping("/login")
    public String login_handle(HttpServletRequest request,LoginFormDTO loginFormDTO,Model model){
       UserDTO userDTO=userService.login(loginFormDTO);
       if(userDTO!=null){
           String email=userDTO.getEmail();
           HttpSession session=request.getSession(true);
           session.setAttribute("email",email);
           if(userDTO.getPwd_reset()) {
               return "redirect:/dashboard";
           }else {
               return "redirect:/reset";
           }
       }else{
           model.addAttribute("errmsg", "Invalid credentials");
           model.addAttribute("user", new LoginFormDTO());
       }
        return "login";
    }
    @GetMapping("/dashboard")
    public String displayDashboard(Model model){

        QuoteApiResponseDTO quoteApiResponseDTO=dashboardService.getQuote();
        model.addAttribute("quote",quoteApiResponseDTO);
        return "dashboard";
    }


    @GetMapping("/register")
    public  String register( Model model) throws IOException {
        RegisterFormDTO registerFormDTO=new RegisterFormDTO();
        model.addAttribute("registerform",registerFormDTO);
        return "register.html";
    }
    @PostMapping("/register")
    public String saveDetails(RegisterFormDTO registerFormDTO,Model model){
        if(userService.register(registerFormDTO)){
            model.addAttribute("smsg", "Successfully registered,please check your email");
        }else{
            model.addAttribute("emsg","Registration failed");
        }
        model.addAttribute("registerform",new RegisterFormDTO());
        return "register";
    }
    @GetMapping("/reset")
    public String reset(HttpServletRequest request,Model model){
        HttpSession session= request.getSession(false);
        String email=(String) session.getAttribute("email");
        ResetPasswordDTO resetPasswordDTO=new ResetPasswordDTO();
        resetPasswordDTO.setEmail(email);
        model.addAttribute("reset", resetPasswordDTO);

        return "resetpage";
    }

    @PostMapping("/resetSubmit")
    public String handlePwdReset(HttpServletRequest request,ResetPasswordDTO resetPasswordDTO, Model model){
        String email=resetPasswordDTO.getEmail();
        String newpwd=resetPasswordDTO.getNewpwd();
        String confirmnewpwd=resetPasswordDTO.getConfirmnewpwd();
        String errmsg=null;
        if(newpwd.equals(confirmnewpwd)){
            if(userService.updatePassword(resetPasswordDTO)) {
                return "redirect:/dashboard";
            }
        }
        if (!newpwd.equals(confirmnewpwd)){
            errmsg="New passwords do not match";
            model.addAttribute("reset",resetPasswordDTO);
            model.addAttribute("errmsg", errmsg);
            return "resetpage";
        }

        return "redirect:/resetpage";
    }


    @RequestMapping ("/register/validateEmail")
    public @ResponseBody String checkEmailValidity(HttpServletRequest req,Model model){
        String email=req.getParameter("email");
        System.out.println("Calling validation");
        return userService.findByEmail(email);
    }
    @RequestMapping("/register/countrydropdown")
    public @ResponseBody String getCountryDropdown(Model model) {
        Map<Integer, String> countryMap = userService.getCountriesMap();
        Gson json = new Gson();
        return json.toJson(countryMap);
    }
    @RequestMapping("/register/statedropdown")
    public @ResponseBody String getStateDropdown(HttpServletRequest request,Model model) {
        String countryId=request.getParameter("countryId");
        Map<Integer, String> stateMap = userService.getStatesMap(Integer.parseInt(countryId));
        Gson json = new Gson();
        return json.toJson(stateMap);
    }
    @RequestMapping("/register/citydropdown")
    public @ResponseBody String getCityDropdown(HttpServletRequest request,Model model) {
        String stateId=request.getParameter("stateId");
        Map<Integer, String> citiesMap = userService.getCitiesMap(Integer.parseInt(stateId));
        Gson json = new Gson();
        return json.toJson(citiesMap);
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model){

        HttpSession session=request.getSession(false);
        session.invalidate();
        return "redirect:/";

    }
}
