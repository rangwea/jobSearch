package cn.hrg.cd.web.action;

import org.apache.struts2.ServletActionContext;

import cn.hrg.cd.dao.IEmploymentInfoDao;
import cn.hrg.cd.dao.impl.EmploymentInfoDaoImpl;
import cn.hrg.cd.domain.EmploymentInfo;
import cn.hrg.cd.index.EmploymentInfoIndexHelper;
import cn.hrg.cd.query.EmploymentInfoQueryObject;
import cn.hrg.cd.util.PageList;

import com.opensymphony.xwork2.ActionSupport;

public class EmploymentInfoAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private IEmploymentInfoDao dao=new EmploymentInfoDaoImpl();
	private EmploymentInfoQueryObject qo = new EmploymentInfoQueryObject();
	private EmploymentInfoIndexHelper indexHelper=new EmploymentInfoIndexHelper();
	private PageList pager;
	private EmploymentInfo employmentInfo;
	private Long id;
	
	public EmploymentInfo getEmploymentInfo() {
		return employmentInfo;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public EmploymentInfoQueryObject getQo() {
		return qo;
	}
	public void setQo(EmploymentInfoQueryObject qo) {
		this.qo = qo;
	}
	public PageList getPager() {
		return pager;
	}
	
	public String execute() throws Exception {
		pager = indexHelper.search(qo);
		return SUCCESS;
	}
	
	public String reCreateIndex() {
		Object obj = ServletActionContext.getRequest().getSession().getAttribute("USER_IN_SESSION");
		if(obj!=null){
			try {
				indexHelper.reCreateIndex();
				return "reCreateIndexSuccess";
			} catch (Exception e) {
				return "reCreateIndexError";
			}
		}else{
			return "login";
		}
	}
	
	public String view() {
		employmentInfo = dao.get(id);
		return "view";
	}
	
}
