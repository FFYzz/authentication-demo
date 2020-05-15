package cn.ffyzz.application;

import cn.ffyzz.auth.ApiRequest;
import cn.ffyzz.auth.AuthToken;
import cn.ffyzz.auth.CredentialStorage;
import cn.ffyzz.auth.MysqlCredentialStorage;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/5/15
 */
public class DefaultApiAuthencator implements ApiAuthencator {

    private CredentialStorage credentialStorage;

    public DefaultApiAuthencator() {
        // default implementation
        this.credentialStorage = new MysqlCredentialStorage();
    }

    public DefaultApiAuthencator(CredentialStorage credentialStorage) {
        this.credentialStorage = credentialStorage;
    }

    @Override
    public void auth(String url) {
        ApiRequest apiRequest = ApiRequest.buildFromUrl(url);
        auth(apiRequest);
    }

    @Override
    public void auth(ApiRequest apiRequest) {
        String baseUrl = apiRequest.getBaseUrl();
        String appId = apiRequest.getAppId();
        String token = apiRequest.getToken();
        long timestamp = apiRequest.getTimestamp();

        AuthToken clientAuthToken = new AuthToken(token, timestamp);
        if (clientAuthToken.isExpired()) {
            throw new RuntimeException("Token is expired");
        }
        String password = credentialStorage.getPasswordByAppId(appId);
        AuthToken serverAuthToken = AuthToken.buildToken(baseUrl, appId, password, timestamp);
        if (!serverAuthToken.match(clientAuthToken)) {
            throw new RuntimeException("Token verification failed");
        }
        System.out.println("鉴权通过");
    }

}
