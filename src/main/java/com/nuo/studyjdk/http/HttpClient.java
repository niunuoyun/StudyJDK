package com.nuo.studyjdk.http;

import com.alibaba.fastjson.JSONObject;
import com.nuo.studyjdk.contanst.Constant;
import com.nuo.studyjdk.contanst.RestUrlProperties;
import com.nuo.studyjdk.sy.RequestDataSource;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
public class HttpClient {
    @Autowired
    private RestTemplate restTemplate;
   /* private ByteArrayResource handlerFile(String addr) throws MalformedURLException {
        URL url = new URL(addr);
        byte[] fileBytes = IOUtils.toByteArray(url);
        ByteArrayResource fileResource = new ByteArrayResource(fileBytes) {
            @Override
            public String getFilename() {
                return "file.txt";
            }
        };
        return fileResource;
    }*/
    public String  getDataSource(String API_TOKEN,String url) throws MalformedURLException {
        MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("description","string");
        bodyMap.add("scheme","KNOWLEDGE_BASE_1");

        File file = new File("C:\\Users\\work\\study\\StudyJDK\\src\\main\\resources/data.json");
       // bodyMap.add("file",new UrlResource(url));
        bodyMap.add("file",new FileSystemResource(file));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+API_TOKEN);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange("https://file.sensenova.cn/v1/files", HttpMethod.POST, requestEntity, String.class);

       return responseEntity.getBody();
    }


    public String postJsonParam(String url, RequestDataSource param, String API_TOKEN) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+API_TOKEN);
        HttpEntity<RequestDataSource> requestEntity = new HttpEntity<>(param, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        return responseEntity.getBody();
    }

    public static InputStream postWithStream(String url, Map<String, String> header, String body)  {
        String addr = RestUrlProperties.chatGPTServer+url;
        try {
            log.info("connectModule rul:{}",addr);
            HttpURLConnection conn = (HttpURLConnection) new URL(addr).openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);
            if (!CollectionUtils.isEmpty(header)) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            conn.connect();
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.write(body.getBytes(StandardCharsets.UTF_8));
            out.flush();
            out.close();
            return conn.getInputStream();
        }catch (Exception e){
            log.error("connectModule:{}", addr+"连接异常");
            return null;
        }
    }

    public static  Map<String, String> builderModuleRequestHeader(String token,String requestId){
        Map<String,String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("requestId", requestId);
        if (StringUtils.isEmpty(token))   header.put(Constant.CHATGPT_TOKEN_KEY, Constant.CHATGPT_TOKEN_VALUE);
        else  header.put(Constant.CHATGPT_TOKEN_KEY, token);
        return header;
    }
}
