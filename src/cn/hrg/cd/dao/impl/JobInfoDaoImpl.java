package cn.hrg.cd.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.hrg.cd.dao.IJobInfoDao;
import cn.hrg.cd.domain.JobInfo;

public class JobInfoDaoImpl extends DbUtilsDaoSupport implements IJobInfoDao {

	public void save(JobInfo jobInfo) {
		String sql = "INSERT INTO jobinfo(title,content,company,city,salaryLevel,type,date) VALUES(?,?,?,?,?,?,?)";
		Object[] params = {jobInfo.getTitle(), jobInfo.getContent(),jobInfo.getCompany(),jobInfo.getCity(),jobInfo.getSalaryLevel(),jobInfo.getType(),jobInfo.getDate()};
		super.update(sql, params);
	}

	public JobInfo get(Long id) {
		String sql = "SELECT * FROM jobinfo WHERE id=?";
		List<JobInfo> list = super.query(sql, new JobInfoResultSetHandler(), id);
		return list.size() == 1 ? list.get(0) : null;
	}

	public List<JobInfo> getAll() {
		String sql = "SELECT * FROM jobinfo";
		return super.query(sql, new JobInfoResultSetHandler());
	}
	
	public Long getDataCount() {
		String sql = "SELECT COUNT(id) FROM jobinfo";
		return super.query(sql, new ScalarHandler());
	}

	private class JobInfoResultSetHandler implements ResultSetHandler<List<JobInfo>> {
		List<JobInfo> list = new ArrayList<JobInfo>();

		public List<JobInfo> handle(ResultSet rs) {
			try {
				while (rs.next()) {
					JobInfo jobInfo = new JobInfo();
					
					jobInfo.setId(rs.getLong("id"));
					jobInfo.setTitle(rs.getString("title"));
					jobInfo.setContent(rs.getString("content"));
					jobInfo.setCompany(rs.getString("company"));
					jobInfo.setCity(rs.getString("city"));
					jobInfo.setSalaryLevel(rs.getString("salaryLevel"));
					jobInfo.setType(rs.getString("type"));
					jobInfo.setDate(rs.getString("date"));
					
					list.add(jobInfo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
	}

}
