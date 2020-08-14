package com.ginkgo.crawl.job;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import hf.wj.dir.Directory;
import net.sf.json.JSONArray;

public class FileJob implements Job {
	final static Log log = LogFactory.getLog(FileJob.class);
	long count = 0;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		log.info("jbos start: " + (++count));
		String path = new File(FileJob.class.getResource("/").getFile()).getParentFile().getParent();
		log.info(path);
		Directory dir = new Directory(path);
		JSONArray ja = null;
		try {
			ja = dir.gen(new String[] { path });
		} catch (UnsupportedEncodingException e) {
			log.error(e);
		}
		log.info(ja);
	}

}
