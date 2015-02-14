/**
 * Created with Eclipse.
 * User: <a href="jiaoyiping@gmail.com">焦一平</a>
 * Date: 2014年11月15日
 * Time: 下午9:10:52
 */
package com.jiaoyiping.util.expressengine;

/**
 * @author 焦一平
 * 
 */
public enum OperatorEnum {
	EQUAL("equals"), NOT_EQUAL("not_equals"), LIKE("like"), NOT_LIKE("not_like"), 
	IN("in"), NOT_IN("not_in"), LESS_THAN("lt"), GREAT_THAN("gt"), LESS_THAN_EQUALS("lte"), 
	GREAT_THAN_EQUALS("gte"), MATCH("match"), NOT_MATCH("not_match");
	private String value;

	private OperatorEnum(String value) {
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static OperatorEnum getOperatorByValue(String value) {
		for (OperatorEnum s : OperatorEnum.values()) {
			if (s.getValue().equals(value)) {
				return s;
			}
		}
		return EQUAL;
	}

}
