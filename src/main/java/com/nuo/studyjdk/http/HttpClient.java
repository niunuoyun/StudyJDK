package com.nuo.studyjdk.http;

import com.nuo.studyjdk.contanst.Constant;
import com.nuo.studyjdk.contanst.RestUrlProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
@Slf4j
public class HttpClient {

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
