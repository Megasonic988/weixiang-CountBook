package com.ualberta.kevin.countbook;

import java.util.Date;

/**
 * Created by Kevin on 2017-09-24.
 */

public class Counter {

    private String name;
    private Date date;
    private Integer initialValue;
    private Integer currentValue;
    private String comment;

    public Counter (String name, Date date, Integer initialValue, String comment) {
        this.name = name;
        this.date = date;
        this.initialValue = initialValue;
        this.currentValue = initialValue;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getInitialValue() {
        return initialValue;
    }

    public Integer increment() {
        return ++currentValue;
    }

    public Integer decrement() {
        return --currentValue;
    }

    public Integer getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Integer currentValue) {
        this.currentValue = currentValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
