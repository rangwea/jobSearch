package cn.hrg.cd.query;

import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;

import cn.hrg.cd.index.utill.LuceneUtil;

/**
 * 搜索的就业信息对象
 */
public class EmploymentInfoQueryObject {
	private String keyWords;
	private Integer currentPage = 1;

	/**
	 * 如何拼装查询对象
	 * @return lucence查询对象
	 */
	public Query createQuery() throws Exception {
		BooleanQuery query = new BooleanQuery();
		if ((keyWords!=null&&!"".equals(keyWords))) {
			QueryParser parser = new QueryParser(LuceneUtil.matchVersion, "title",
					LuceneUtil.analyzer);
			StringBuilder sb = new StringBuilder(keyWords);
			sb.append(" content:(").append(keyWords).append(")");
			Query qo = parser.parse(sb.toString());
			query.add(qo, Occur.MUST);
		}
		return query;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	
	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

}
