package com.nesterlom.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Recipient {

    private Integer id;

    private String email;

    private Integer userId;

    private String name;

    private String surname;
}
