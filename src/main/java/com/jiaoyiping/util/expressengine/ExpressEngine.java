/**
 * Created with Eclipse.
 * User: <a href="jiaoyiping@gmail.com">焦一平</a>
 * Date: 2014年11月15日
 * Time: 下午9:24:08
 */
package com.jiaoyiping.util.expressengine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author 焦一平
 *
 */
public class ExpressEngine {
	/**
	 * 
	 * <pre>
	 * Desc  给定比较类型和操作数返回是否满足
	 * @param input 操作数1
	 * @param expect 操作数2
	 * @param opreator 比较类型
	 * @return
	 * @author 焦一平
	 * @refactor 焦一平
	 * @date   2014年11月5日 下午12:18:57
	 * </pre>
	 */
	public static boolean valid(String input, String expect, OperatorEnum opreator) {
		if (opreator == null) {
			opreator = OperatorEnum.EQUAL;
		}
		switch (opreator) {
		case EQUAL:
			return (input.equals(expect));
		case NOT_EQUAL:
			return (!input.equals(expect));
		case LIKE:
			return (expect.indexOf(input) != -1);
		case NOT_LIKE:
			return (expect.indexOf(input) == -1);
		case IN:
		case NOT_IN:
		case GREAT_THAN:
		case LESS_THAN:
		case GREAT_THAN_EQUALS:
		case LESS_THAN_EQUALS:
		case MATCH:
			Pattern p = Pattern.compile(input);
			Matcher m = p.matcher(expect);
			return (m.matches());
		case NOT_MATCH:
			Pattern p1 = Pattern.compile(input);
			Matcher m1 = p1.matcher(expect);
			return (!m1.matches());
		default:
			return (input.equals(expect));
		}

	}

}

