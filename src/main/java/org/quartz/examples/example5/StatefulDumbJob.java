/*    */ package org.quartz.examples.example5;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.Date;
/*    */ import org.quartz.DisallowConcurrentExecution;
/*    */ import org.quartz.Job;
/*    */ import org.quartz.JobDataMap;
/*    */ import org.quartz.JobDetail;
/*    */ import org.quartz.JobExecutionContext;
/*    */ import org.quartz.JobExecutionException;
/*    */ import org.quartz.PersistJobDataAfterExecution;
/*    */ 
/*    */ @PersistJobDataAfterExecution
/*    */ @DisallowConcurrentExecution
/*    */ public class StatefulDumbJob
/*    */   implements Job
/*    */ {
/*    */   public static final String NUM_EXECUTIONS = "NumExecutions";
/*    */   public static final String EXECUTION_DELAY = "ExecutionDelay";
/*    */ 
/*    */   public void execute(JobExecutionContext context)
/*    */     throws JobExecutionException
/*    */   {
/* 71 */     System.err.println("---" + context.getJobDetail().getKey() + " executing.[" + new Date() + "]");
/*    */ 
/* 73 */     JobDataMap map = context.getJobDetail().getJobDataMap();
/*    */ 
/* 75 */     int executeCount = 0;
/* 76 */     if (map.containsKey("NumExecutions")) {
/* 77 */       executeCount = map.getInt("NumExecutions");
/*    */     }
/*    */ 
/* 80 */     executeCount++;
/*    */ 
/* 82 */     map.put("NumExecutions", executeCount);
/*    */ 
/* 84 */     long delay = 5000L;
/* 85 */     if (map.containsKey("ExecutionDelay")) {
/* 86 */       delay = map.getLong("ExecutionDelay");
/*    */     }
/*    */     try
/*    */     {
/* 90 */       Thread.sleep(delay);
/*    */     }
/*    */     catch (Exception ignore)
/*    */     {
/*    */     }
/* 95 */     System.err.println("  -" + context.getJobDetail().getKey() + " complete (" + executeCount + ").");
/*    */   }
/*    */ }

/* Location:           D:\Quartz\quartz-2.2.1-distribution\quartz-2.2.1\examples\lib\quartz-examples-2.2.1\
 * Qualified Name:     org.quartz.examples.example5.StatefulDumbJob
 * JD-Core Version:    0.6.2
 */