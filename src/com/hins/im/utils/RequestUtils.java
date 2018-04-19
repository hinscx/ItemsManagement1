package com.hins.sm.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;
import java.util.Map;



/**
 * if( connector处理request的转码 = ISO8859-1 ) {
 *     使用RequestUtils对GET请求的HttpServletRequest转码
 * }
 * else{
 *     直接使用HttpServletRequest
 * }
 *
 */
public class RequestUtils extends HttpServletRequestWrapper {
    
    private HttpServletRequest request;

    public RequestUtils(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public String getParameter(String name) {
        // 获取参数
        String value = request.getParameter(name);
        if(value == null) return null;//如果为null，直接返回null
        try {
            // 对参数进行转码处理后返回
            return new String(value.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map getParameterMap() {
        Map<String,String[]> map = request.getParameterMap();
        if(map == null) return map;
        // 遍历map，对每个值进行转码处理
        for(String key : map.keySet()) {
            String[] values = map.get(key);
            for(int i = 0; i < values.length; i++) {
                try {
                    values[i] = new String(values[i].getBytes("ISO-8859-1"), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        // 处理后返回
        return map;
    }
}

