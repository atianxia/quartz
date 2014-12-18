/*     */ package org.quartz.examples.example14;
/*     */ 
/*     */ import java.util.Date;
/*     */ import org.quartz.DateBuilder;
/*     */ import org.quartz.DateBuilder.IntervalUnit;
/*     */ import org.quartz.JobBuilder;
/*     */ import org.quartz.JobDetail;
/*     */ import org.quartz.Scheduler;
/*     */ import org.quartz.SchedulerFactory;
/*     */ import org.quartz.SimpleScheduleBuilder;
/*     */ import org.quartz.Trigger;
/*     */ import org.quartz.TriggerBuilder;
/*     */ import org.quartz.impl.StdSchedulerFactory;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class PriorityExample
/*     */ {
/*     */   public void run()
/*     */     throws Exception
/*     */   {
/*  41 */     Logger log = LoggerFactory.getLogger(PriorityExample.class);
/*     */ 
/*  43 */     log.info("------- Initializing ----------------------");
/*     */ 
/*  46 */     SchedulerFactory sf = new StdSchedulerFactory("org/quartz/examples/example14/quartz_priority.properties");
/*  47 */     Scheduler sched = sf.getScheduler();
/*     */ 
/*  49 */     log.info("------- Initialization Complete -----------");
/*     */ 
/*  51 */     log.info("------- Scheduling Jobs -------------------");
/*     */ 
/*  53 */     JobDetail job = JobBuilder.newJob(TriggerEchoJob.class).withIdentity("TriggerEchoJob").build();
/*     */ 
/*  68 */     Date startTime = DateBuilder.futureDate(5, DateBuilder.IntervalUnit.SECOND);
/*     */ 
/*  71 */     Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("Priority1Trigger5SecondRepeat").startAt(startTime).withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(1).withIntervalInSeconds(5)).withPriority(1).forJob(job).build();
/*     */ 
/*  75 */     Trigger trigger2 = TriggerBuilder.newTrigger().withIdentity("Priority5Trigger10SecondRepeat").startAt(startTime).withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(1).withIntervalInSeconds(10)).forJob(job).build();
/*     */ 
/*  79 */     Trigger trigger3 = TriggerBuilder.newTrigger().withIdentity("Priority10Trigger15SecondRepeat").startAt(startTime).withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(1).withIntervalInSeconds(15)).withPriority(10).forJob(job).build();
/*     */ 
/*  84 */     sched.scheduleJob(job, trigger1);
/*  85 */     sched.scheduleJob(trigger2);
/*  86 */     sched.scheduleJob(trigger3);
/*     */ 
/*  90 */     sched.start();
/*  91 */     log.info("------- Started Scheduler -----------------");
/*     */ 
/*  95 */     log.info("------- Waiting 30 seconds... -------------");
/*     */     try {
/*  97 */       Thread.sleep(30000L);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */     }
/*     */ 
/* 104 */     log.info("------- Shutting Down ---------------------");
/* 105 */     sched.shutdown(true);
/* 106 */     log.info("------- Shutdown Complete -----------------");
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) throws Exception {
/* 110 */     PriorityExample example = new PriorityExample();
/* 111 */     example.run();
/*     */   }
/*     */ }

/* Location:           D:\Quartz\quartz-2.2.1-distribution\quartz-2.2.1\examples\lib\quartz-examples-2.2.1\
 * Qualified Name:     org.quartz.examples.example14.PriorityExample
 * JD-Core Version:    0.6.2
 */