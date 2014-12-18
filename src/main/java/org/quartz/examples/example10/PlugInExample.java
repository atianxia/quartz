/*    */ package org.quartz.examples.example10;
/*    */ 
/*    */ import org.quartz.Scheduler;
/*    */ import org.quartz.SchedulerFactory;
/*    */ import org.quartz.SchedulerMetaData;
/*    */ import org.quartz.impl.StdSchedulerFactory;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class PlugInExample
/*    */ {
/*    */   public void run()
/*    */     throws Exception
/*    */   {
/* 35 */     Logger log = LoggerFactory.getLogger(PlugInExample.class);
/*    */ 
/* 38 */     SchedulerFactory sf = new StdSchedulerFactory();
/* 39 */     Scheduler sched = sf.getScheduler();
/*    */ 
/* 41 */     log.info("------- Initialization Complete -----------");
/*    */ 
/* 43 */     log.info("------- (Not Scheduling any Jobs - relying on XML definitions --");
/*    */ 
/* 45 */     log.info("------- Starting Scheduler ----------------");
/*    */ 
/* 48 */     sched.start();
/*    */ 
/* 50 */     log.info("------- Started Scheduler -----------------");
/*    */ 
/* 52 */     log.info("------- Waiting five minutes... -----------");
/*    */     try
/*    */     {
/* 56 */       Thread.sleep(300000L);
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/*    */     }
/*    */ 
/* 62 */     log.info("------- Shutting Down ---------------------");
/* 63 */     sched.shutdown(true);
/* 64 */     log.info("------- Shutdown Complete -----------------");
/*    */ 
/* 66 */     SchedulerMetaData metaData = sched.getMetaData();
/* 67 */     log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) throws Exception
/*    */   {
/* 72 */     PlugInExample example = new PlugInExample();
/* 73 */     example.run();
/*    */   }
/*    */ }

/* Location:           D:\Quartz\quartz-2.2.1-distribution\quartz-2.2.1\examples\lib\quartz-examples-2.2.1\
 * Qualified Name:     org.quartz.examples.example10.PlugInExample
 * JD-Core Version:    0.6.2
 */