/*     */ package org.quartz.examples.example11;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import org.quartz.DateBuilder;
/*     */ import org.quartz.DateBuilder.IntervalUnit;
/*     */ import org.quartz.JobBuilder;
/*     */ import org.quartz.JobDataMap;
/*     */ import org.quartz.JobDetail;
/*     */ import org.quartz.Scheduler;
/*     */ import org.quartz.SchedulerFactory;
/*     */ import org.quartz.SchedulerMetaData;
/*     */ import org.quartz.Trigger;
/*     */ import org.quartz.TriggerBuilder;
/*     */ import org.quartz.impl.StdSchedulerFactory;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class LoadExample
/*     */ {
/*  41 */   private int _numberOfJobs = 500;
/*     */ 
/*     */   public LoadExample(int inNumberOfJobs) {
/*  44 */     this._numberOfJobs = inNumberOfJobs;
/*     */   }
/*     */ 
/*     */   public void run() throws Exception {
/*  48 */     Logger log = LoggerFactory.getLogger(LoadExample.class);
/*     */ 
/*  51 */     SchedulerFactory sf = new StdSchedulerFactory();
/*  52 */     Scheduler sched = sf.getScheduler();
/*     */ 
/*  54 */     log.info("------- Initialization Complete -----------");
/*     */ 
/*  57 */     for (int count = 1; count <= this._numberOfJobs; count++) {
/*  58 */       JobDetail job = JobBuilder.newJob(SimpleJob.class).withIdentity("job" + count, "group_1").requestRecovery().build();
/*     */ 
/*  68 */       long timeDelay = (long)(Math.random() * 2500.0D);
/*  69 */       job.getJobDataMap().put("delay time", timeDelay);
/*     */ 
/*  71 */       Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger_" + count, "group_1").startAt(DateBuilder.futureDate(10000 + count * 100, DateBuilder.IntervalUnit.MILLISECOND)).build();
/*     */ 
/*  75 */       sched.scheduleJob(job, trigger);
/*  76 */       if (count % 25 == 0) {
/*  77 */         log.info("...scheduled " + count + " jobs");
/*     */       }
/*     */     }
/*     */ 
/*  81 */     log.info("------- Starting Scheduler ----------------");
/*     */ 
/*  84 */     sched.start();
/*     */ 
/*  86 */     log.info("------- Started Scheduler -----------------");
/*     */ 
/*  88 */     log.info("------- Waiting five minutes... -----------");
/*     */     try
/*     */     {
/*  92 */       Thread.sleep(300000L);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */     }
/*     */ 
/*  98 */     log.info("------- Shutting Down ---------------------");
/*  99 */     sched.shutdown(true);
/* 100 */     log.info("------- Shutdown Complete -----------------");
/*     */ 
/* 102 */     SchedulerMetaData metaData = sched.getMetaData();
/* 103 */     log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) throws Exception
/*     */   {
/* 108 */     int numberOfJobs = 500;
/* 109 */     if (args.length == 1) {
/* 110 */       numberOfJobs = Integer.parseInt(args[0]);
/*     */     }
/* 112 */     if (args.length > 1) {
/* 113 */       System.out.println("Usage: java " + LoadExample.class.getName() + "[# of jobs]");
/* 114 */       return;
/*     */     }
/* 116 */     LoadExample example = new LoadExample(numberOfJobs);
/* 117 */     example.run();
/*     */   }
/*     */ }

/* Location:           D:\Quartz\quartz-2.2.1-distribution\quartz-2.2.1\examples\lib\quartz-examples-2.2.1\
 * Qualified Name:     org.quartz.examples.example11.LoadExample
 * JD-Core Version:    0.6.2
 */