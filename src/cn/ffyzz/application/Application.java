package cn.ffyzz.application;

import cn.ffyzz.auth.MysqlCredentialStorage;
import cn.ffyzz.util.SHA256Helper;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/5/15
 */
public class Application {

    public static void main(String[] args) {
        long timestamp = System.currentTimeMillis();
        String originUrl = "https://www.ffyzz.com/test?appId=1&pwd=123456&ts=" + timestamp;
        String token = SHA256Helper.getInstance().SHA256(originUrl);
        String encodedUrl = "https://www.ffyzz.com/test?appId=1&token=" + token + "&ts=" + timestamp;
        ApiAuthencator apiAuthencator = new DefaultApiAuthencator(new MysqlCredentialStorage());
        apiAuthencator.auth(encodedUrl);
    }

}
