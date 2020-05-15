package cn.ffyzz.auth;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/5/15
 */
public interface CredentialStorage {

    /**
     * 获取 appId 的方法，可以有多种实现
     *
     * @param appId
     * @return
     */
    String getPasswordByAppId(String appId);

}
