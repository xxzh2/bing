package com.ginkgo.crawl.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.ginkgo.crawl.html.NewsCrawl;

import lombok.extern.log4j.Log4j;

@Log4j
public class TestNews {

	public static void main(String[] args) {

		Properties prop = new Properties();
		InputStream ins = TestNews.class.getResourceAsStream("/keywords.properties");
		try {
			if (ins != null)
				prop.load(ins);
			else
				log.warn("keywords.properties not exists.");
		} catch (IOException e) {
			e.printStackTrace();
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
