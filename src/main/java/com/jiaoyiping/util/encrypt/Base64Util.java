package com.jiaoyiping.util.encrypt;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class Base64Util {
    public static String encode(String source) {
        byte[] bytes;
        String binary = null;
        try {
            bytes = source.getBytes(PREFERRED_ENCODING);
            binary = binary(bytes,2);
            System.out.println("长度："+binary.length());
            int temp = binary.length() % 24;
            if(temp!=0){
                for(int i = 0;i<24-temp;i++){
                    binary+="0";
                }
            }



        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        for(int i = 0;i<binary.length();i+=6){
            String str = binary.substring(i,i+6);
            System.out.println(str);
            int offset= Integer.parseInt(str, 2);
            System.out.println(offset);

            System.out.println(NATIVE_ALPHABET[offset]);
            System.out.println(new String());
        }

        return binary;
    }

    public static void main(String[] args) {

        System.out.println(Base64Util.encode("焦一平"));
    }

    public static String decode(String base64Str) {


        return "";
    }

    private static final byte[] NATIVE_ALPHABET =
            {
                    (byte) 'A', (byte) 'B', (byte) 'C', (byte) 'D', (byte) 'E', (byte) 'F', (byte) 'G', (byte) 'H', (byte) 'I',
                    (byte) 'J', (byte) 'K', (byte) 'L', (byte) 'M', (byte) 'N', (byte) 'O', (byte) 'P', (byte) 'Q', (byte) 'R',
                    (byte) 'S', (byte) 'T', (byte) 'U', (byte) 'V', (byte) 'W', (byte) 'X', (byte) 'Y', (byte) 'Z', (byte) 'a',
                    (byte) 'b', (byte) 'c', (byte) 'd', (byte) 'e', (byte) 'f', (byte) 'g', (byte) 'h', (byte) 'i', (byte) 'j',
                    (byte) 'k', (byte) 'l', (byte) 'm', (byte) 'n', (byte) 'o', (byte) 'p', (byte) 'q', (byte) 'r', (byte) 's',
                    (byte) 't', (byte) 'u', (byte) 'v', (byte) 'w', (byte) 'x', (byte) 'y', (byte) 'z', (byte) '0', (byte) '1',
                    (byte) '2', (byte) '3', (byte) '4', (byte) '5', (byte) '6', (byte) '7', (byte) '8', (byte) '9', (byte) '+',
                    (byte) '/'
            };


    public static final String PREFERRED_ENCODING = "UTF-8";

    public static String binary(byte[] bytes, int radix){
                return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
            }

}
