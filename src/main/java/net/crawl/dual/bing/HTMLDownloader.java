/**
 * File Download.<br>
 * download file for given URL or content.<br>
 * @author Asparagus
 */
package net.crawl.dual.bing;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import lombok.extern.log4j.Log4j;

/**
 * Download for HTML files.
 *
 * @author Asparagus 2016-08-27
 *
 */
@Log4j
public class HTMLDownloader extends Downloader {

	/**
	 * Accepted File Extend.
	 */
	public final static String[] fileType = { ".htm", ".html" };

	/**
	 * Default Constructor
	 */
	public HTMLDownloader() {
	}

	/**
	 * Get file path for new file with extend.
	 *
	 * @return filePath
	 */
	@Override
	public String getLocalDir(String pic) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		String dateStr = sdf.format(new Date());
		// log.debug(path);

		final String SEPARATOR = System.getProperty("file.separator");

		String filePath = (new StringBuilder()).append(SEPARATOR)//
				.append("html-archive").append(SEPARATOR).toString();

		// current file name or path is not valid. then given the specified
		// name.
		if (pic.contains("*") || pic.contains(":") || pic.contains("/")) {
			String name = dateStr + ".html";
			filePath = (new StringBuilder(String.valueOf(filePath)))//
					.append(dateStr).append(SEPARATOR).toString();
			return (new StringBuilder(String.valueOf(filePath))).append(name).toString();

		} else {
			String name = "unnamed";
			if (pic != null && pic.indexOf("/") > 0) {
				String str[] = pic.split("/");
				name = str[str.length - 1];
			} else if (pic != null && pic.indexOf(".") < 0) {
				name += ".html";
			}

			filePath = (new StringBuilder(String.valueOf(filePath))).append(dateStr).append(SEPARATOR).toString();

			return (new StringBuilder(String.valueOf(filePath))).append(name).toString();
		}
	}

	public String getLocalFileName(String pic) {
		// current file name or path is not valid. then given the specified
		// name.
		if (pic == null) {
			return null;
		} else if (pic.contains("*") || pic.contains(":") || pic.contains("/")) {
			int lastIndex = pic.lastIndexOf(".");
			String name = pic.substring(lastIndex);
			return (new StringBuilder(String.valueOf(name))).toString();

		} else {
			return (new StringBuilder(String.valueOf(pic))).toString();
		}
	}

	/**
	 * Local Context Base Path.
	 *
	 * @return basePath of WEB-INF
	 */
	public String getLocalBase() {
		String path = HTMLDownloader.class.getResource("/").getPath();
		if (path != null && path.indexOf("WEB-INF") > 0) {
			path = path.substring(0, path.indexOf("WEB-INF"));
		}
		return path;
	}

	@Override
	public List<String> getFileType() {
		return null;
	}

	/**
	 * Return the full path.
	 *
	 * @param pic
	 * @return fullPath
	 */
	@Override
	public String getRealPath(String pic) {
		final String SEPARATOR = System.getProperty("file.separator");
		String basePath = this.getLocalBase();
		if (basePath != null && basePath.endsWith(SEPARATOR)) {
			basePath = basePath.substring(0, basePath.lastIndexOf(SEPARATOR));
		}
		String path = null;
		try {
			new URLDecoder();
			path = URLDecoder.decode(basePath + this.getLocalDir(pic), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		log.warn("URLDecode: " + path);
		return path;
	}
}
