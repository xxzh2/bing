/**
 * File Download.<br>
 * download file for given URL or content.<br>
 * @author Asparagus
 */
package net.crawl.dual.bing;

import java.util.List;

import lombok.extern.log4j.Log4j;

@Log4j
public class ImageDownloader extends Downloader {

	final static String[] FILE_TYPE = { ".png", ".jpg" };

	final static String wallpaper = "Bing-wallpaper";

	public ImageDownloader() {
	}

	/**
	 * get relative file path.
	 */
	@Override
	public String getLocalDir(String pic) {

		String basePath = this.getLocalDefault();
		log.debug("getLocalDefault basePath" + basePath);
		if (pic == null) {
			return null;

		} else if (pic.contains("*") || pic.contains(":") || pic.contains("/")) {
			String fileExt = null;
			if (pic.contains(".")) {
				fileExt = pic.substring(pic.lastIndexOf("."));

				if (!this.isAccepted(fileExt))
					fileExt = FILE_TYPE[1];

			} else {
				fileExt = FILE_TYPE[0];
			}
			String fname = pic.substring(pic.lastIndexOf("/"));

			return new StringBuilder(String.valueOf(basePath)).append(fname).toString();

		} else {
			String str[] = pic.split("/");
			String name = str[str.length - 1];

			return new StringBuilder(String.valueOf(basePath)).append(name).toString();
		}
	}

	/**
	 * Get file Name.
	 *
	 * @param pic
	 * @return fileName
	 */
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

	@Override
	public List<String> getFileType() {
		return null;
	}

	/**
	 * Check fileExt can accepted.
	 */
	private boolean isAccepted(String fileExt) {
		boolean isAccepted = false;
		for (String ft : FILE_TYPE) {
			if (ft.equals(fileExt)) {
				isAccepted = true;
			}
		}
		return isAccepted;
	}

	/**
	 * Return the full path.
	 *
	 * @param pic
	 * @return fullPath
	 */
	@Override
	public String getRealPath(String pic) {
		return getLocalDir(pic);
	}
}
