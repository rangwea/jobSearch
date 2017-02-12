package cn.hrg.cd.web.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.hrg.cd.dao.IEmploymentInfoDao;
import cn.hrg.cd.dao.IJobInfoDao;
import cn.hrg.cd.dao.impl.EmploymentInfoDaoImpl;
import cn.hrg.cd.dao.impl.JobInfoDaoImpl;
import cn.hrg.cd.index.EmploymentInfoProcessor;
import cn.hrg.cd.index.EmploymentInfoToDatabase;
import cn.hrg.cd.index.JobInfoProcessor;
import cn.hrg.cd.index.JobInfoToDatabase;
import us.codecraft.webmagic.Spider;

/**
 *数据爬取action 
 *
 */
public class SpiderAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private IJobInfoDao jobInfoDao=new JobInfoDaoImpl();
	private IEmploymentInfoDao employmentInfoDao=new EmploymentInfoDaoImpl();
	private static Spider spider=null;
	
	public String spiderJobInfo(){
		Object obj = ServletActionContext.getRequest().getSession().getAttribute("USER_IN_SESSION");
		if(obj!=null){
			spider = Spider.create(new JobInfoProcessor());
			spider.addPipeline(new JobInfoToDatabase()).addUrl("http://sou.zhaopin.com/jobs/searchresult.ashx?jl=%E9%87%8D%E5%BA%86%2b%E5%B9%BF%E5%B7%9E%2b%E6%9D%AD%E5%B7%9E%2b%E8%8B%8F%E5%B7%9E%2b%E4%B8%8A%E6%B5%B7&isadv=0&sg=61049d2a3f00427cb6f1d86b1f8a1102&p=8")
	        .run();
			jobInfoCount = jobInfoDao.getDataCount();
		    return "message";
		}else{
			return "login";
		}
	}
	
	public String spiderEmploymentInfo(){
		Object obj = ServletActionContext.getRequest().getSession().getAttribute("USER_IN_SESSION");
		if(obj!=null){
			spider = Spider.create(new EmploymentInfoProcessor());
			spider.addPipeline(new EmploymentInfoToDatabase()).addUrl("http://www.qncye.com/ruhe/jingyan/list_5_1.html")
	        .run();
			employmentInfoCount = employmentInfoDao.getDataCount();
		    return "message";
		}else{
			return "login";
		}
	}
	
	public String stopSpider(){
		System.out.println("停止spider");
		if(spider!=null){
			spider.stop();
			return SUCCESS;
		}
		return "error";
	}
	
	private Long jobInfoCount;
	public Long getJobInfoCount() {
		return jobInfoCount;
	}
	
	private Long employmentInfoCount;
	public Long getEmploymentInfoCount() {
		return employmentInfoCount;
	}
}
