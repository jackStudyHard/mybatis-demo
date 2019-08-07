package com.fc.dal.dao;

import lombok.Data;

//lombok
@Data
public class Test {
    private Integer id;

    private Integer nums;

    private String name;


    public enum Name {
        A,B,C
    }
}