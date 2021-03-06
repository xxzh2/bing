package com.ginkgo.crawl.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ginkgo.crawl.html.NewsCrawl;
import com.ginkgo.jspider.BingPicture;

public class CrawlJob implements Job {
	static final Log log = LogFactory.getLog(CrawlJob.class);
	long count = 0;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		log.info("jbos start: " + (++count));

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
