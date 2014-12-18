/*    */ package org.quartz.examples.example6;
/*    */ 
/*    */ import java.util.Date;
/*    */ import org.quartz.DisallowConcurrentExecution;
/*    */ import org.quartz.Job;
/*    */ import org.quartz.JobDetail;
/*    */ import org.quartz.JobExecutionContext;
/*    */ import org.quartz.JobExecutionException;
/*    */ import org.quartz.JobKey;
/*    */ import org.quartz.PersistJobDataAfterExecution;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ @PersistJobDataAfterExecution
/*    */ @DisallowConcurrentExecution
/*    */ public class BadJob2
/*    */   implements Job
/*    */ {
/* 43 */   private static Logger _log = LoggerFactory.getLogger(BadJob2.class);
/*    */   private int calculation;
/*    */ 
/*    */   public void execute(JobExecutionContext context)
/*    */     throws JobExecutionException
/*    */   {
/* 63 */     JobKey jobKey = context.getJobDetail().getKey();
/* 64 */     _log.info("---" + jobKey + " executing at " + new Date());
/*    */     try
/*    */     {
/* 70 */       int zero = 0;
/* 71 */       this.calculation = (4815 / zero);
/*    */     } catch (Exception e) {
/* 73 */       _log.info("--- Error in job!");
/* 74 */       JobExecutionException e2 = new JobExecutionException(e);
/*    */ 
/* 79 */       e2.setUnscheduleAllTriggers(true);
/* 80 */       throw e2;
/*    */     }
/*    */ 
/* 83 */     _log.info("---" + jobKey + " completed at " + new Date());
/*    */   }
/*    */ }

/* Location:           D:\Quartz\quartz-2.2.1-distribution\quartz-2.2.1\examples\lib\quartz-examples-2.2.1\
 * Qualified Name:     org.quartz.examples.example6.BadJob2
 * JD-Core Version:    0.6.2
 */