package cn.hrg.cd.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.hrg.cd.dao.IEmploymentInfoDao;
import cn.hrg.cd.domain.EmploymentInfo;

public class EmploymentInfoDaoImpl extends DbUtilsDaoSupport implements IEmploymentInfoDao {

	public void save(EmploymentInfo employmentInfo) {
		String sql = "INSERT INTO employmentInfo(title,content,date) VALUES(?,?,?)";
		Object[] params = {employmentInfo.getTitle(), employmentInfo.getContent(),employmentInfo.getDate()};
		super.update(sql, params);
	}


	public EmploymentInfo get(Long id) {
		String sql = "SELECT * FROM employmentInfo WHERE id=?";
		List<EmploymentInfo> list = super.query(sql, new EmploymentInfoResultSetHandler(), id);
		return list.size() == 1 ? list.get(0) : null;
	}

	public List<EmploymentInfo> getAll() {
		String sql = "SELECT * FROM jobinfo";
		return super.query(sql, new EmploymentInfoResultSetHandler());
	}

	private class EmploymentInfoResultSetHandler implements ResultSetHandler<List<EmploymentInfo>> {
		List<EmploymentInfo> list = new ArrayList<EmploymentInfo>();

		public List<EmploymentInfo> handle(ResultSet rs) {
			try {
				while (rs.next()) {
					EmploymentInfo employmentInfo = new EmploymentInfo();
					
					employmentInfo.setTitle(rs.getString("title"));
					employmentInfo.setContent(rs.getString("content"));
					employmentInfo.setDate(rs.getString("date"));
					
					list.add(employmentInfo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
	}

	public Long getDataCount() {
		String sql = "SELECT COUNT(id) FROM employmentinfo";
		return super.query(sql, new ScalarHandler());
	}

}
