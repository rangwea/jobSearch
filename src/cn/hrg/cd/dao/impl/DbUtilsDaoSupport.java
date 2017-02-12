package cn.hrg.cd.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import cn.hrg.cd.util.JdbcUtil;

public class DbUtilsDaoSupport {
	public QueryRunner qr = new QueryRunner(JdbcUtil.INSTANCE.getDS());

	public void update(String sql, Object... params) {
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public <T>T query(String sql,ResultSetHandler<T> handler, Object...params){
		try {
			return qr.query(sql, handler,params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
