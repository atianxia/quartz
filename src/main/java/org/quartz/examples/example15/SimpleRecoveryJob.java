/*    */ package org.quartz.examples.example15;
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
/*    */ public class SimpleRecoveryJob
/*    */   implements Job
/*    */ {
/* 39 */   private static Logger _log = LoggerFactory.getLogger(SimpleRecoveryJob.class);
/*    */   private static final String COUNT = "count";
/*    */ 
/*    */   public void execute(JobExecutionContext context)
/*    */     throws JobExecutionException
/*    */   {
/* 59 */     JobKey jobKey = context.getJobDetail().getKey();
/*    */ 
/* 62 */     if (context.isRecovering())
/* 63 */       _log.info("SimpleRecoveryJob: " + jobKey + " RECOVERING at " + new Date());
/*    */     else {
/* 65 */       _log.info("SimpleRecoveryJob: " + jobKey + " starting at " + new Date());
/*    */     }
/*    */ 
/* 69 */     long delay = 10000L;
/*    */     try {
/* 71 */       Thread.sleep(delay);
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/*    */     }
/* 76 */     JobDataMap data = context.getJobDetail().getJobDataMap();
/*    */     int count;
/* 78 */     if (data.containsKey("count"))
/* 79 */       count = data.getInt("count");
/*    */     else {
/* 81 */       count = 0;
/*    */     }
/* 83 */     count++;
/* 84 */     data.put("count", count);
/*    */ 
/* 86 */     _log.info("SimpleRecoveryJob: " + jobKey + " done at " + new Date() + "\n Execution #" + count);
/*    */   }
/*    */ }

/* Location:           D:\Quartz\quartz-2.2.1-distribution\quartz-2.2.1\examples\lib\quartz-examples-2.2.1\
 * Qualified Name:     org.quartz.examples.example15.SimpleRecoveryJob
 * JD-Core Version:    0.6.2
 */