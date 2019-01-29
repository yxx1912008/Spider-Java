package cn.luckydeer.spider.common.utils.wechat;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 
 * SHA-1加密工具类
 * 
 * @author yuanxx
 * @version $Id: EncryptUtil.java, v 0.1 2018年9月26日 下午3:08:39 yuanxx Exp $
 */
public class EncryptUtil {
    private static final Log    logger     = LogFactory.getLog(EncryptUtil.class);
    @SuppressWarnings("unused")
    private static final String UTF8       = "utf-8";
    private static final String CLASS_NAME = "EncryptUtil:";

    public String md5Digest(String src) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b = md.digest(src.getBytes("utf-8"));
            return byte2HexStr(b);
        } catch (Exception ex) {
            logger.debug(CLASS_NAME + ex);
        }
        return "";
    }

    private String byte2HexStr(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; ++i) {
            String s = Integer.toHexString(b[i] & 0xFF);
            if (s.length() == 1)
                sb.append("0");

            sb.append(s.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 用SHA-1算法加密字符串并返回16进制串
     * @param strSrc
     * @return
     */
    public static String sha1(String strSrc) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance("SHA-1");
            md.update(bt);
            strDes = bytes2Hex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            logger.debug(CLASS_NAME + e);
        }
        return strDes;
    }

    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }
}