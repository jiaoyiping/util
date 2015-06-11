package com.jiaoyiping.guava;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 焦一平
 * Date: 2015/4/6
 * Time: 18:09
 * To change this template use File | Settings | File Templates.
 */
public class Test1 {
    @Test
    public void test1(){
        List<String> strList = new ArrayList<String>();
        strList.add("JIAO");
        strList.add("YI");
        strList.add("PING");
        System.out.println(Joiner.on("-").skipNulls().join(strList).toString());
    }
}
