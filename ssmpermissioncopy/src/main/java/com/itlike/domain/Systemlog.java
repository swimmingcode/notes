package com.itlike.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Systemlog {
    private Long id;

    private Date optime;

    private String ip;

    private String usefunction;

    private String useparams;
}