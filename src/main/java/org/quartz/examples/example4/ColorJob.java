/*     */ package org.quartz.examples.example4;
/*     */ 
/*     */ import java.util.Date;
/*     */ import org.quartz.DisallowConcurrentExecution;
/*     */ import org.quartz.Job;
/*     */ import org.quartz.JobDataMap;
/*     */ import org.quartz.JobDetail;
/*     */ import org.quartz.JobExecutionContext;
/*     */ import org.quartz.JobExecutionException;
/*     */ import org.quartz.JobKey;
/*     */ import org.quartz.PersistJobDataAfterExecution;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ @PersistJobDataAfterExecution
/*     */ @DisallowConcurrentExecution
/*     */ public class ColorJob
/*     */   implements Job
/*     */ {
/*  44 */   private static Logger _log = LoggerFactory.getLogger(ColorJob.class);
/*     */   public static final String FAVORITE_COLOR = "favorite color";
/*     */   public static final String EXECUTION_COUNT = "count";
/*  53 */   private int _counter = 1;
/*     */ 
/*     */   public void execute(JobExecutionContext context)
/*     */     throws JobExecutionException
/*     */   {
/*  82 */     JobKey jobKey = context.getJobDetail().getKey();
/*     */ 
/*  85 */     JobDataMap data = context.getJobDetail().getJobDataMap();
/*  86 */     String favoriteColor = data.getString("favorite color");
/*  87 */     int count = data.getInt("count");
/*  88 */     _log.info("ColorJob: " + jobKey + " executing at " + new Date() + "\n" + "  favorite color is " + favoriteColor + "\n" + "  execution count (from job map) is " + count + "\n" + "  execution count (from job member variable) is " + this._counter);
/*     */ 
/*  95 */     count++;
/*  96 */     data.put("count", count);
/*     */ 
/* 101 */     this._counter += 1;
/*     */   }
/*     */ }

/* Location:           D:\Quartz\quartz-2.2.1-distribution\quartz-2.2.1\examples\lib\quartz-examples-2.2.1\
 * Qualified Name:     org.quartz.examples.example4.ColorJob
 * JD-Core Version:    0.6.2
 */