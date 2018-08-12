package com.rtlabs.acidrain.service;
import com.rtlabs.acidrain.db.dao.ApplicationDao;
import com.rtlabs.acidrain.db.dao.DeclarerDao;
import com.rtlabs.acidrain.db.security.FormsCheck;
import com.rtlabs.acidrain.pojo.Application;
import com.rtlabs.acidrain.pojo.Declarer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Service
public class DeclareServiceImpl implements DeclareService {

    @Autowired
    private final FormsCheck formsCheck;
    private final DeclarerDao declarerDao;
    private final ApplicationDao applicationDao;

    public DeclareServiceImpl(FormsCheck formsCheck, DeclarerDao declarerDao, ApplicationDao applicationDao) {
        this.formsCheck = formsCheck;
        this.declarerDao = declarerDao;
        this.applicationDao = applicationDao;
    }


    @Override
    public String declare(String userLastName, String userFirstName, String userPatronymic, String userPhone, String userEmail, Date userAge, String departmentCode, String serviceId) {
        String paramsChecking = "";

        if (!formsCheck.checkName(userLastName))
            paramsChecking += "Ошибка в фамилии. Необходимо использовать только кириллицу.<br>";

        if (!formsCheck.checkName(userFirstName))
            paramsChecking += "Ошибка в имени. Необходимо использовать только кириллицу.<br>";

        if (!formsCheck.checkName(userPatronymic))
            paramsChecking += "Ошибка в отчестве. Необходимо использовать только кириллицу.<br>";

        if (!formsCheck.checkPhone(userPhone))
            paramsChecking += "Телефон введен неверно.<br>";

        int age = Period.between(convertToLocalDate(userAge), convertToLocalDate(new Date())).getYears();

        if (age <= 18)
            paramsChecking += "Вам должно быть больше 18 лет!";

        if (paramsChecking.equals("")) {
            Declarer declarer = new Declarer();
            declarer.setFirstName(userFirstName);
            declarer.setLastName(userLastName);
            declarer.setPatronymic(userPatronymic);
            declarer.setPhone(userPhone);
            declarer.setEmail(userEmail);
            declarer.setBirthDate(userAge);

            Integer declarerId = declarerDao.addDeclarer(declarer);

            if(declarerId != 0) {
                Application application = new Application();
                application.setServiceId(Integer.parseInt(serviceId));
                application.setDeclarerId(declarerId);
                application.setCreated(new Date());

                applicationDao.addApplication(application);
            }

        }
        return paramsChecking;
    }

    private LocalDate convertToLocalDate(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
