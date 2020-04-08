package com.ssh.bbc.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * md5加密类  自定义盐值 加上用户名和密码生成密码
 */
public class MD5hexUtil {
    private static final String  salt = "@@";
    public static String getMd5hex(String salt,String pwd){
        return DigestUtils.md5Hex(salt+salt+pwd);
    }
}
