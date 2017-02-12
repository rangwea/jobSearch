package cn.hrg.cd.index;

import java.util.Map;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import cn.hrg.cd.dao.IEmploymentInfoDao;
import cn.hrg.cd.dao.impl.EmploymentInfoDaoImpl;
import cn.hrg.cd.domain.EmploymentInfo;
/**
 * 
 *将爬取的工作信息放入数据库
 */
public class EmploymentInfoToDatabase implements Pipeline{
	private IEmploymentInfoDao dao=new EmploymentInfoDaoImpl();
	
	public void process(ResultItems resultItems, Task task) {
		EmploymentInfo employmentInfo=new EmploymentInfo();
		for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
			
            if("title".equals(entry.getKey())){
            	employmentInfo.setTitle(entry.getValue().toString());
            }
            if("content".equals(entry.getKey())){
            	employmentInfo.setContent(entry.getValue().toString());
            }
            if("date".equals(entry.getKey())){
            	employmentInfo.setDate(entry.getValue().toString());
            }
            
        }
		if(employmentInfo.getTitle()!=null&&!"".equals(employmentInfo.getTitle())){
			dao.save(employmentInfo);
		}
		System.out.println("添加就业信息数据:"+employmentInfo.getTitle());
	}
	
}
