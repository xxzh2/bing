package net.crawl.dual.bing;

import java.io.IOException;
import java.util.Properties;

public abstract class Downloader implements Duplicate {
	/**
	 * Get default base path.
	 *
	 * @return basePath
	 */
	public String getLocalDefault() {
		String downloaderDefault = null;
		Properties prop = null;
		try {
			prop = load();
			downloaderDefault = prop.getProperty("downloader.default");
			// System.out.println("Default: " + downloaderDefault);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 自定义目录
		return downloaderDefault == null ? System.getProperty("user.home") : downloaderDefault;
	}

	private Properties load() throws IOException {
		Properties prop = new Properties();
		prop.load(this.getClass().getResourceAsStream("/conf.properties"));
		return prop;
	}
}
