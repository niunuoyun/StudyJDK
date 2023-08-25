package com.nuo.studyjdk.contanst;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RestUrlProperties {

    public static String searchServiceAddr;
    public static String sidJavaServerAddr;
    public static String chatGPTServer;
    public static String vectorServer;
    public static String docSearchServer;
    public static String sqaServiceAddr;
    public static String env;

    @Value("${ENV:local}")
    public void setEnv(String env) {
        RestUrlProperties.env = env;
    }

    @Value("${LK_SEARCH_INTERNAL:http://internal.talkinggenie.com/}")
    public void setSearchServiceAddr(String searchServiceAddr) {
        RestUrlProperties.searchServiceAddr = searchServiceAddr;
    }

    @Value("${LK_SID_NEW_INTERNAL:http://internal.talkinggenie.com/}")
    public void setSidJavaServerAddr(String sidJavaServerAddr) {
        RestUrlProperties.sidJavaServerAddr = sidJavaServerAddr;
    }
    @Value("${LK_LLM_API_INTERNAL_URL:http://lk.t.duiopen.com/llm}")
    public void setChatGPTServer(String chatGPTServer) {
        RestUrlProperties.chatGPTServer = chatGPTServer;
    }
    @Value("${LK_FAISS_VECTOR_INTERNAL_URL:http://lk.dev.duiopen.com}")
    public void setVectorServer(String vectorServer) {
        RestUrlProperties.vectorServer = vectorServer;
    }
    @Value("${LK_DOC_SEARCH_INTERNAL_URL:http://chat-doc-service.his.svc.cluster.local:9090}")
    public void setDocSearchServer(String docSearchServer) {
        RestUrlProperties.docSearchServer = docSearchServer;
    }
    @Value("${HIS_SQASERVER_INTERNAL:http://internalprod.talkinggenie.com}")
    public void setSqaServiceAddr(String sqaServiceAddr) {
        RestUrlProperties.sqaServiceAddr = sqaServiceAddr;
    }
}
