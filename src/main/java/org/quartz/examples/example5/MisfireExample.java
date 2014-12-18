/*     */ package org.quartz.examples.example5;
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
/*     */ public class MisfireExample
/*     */ {
/*     */   public void run()
/*     */     throws Exception
/*     */   {
/*  56 */     Logger log = LoggerFactory.getLogger(MisfireExample.class);
/*     */ 
/*  58 */     log.info("------- Initializing -------------------");
/*     */ 
/*  61 */     SchedulerFactory sf = new StdSchedulerFactory();
/*  62 */     Scheduler sched = sf.getScheduler();
/*     */ 
/*  64 */     log.info("------- Initialization Complete -----------");
/*     */ 
/*  66 */     log.info("------- Scheduling Jobs -----------");
/*     */ 
/*  71 */     Date startTime = DateBuilder.nextGivenSecondDate(null, 15);
/*     */ 
/*  75 */     JobDetail job = JobBuilder.newJob(StatefulDumbJob.class).withIdentity("statefulJob1", "group1").usingJobData("ExecutionDelay", Long.valueOf(10000L)).build();
/*     */ 
/*  78 */     SimpleTrigger trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(startTime).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever()).build();
/*     */ 
/*  81 */     Date ft = sched.scheduleJob(job, trigger);
/*  82 */     log.info(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000L + " seconds");
/*     */ 
/*  87 */     job = JobBuilder.newJob(StatefulDumbJob.class).withIdentity("statefulJob2", "group1").usingJobData("ExecutionDelay", Long.valueOf(10000L)).build();
/*     */ 
/*  90 */     trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("trigger2", "group1").startAt(startTime).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever().withMisfireHandlingInstructionNowWithExistingCount()).build();
/*     */ 
/*  97 */     ft = sched.scheduleJob(job, trigger);
/*  98 */     log.info(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000L + " seconds");
/*     */ 
/* 101 */     log.info("------- Starting Scheduler ----------------");
/*     */ 
/* 104 */     sched.start();
/*     */ 
/* 106 */     log.info("------- Started Scheduler -----------------");
/*     */     try
/*     */     {
/* 110 */       Thread.sleep(600000L);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */     }
/* 115 */     log.info("------- Shutting Down ---------------------");
/*     */ 
/* 117 */     sched.shutdown(true);
/*     */ 
/* 119 */     log.info("------- Shutdown Complete -----------------");
/*     */ 
/* 121 */     SchedulerMetaData metaData = sched.getMetaData();
/* 122 */     log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) throws Exception
/*     */   {
/* 127 */     MisfireExample example = new MisfireExample();
/* 128 */     example.run();
/*     */   }
/*     */ }

/* Location:           D:\Quartz\quartz-2.2.1-distribution\quartz-2.2.1\examples\lib\quartz-examples-2.2.1\
 * Qualified Name:     org.quartz.examples.example5.MisfireExample
 * JD-Core Version:    0.6.2
 */