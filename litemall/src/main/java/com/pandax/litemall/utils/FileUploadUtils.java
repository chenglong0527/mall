package com.pandax.litemall.utils;

import java.util.UUID;

public class FileUploadUtils {
    public static String getRandomFileName(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
    public static String getFilePath(){
        UUID uuid = UUID.randomUUID();
        int i = uuid.hashCode();
        byte[] bytes = Integer.toHexString(i).getBytes();
        StringBuilder builder = new StringBuilder();
        builder.append("img/");
        for (byte aByte : bytes) {
            builder.append((char)aByte).append("/");
        }
        return builder.toString();
    }
}
