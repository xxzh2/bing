package com.ginkgo.crawl.html;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ginkgo.crawl.sum.FixedList;
import com.ginkgo.crawl.sum.Localization;
import com.ginkgo.crawl.sum.LocalizationHTML;
import com.ginkgo.crawl.sum.document.PrasedDocument;
import com.ginkgo.jspider.HTMLDownloader;

/**
 * News Crawl. 网络爬虫，抓取新闻
 *
 * @author Asparagus
 *
 */
public class NewsCrawl {

	public static final Log log = LogFactory.getLog(NewsCrawl.class);

	public NewsCrawl() {
	}

	/**
	 * Set net resources URL.
	 *
	 * @param url String
	 */
	public void setUrl(String url) {
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			log.error(e);
			this.url = null;
		}
	}

	public URL getUrl() {
		return this.url;
	}

	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public void searchPathByTag(Document doc, String tagName) {
		log.debug(doc.getElementsContainingText(".jpg").html());
	}

	private List<String> getPicByKey(String key) {
		String dateStr0 = new SimpleDateFormat("yyy-MM-dd").format(new Date());
		String dateStr1 = new SimpleDateFormat("yyyMMdd").format(new Date());

		List<String> picPath = new ArrayList<String>();
		PrasedDocument doc = new PrasedDocument(this.url);
		Elements elements = doc.getElementsByTag("a");
		log.debug("found target tag Elements: " + elements.size());
		log.debug("found keywords: " + dateStr0 + ", " + dateStr1 + ", " + key);
		for (Iterator<Element> iterator = elements.iterator(); iterator.hasNext();) {
			Element e = iterator.next();
			log.debug(e.html());
			if (e.html().contains(key) //
					&& !e.html().contains("<img") //
					&& e.outerHtml().contains("target")//
					&& (e.outerHtml().contains("class") || e.outerHtml().contains("title")) //
					&& (e.outerHtml().contains(dateStr0) || e.outerHtml().contains(dateStr1))) {
				String str = e.outerHtml();
				log.debug("found tag: " + str);
				picPath.add(str);
			}
		}

		return picPath;
	}

	/**
	 * Filter keywords
	 *
	 * @return urls FixedList<String>
	 */
	private FixedList<String> getKeyHTML() {
		List<String> list = this.getPicByKey(this.keywords);
		if (list != null) {
			log.info("match key num: " + list.size());
		}
		FixedList<String> urls = new FixedList<String>(10);
		for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
			String s = iterator.next();
			log.debug("jpg file s: " + s);
			try {
				urls.add(s);
			} catch (Exception e) {
				log.error(e);
			}
		}
		return urls;
	}

	/**
	 * write file to local disk.
	 */
	public int download() {
		int fc = 0;
		FixedList<String> holder = this.getKeyHTML();
		for (String text : holder) {
			String txtStr = text.toString();
			log.debug((new StringBuilder("PIC:")).append(txtStr).toString());
			String filePath = new HTMLDownloader().getRealPath(txtStr);
			log.error("filePath: " + filePath);
			Localization _f = new LocalizationHTML(new File(filePath), text);
			_f.localize();
			fc++;
		}
		return fc;
	}

	/**
	 * Net Resources URL.
	 */
	private URL url;

	/**
	 * Crawl filter keywords.
	 */
	private String keywords;
}
