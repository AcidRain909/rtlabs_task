package com.rtlabs.acidrain.service;

import java.util.Date;

public interface DeclareService {
    String declare(String userLastName,
                          String userFirstName,
                          String userPatronymic,
                          String userPhone,
                          String userEmail,
                          Date userAge);
}
