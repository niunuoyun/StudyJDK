package com.nuo.studyjdk.controller;

import com.alibaba.fastjson.JSONObject;
import com.nuo.studyjdk.contanst.Constant;
import com.nuo.studyjdk.http.HttpClient;
import com.nuo.studyjdk.sy.DataResponseBody;
import com.nuo.studyjdk.sy.RequestDataSource;
import com.nuo.studyjdk.sy.YourClassName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HttpStreamController {

    @Autowired
    HttpClient httpClient;

    @RequestMapping("/getStream")
    public StreamingResponseBody test(@RequestBody JSONObject queryInfo) throws IOException {
        String url = "/v1/tasks/mrc?use_cache=false&return_openai_completion_result=false";
        String line;

        InputStream inputStream = HttpClient.postWithStream(url, HttpClient.builderModuleRequestHeader(Constant.CHATGPT_TOKEN_VALUE,"demo_http"),JSONObject.toJSONString(queryInfo));
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        return new StreamingResponseBody() {
            @Override
            public void writeTo (OutputStream out) throws IOException {
                OutputStreamWriter outs=new OutputStreamWriter(out,"utf-8");//"utf-8"
                BufferedWriter bufferedWriter=new BufferedWriter(outs);
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    bufferedWriter.write(line);
                }
            }
        };
    }


    @RequestMapping("/getStream1")
    public DataResponseBody getStream() throws IOException {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE2OTkwMDA2NDUsImlzcyI6IjJYRVBOSGFvZzUwTXFzand1dUFuUkZ6R2p2diIsImV4cCI6MTY5OTAwNzg1MH0.3JGMGXvd9itlRCjOyxv407s_UYF_hPe2U6S-OCFA49U";
        String result = httpClient.getDataSource(token,"https://devoss.xue.lenovomm.com/aigc/original/e13dc5524bcf4853aa519f075a355e1d.png");
        YourClassName yourClassName = JSONObject.parseObject(result, YourClassName.class);
        RequestDataSource requestBody = new RequestDataSource();
        requestBody.setDescription(yourClassName.getDescription());
        List<String> files = new ArrayList<>();
        files.add(yourClassName.getId());
        requestBody.setFiles(files);
       String dataResResp =  httpClient.postJsonParam("https://api.sensenova.cn/v1/imgen/internal/datasets",requestBody,token);
       return JSONObject.parseObject(dataResResp, DataResponseBody.class);
    }

}
