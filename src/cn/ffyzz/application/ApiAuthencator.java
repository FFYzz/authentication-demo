package cn.ffyzz.application;

import cn.ffyzz.auth.ApiRequest;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/5/15
 */
public interface ApiAuthencator {

    /**
     * 根据 url 鉴权
     *
     * @param url
     */
    void auth(String url);

    /**
     * 最终都构造成 ApiRequest
     * 责任链中的一环， auth 通过后开始发送请求
     *
     * @param apiRequest
     */
    void auth(ApiRequest apiRequest);


}
