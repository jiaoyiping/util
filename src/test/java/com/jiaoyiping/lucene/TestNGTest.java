package com.jiaoyiping.lucene;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: 焦一平
 * Date: 2015/3/21
 * Time: 23:50
 * To change this template use File | Settings | File Templates.
 */
public class TestNGTest {
    private int repeat;
    @BeforeTest
    public void before(){
        repeat = 5;
    }
    @Test
    public void test1(){
        Assert.assertEquals(5,repeat);
    }
}
