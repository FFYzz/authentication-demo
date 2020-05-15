package cn.ffyzz.auth;

import cn.ffyzz.util.SHA256Helper;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/5/15
 */
public class AuthToken {

    /**
     * UNIT : ms
     */
    private static final long DEFAULT_EXPIRED_TIME_INTERVAL = 1 * 60 * 1000;

    private String token;

    private long createTime;

    private long expiredTimeInterval;

    public AuthToken(String token, long createTime) {
        this.token = token;
        this.createTime = createTime;
    }

    public AuthToken(String token, long createTime, long expiredTimeInterval) {
        this.token = token;
        this.createTime = createTime;
        this.expiredTimeInterval = expiredTimeInterval;
    }

    /**
     * 服务端重新生成 token 用于验证
     *
     * @param baseUrl
     * @param appId
     * @param password
     * @return
     */
    public static AuthToken buildToken(String baseUrl, String appId, String password, long timestamp) {
        String completeUrl = baseUrl + "?appId=" + appId + "&pwd=" + password + "&ts=" + timestamp;
        String token = SHA256Helper.getInstance().SHA256(completeUrl);
        AuthToken authToken = new AuthToken(token, timestamp);
        return authToken;
    }

    public String getToken() {
        return token;
    }

    public boolean isExpired() {
        long interval = expiredTimeInterval == 0L ? DEFAULT_EXPIRED_TIME_INTERVAL : expiredTimeInterval;
        long currentTime = System.currentTimeMillis();
        return System.currentTimeMillis() - createTime > interval;
    }

    public boolean match(AuthToken token) {
        if (!this.token.equals(token.getToken())) {
            return false;
        }
        return true;
    }

}
