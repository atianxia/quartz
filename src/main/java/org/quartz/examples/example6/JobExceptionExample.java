/*     */ package org.quartz.examples.example6;
/*     */ 
/*     */ import java.util.Date;
/*     */ import org.quartz.DateBuilder;
/*     */ import org.quartz.JobBuilder;
/*     */ import org.quartz.JobDetail;
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
/*     */ public class JobExceptionExample
/*     */ {
/*     */   public void run()
/*     */     throws Exception
/*     */   {
/*  44 */     Logger log = LoggerFactory.getLogger(JobExceptionExample.class);
/*     */ 
/*  46 */     log.info("------- Initializing ----------------------");
/*     */ 
/*  49 */     SchedulerFactory sf = new StdSchedulerFactory();
/*  50 */     Scheduler sched = sf.getScheduler();
/*     */ 
/*  52 */     log.info("------- Initialization Complete ------------");
/*     */ 
/*  54 */     log.info("------- Scheduling Jobs -------------------");
/*     */ 
/*  59 */     Date startTime = DateBuilder.nextGivenSecondDate(null, 15);
/*     */ 
/*  64 */     JobDetail job = JobBuilder.newJob(BadJob1.class).withIdentity("badJob1", "group1").usingJobData("denominator", "0").build();
/*     */ 
/*  66 */     SimpleTrigger trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(startTime).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever()).build();
/*     */ 
/*  69 */     Date ft = sched.scheduleJob(job, trigger);
/*  70 */     log.info(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000L + " seconds");
/*     */ 
/*  76 */     job = JobBuilder.newJob(BadJob2.class).withIdentity("badJob2", "group1").build();
/*     */ 
/*  78 */     trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("trigger2", "group1").startAt(startTime).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();
/*     */ 
/*  81 */     ft = sched.scheduleJob(job, trigger);
/*  82 */     log.info(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000L + " seconds");
/*     */ 
/*  85 */     log.info("------- Starting Scheduler ----------------");
/*     */ 
/*  88 */     sched.start();
/*     */ 
/*  90 */     log.info("------- Started Scheduler -----------------");
/*     */     try
/*     */     {
/*  94 */       Thread.sleep(30000L);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */     }
/*  99 */     log.info("------- Shutting Down ---------------------");
/*     */ 
/* 101 */     sched.shutdown(false);
/*     */ 
/* 103 */     log.info("------- Shutdown Complete -----------------");
/*     */ 
/* 105 */     SchedulerMetaData metaData = sched.getMetaData();
/* 106 */     log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) throws Exception
/*     */   {
/* 111 */     JobExceptionExample example = new JobExceptionExample();
/* 112 */     example.run();
/*     */   }
/*     */ }

/* Location:           D:\Quartz\quartz-2.2.1-distribution\quartz-2.2.1\examples\lib\quartz-examples-2.2.1\
 * Qualified Name:     org.quartz.examples.example6.JobExceptionExample
 * JD-Core Version:    0.6.2
 */