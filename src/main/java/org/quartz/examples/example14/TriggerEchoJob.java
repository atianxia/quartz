/*    */ package org.quartz.examples.example14;
/*    */ 
/*    */ import org.quartz.Job;
/*    */ import org.quartz.JobExecutionContext;
/*    */ import org.quartz.JobExecutionException;
/*    */ import org.quartz.Trigger;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class TriggerEchoJob
/*    */   implements Job
/*    */ {
/* 31 */   private static final Logger LOG = LoggerFactory.getLogger(TriggerEchoJob.class);
/*    */ 
/*    */   public void execute(JobExecutionContext context)
/*    */     throws JobExecutionException
/*    */   {
/* 56 */     LOG.info("TRIGGER: " + context.getTrigger().getKey());
/*    */   }
/*    */ }

/* Location:           D:\Quartz\quartz-2.2.1-distribution\quartz-2.2.1\examples\lib\quartz-examples-2.2.1\
 * Qualified Name:     org.quartz.examples.example14.TriggerEchoJob
 * JD-Core Version:    0.6.2
 */