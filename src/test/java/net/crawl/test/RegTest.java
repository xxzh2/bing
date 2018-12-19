package net.crawl.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class RegTest {

	@Test
	public void main() {
		String str = "t)})();g_img={url: \"http://s.cn.bing.net/az/hprichbg/rb/NazcaLines_ZH-CN10481196093_1920x1080.jpg\"";
		log.info(str);
		String ragex = "[^\";]*";
		Pattern p = Pattern.compile(ragex);
		Matcher m = p.matcher(str);
		while (m.find()) {
			String s = m.group();
			if (s.length() > 0)
				log.debug("-->" + s + "@");
		}
	}

}
