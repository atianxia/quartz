/*    */ package org.quartz.examples.example6;
/*    */ 
/*    */ import java.util.Date;
/*    */ import org.quartz.DisallowConcurrentExecution;
/*    */ import org.quartz.Job;
/*    */ import org.quartz.JobDataMap;
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
/*    */ public class BadJob1
/*    */   implements Job
/*    */ {
/* 44 */   private static Logger _log = LoggerFactory.getLogger(BadJob1.class);
/*    */   private int calculation;
/*    */ 
/*    */   public void execute(JobExecutionContext context)
/*    */     throws JobExecutionException
/*    */   {
/* 62 */     JobKey jobKey = context.getJobDetail().getKey();
/* 63 */     JobDataMap dataMap = context.getJobDetail().getJobDataMap();
/*    */ 
/* 65 */     int denominator = dataMap.getInt("denominator");
/* 66 */     _log.info("---" + jobKey + " executing at " + new Date() + " with denominator " + denominator);
/*    */     try
/*    */     {
/* 72 */       this.calculation = (4815 / denominator);
/*    */     } catch (Exception e) {
/* 74 */       _log.info("--- Error in job!");
/* 75 */       JobExecutionException e2 = new JobExecutionException(e);
/*    */ 
/* 79 */       dataMap.put("denominator", "1");
/*    */ 
/* 82 */       e2.setRefireImmediately(true);
/* 83 */       throw e2;
/*    */     }
/*    */ 
/* 86 */     _log.info("---" + jobKey + " completed at " + new Date());
/*    */   }
/*    */ }

/* Location:           D:\Quartz\quartz-2.2.1-distribution\quartz-2.2.1\examples\lib\quartz-examples-2.2.1\
 * Qualified Name:     org.quartz.examples.example6.BadJob1
 * JD-Core Version:    0.6.2
 */