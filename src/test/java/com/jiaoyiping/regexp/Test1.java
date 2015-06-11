package com.jiaoyiping.regexp;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: 焦一平
 * Date: 2015/4/5
 * Time: 13:09
 * To change this template use File | Settings | File Templates.
 */
public class Test1 {
    @Test
    public void test1(){
        System.out.println("213".matches("[^8-9].+"));
        String str="hell my name is jiaoyiping this is my book";
        System.out.println(str.matches("jiaoyiping"));
    }
    @Test
    public void testEmail(){
        String regexp ="([^<>\\[\\]]+)\\s*([<\\[]([\\w\\-\\.]+@([\\w\\-]+\\.)+[a-zA-Z]+)[>\\]])?";
        System.out.println("jiaoyiping@gmail.com".matches(regexp));
        System.out.println("jiao".matches(regexp));
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher("jiaoyiping@gmail.com");
        System.out.println(matcher.matches());
        System.out.println(matcher.group(1));

        String regexp1 = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        System.out.println("jiaoyiping".matches(regexp1));
        System.out.println("ji._aoyiping@gmailco.m".matches(regexp1));
        String str3="^(http|https|ftp|file)://[A-Za-z0-9_.]+(\\:\\d{1,5})?(/[a-zA-Z0-9_]*)*([A-Za-z0-9_&?=]*)?";

    }
}
