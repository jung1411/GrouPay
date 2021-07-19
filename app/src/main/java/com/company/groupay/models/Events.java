package com.company.groupay.models;

import java.io.Serializable;

public class Events implements Serializable {
    private String event_name,event_cost,group_name;
    private Integer event_ID;

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_cost() {
        return event_cost;
    }

    public void setEvent_cost(String event_cost) {
        this.event_cost = event_cost;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public Integer getEvent_ID() {
        return event_ID;
    }

    public void setEvent_ID(Integer event_ID) {
        this.event_ID = event_ID;
    }
}
