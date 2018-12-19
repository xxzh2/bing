package net.crawl.sum;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.logging.Log;

import net.crawl.utils.LogWrapper;

/**
 * Copy remote File to local Disk.
 *
 * @author Asparagus
 *
 */
public class Localization {
	final static Log log = LogWrapper.getLog(Localization.class);

	public Localization(String path, URL url) {
		destPath = path;
		srcUrl = url;
		initFile();
	}

	public Localization(String path, String url) {
		destPath = path;
		try {
			srcUrl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		initFile();
	}

	public void initFile() {
		if (isValid())
			local = new File(destPath);
		if (local != null && !local.exists()) {
			try {
				LogWrapper._i(log, "%s", local.getPath());
				local.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Localization(File file, URL url) {
		local = file;
		srcUrl = url;
	}

	public boolean localize() {

		if (local == null) {
			log.debug("Local can't null, error!");
			return false;
		}
		if (!local.exists()) {
			LogWrapper._e(log, String.format("Create  parent director: %", local.getParentFile().getPath()));
			local.getParentFile().mkdirs();
		}
		(new Thread() {

			@Override
			public void run() {
				try {
					FileOutputStream fo = new FileOutputStream(local);
					InputStream in = getInputStream();
					for (byte b[] = new byte[1]; in.read(b) > 0; fo.write(b))
						;
					fo.flush();
					fo.close();
					in.close();
					local.createNewFile();
					log.debug((new StringBuilder(String.valueOf(local.getPath()))).append(" \u521B\u5EFA!").toString());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}).run();
		return true;
	}

	protected InputStream getInputStream() throws IOException {
		if (isValid())
			return srcUrl.openStream();
		else
			return null;
	}

	public boolean isValid() {
		if (srcUrl != null) {
			if (destPath != null || destPath.trim().length() != 0)
				return true;
			return local != null;
		} else {
			return false;
		}
	}

	private URL srcUrl;
	private String destPath;
	private File local;

}
