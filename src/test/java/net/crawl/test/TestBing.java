package net.crawl.test;

import org.junit.Test;

import lombok.extern.log4j.Log4j;
import net.crawl.dual.bing.BingPicture;

@Log4j
public class TestBing {

	@Test
	public void main() {
		BingPicture pic = new BingPicture();
		// 设置URL
		pic.setUrl("https://cn.bing.com");
		// 搜索并下载
		pic.download();
	}

}
