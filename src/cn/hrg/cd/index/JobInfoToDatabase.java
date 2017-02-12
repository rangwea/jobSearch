package cn.hrg.cd.index;

import java.util.Map;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import cn.hrg.cd.dao.IJobInfoDao;
import cn.hrg.cd.dao.impl.JobInfoDaoImpl;
import cn.hrg.cd.domain.JobInfo;
/**
 * 
 *将爬取的工作信息放入数据库
 */
public class JobInfoToDatabase implements Pipeline{
	private IJobInfoDao dao=new JobInfoDaoImpl();
	
	public void process(ResultItems resultItems, Task task) {
		JobInfo jobInfo=new JobInfo();
		for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
			
            if("title".equals(entry.getKey())){
            	jobInfo.setTitle(entry.getValue().toString());
            }
            if("content".equals(entry.getKey())){
            	jobInfo.setContent(entry.getValue().toString());
            }
            if("company".equals(entry.getKey())){
            	jobInfo.setCompany(entry.getValue().toString());
            }
            if("salaryLevel".equals(entry.getKey())){
            	jobInfo.setSalaryLevel(entry.getValue().toString());
            }
            if("city".equals(entry.getKey())){
            	jobInfo.setCity(entry.getValue().toString());
            }
            if("type".equals(entry.getKey())){
            	jobInfo.setType(entry.getValue().toString());
            }
            if("date".equals(entry.getKey())){
            	jobInfo.setDate(entry.getValue().toString());
            }
            
        }
		if(jobInfo.getTitle()!=null&&!"".equals(jobInfo.getTitle())){
			dao.save(jobInfo);
		}
		System.out.println("添加数据:"+jobInfo.getTitle());
	}
	
}
