package cn.hrg.cd.web.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.hrg.cd.dao.IAdminDao;
import cn.hrg.cd.dao.impl.AdminDaoImpl;
import cn.hrg.cd.domain.Admin;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 登录action
 *
 */
public class DownLoadAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private IAdminDao dao=new AdminDaoImpl();
	private String username;
	private String password;
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String execute(){
		System.out.println("开始验证登录.....");
		List<Admin> list = dao.checkLogin(username, password);
		if(list.size()==1){
			ServletActionContext.getRequest().getSession().setAttribute("USER_IN_SESSION", 1);
			System.out.println("登录成功");
			return SUCCESS;
		}
		return "error";
	}
}
