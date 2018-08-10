package com.rtlabs.acidrain.controller;

import com.rtlabs.acidrain.service.DeclareService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class DeclareController {
    static String userLastName = "";
    static String userFirstName = "";
    static String userPatronymic = "";
    static String userPhone = "";
    static String userEmail = "";
    static Date userAge = new Date();

    static String paramsChecking = "";

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM YYYY");

    private final DeclareService declareService;

    public DeclareController(DeclareService declareService) {
        this.declareService = declareService;
    }


    @RequestMapping("/declare")
    public String getDeclarePage(Model model) {
       returnParamsToModel(model);
        return "declare";
    }

    @RequestMapping(value = "/declare", method = RequestMethod.POST)
    public String userRegistration(@RequestParam(value = "userLastName", required = true) String userLastNamePost,
                                   @RequestParam(value = "userFirstName", required = true) String userFirstNamePost,
                                   @RequestParam(value = "userPatronymic", required = true) String userPatronymicPost,
                                   @RequestParam(value = "userEmail") String userEmailPost,
                                   @RequestParam(value = "userPhone", required = true) String userPhonePost,
                                   @RequestParam(value = "userBirthDate", required = true) String userBirthDatePost,
                                   Model model) {

        userLastName = userLastNamePost;
        userFirstName = userFirstNamePost;
        userPatronymic = userPatronymicPost;
        userEmail = userEmailPost;
        userPhone = userPhonePost;

        try {
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("yyyy-MM-dd");
            userAge = format.parse(userBirthDatePost);
        } catch (ParseException e) { }

        paramsChecking = declareService.declare(
                userLastName, userFirstName, userPatronymic,
                userPhone, userEmail, userAge);

        if (paramsChecking.equals("")) {
            model.addAttribute("paramsChecking", "success");
            return "declare";
        } else {
            returnParamsToModel(model);
            return "declare";
        }
    }

    public void returnParamsToModel(Model model){
        model.addAttribute("userLastName", userLastName);
        model.addAttribute("userFirstName", userFirstName);
        model.addAttribute("userPatronymic", userPatronymic);
        model.addAttribute("userPhone", userPhone);
        model.addAttribute("userEmail", userEmail);
        model.addAttribute("userBirthDate", userAge);
        model.addAttribute("paramsChecking", paramsChecking);
    }
}