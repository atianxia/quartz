/*    */ package org.quartz.examples.example12;
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
/*    */   public static final String MESSAGE = "msg";
/* 40 */   private static Logger _log = LoggerFactory.getLogger(SimpleJob.class);
/*    */ 
/*    */   public void execute(JobExecutionContext context)
/*    */     throws JobExecutionException
/*    */   {
/* 64 */     JobKey jobKey = context.getJobDetail().getKey();
/*    */ 
/* 66 */     String message = (String)context.getJobDetail().getJobDataMap().get("msg");
/*    */ 
/* 68 */     _log.info("SimpleJob: " + jobKey + " executing at " + new Date());
/* 69 */     _log.info("SimpleJob: msg: " + message);
/*    */   }
/*    */ }

/* Location:           D:\Quartz\quartz-2.2.1-distribution\quartz-2.2.1\examples\lib\quartz-examples-2.2.1\
 * Qualified Name:     org.quartz.examples.example12.SimpleJob
 * JD-Core Version:    0.6.2
 */