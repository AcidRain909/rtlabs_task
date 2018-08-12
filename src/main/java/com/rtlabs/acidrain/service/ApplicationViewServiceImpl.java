package com.rtlabs.acidrain.service;

import com.rtlabs.acidrain.db.dao.AppicationViewDao;
import com.rtlabs.acidrain.pojo.ApplicationView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ApplicationViewServiceImpl implements ApplicationViewService {

    @Autowired
    private final AppicationViewDao appicationViewDao;

    public ApplicationViewServiceImpl(AppicationViewDao appicationViewDao) {
        this.appicationViewDao = appicationViewDao;
    }

    @Override
    public ArrayList<ApplicationView> getAllApplicationView() {
        return appicationViewDao.getAllApplicationView();
    }
}
