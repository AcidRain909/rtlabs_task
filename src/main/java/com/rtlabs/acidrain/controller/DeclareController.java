package com.rtlabs.acidrain.controller;

import com.google.gson.Gson;
import com.rtlabs.acidrain.service.ApplicationViewService;
import com.rtlabs.acidrain.service.DeclareService;
import com.rtlabs.acidrain.service.DepartmentService;
import com.rtlabs.acidrain.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    static String departmentCode = "";
    static String serviceId = "";

    static String paramsChecking = "";

    @Autowired
    private final DeclareService declareService;
    private final DepartmentService departmentService;
    private final ServiceService serviceService;
    private final ApplicationViewService applicationViewService;

    public DeclareController(DeclareService declareService, DepartmentService departmentService, ServiceService serviceService, ApplicationViewService applicationViewService) {
        this.declareService = declareService;
        this.departmentService = departmentService;
        this.serviceService = serviceService;
        this.applicationViewService = applicationViewService;
    }


    @RequestMapping("/declare")
    public String getDeclarePage(Model model) {
       returnParamsToModel(model);
       getDepartmentsAndServicesInfo(model);
       return "declare";
    }

    @RequestMapping(value = "/declare", method = RequestMethod.POST)
    public String userRegistration(@RequestParam(value = "userLastName", required = true) String userLastNamePost,
                                   @RequestParam(value = "userFirstName", required = true) String userFirstNamePost,
                                   @RequestParam(value = "userPatronymic", required = true) String userPatronymicPost,
                                   @RequestParam(value = "userEmail") String userEmailPost,
                                   @RequestParam(value = "userPhone", required = true) String userPhonePost,
                                   @RequestParam(value = "userBirthDate", required = true) String userBirthDatePost,
                                   @RequestParam(value = "departmentCode", required = true) String departmentCodePost,
                                   @RequestParam(value = "serviceId", required = true) String serviceIdPost,
                                   Model model) {

        userLastName = userLastNamePost;
        userFirstName = userFirstNamePost;
        userPatronymic = userPatronymicPost;
        userEmail = userEmailPost;
        userPhone = userPhonePost;
        departmentCode = departmentCodePost;
        serviceId = serviceIdPost;

        try {
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("yyyy-MM-dd");
            userAge = format.parse(userBirthDatePost);

            paramsChecking = declareService.declare(
                    userLastName, userFirstName, userPatronymic,
                    userPhone, userEmail, userAge, departmentCode, serviceId);

        } catch (ParseException e) {
            paramsChecking = "Вы не ввели Дату рождения.";
            System.out.println(e.getMessage());
        }



        if (paramsChecking.equals("")) {
            model.addAttribute("paramsChecking", "success");
            getDepartmentsAndServicesInfo(model);
            return "declare";
        } else {
            getDepartmentsAndServicesInfo(model);
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
        model.addAttribute("paramsChecking", paramsChecking);
    }

    public void getDepartmentsAndServicesInfo(Model model){
        model.addAttribute("departmentArrayList", departmentService.getAllDepartments());
        model.addAttribute("serviceArrayList", serviceService.getServicesByCode(departmentService.getAllDepartments().get(0).getCode()));
    }

    @RequestMapping(value = "/serviceListAjax/", method = RequestMethod.POST)
    public @ResponseBody String getServicesListAjax(@RequestParam("departmentCode") String departmentCode) {
        String servicesJson = new Gson().toJson(serviceService.getServicesByCode(Integer.parseInt(departmentCode)));
        return servicesJson;
    }

    @RequestMapping(value = "/getApplicationsAjax/", method = RequestMethod.POST)
    public @ResponseBody String getApplicationsAjax() {
        String applicationsJson = new Gson().toJson(applicationViewService.getAllApplicationView());
        return applicationsJson;
    }
}