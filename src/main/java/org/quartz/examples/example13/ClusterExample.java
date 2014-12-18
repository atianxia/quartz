/*     */ package org.quartz.examples.example13;
/*     */ 
/*     */ import org.quartz.DateBuilder;
/*     */ import org.quartz.DateBuilder.IntervalUnit;
/*     */ import org.quartz.JobBuilder;
/*     */ import org.quartz.JobDetail;
/*     */ import org.quartz.Scheduler;
/*     */ import org.quartz.SchedulerFactory;
/*     */ import org.quartz.SimpleScheduleBuilder;
/*     */ import org.quartz.SimpleTrigger;
/*     */ import org.quartz.TriggerBuilder;
/*     */ import org.quartz.impl.StdSchedulerFactory;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class ClusterExample
/*     */ {
/*  66 */   private static Logger _log = LoggerFactory.getLogger(ClusterExample.class);
/*     */ 
/*     */   public void run(boolean inClearJobs, boolean inScheduleJobs)
/*     */     throws Exception
/*     */   {
/*  71 */     SchedulerFactory sf = new StdSchedulerFactory();
/*  72 */     Scheduler sched = sf.getScheduler();
/*     */ 
/*  74 */     if (inClearJobs) {
/*  75 */       _log.warn("***** Deleting existing jobs/triggers *****");
/*  76 */       sched.clear();
/*     */     }
/*     */ 
/*  79 */     _log.info("------- Initialization Complete -----------");
/*     */ 
/*  81 */     if (inScheduleJobs)
/*     */     {
/*  83 */       _log.info("------- Scheduling Jobs ------------------");
/*     */ 
/*  85 */       String schedId = sched.getSchedulerInstanceId();
/*     */ 
/*  87 */       int count = 1;
/*     */ 
/*  89 */       JobDetail job = JobBuilder.newJob(SimpleRecoveryJob.class).withIdentity("job_" + count, schedId).requestRecovery().build();
/*     */ 
/*  99 */       SimpleTrigger trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("triger_" + count, schedId).startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND)).withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(20).withIntervalInSeconds(5)).build();
/*     */ 
/* 103 */       _log.info(job.getKey() + " will run at: " + trigger.getNextFireTime() + " and repeat: " + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000L + " seconds");
/*     */ 
/* 105 */       sched.scheduleJob(job, trigger);
/*     */ 
/* 107 */       count++;
/*     */ 
/* 109 */       job = JobBuilder.newJob(SimpleRecoveryJob.class).withIdentity("job_" + count, schedId).requestRecovery().build();
/*     */ 
/* 117 */       trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("triger_" + count, schedId).startAt(DateBuilder.futureDate(2, DateBuilder.IntervalUnit.SECOND)).withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(20).withIntervalInSeconds(5)).build();
/*     */ 
/* 120 */       _log.info(job.getKey() + " will run at: " + trigger.getNextFireTime() + " and repeat: " + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000L + " seconds");
/*     */ 
/* 122 */       sched.scheduleJob(job, trigger);
/*     */ 
/* 124 */       count++;
/*     */ 
/* 126 */       job = JobBuilder.newJob(SimpleRecoveryStatefulJob.class).withIdentity("job_" + count, schedId).requestRecovery().build();
/*     */ 
/* 136 */       trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("triger_" + count, schedId).startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND)).withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(20).withIntervalInSeconds(3)).build();
/*     */ 
/* 139 */       _log.info(job.getKey() + " will run at: " + trigger.getNextFireTime() + " and repeat: " + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000L + " seconds");
/*     */ 
/* 141 */       sched.scheduleJob(job, trigger);
/*     */ 
/* 143 */       count++;
/*     */ 
/* 145 */       job = JobBuilder.newJob(SimpleRecoveryJob.class).withIdentity("job_" + count, schedId).requestRecovery().build();
/*     */ 
/* 153 */       trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("triger_" + count, schedId).startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND)).withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(20).withIntervalInSeconds(4)).build();
/*     */ 
/* 156 */       _log.info(job.getKey() + " will run at: " + trigger.getNextFireTime() + " & repeat: " + trigger.getRepeatCount() + "/" + trigger.getRepeatInterval());
/*     */ 
/* 158 */       sched.scheduleJob(job, trigger);
/*     */ 
/* 160 */       count++;
/*     */ 
/* 162 */       job = JobBuilder.newJob(SimpleRecoveryJob.class).withIdentity("job_" + count, schedId).requestRecovery().build();
/*     */ 
/* 170 */       trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("triger_" + count, schedId).startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND)).withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(20).withIntervalInMilliseconds(4500L)).build();
/*     */ 
/* 173 */       _log.info(job.getKey() + " will run at: " + trigger.getNextFireTime() + " & repeat: " + trigger.getRepeatCount() + "/" + trigger.getRepeatInterval());
/*     */ 
/* 175 */       sched.scheduleJob(job, trigger);
/*     */     }
/*     */ 
/* 179 */     _log.info("------- Starting Scheduler ---------------");
/* 180 */     sched.start();
/* 181 */     _log.info("------- Started Scheduler ----------------");
/*     */ 
/* 183 */     _log.info("------- Waiting for one hour... ----------");
/*     */     try {
/* 185 */       Thread.sleep(3600000L);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */     }
/* 190 */     _log.info("------- Shutting Down --------------------");
/* 191 */     sched.shutdown();
/* 192 */     _log.info("------- Shutdown Complete ----------------");
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) throws Exception {
/* 196 */     boolean clearJobs = false;
/* 197 */     boolean scheduleJobs = true;
/*     */ 
/* 199 */     for (String arg : args) {
/* 200 */       if (arg.equalsIgnoreCase("clearJobs"))
/* 201 */         clearJobs = true;
/* 202 */       else if (arg.equalsIgnoreCase("dontScheduleJobs")) {
/* 203 */         scheduleJobs = false;
/*     */       }
/*     */     }
/*     */ 
/* 207 */     ClusterExample example = new ClusterExample();
/* 208 */     example.run(clearJobs, scheduleJobs);
/*     */   }
/*     */ }

/* Location:           D:\Quartz\quartz-2.2.1-distribution\quartz-2.2.1\examples\lib\quartz-examples-2.2.1\
 * Qualified Name:     org.quartz.examples.example13.ClusterExample
 * JD-Core Version:    0.6.2
 */