package com.nuo.studyjdk.sy;

import lombok.Data;

import java.util.List;

@Data
public class DataResponseBody {
    private String id;
    private String description;
    private String created_at;
    private String status;
    private List<File> files;
    private String updated_at;

    @Data
    public class File {
        private String id;
        private String description;
        private String status;

    }
}