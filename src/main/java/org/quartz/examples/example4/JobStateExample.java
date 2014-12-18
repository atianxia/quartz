/*     */ package org.quartz.examples.example4;
/*     */ 
/*     */ import java.util.Date;
/*     */ import org.quartz.DateBuilder;
/*     */ import org.quartz.JobBuilder;
/*     */ import org.quartz.JobDataMap;
/*     */ import org.quartz.JobDetail;
/*     */ import org.quartz.JobKey;
/*     */ import org.quartz.Scheduler;
/*     */ import org.quartz.SchedulerFactory;
/*     */ import org.quartz.SchedulerMetaData;
/*     */ import org.quartz.SimpleScheduleBuilder;
/*     */ import org.quartz.SimpleTrigger;
/*     */ import org.quartz.TriggerBuilder;
/*     */ import org.quartz.impl.StdSchedulerFactory;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class JobStateExample
/*     */ {
/*     */   public void run()
/*     */     throws Exception
/*     */   {
/*  44 */     Logger log = LoggerFactory.getLogger(JobStateExample.class);
/*     */ 
/*  46 */     log.info("------- Initializing -------------------");
/*     */ 
/*  49 */     SchedulerFactory sf = new StdSchedulerFactory();
/*  50 */     Scheduler sched = sf.getScheduler();
/*     */ 
/*  52 */     log.info("------- Initialization Complete --------");
/*     */ 
/*  54 */     log.info("------- Scheduling Jobs ----------------");
/*     */ 
/*  57 */     Date startTime = DateBuilder.nextGivenSecondDate(null, 10);
/*     */ 
/*  60 */     JobDetail job1 = JobBuilder.newJob(ColorJob.class).withIdentity("job1", "group1").build();
/*     */ 
/*  62 */     SimpleTrigger trigger1 = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(startTime).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).withRepeatCount(4)).build();
/*     */ 
/*  66 */     job1.getJobDataMap().put("favorite color", "Green");
/*  67 */     job1.getJobDataMap().put("count", 1);
/*     */ 
/*  70 */     Date scheduleTime1 = sched.scheduleJob(job1, trigger1);
/*  71 */     log.info(job1.getKey() + " will run at: " + scheduleTime1 + " and repeat: " + trigger1.getRepeatCount() + " times, every " + trigger1.getRepeatInterval() / 1000L + " seconds");
/*     */ 
/*  75 */     JobDetail job2 = JobBuilder.newJob(ColorJob.class).withIdentity("job2", "group1").build();
/*     */ 
/*  77 */     SimpleTrigger trigger2 = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("trigger2", "group1").startAt(startTime).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).withRepeatCount(4)).build();
/*     */ 
/*  82 */     job2.getJobDataMap().put("favorite color", "Red");
/*  83 */     job2.getJobDataMap().put("count", 1);
/*     */ 
/*  86 */     Date scheduleTime2 = sched.scheduleJob(job2, trigger2);
/*  87 */     log.info(job2.getKey().toString() + " will run at: " + scheduleTime2 + " and repeat: " + trigger2.getRepeatCount() + " times, every " + trigger2.getRepeatInterval() / 1000L + " seconds");
/*     */ 
/*  90 */     log.info("------- Starting Scheduler ----------------");
/*     */ 
/*  94 */     sched.start();
/*     */ 
/*  96 */     log.info("------- Started Scheduler -----------------");
/*     */ 
/*  98 */     log.info("------- Waiting 60 seconds... -------------");
/*     */     try
/*     */     {
/* 101 */       Thread.sleep(60000L);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */     }
/*     */ 
/* 107 */     log.info("------- Shutting Down ---------------------");
/*     */ 
/* 109 */     sched.shutdown(true);
/*     */ 
/* 111 */     log.info("------- Shutdown Complete -----------------");
/*     */ 
/* 113 */     SchedulerMetaData metaData = sched.getMetaData();
/* 114 */     log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */     throws Exception
/*     */   {
/* 120 */     JobStateExample example = new JobStateExample();
/* 121 */     example.run();
/*     */   }
/*     */ }

/* Location:           D:\Quartz\quartz-2.2.1-distribution\quartz-2.2.1\examples\lib\quartz-examples-2.2.1\
 * Qualified Name:     org.quartz.examples.example4.JobStateExample
 * JD-Core Version:    0.6.2
 */