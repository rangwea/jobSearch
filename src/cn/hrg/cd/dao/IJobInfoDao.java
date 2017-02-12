package cn.hrg.cd.dao;

import java.util.List;

import cn.hrg.cd.domain.JobInfo;

public interface IJobInfoDao {
	void save(JobInfo jobInfo);

	JobInfo get(Long id);

	List<JobInfo> getAll();
	
	Long getDataCount();
}
