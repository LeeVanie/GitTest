package com.vane.t05_test.utils.http;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class APIUtils {

    private static int appId = 62147;
    private static String appsecret = "bcedc450f8481e89b1445069acdc3dd9";

    /**
     * md5加密
     *
     * @param paramString
     * @return
     */
    public static String getMd5(String paramString) {
        try {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(paramString.getBytes());
            byte[] arrayOfByte = localMessageDigest.digest();
            StringBuffer localStringBuffer = new StringBuffer("");
            for (int i = 0; i < arrayOfByte.length; i++) {
                int j = arrayOfByte[i];
                if (j < 0)
                    j += 256;
                if (j < 16)
                    localStringBuffer.append("0");
                localStringBuffer.append(Integer.toHexString(j));
            }
            String str = localStringBuffer.toString();
            return str;
        } catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
            localNoSuchAlgorithmException.printStackTrace();
        }
        return null;
    }

    /**
     * 获取随机数
     *
     * @param paramInt
     * @return
     */
    public static String getRandomString(int paramInt) {
        Random localRandom = new Random();
        StringBuffer localStringBuffer = new StringBuffer();
        for (int i = 0; i < paramInt; i++)
            localStringBuffer.append("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(localRandom.nextInt(62)));
        return localStringBuffer.toString();
    }

    /**
     * 获取apiToken
     *
     * @param paramHashMap
     * @return
     */
    public static String getXPassportApitoken(HashMap<String, String> paramHashMap) {
        long l = System.currentTimeMillis() / 1000L;
        String str1 = sortAscll(paramHashMap);
        String str2 = getRandomString(6);
        String str3 = appId + "." + l + "." + str2 + "." + appsecret;
        return appId + "." + l + "." + str2 + "." + getMd5(new StringBuilder().append(str1).append(";").append(str3).toString());
    }

    /**
     * 排序
     *
     * @param paramHashMap
     * @return
     */
    public static String sortAscll(HashMap<String, String> paramHashMap) {
        String str = "";
        ArrayList localArrayList = new ArrayList(paramHashMap.keySet());
        Collections.sort(localArrayList);
        for (int i = 0; i < localArrayList.size(); i++) {
            str = str + localArrayList.get(i) + "=" + (String) paramHashMap.get(localArrayList.get(i));
            if (i != -1 + localArrayList.size())
                str = str + "&";
        }
        return str;
    }


}
