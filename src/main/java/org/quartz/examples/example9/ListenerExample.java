/*    */ package org.quartz.examples.example9;
/*    */ 
/*    */ import org.quartz.JobBuilder;
/*    */ import org.quartz.JobDetail;
/*    */ import org.quartz.JobListener;
/*    */ import org.quartz.ListenerManager;
/*    */ import org.quartz.Matcher;
/*    */ import org.quartz.Scheduler;
/*    */ import org.quartz.SchedulerFactory;
/*    */ import org.quartz.SchedulerMetaData;
/*    */ import org.quartz.Trigger;
/*    */ import org.quartz.TriggerBuilder;
/*    */ import org.quartz.impl.StdSchedulerFactory;
/*    */ import org.quartz.impl.matchers.KeyMatcher;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class ListenerExample
/*    */ {
/*    */   public void run()
/*    */     throws Exception
/*    */   {
/* 43 */     Logger log = LoggerFactory.getLogger(ListenerExample.class);
/*    */ 
/* 45 */     log.info("------- Initializing ----------------------");
/*    */ 
/* 48 */     SchedulerFactory sf = new StdSchedulerFactory();
/* 49 */     Scheduler sched = sf.getScheduler();
/*    */ 
/* 51 */     log.info("------- Initialization Complete -----------");
/*    */ 
/* 53 */     log.info("------- Scheduling Jobs -------------------");
/*    */ 
/* 57 */     JobDetail job = JobBuilder.newJob(SimpleJob1.class).withIdentity("job1").build();
/*    */ 
/* 59 */     Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1").startNow().build();
/*    */ 
/* 62 */     JobListener listener = new Job1Listener();
/* 63 */     Matcher matcher = KeyMatcher.keyEquals(job.getKey());
/* 64 */     sched.getListenerManager().addJobListener(listener, matcher);
/*    */ 
/* 67 */     sched.scheduleJob(job, trigger);
/*    */ 
/* 71 */     log.info("------- Starting Scheduler ----------------");
/* 72 */     sched.start();
/*    */ 
/* 76 */     log.info("------- Waiting 30 seconds... --------------");
/*    */     try
/*    */     {
/* 79 */       Thread.sleep(30000L);
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/*    */     }
/*    */ 
/* 86 */     log.info("------- Shutting Down ---------------------");
/* 87 */     sched.shutdown(true);
/* 88 */     log.info("------- Shutdown Complete -----------------");
/*    */ 
/* 90 */     SchedulerMetaData metaData = sched.getMetaData();
/* 91 */     log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */     throws Exception
/*    */   {
/* 97 */     ListenerExample example = new ListenerExample();
/* 98 */     example.run();
/*    */   }
/*    */ }

/* Location:           D:\Quartz\quartz-2.2.1-distribution\quartz-2.2.1\examples\lib\quartz-examples-2.2.1\
 * Qualified Name:     org.quartz.examples.example9.ListenerExample
 * JD-Core Version:    0.6.2
 */