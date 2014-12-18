/*     */ package org.quartz.examples.example7;
/*     */ 
/*     */ import java.util.Date;
/*     */ import org.quartz.InterruptableJob;
/*     */ import org.quartz.JobDetail;
/*     */ import org.quartz.JobExecutionContext;
/*     */ import org.quartz.JobExecutionException;
/*     */ import org.quartz.JobKey;
/*     */ import org.quartz.UnableToInterruptJobException;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class DumbInterruptableJob
/*     */   implements InterruptableJob
/*     */ {
/*  42 */   private static Logger _log = LoggerFactory.getLogger(DumbInterruptableJob.class);
/*     */ 
/*  45 */   private boolean _interrupted = false;
/*     */ 
/*  48 */   private JobKey _jobKey = null;
/*     */ 
/*     */   public void execute(JobExecutionContext context)
/*     */     throws JobExecutionException
/*     */   {
/*  71 */     this._jobKey = context.getJobDetail().getKey();
/*  72 */     _log.info("---- " + this._jobKey + " executing at " + new Date());
/*     */     try
/*     */     {
/*  78 */       for (int i = 0; i < 4; ) {
/*     */         try {
/*  80 */           Thread.sleep(1000L);
/*     */         } catch (Exception ignore) {
/*  82 */           ignore.printStackTrace();
/*     */         }
/*     */ 
/*  86 */         if (this._interrupted) {
/*  87 */           _log.info("--- " + this._jobKey + "  -- Interrupted... bailing out!");
/*     */           return;
/*     */         }
/*  78 */         i++;
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/*     */       int i;
/*  95 */       _log.info("---- " + this._jobKey + " completed at " + new Date());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void interrupt()
/*     */     throws UnableToInterruptJobException
/*     */   {
/* 110 */     _log.info("---" + this._jobKey + "  -- INTERRUPTING --");
/* 111 */     this._interrupted = true;
/*     */   }
/*     */ }

/* Location:           D:\Quartz\quartz-2.2.1-distribution\quartz-2.2.1\examples\lib\quartz-examples-2.2.1\
 * Qualified Name:     org.quartz.examples.example7.DumbInterruptableJob
 * JD-Core Version:    0.6.2
 */