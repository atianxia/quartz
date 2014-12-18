/*    */ package org.quartz.examples.example10;
/*    */ 
/*    */ import java.util.Date;
/*    */ import java.util.Set;

/*    */ import org.quartz.Job;
/*    */ import org.quartz.JobExecutionContext;
/*    */ import org.quartz.JobExecutionException;
/*    */ import org.quartz.JobKey;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SimpleJob
/*    */   implements Job
/*    */ {
/* 39 */   private static Logger _log = LoggerFactory.getLogger(SimpleJob.class);
/*    */ 
/*    */   public void execute(JobExecutionContext context)
/*    */     throws JobExecutionException
/*    */   {
/* 63 */     JobKey jobKey = context.getJobDetail().getKey();
/* 64 */     _log.info("Executing job: " + jobKey + " executing at " + new Date() + ", fired by: " + context.getTrigger().getKey());
/*    */ 
/* 66 */     if (context.getMergedJobDataMap().size() > 0) {
/* 67 */       Set<String> keys = context.getMergedJobDataMap().keySet();
/* 68 */       for (String key : keys) {
/* 69 */         String val = context.getMergedJobDataMap().getString(key);
/* 70 */         _log.info(" - jobDataMap entry: " + key + " = " + val);
/*    */       }
/*    */     }
/*    */ 
/* 74 */     context.setResult("hello");
/*    */   }
/*    */ }

