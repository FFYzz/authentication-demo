package cn.ffyzz.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/5/15
 */
public class SHA256Helper {

    private static class SHA256HelperHolder {
        private static SHA256Helper instance = new SHA256Helper();
    }

    private SHA256Helper() {
    }

    public static SHA256Helper getInstance() {
        return SHA256HelperHolder.instance;
    }

    /**
     * 传入文本内容，返回 SHA-256 串
     *
     * @param strText
     * @return
     */
    public String SHA256(final String strText) {
        return SHA(strText, "SHA-256");
    }

    /**
     * 传入文本内容，返回 SHA-512 串
     *
     * @param strText
     * @return
     */
    public String SHA512(final String strText) {
        return SHA(strText, "SHA-512");
    }

    /**
     * @param strText
     * @param strType
     * @return
     */
    private String SHA(final String strText, final String strType) {
        String strResult = null;
        if (strText != null && strText.length() > 0) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(strType);
                messageDigest.update(strText.getBytes());
                byte byteBuffer[] = messageDigest.digest();

                StringBuffer strHexString = new StringBuffer();
                for (int i = 0; i < byteBuffer.length; i++) {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                strResult = strHexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return strResult;
    }

}