package com.jiaoyiping.util.fenye.tag;

/**
 * 分页的标签处理类
 * @author jiao
 *
 */
import java.io.IOException;
import java.util.ResourceBundle;
import javax.servlet.jsp.jstl.fmt.LocalizationContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings({ "serial", "unused" })
public class PageDeal extends TagSupport {

	/** 处理分页的action path */
	private String actionName;

	/** 页面数 */
	private Integer total;

	/** 当前页码 */
	private Integer num;

	/** 当前pageSize */
	private Integer pageSize;

	/** 分类的id */
	private Integer catalogId;

	/** 降序排序字段 */
	private String orderByItem;

	/** 其他参数,格式 &key1=value1&key2=value2 */
	private String otherParams;

	/** 资源文件Bundle变量 */
	private String bundle;

	/** 总记录数 */
	private Integer totalRecords;

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public void setOtherParams(String params) {
		this.otherParams = params;
	}

	public void setOrderByItem(String orderByItem) {
		this.orderByItem = orderByItem;
	}

	public String getActionName() {
		int pos = actionName.indexOf("?");
		if (pos > 0) {
			actionName = actionName.substring(0, pos - 1);
		}
		return actionName + ".jhtml";
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public void setCatalogId(Integer id) {
		catalogId = id;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public int getPreviousPage() {
		if (num - 1 <= 0) {
			return 1;
		} else {
			return (num - 1);
		}
	}

	public int getNextPage() {
		if (num + 1 >= total) {
			return total;
		} else {
			return (num + 1);
		}
	}

	/**
	 * 产生类似如下的html代码 <div class="turn"><b><a href="#"><<
	 * 上一页</a></b><span>1</span> <a href="#">2</a><a href="#">3</a><b>...</b><a
	 * href="#">4</a> <a href="#">5</a><a href="#">6</a><a href="#">7</a><a
	 * href="#">8</a> <a href="#">9</a><a href="#">10</a><b><a href="#">下一页
	 * >></a></b> </div>
	 * 
	 */
	public int doStartTag() throws JspException {
		StringBuffer sb = new StringBuffer();
		LocalizationContext localContext = (LocalizationContext) this.pageContext
				.getAttribute(bundle == null ? "common" : bundle);
		if (localContext != null) {
			// 分页国际化
			ResourceBundle resBundle = localContext.getResourceBundle();
			String beforeSelect = resBundle
					.getString("messageResource.pageNote");
			String afterSelect = "";
			if (resBundle.getString("messageResource.pageTiao").trim().length() == 0) {
				// 英文
				afterSelect = beforeSelect;
				beforeSelect = "";
			}
			if (total > 0) {
				sb.append("<form id='pageForm' action='" + getActionName()
						+ "' method='post'>");
				sb.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#FFFFFF' class='TdRight'>");
				sb.append(beforeSelect
						+ "<select id='pageSizeInTag' name='pageSize' onchange='changePageSize(this.value)'>");
				sb.append("<option value='10'>10</option>");
				sb.append("<option value='15'>15</option>");
				sb.append("<option value='20'>20</option>");
				sb.append("<option value='30'>30</option>");
				sb.append("<option value='50'>50</option>");
				sb.append("</select>" + afterSelect
						+ resBundle.getString("messageResource.pageTiao") + " ");
				sb.append("" + num + "/" + total
						+ resBundle.getString("messageResource.page") + " ");
				sb.append("<a href='javascript:page(1)");

				sb.append("'>"
						+ resBundle.getString("messageResource.pageFirst")
						+ "</a> | ");
				sb.append("<a href='javascript:page(" + getPreviousPage() + ")");

				sb.append("'>"
						+ resBundle.getString("messageResource.pagePrevious")
						+ "</a> | ");
				sb.append("<a href='javascript:page(" + getNextPage() + ")");

				sb.append("'>"
						+ resBundle.getString("messageResource.pageNext")
						+ "</a> | ");
				sb.append("<a href='javascript:page(" + total + ")");

				sb.append("'>"
						+ resBundle.getString("messageResource.pageLast")
						+ "</a> ");

				sb.append("<input id='pageNoInTag' name='pageNo' type='text' style='width:30px;' align='absmiddle' maxlength='3' value='"
						+ num + "'>");
				sb.append("<input name='go' type='button' id='go' value='GO' align='absmiddle' onclick='page(-1)'>");

				if (otherParams != null && !otherParams.equals("")) {
					String[] params = otherParams.split("&");
					String name = "";
					String value = "";
					for (String param : params) {
						name = param.split("=")[0];
						if (param.split("=").length > 1) {
							value = param.split("=")[1];
						} else {
							value = "";
						}

						sb.append("<input type='hidden' name='" + name
								+ "' value='" + value + "'>");
					}
				}
				sb.append("</td></tr></table></form>");
				sb.append("<script>");
				sb.append("function page(toPageNo){");
				sb.append("if(toPageNo!='-1'){document.getElementById('pageNoInTag').value = toPageNo;}");
				sb.append("if(!isPlus(document.getElementById('pageNoInTag').value)){alert('请您大于0的数字');return;}");
				sb.append("if(document.getElementById('pageNoInTag').value > "
						+ total + "){alert('您输入的页数已经超过最大页数，最大页数是" + total
						+ "');return;}");
				sb.append("document.getElementById('pageForm').submit();}");

				sb.append("function isPlus(value){");
				sb.append("ValidationExpression=/^[0-9]+$/;");
				sb.append("if (ValidationExpression.test(value))return true;");
				sb.append("return false;");
				sb.append("}");

				sb.append("function changePageSize(pageSize) {");
				sb.append("document.getElementById('pageNoInTag').value = 1;");
				sb.append("document.getElementById('pageForm').submit();");
				sb.append("}");
				sb.append("var pageSize = document.getElementById('pageSizeInTag');");
				sb.append("for (var i = 0 ; i < pageSize.options.length ; i++) {");
				sb.append("if (pageSize.options[i].value == " + pageSize
						+ ") {");
				sb.append("pageSize.selectedIndex = i");
				sb.append("}");
				sb.append("}");
				sb.append("</script>");
			}
		} else {
			// 维持以前的分页流程
			if (total > 0) {
				sb.append("<form id='pageForm' action='" + getActionName()
						+ "' method='post'>");
				sb.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#FFFFFF' class='TdRight'>");
				sb.append("总记录数 " + this.totalRecords + "条 ");
				sb.append("每页显示<select id='pageSizeInTag' name='pageSize' onchange='changePageSize(this.value)'>");
				sb.append("<option value='10'>10</option>");
				sb.append("<option value='15'>15</option>");
				sb.append("<option value='20'>20</option>");
				sb.append("<option value='30'>30</option>");
				sb.append("<option value='50'>50</option>");
				sb.append("</select>条 ");
				sb.append("" + num + "/" + total + "页 ");
				sb.append("<a href='javascript:page(1)");

				sb.append("'>首页</a> | ");
				sb.append("<a href='javascript:page(" + getPreviousPage() + ")");

				sb.append("'>上一页</a> | ");
				sb.append("<a href='javascript:page(" + getNextPage() + ")");

				sb.append("'>下一页</a> | ");
				sb.append("<a href='javascript:page(" + total + ")");

				sb.append("'>末页</a> ");

				sb.append("<input id='pageNoInTag' name='pageNo' type='text' style='width:30px;' align='absmiddle' maxlength='3' value='"
						+ num + "'>");
				sb.append("<input name='go' type='button' id='go' value='GO' align='absmiddle' onclick='page(-1)'>");

				if (otherParams != null && !otherParams.equals("")) {
					String[] params = otherParams.split("&");
					String name = "";
					String value = "";
					for (String param : params) {
						name = param.split("=")[0];
						if (param.split("=").length > 1) {
							value = param.split("=")[1];
						} else {
							value = "";
						}

						sb.append("<input type='hidden' name='" + name
								+ "' value='" + value + "'>");
					}
				}
				sb.append("</td></tr></table></form>");
				sb.append("<script>");
				sb.append("function page(toPageNo){");
				sb.append("if(toPageNo!='-1'){document.getElementById('pageNoInTag').value = toPageNo;}");
				sb.append("if(!isPlus(document.getElementById('pageNoInTag').value)){alert('请您大于0的数字');return;}");
				sb.append("if(document.getElementById('pageNoInTag').value > "
						+ total + "){alert('您输入的页数已经超过最大页数，最大页数是" + total
						+ "');return;}");
				sb.append("document.getElementById('pageForm').submit();}");

				sb.append("function isPlus(value){");
				sb.append("ValidationExpression=/^[0-9]+$/;");
				sb.append("if (ValidationExpression.test(value))return true;");
				sb.append("return false;");
				sb.append("}");

				sb.append("function changePageSize(pageSize) {");
				sb.append("document.getElementById('pageNoInTag').value = 1;");
				sb.append("document.getElementById('pageForm').submit();");
				sb.append("}");
				sb.append("var pageSize = document.getElementById('pageSizeInTag');");
				sb.append("for (var i = 0 ; i < pageSize.options.length ; i++) {");
				sb.append("if (pageSize.options[i].value == " + pageSize
						+ ") {");
				sb.append("pageSize.selectedIndex = i");
				sb.append("}");
				sb.append("}");
				sb.append("</script>");
			}
		}
		try {
			JspWriter out = pageContext.getOut();
			out.write(sb.toString());
			return EVAL_PAGE;
		} catch (IOException e) {
			throw new JspException(e);
		}

	}

	public String getOtherParams() {
		return otherParams;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getBundle() {
		return bundle;
	}

	public void setBundle(String bundle) {
		this.bundle = bundle;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

}
