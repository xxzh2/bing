package com.ginkgo.crawl.html;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class NewsCrawlTest {

	@Test
	public void test() {
		Properties prop = new Properties();
		InputStream ins = NewsCrawlTest.class.getResourceAsStream("/keywords.properties");
		try {
			if (ins != null)
				prop.load(ins);
			else
				log.warn("keywords.properties not exists.");
		} catch (IOException e) {
			log.error(e);
		}
		String key = prop.getProperty("key", "");
		String url = prop.getProperty("crawl_url", "");
		log.info(key);
		log.info(url);

		NewsCrawl pic = new NewsCrawl();
		// 设置URL
		pic.setUrl(url);
		pic.setKeywords(key);
		// 搜索并下载
		int count = pic.download();

		log.info(String.format("%d", count));
	}

}
