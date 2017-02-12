package cn.hrg.cd.web.action;

import org.apache.struts2.ServletActionContext;

import cn.hrg.cd.dao.IJobInfoDao;
import cn.hrg.cd.dao.impl.JobInfoDaoImpl;
import cn.hrg.cd.domain.JobInfo;
import cn.hrg.cd.index.JobInfoIndexHelper;
import cn.hrg.cd.query.JobInfoQueryObject;
import cn.hrg.cd.util.PageList;

import com.opensymphony.xwork2.ActionSupport;

public class JobInfoAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private IJobInfoDao dao=new JobInfoDaoImpl();
	private JobInfoQueryObject qo = new JobInfoQueryObject();
	private JobInfoIndexHelper indexHelper=new JobInfoIndexHelper();
	private JobInfo jobInfo;
	private PageList pager;
	private Long id;
	
	public void setId(Long id) {
		this.id = id;
	}
	public JobInfo getJobInfo() {
		return jobInfo;
	}
	public JobInfoQueryObject getQo() {
		return qo;
	}
	public void setQo(JobInfoQueryObject qo) {
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
		System.out.println(id);
		jobInfo = dao.get(id);
		System.out.println(jobInfo);
		return "view";
	}

}
