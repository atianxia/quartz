/*    */ package org.quartz.examples.example12;
/*    */ 
/*    */ import org.quartz.CronScheduleBuilder;
/*    */ import org.quartz.JobBuilder;
/*    */ import org.quartz.JobDataMap;
/*    */ import org.quartz.JobDetail;
/*    */ import org.quartz.Scheduler;
/*    */ import org.quartz.SchedulerFactory;
/*    */ import org.quartz.Trigger;
/*    */ import org.quartz.TriggerBuilder;
/*    */ import org.quartz.impl.StdSchedulerFactory;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class RemoteClientExample
/*    */ {
/*    */   public void run()
/*    */     throws Exception
/*    */   {
/* 46 */     Logger log = LoggerFactory.getLogger(RemoteClientExample.class);
/*    */ 
/* 49 */     SchedulerFactory sf = new StdSchedulerFactory();
/* 50 */     Scheduler sched = sf.getScheduler();
/*    */ 
/* 53 */     JobDetail job = JobBuilder.newJob(SimpleJob.class).withIdentity("remotelyAddedJob", "default").build();
/*    */ 
/* 57 */     JobDataMap map = job.getJobDataMap();
/* 58 */     map.put("msg", "Your remotely added job has executed!");
/*    */ 
/* 60 */     Trigger trigger = TriggerBuilder.newTrigger().withIdentity("remotelyAddedTrigger", "default").forJob(job.getKey()).withSchedule(CronScheduleBuilder.cronSchedule("/5 * * ? * *")).build();
/*    */ 
/* 67 */     sched.scheduleJob(job, trigger);
/*    */ 
/* 69 */     log.info("Remote job scheduled.");
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) throws Exception
/*    */   {
/* 74 */     RemoteClientExample example = new RemoteClientExample();
/* 75 */     example.run();
/*    */   }
/*    */ }

/* Location:           D:\Quartz\quartz-2.2.1-distribution\quartz-2.2.1\examples\lib\quartz-examples-2.2.1\
 * Qualified Name:     org.quartz.examples.example12.RemoteClientExample
 * JD-Core Version:    0.6.2
 */