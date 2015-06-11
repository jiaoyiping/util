package com.jiaoyiping;

import com.jiaoyiping.util.DependencyResolver;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 焦一平
 * Date: 2015/6/11
 * Time: 6:55
 * To change this template use File | Settings | File Templates.
 */
public class TestDependencyResolver {
    @Test
    public void test() {
        String jarFilePath = "E:\\uconsole\\lib";
        Map<String,String> conflict = DependencyResolver.getConflictJars(jarFilePath);
        Assert.assertTrue(conflict.size()!=0);
    }
}
