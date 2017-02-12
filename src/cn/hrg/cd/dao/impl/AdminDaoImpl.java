package cn.hrg.cd.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;

import cn.hrg.cd.dao.IAdminDao;
import cn.hrg.cd.domain.Admin;


public class AdminDaoImpl extends DbUtilsDaoSupport implements IAdminDao {

	public List<Admin> checkLogin(String username, String password) {
		String sql = "SELECT id FROM admin WHERE name=? and password=?";
		return super.query(sql, new AdminResultSetHander(),username,password);
	}
	
	class AdminResultSetHander implements ResultSetHandler<List<Admin>> {
		List<Admin> list = new ArrayList<Admin>();

		public List<Admin> handle(ResultSet rs) throws SQLException {
			try {
				while (rs.next()) {
					Admin admin = new Admin();
					admin.setId(rs.getLong("id"));
					list.add(admin);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}

	}
	
}
