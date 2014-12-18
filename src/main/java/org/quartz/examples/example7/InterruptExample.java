/*     */ package org.quartz.examples.example7;
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
/*     */ public class InterruptExample
/*     */ {
/*     */   public void run()
/*     */     throws Exception
/*     */   {
/*  56 */     Logger log = LoggerFactory.getLogger(InterruptExample.class);
/*     */ 
/*  58 */     log.info("------- Initializing ----------------------");
/*     */ 
/*  61 */     SchedulerFactory sf = new StdSchedulerFactory();
/*  62 */     Scheduler sched = sf.getScheduler();
/*     */ 
/*  64 */     log.info("------- Initialization Complete -----------");
/*     */ 
/*  66 */     log.info("------- Scheduling Jobs -------------------");
/*     */ 
/*  69 */     Date startTime = DateBuilder.nextGivenSecondDate(null, 15);
/*     */ 
/*  71 */     JobDetail job = JobBuilder.newJob(DumbInterruptableJob.class).withIdentity("interruptableJob1", "group1").build();
/*     */ 
/*  73 */     SimpleTrigger trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(startTime).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();
/*     */ 
/*  76 */     Date ft = sched.scheduleJob(job, trigger);
/*  77 */     log.info(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000L + " seconds");
/*     */ 
/*  82 */     sched.start();
/*  83 */     log.info("------- Started Scheduler -----------------");
/*     */ 
/*  85 */     log.info("------- Starting loop to interrupt job every 7 seconds ----------");
/*  86 */     for (int i = 0; i < 50; i++) {
/*     */       try {
/*  88 */         Thread.sleep(7000L);
/*     */ 
/*  90 */         sched.interrupt(job.getKey());
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/*     */       }
/*     */     }
/*  96 */     log.info("------- Shutting Down ---------------------");
/*     */ 
/*  98 */     sched.shutdown(true);
/*     */ 
/* 100 */     log.info("------- Shutdown Complete -----------------");
/* 101 */     SchedulerMetaData metaData = sched.getMetaData();
/* 102 */     log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */     throws Exception
/*     */   {
/* 108 */     InterruptExample example = new InterruptExample();
/* 109 */     example.run();
/*     */   }
/*     */ }

/* Location:           D:\Quartz\quartz-2.2.1-distribution\quartz-2.2.1\examples\lib\quartz-examples-2.2.1\
 * Qualified Name:     org.quartz.examples.example7.InterruptExample
 * JD-Core Version:    0.6.2
 */