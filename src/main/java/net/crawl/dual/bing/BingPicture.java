package net.crawl.dual.bing;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import net.crawl.sum.Localization;
import net.crawl.sum.document.PrasedDocument;

/**
 * Download Bing Background Image.
 *
 * @author Asparagus 2016-08-26
 *
 */
public class BingPicture {

	public static Log log = LogFactory.getLog(BingPicture.class);

	public BingPicture() {
	}

	public void setUrl(String arg0) {
		try {
			this.url = new URL(arg0);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
			this.url = null;
		}
	}

	public URL getUrl() {
		return this.url;
	}

	public void searchPathByTag(Document doc, String tagName) {
		log.debug(doc.getElementsContainingText(".jpg").html());
		log.debug("-->");
	}

	private List<String> getPicByKey(String key) {
		List<String> picPath = new ArrayList<String>();
		PrasedDocument doc = new PrasedDocument(this.url);
		Elements elements = doc.getElementsByTag("script");
		for (Iterator<?> iterator = elements.iterator(); iterator.hasNext();) {
			Element e = (Element) iterator.next();
			if (e.html().contains(key)) {
				String str = e.html();
				// log.debug("sss: " + str);
				String st[] = str.split(",");
				String as[];
				int j = (as = st).length;
				for (int i = 0; i < j; i++) {
					String s = as[i];
					if (s.contains(key)) {

						final String ragex = "[^\"';]*/[^\"';]*";
						Pattern p = Pattern.compile(ragex);
						Matcher m = p.matcher(s);
						while (m.find()) {
							String _s = m.group();
							// log.debug(_s);
							if (_s.contains(key)) {
								picPath.add(_s.replace("\\", ""));
							}
						}
					}
				}
			}
		}
		return picPath;
	}

	private URL[] getPicRealPath() {
		List<?> list = this.getPicByKey(".jpg");
		if (list != null) {
			log.info("jpg file num: " + list.size());
		}
		URL urls[] = new URL[list.size()];
		int i = 0;
		for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
			String s = (String) iterator.next();
			log.debug("jpg file s: " + s);
			try {
				if (!s.contains("http:"))
					s = (new StringBuilder("http://cn.bing.com")).append(s).toString();
				urls[i] = new URL(s);
				i++;
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		return urls;
	}

	public void download() {
		URL urls[] = this.getPicRealPath();
		URL aurl[];
		int j = (aurl = urls).length;
		for (int i = 0; i < j; i++) {
			URL url = aurl[i];
			String pic = url.toString();
			log.debug(String.format("Image URL: %s", pic));
			String filePath = new ImageDownloader().getRealPath(pic);

			// log.debug(filePath);
			Localization fc = new Localization(filePath, url);
			fc.localize();
		}
	}

	private URL url;
}
