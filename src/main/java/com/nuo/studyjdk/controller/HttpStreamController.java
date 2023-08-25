package com.nuo.studyjdk.controller;

import com.alibaba.fastjson.JSONObject;
import com.nuo.studyjdk.contanst.Constant;
import com.nuo.studyjdk.http.HttpClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;

@RestController
public class HttpStreamController {

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
    public StreamingResponseBody getStream(@RequestBody JSONObject queryInfo) throws IOException {
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
}
