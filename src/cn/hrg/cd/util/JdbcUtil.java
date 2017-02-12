package cn.hrg.cd.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public enum JdbcUtil {
	INSTANCE;
	private static DataSource ds = null;
	private static Properties p = new Properties();
	static {
		try {
			InputStream inStream = Thread.currentThread()
					.getContextClassLoader()
					.getResourceAsStream("dbcp.properties");
			p.load(inStream);

			ds = BasicDataSourceFactory.createDataSource(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//返回数据源
	public DataSource getDS(){
		return ds;
	}
	

	//释放资源
	public void close(Connection conn, Statement st, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
