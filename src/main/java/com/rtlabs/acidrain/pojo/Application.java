package com.rtlabs.acidrain.pojo;

import java.util.Date;

public class Application {
    private int id;
    private int serviceId;
    private int declarerId;
    private Date created;

    public Application() {
    }

    public Application(int id, int serviceId, int declarerId, Date created) {
        this.id = id;
        this.serviceId = serviceId;
        this.declarerId = declarerId;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getDeclarerId() {
        return declarerId;
    }

    public void setDeclarerId(int declarerId) {
        this.declarerId = declarerId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
