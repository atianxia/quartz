/*    */ package org.quartz.examples.example11;
/*    */ 
/*    */ import java.util.Date;
/*    */ import org.quartz.Job;
/*    */ import org.quartz.JobDataMap;
/*    */ import org.quartz.JobDetail;
/*    */ import org.quartz.JobExecutionContext;
/*    */ import org.quartz.JobExecutionException;
/*    */ import org.quartz.JobKey;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class SimpleJob
/*    */   implements Job
/*    */ {
/* 38 */   private static Logger _log = LoggerFactory.getLogger(SimpleJob.class);
/*    */   public static final String DELAY_TIME = "delay time";
/*    */ 
/*    */   public void execute(JobExecutionContext context)
/*    */     throws JobExecutionException
/*    */   {
/* 61 */     JobKey jobKey = context.getJobDetail().getKey();
/* 62 */     _log.info("Executing job: " + jobKey + " executing at " + new Date());
/*    */ 
/* 65 */     long delayTime = context.getJobDetail().getJobDataMap().getLong("delay time");
/*    */     try {
/* 67 */       Thread.sleep(delayTime);
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/*    */     }
/* 72 */     _log.info("Finished Executing job: " + jobKey + " at " + new Date());
/*    */   }
/*    */ }

/* Location:           D:\Quartz\quartz-2.2.1-distribution\quartz-2.2.1\examples\lib\quartz-examples-2.2.1\
 * Qualified Name:     org.quartz.examples.example11.SimpleJob
 * JD-Core Version:    0.6.2
 */