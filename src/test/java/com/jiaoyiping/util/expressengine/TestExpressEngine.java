
package com.jiaoyiping.util.expressengine;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created with Eclipse.
 * User: <a href="jiaoyiping@gmail.com">焦一平</a>
 * Date: 2014年11月15日
 * Time: 下午9:31:32
 */
public class TestExpressEngine {
	@Test
	public void testLike(){
		boolean result = ExpressEngine.valid("123", "123456", OperatorEnum.LIKE);
		Assert.assertTrue(result);
	}
}
