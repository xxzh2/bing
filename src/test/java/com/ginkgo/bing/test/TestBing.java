package com.ginkgo.bing.test;

import org.junit.Before;
import org.junit.Test;

import com.ginkgo.bing.BingPicture;

import lombok.extern.log4j.Log4j;

/**
 * Bing Wallpaper Download Tests
 * 
 * @author Think
 *
 */
@Log4j
public class TestBing {

	BingPicture pic;

	@Before
	public void init() {
		pic = new BingPicture();
	}

	@Test
	public void main() {

		// 设置URL
		pic.setUrl("https://cn.bing.com");
		
		// 搜索并下载
		pic.download();
		log.info("end of main");
		
	}
	

}
