/*    */ package org.quartz.examples.example12;
/*    */ 
/*    */ import org.quartz.Scheduler;
/*    */ import org.quartz.SchedulerFactory;
/*    */ import org.quartz.SchedulerMetaData;
/*    */ import org.quartz.impl.StdSchedulerFactory;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class RemoteServerExample
/*    */ {
/*    */   public void run()
/*    */     throws Exception
/*    */   {
/* 38 */     Logger log = LoggerFactory.getLogger(RemoteServerExample.class);
/*    */ 
/* 41 */     SchedulerFactory sf = new StdSchedulerFactory();
/* 42 */     Scheduler sched = sf.getScheduler();
/*    */ 
/* 44 */     log.info("------- Initialization Complete -----------");
/*    */ 
/* 46 */     log.info("------- (Not Scheduling any Jobs - relying on a remote client to schedule jobs --");
/*    */ 
/* 48 */     log.info("------- Starting Scheduler ----------------");
/*    */ 
/* 51 */     sched.start();
/*    */ 
/* 53 */     log.info("------- Started Scheduler -----------------");
/*    */ 
/* 55 */     log.info("------- Waiting ten minutes... ------------");
/*    */     try
/*    */     {
/* 59 */       Thread.sleep(600000L);
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/*    */     }
/*    */ 
/* 65 */     log.info("------- Shutting Down ---------------------");
/* 66 */     sched.shutdown(true);
/* 67 */     log.info("------- Shutdown Complete -----------------");
/*    */ 
/* 69 */     SchedulerMetaData metaData = sched.getMetaData();
/* 70 */     log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) throws Exception
/*    */   {
/* 75 */     RemoteServerExample example = new RemoteServerExample();
/* 76 */     example.run();
/*    */   }
/*    */ }

/* Location:           D:\Quartz\quartz-2.2.1-distribution\quartz-2.2.1\examples\lib\quartz-examples-2.2.1\
 * Qualified Name:     org.quartz.examples.example12.RemoteServerExample
 * JD-Core Version:    0.6.2
 */