package design_mode.chain_of_responsibility;

import java.util.HashMap;

/**
 * 请求携带参数相关类
 */
public class HttpRequest {

    private String requestContext;

    private HashMap<String, String> requestParam = new HashMap<>();

    public String getRequestContext() {
        return requestContext;
    }

    public void setRequestContext(String requestContext) {
        this.requestContext = requestContext;
    }

    public HashMap<String, String> getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(HashMap<String, String> requestParam) {
        this.requestParam = requestParam;
    }

    public void setRequestParamMap(String key, String value) {
        requestParam.put(key, value);
    }

    public String getRequestParamMap(String key) {
        return requestParam.get(key);
    }
}
