/*     */ package org.quartz.examples.example8;
/*     */ 
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import org.quartz.DateBuilder;
/*     */ import org.quartz.JobBuilder;
/*     */ import org.quartz.JobDetail;
/*     */ import org.quartz.Scheduler;
/*     */ import org.quartz.SchedulerFactory;
/*     */ import org.quartz.SchedulerMetaData;
/*     */ import org.quartz.SimpleScheduleBuilder;
/*     */ import org.quartz.SimpleTrigger;
/*     */ import org.quartz.TriggerBuilder;
/*     */ import org.quartz.examples.example2.SimpleJob;
/*     */ import org.quartz.impl.StdSchedulerFactory;
/*     */ import org.quartz.impl.calendar.AnnualCalendar;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class CalendarExample
/*     */ {
/*     */   public void run()
/*     */     throws Exception
/*     */   {
/*  47 */     Logger log = LoggerFactory.getLogger(CalendarExample.class);
/*     */ 
/*  49 */     log.info("------- Initializing ----------------------");
/*     */ 
/*  52 */     SchedulerFactory sf = new StdSchedulerFactory();
/*  53 */     Scheduler sched = sf.getScheduler();
/*     */ 
/*  55 */     log.info("------- Initialization Complete -----------");
/*     */ 
/*  57 */     log.info("------- Scheduling Jobs -------------------");
/*     */ 
/*  60 */     AnnualCalendar holidays = new AnnualCalendar();
/*     */ 
/*  63 */     Calendar fourthOfJuly = new GregorianCalendar(2005, 6, 4);
/*  64 */     holidays.setDayExcluded(fourthOfJuly, true);
/*     */ 
/*  66 */     Calendar halloween = new GregorianCalendar(2005, 9, 31);
/*  67 */     holidays.setDayExcluded(halloween, true);
/*     */ 
/*  69 */     Calendar christmas = new GregorianCalendar(2005, 11, 25);
/*  70 */     holidays.setDayExcluded(christmas, true);
/*     */ 
/*  73 */     sched.addCalendar("holidays", holidays, false, false);
/*     */ 
/*  77 */     Date runDate = DateBuilder.dateOf(0, 0, 10, 31, 10);
/*     */ 
/*  79 */     JobDetail job = JobBuilder.newJob(SimpleJob.class).withIdentity("job1", "group1").build();
/*     */ 
/*  81 */     SimpleTrigger trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(runDate).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(1).repeatForever()).modifiedByCalendar("holidays").build();
/*     */ 
/*  85 */     Date firstRunTime = sched.scheduleJob(job, trigger);
/*     */ 
/*  90 */     log.info(job.getKey() + " will run at: " + firstRunTime + " and repeat: " + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000L + " seconds");
/*     */ 
/*  95 */     log.info("------- Starting Scheduler ----------------");
/*  96 */     sched.start();
/*     */ 
/* 100 */     log.info("------- Waiting 30 seconds... --------------");
/*     */     try
/*     */     {
/* 103 */       Thread.sleep(30000L);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */     }
/*     */ 
/* 110 */     log.info("------- Shutting Down ---------------------");
/* 111 */     sched.shutdown(true);
/* 112 */     log.info("------- Shutdown Complete -----------------");
/*     */ 
/* 114 */     SchedulerMetaData metaData = sched.getMetaData();
/* 115 */     log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */     throws Exception
/*     */   {
/* 121 */     CalendarExample example = new CalendarExample();
/* 122 */     example.run();
/*     */   }
/*     */ }

/* Location:           D:\Quartz\quartz-2.2.1-distribution\quartz-2.2.1\examples\lib\quartz-examples-2.2.1\
 * Qualified Name:     org.quartz.examples.example8.CalendarExample
 * JD-Core Version:    0.6.2
 */