package cn.hrg.cd.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;

import cn.hrg.cd.dao.IJobInfoDao;
import cn.hrg.cd.dao.impl.JobInfoDaoImpl;
import cn.hrg.cd.domain.JobInfo;
import cn.hrg.cd.index.utill.LuceneUtil;
import cn.hrg.cd.query.JobInfoQueryObject;
import cn.hrg.cd.util.PageList;

public class JobInfoIndexHelper {

	private IJobInfoDao dao=new JobInfoDaoImpl();

	private Document obj2doc(JobInfo obj) {
		Document doc = new Document();//
		
		Long id=obj.getId();
		String title = obj.getTitle();
		String content = obj.getContent();
		String city=obj.getCity();
		String company=obj.getCompany();
		String salaryLevel=obj.getSalaryLevel();
		String type=obj.getType();
		String date=obj.getDate();
		
		if(id!=null){
			doc.add(new TextField("id", id.toString(), Store.YES));
		}
		if(title!=null&&!"".equals(title)){
			doc.add(new TextField("title", title, Store.YES));
		}
		if(content!=null&&!"".equals(content)){
			doc.add(new TextField("content", content, Store.YES));
			int len = Math.min(100, content.length());
			FieldType fieldType = new FieldType();
			fieldType.setStored(true);
			doc.add(new Field("contentSmall", content.substring(0, len), fieldType));
		}
		if(city!=null&&!"".equals(city)){
			doc.add(new TextField("city", obj.getCity(), Store.YES));
		}
		if(company!=null&&!"".equals(company)){
			doc.add(new TextField("company", city, Store.YES));
		}
		if(salaryLevel!=null&&!"".equals(salaryLevel)){
			doc.add(new TextField("salaryLevel", salaryLevel, Store.YES));
		}
		if(type!=null&&!"".equals(type)){
			doc.add(new TextField("type", type, Store.YES));
		}
		if(date!=null&&!"".equals(date)){
			doc.add(new TextField("date", date, Store.YES));
		}

		return doc;
	}

	/**
	 * 重建索引
	 * 
	 * 	得到所有的职位信息,设置到索引中去
	 */
	public void reCreateIndex() {
		try {
			IndexWriter writer = LuceneUtil.openIndexWriter(JobInfo.class, true);
			writer.deleteAll();
			writer.commit();
			List<JobInfo> list = dao.getAll();
			//System.out.println(list);
			for (JobInfo ja : list) {
				Document doc = this.obj2doc(ja);
				writer.addDocument(doc);
			}
			writer.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 索引搜索
	 * @param qo
	 */
	public PageList search(JobInfoQueryObject qo) {
		PageList pList = new PageList();
		List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();
		IndexSearcher searcher = LuceneUtil.openIndexSearcher(JobInfo.class);
		try {
			Query query = qo.createQuery();// 把拼装查询对象的方法交给QueryObject来做
			System.out.println(query);
			Integer currentPage = qo.getCurrentPage();//当前页
			
			int begin = (currentPage-1)*pList.getPageSize();//开始
			int end = currentPage * pList.getPageSize();//结束
			
			//一共26数据,而end是30
			TopDocs tds = searcher.search(query, end);
			end = Math.min(end, tds.totalHits);
			Highlighter hl = this.getHL(query);
		
			ScoreDoc[] sds = tds.scoreDocs;
			for (int i = begin; i < end; i++) {
				int docId = sds[i].doc;
				Document doc = searcher.doc(docId);

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", doc.get("id"));
				map.put("title", this.setColor(hl, doc, "title"));
				map.put("content", this.setColor(hl, doc, "content"));
				map.put("company", doc.get("company"));
				map.put("city", this.setColor(hl, doc, "city"));
				map.put("type", this.setColor(hl, doc, "type"));
				map.put("date", doc.get("date"));

				ret.add(map);
			}
			pList.setListData(ret);//设置数据
			pList.setTotalCount( tds.totalHits);
			pList.setCurrentPage(currentPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pList;
	}

	/*
	 * 给字段设置高亮
	 */
	private String setColor(Highlighter hl, Document doc, String fieldName) throws Exception {
		String data = hl.getBestFragment(LuceneUtil.analyzer, fieldName, doc.get(fieldName));
		return (data!=null&&!"".equals(data)) ? data : doc.get(fieldName);
	}

	/**
	 * 得到高亮组件对象
	 * @param query
	 * @return
	 */
	private Highlighter getHL(Query query) {
		// 高亮 <span style="background-color:red;">java</span>
		String preTag = "<span style=color:red;>";
		String postTag = "</span>";
		Formatter formatter = new SimpleHTMLFormatter(preTag, postTag);// 设置高亮的格式的
		Scorer scorer = new QueryScorer(query);// 对哪些高亮
		Highlighter hl = new Highlighter(formatter, scorer);
		return hl;
	}

}
