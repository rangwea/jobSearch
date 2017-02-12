package cn.hrg.cd.index;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 */
public class EmploymentInfoProcessor  implements PageProcessor {
	
    public static final String URL_LIST = "http://www.qncye.com/ruhe/jingyan/list_\\w+_\\w+.html";
    
    public static final String URL_POST = "http://www.qncye.com/ruhe/jingyan/\\d+.html";

    private Site site = Site
            .me()
            .setDomain("http://www.qncye.com/")
            .setSleepTime(1000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    public void process(Page page) {
        //列表页
        if (page.getUrl().regex(URL_LIST).match()) {
            page.addTargetRequests(page.getHtml().xpath("//div[@class=\"news_list\"]").links().regex(URL_POST).all());
            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
            //详细页
        } else {
        	 page.putField("title", page.getHtml().xpath("//h1[@class='article-heading']/html()"));
             page.putField("date", page.getHtml().xpath("//div[@class='times']/span[@class='article-published']/html()"));
             page.putField("content", page.getHtml().xpath("//div[@class='content cont-detail fs-small']/html()"));
        }
    }

    public Site getSite() {
        return site;
    }
    
}