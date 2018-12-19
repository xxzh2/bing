package net.crawl.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LogWrapper {

	public static Log getLog(Class<?> clazz) {
		return LogFactory.getLog(clazz);

	}

	public static void _d(Log log, String format, Object... args) {
		log.debug(String.format(format, args));
	}

	public static void _t(Log log, String format, Object... args) {
		log.trace(String.format(format, args));
	}

	public static void _i(Log log, String format, Object... args) {
		log.info(String.format(format, args));
	}

	public static void _w(Log log, String format, Object... args) {
		log.warn(String.format(format, args));
	}

	public static void _e(Log log, String format, Object... args) {
		log.error(String.format(format, args));
	}

}
