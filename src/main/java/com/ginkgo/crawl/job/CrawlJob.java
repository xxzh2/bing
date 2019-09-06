package com.ginkgo.crawl.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ginkgo.bing.BingPicture;
import com.ginkgo.crawl.html.NewsCrawl;

public class CrawlJob implements Job{
    static Log log = LogFactory.getLog(CrawlJob.class);
    static long count = 0;
    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        // TODO Auto-generated method stub
        log.info("jbos start: " + (++count) );

        //		System.setProperty("user.download","C:\\Users\\leovo\\Desktop\\新建文件夹\\");
        NewsCrawl nc = new NewsCrawl();
        // 设置URL
        nc.setUrl("http://sports.sina.com.cn/csl/");
        nc.setKeywords("恒大");
        // 搜索并下载
        nc.download();


        BingPicture pic = new BingPicture();
        // 设置URL
        pic.setUrl("http://cn.bing.com");
        // 搜索并下载图片
        pic.download();
    }

}
