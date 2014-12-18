/*    */ package org.quartz.examples.example8;
/*    */ 
/*    */ import java.util.Date;
/*    */ import org.quartz.Job;
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
/*    */ 
/*    */   public void execute(JobExecutionContext context)
/*    */     throws JobExecutionException
/*    */   {
/* 61 */     JobKey jobKey = context.getJobDetail().getKey();
/* 62 */     _log.info("SimpleJob says: " + jobKey + " executing at " + new Date());
/*    */   }
/*    */ }

/* Location:           D:\Quartz\quartz-2.2.1-distribution\quartz-2.2.1\examples\lib\quartz-examples-2.2.1\
 * Qualified Name:     org.quartz.examples.example8.SimpleJob
 * JD-Core Version:    0.6.2
 */