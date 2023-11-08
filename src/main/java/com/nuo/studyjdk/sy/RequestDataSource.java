package com.nuo.studyjdk.sy;

import lombok.Data;

import java.util.List;

@Data
public class RequestDataSource {
    private String description;
    private List<String> files;

}