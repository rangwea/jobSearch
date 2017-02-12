package cn.hrg.cd.index.utill;

import java.io.File;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

import cn.hrg.cd.util.Global;
/*
 * Lucene工具类
 */
public class LuceneUtil {
	//版本号，在一些一些对象中需要用到：比如获取词法分析器
	public static Version matchVersion = Version.LUCENE_47;
	//词法分析器：此处使用的第三方实现的中文词法分析器
	public static Analyzer analyzer = new SmartChineseAnalyzer(matchVersion);
	//索引写入器
	private static IndexWriter writer = null;
	
	///WEB-INF/index/jobApply
	//获取索引存储的位置
	private static String getIndexDirParh(Class<?> clzz){
		StringBuilder sb = new StringBuilder(Global.webAppPath);
		sb.append("/WEB-INF/index/").append(clzz.getSimpleName());
		return sb.toString();
	}
	
	//Lucene对象，用来描述索引的位置的
	public static Directory getIndexDir(Class<?> clzz) throws Exception{
		String dirPath = getIndexDirParh(clzz);
		//lucene提供了多个目录类，此处选用了简单文件基于文件系统的对象，需要file对象作为参数
		Directory dir = new SimpleFSDirectory(new File(dirPath));
		return dir;
	}
	public static IndexWriter openIndexWriter(Class<?> clzz,boolean create){
		try {
			Directory dir = getIndexDir(clzz);
			IndexWriterConfig config = new IndexWriterConfig(matchVersion, analyzer);
			synchronized (LuceneUtil.class) {
				writer = new IndexWriter(dir, config);
			}
			System.out.println(dir);
			return writer;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static IndexSearcher openIndexSearcher(Class<?> clzz){
		try {
			Directory dir = getIndexDir(clzz);
			System.out.println(dir);
			DirectoryReader directoryReader = DirectoryReader.open(dir);
			IndexSearcher searcher = new IndexSearcher(directoryReader);
			return searcher;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
