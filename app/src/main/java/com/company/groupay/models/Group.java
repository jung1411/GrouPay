package com.company.groupay.models;

import java.io.Serializable;

public class Group implements Serializable {
    private String group_name, group_number;
    private Integer group_ID;
    private String group_date;
    private String group_cost;

    public String getGroup_cost() {
        return group_cost;
    }

    public void setGroup_cost(String group_cost) {
        this.group_cost = group_cost;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_number() {
        return group_number;
    }

    public void setGroup_number(String group_number) {
        this.group_number = group_number;
    }

    public Integer getGroup_ID() {
        return group_ID;
    }

    public void setGroup_ID(Integer group_ID) {
        this.group_ID = group_ID;
    }

    public String getGroup_date() {
        return group_date;
    }

    public void setGroup_date(String group_date) {
        this.group_date = group_date;
    }
}
