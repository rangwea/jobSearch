package cn.hrg.cd.index;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 */
public class JobInfoProcessor  implements PageProcessor {
	
    public static final String URL_LIST = "http://sou\\.zhaopin\\.com/jobs/searchresult\\.ashx\\?jl=";

    public static final String URL_POST = "http://jobs.zhaopin.com/\\w+\\.htm";

    private Site site = Site
            .me()
            .setDomain("http://sou.zhaopin.com/")
            .setSleepTime(1000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    public void process(Page page) {
        //列表页
        if (page.getUrl().regex(URL_LIST).match()) {
            page.addTargetRequests(page.getHtml().xpath("//td[@class=\"zwmc\"]").links().regex(URL_POST).all());
            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
            //详细页
        } else {
        	 page.putField("title", page.getHtml().xpath("//div[@class='inner-left fl']/h1/html()"));
             page.putField("content", page.getHtml().xpath("//div[@class='tab-inner-cont'][1]"));
             page.putField("company", page.getHtml().xpath("//div[@class='inner-left fl']/h2/a/html()"));
             page.putField("city", page.getHtml().xpath("//ul[@class='terminal-ul clearfix'][1]/li[2]/strong/a/html()"));
             page.putField("salaryLevel", page.getHtml().xpath("//ul[@class='terminal-ul clearfix'][1]/li[1]/strong/html()"));
             page.putField("type", page.getHtml().xpath("//ul[@class='terminal-ul clearfix'][1]/li[8]/strong/a[2]/html()"));
             page.putField("date", page.getHtml().xpath("//ul[@class='terminal-ul clearfix'][1]/li[3]/strong/span/html()"));
        }
    }

    public Site getSite() {
        return site;
    }
    
}