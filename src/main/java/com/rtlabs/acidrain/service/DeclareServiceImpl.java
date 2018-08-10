package com.rtlabs.acidrain.service;
import com.rtlabs.acidrain.db.dao.DeclarerDao;
import com.rtlabs.acidrain.db.security.FormsCheck;
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

    public DeclareServiceImpl(FormsCheck formsCheck, DeclarerDao declarerDao) {
        this.formsCheck = formsCheck;
        this.declarerDao = declarerDao;
    }


    @Override
    public String declare(String userLastName, String userFirstName, String userPatronymic, String userPhone, String userEmail, Date userAge) {
        String paramsChecking = "";

        if (!formsCheck.checkName(userLastName))
            paramsChecking += "Ошибка в фамилии. Необходимо использовать только кириллицу.<br>";

        if (!formsCheck.checkName(userFirstName))
            paramsChecking += "Ошибка в имени. Необходимо использовать только кириллицу.<br>";

        if (!formsCheck.checkName(userPatronymic))
            paramsChecking += "Ошибка в отчестве. Необходимо использовать только кириллицу.<br>";

        if (!formsCheck.checkPhone(userPhone))
            paramsChecking += "Телефон введен неверно.<br>";

        if (!formsCheck.checkEmail(userEmail))
            paramsChecking += "Email введен неверно.<br>";

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

            declarerDao.addDeclarer(declarer);

        }
        return paramsChecking;
    }

    private LocalDate convertToLocalDate(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
