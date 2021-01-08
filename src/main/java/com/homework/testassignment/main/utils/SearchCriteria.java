package com.homework.testassignment.main.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCriteria {
    private Object firstValue;
    private Object secondValue;
    private String key;
    private boolean applyFilter;
    private boolean dateCriteria;

    public SearchCriteria(Object firstValue, String key, boolean applyFilter) {
        this.firstValue = firstValue;
        this.key = key;
        this.applyFilter = applyFilter;
    }

    public SearchCriteria(Object firstValue, Object secondValue, String key, boolean applyFilter, boolean dateCriteria) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.key = key;
        this.applyFilter = applyFilter;
        this.dateCriteria = dateCriteria;
    }
}
