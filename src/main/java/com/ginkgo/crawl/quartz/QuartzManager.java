package com.ginkgo.crawl.quartz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Quartz Test.
 *
 * @author Asparagus
 * {@link quartz}
 *
 */
public class QuartzManager {
    /**
     * Default Logger.
     */
    static final Log log = LogFactory.getLog(QuartzManager.class);

    /**
     * start standard scheduler.
     */
    public static void start() {
        try {
            // // Grab the Scheduler instance from the Factory
            // Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            //
            // // and start it off
            // scheduler.start();
            //
            // scheduler.shutdown();
            log.info("\n\n\n quartz scheduler start");
            System.getProperties().put("org.quartz.properties", "quartz-conf.properties");
            StdSchedulerFactory.getDefaultScheduler().start();
            log.info("quartz scheduler end. \n\n\n");
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

    /**
     * isStarted standard scheduler.
     */
    public static boolean isStarted() {
        try {
            Scheduler stdScheduler = StdSchedulerFactory.getDefaultScheduler();
            return stdScheduler.isStarted() ;
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
        return false;
    }

    /**
     * shutdown standard scheduler.
     */
    public static void shutdown() {
        try {
            // // Grab the Scheduler instance from the Factory
            // Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            //
            // // and start it off
            // scheduler.start();
            //
            // scheduler.shutdown();
            Scheduler stdScheduler = StdSchedulerFactory.getDefaultScheduler();
            if (stdScheduler.isShutdown() == false) {
                stdScheduler.shutdown();
                log.info("quartz scheduler shutdown. \n\n\n");
            } else {
                log.info("quartz std scheduler allready shutdown. \n\n\n");
            }
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}
