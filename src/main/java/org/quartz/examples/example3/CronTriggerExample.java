/*     */ package org.quartz.examples.example3;
/*     */ 
/*     */ import java.util.Date;
/*     */ import org.quartz.CronScheduleBuilder;
/*     */ import org.quartz.CronTrigger;
/*     */ import org.quartz.JobBuilder;
/*     */ import org.quartz.JobDetail;
/*     */ import org.quartz.Scheduler;
/*     */ import org.quartz.SchedulerFactory;
/*     */ import org.quartz.SchedulerMetaData;
/*     */ import org.quartz.TriggerBuilder;
/*     */ import org.quartz.impl.StdSchedulerFactory;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class CronTriggerExample
/*     */ {
/*     */   public void run()
/*     */     throws Exception
/*     */   {
/*  43 */     Logger log = LoggerFactory.getLogger(CronTriggerExample.class);
/*     */ 
/*  45 */     log.info("------- Initializing -------------------");
/*     */ 
/*  48 */     SchedulerFactory sf = new StdSchedulerFactory();
/*  49 */     Scheduler sched = sf.getScheduler();
/*     */ 
/*  51 */     log.info("------- Initialization Complete --------");
/*     */ 
/*  53 */     log.info("------- Scheduling Jobs ----------------");
/*     */ 
/*  58 */     JobDetail job = JobBuilder.newJob(SimpleJob.class).withIdentity("job1", "group1").build();
/*     */ 
/*  60 */     CronTrigger trigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * * * ?")).build();
/*     */ 
/*  63 */     Date ft = sched.scheduleJob(job, trigger);
/*  64 */     log.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());
/*     */ 
/*  68 */     job = JobBuilder.newJob(SimpleJob.class).withIdentity("job2", "group1").build();
/*     */ 
/*  70 */     trigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("trigger2", "group1").withSchedule(CronScheduleBuilder.cronSchedule("15 0/2 * * * ?")).build();
/*     */ 
/*  72 */     ft = sched.scheduleJob(job, trigger);
/*  73 */     log.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());
/*     */ 
/*  77 */     job = JobBuilder.newJob(SimpleJob.class).withIdentity("job3", "group1").build();
/*     */ 
/*  79 */     trigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("trigger3", "group1").withSchedule(CronScheduleBuilder.cronSchedule("0 0/2 8-17 * * ?")).build();
/*     */ 
/*  81 */     ft = sched.scheduleJob(job, trigger);
/*  82 */     log.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());
/*     */ 
/*  86 */     job = JobBuilder.newJob(SimpleJob.class).withIdentity("job4", "group1").build();
/*     */ 
/*  88 */     trigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("trigger4", "group1").withSchedule(CronScheduleBuilder.cronSchedule("0 0/3 17-23 * * ?")).build();
/*     */ 
/*  90 */     ft = sched.scheduleJob(job, trigger);
/*  91 */     log.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());
/*     */ 
/*  95 */     job = JobBuilder.newJob(SimpleJob.class).withIdentity("job5", "group1").build();
/*     */ 
/*  97 */     trigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("trigger5", "group1").withSchedule(CronScheduleBuilder.cronSchedule("0 0 10am 1,15 * ?")).build();
/*     */ 
/*  99 */     ft = sched.scheduleJob(job, trigger);
/* 100 */     log.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());
/*     */ 
/* 104 */     job = JobBuilder.newJob(SimpleJob.class).withIdentity("job6", "group1").build();
/*     */ 
/* 106 */     trigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("trigger6", "group1").withSchedule(CronScheduleBuilder.cronSchedule("0,30 * * ? * MON-FRI")).build();
/*     */ 
/* 109 */     ft = sched.scheduleJob(job, trigger);
/* 110 */     log.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());
/*     */ 
/* 114 */     job = JobBuilder.newJob(SimpleJob.class).withIdentity("job7", "group1").build();
/*     */ 
/* 116 */     trigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("trigger7", "group1").withSchedule(CronScheduleBuilder.cronSchedule("0,30 * * ? * SAT,SUN")).build();
/*     */ 
/* 119 */     ft = sched.scheduleJob(job, trigger);
/* 120 */     log.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());
/*     */ 
/* 123 */     log.info("------- Starting Scheduler ----------------");
/*     */ 
/* 128 */     sched.start();
/*     */ 
/* 130 */     log.info("------- Started Scheduler -----------------");
/*     */ 
/* 132 */     log.info("------- Waiting five minutes... ------------");
/*     */     try
/*     */     {
/* 135 */       Thread.sleep(300000L);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */     }
/*     */ 
/* 141 */     log.info("------- Shutting Down ---------------------");
/*     */ 
/* 143 */     sched.shutdown(true);
/*     */ 
/* 145 */     log.info("------- Shutdown Complete -----------------");
/*     */ 
/* 147 */     SchedulerMetaData metaData = sched.getMetaData();
/* 148 */     log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */     throws Exception
/*     */   {
/* 154 */     CronTriggerExample example = new CronTriggerExample();
/* 155 */     example.run();
/*     */   }
/*     */ }

/* Location:           D:\Quartz\quartz-2.2.1-distribution\quartz-2.2.1\examples\lib\quartz-examples-2.2.1\
 * Qualified Name:     org.quartz.examples.example3.CronTriggerExample
 * JD-Core Version:    0.6.2
 */