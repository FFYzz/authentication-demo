package cn.ffyzz.auth;

import java.util.HashMap;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/5/15
 */
public class ApiRequest {

    private String baseUrl;
    private String token;
    private String appId;
    private long timestamp;

    public ApiRequest(String baseUrl, String token, String appId, long timestamp) {
        this.baseUrl = baseUrl;
        this.token = token;
        this.appId = appId;
        this.timestamp = timestamp;
    }

    /**
     * 从 url 请求的一个创建方法
     *
     * @param url
     * @return
     */
    public static ApiRequest buildFromUrl(String url) {
        HashMap<String, String> params = new HashMap<>();
        int indexOfQuestion = url.indexOf("?");
        String baseUrl = url.substring(0, indexOfQuestion);
        int indexOfAdd = indexOfQuestion;
        while (indexOfAdd != -1) {
            int indexOfEqual = url.indexOf("=", indexOfAdd);
            String key = url.substring(indexOfAdd + 1, indexOfEqual);
            String value = null;
            indexOfAdd = url.indexOf("&", indexOfEqual);
            if (!(indexOfAdd == -1)) {
                value = url.substring(indexOfEqual + 1, indexOfAdd);
            } else {
                value = url.substring(indexOfEqual + 1);
            }
            params.put(key, value);
        }
        ApiRequest apiRequest = new ApiRequest(baseUrl, params.get("token"), params.get("appId"), Long.valueOf(params.get("ts")));
        return apiRequest;
    }

    // 可以通过其他方法请求，比如 dubbo 协议等


    public String getBaseUrl() {
        return baseUrl;
    }

    public String getToken() {
        return token;
    }

    public String getAppId() {
        return appId;
    }

    public long getTimestamp() {
        return timestamp;
    }

}
