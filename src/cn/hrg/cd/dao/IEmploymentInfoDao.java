package cn.hrg.cd.dao;

import java.util.List;

import cn.hrg.cd.domain.EmploymentInfo;

public interface IEmploymentInfoDao {
	void save(EmploymentInfo employmentInfo);

	EmploymentInfo get(Long id);

	List<EmploymentInfo> getAll();

	Long getDataCount();
}
