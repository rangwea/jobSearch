package cn.hrg.cd.dao;

import java.util.List;

import cn.hrg.cd.domain.Admin;

public interface IAdminDao {
	List<Admin> checkLogin(String username, String password);
}
