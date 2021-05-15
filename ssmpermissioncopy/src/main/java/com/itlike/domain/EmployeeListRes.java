package com.itlike.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeListRes {
    private List<?> rows =new ArrayList<>();
    private long total;
}
