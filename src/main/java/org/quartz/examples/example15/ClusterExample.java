/*     */ package org.quartz.examples.example15;
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
/*  64 */   private static Logger _log = LoggerFactory.getLogger(ClusterExample.class);
/*     */ 
/*     */   public void run(boolean inClearJobs, boolean inScheduleJobs)
/*     */     throws Exception
/*     */   {
/*  69 */     SchedulerFactory sf = new StdSchedulerFactory();
/*  70 */     Scheduler sched = sf.getScheduler();
/*     */ 
/*  72 */     if (inClearJobs) {
/*  73 */       _log.warn("***** Deleting existing jobs/triggers *****");
/*  74 */       sched.clear();
/*     */     }
/*     */ 
/*  77 */     _log.info("------- Initialization Complete -----------");
/*     */ 
/*  79 */     if (inScheduleJobs)
/*     */     {
/*  81 */       _log.info("------- Scheduling Jobs ------------------");
/*     */ 
/*  83 */       String schedId = sched.getSchedulerInstanceId();
/*     */ 
/*  85 */       int count = 1;
/*     */ 
/*  87 */       JobDetail job = JobBuilder.newJob(SimpleRecoveryJob.class).withIdentity("job_" + count, schedId).requestRecovery().build();
/*     */ 
/*  97 */       SimpleTrigger trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("triger_" + count, schedId).startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND)).withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(20).withIntervalInSeconds(5)).build();
/*     */ 
/* 101 */       _log.info(job.getKey() + " will run at: " + trigger.getNextFireTime() + " and repeat: " + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000L + " seconds");
/*     */ 
/* 103 */       sched.scheduleJob(job, trigger);
/*     */ 
/* 105 */       count++;
/*     */ 
/* 107 */       job = JobBuilder.newJob(SimpleRecoveryJob.class).withIdentity("job_" + count, schedId).requestRecovery().build();
/*     */ 
/* 115 */       trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("triger_" + count, schedId).startAt(DateBuilder.futureDate(2, DateBuilder.IntervalUnit.SECOND)).withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(20).withIntervalInSeconds(5)).build();
/*     */ 
/* 118 */       _log.info(job.getKey() + " will run at: " + trigger.getNextFireTime() + " and repeat: " + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000L + " seconds");
/*     */ 
/* 120 */       sched.scheduleJob(job, trigger);
/*     */ 
/* 122 */       count++;
/*     */ 
/* 124 */       job = JobBuilder.newJob(SimpleRecoveryStatefulJob.class).withIdentity("job_" + count, schedId).requestRecovery().build();
/*     */ 
/* 134 */       trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("triger_" + count, schedId).startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND)).withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(20).withIntervalInSeconds(3)).build();
/*     */ 
/* 137 */       _log.info(job.getKey() + " will run at: " + trigger.getNextFireTime() + " and repeat: " + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000L + " seconds");
/*     */ 
/* 139 */       sched.scheduleJob(job, trigger);
/*     */ 
/* 141 */       count++;
/*     */ 
/* 143 */       job = JobBuilder.newJob(SimpleRecoveryJob.class).withIdentity("job_" + count, schedId).requestRecovery().build();
/*     */ 
/* 151 */       trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("triger_" + count, schedId).startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND)).withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(20).withIntervalInSeconds(4)).build();
/*     */ 
/* 154 */       _log.info(job.getKey() + " will run at: " + trigger.getNextFireTime() + " & repeat: " + trigger.getRepeatCount() + "/" + trigger.getRepeatInterval());
/*     */ 
/* 156 */       sched.scheduleJob(job, trigger);
/*     */ 
/* 158 */       count++;
/*     */ 
/* 160 */       job = JobBuilder.newJob(SimpleRecoveryJob.class).withIdentity("job_" + count, schedId).requestRecovery().build();
/*     */ 
/* 168 */       trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("triger_" + count, schedId).startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND)).withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(20).withIntervalInMilliseconds(4500L)).build();
/*     */ 
/* 171 */       _log.info(job.getKey() + " will run at: " + trigger.getNextFireTime() + " & repeat: " + trigger.getRepeatCount() + "/" + trigger.getRepeatInterval());
/*     */ 
/* 173 */       sched.scheduleJob(job, trigger);
/*     */     }
/*     */ 
/* 177 */     _log.info("------- Starting Scheduler ---------------");
/* 178 */     sched.start();
/* 179 */     _log.info("------- Started Scheduler ----------------");
/*     */ 
/* 181 */     _log.info("------- Waiting for one hour... ----------");
/*     */     try {
/* 183 */       Thread.sleep(3600000L);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */     }
/* 188 */     _log.info("------- Shutting Down --------------------");
/* 189 */     sched.shutdown();
/* 190 */     _log.info("------- Shutdown Complete ----------------");
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) throws Exception {
/* 194 */     boolean clearJobs = false;
/* 195 */     boolean scheduleJobs = true;
/*     */ 
/* 197 */     for (String arg : args) {
/* 198 */       if (arg.equalsIgnoreCase("clearJobs"))
/* 199 */         clearJobs = true;
/* 200 */       else if (arg.equalsIgnoreCase("dontScheduleJobs")) {
/* 201 */         scheduleJobs = false;
/*     */       }
/*     */     }
/*     */ 
/* 205 */     ClusterExample example = new ClusterExample();
/* 206 */     example.run(clearJobs, scheduleJobs);
/*     */   }
/*     */ }

/* Location:           D:\Quartz\quartz-2.2.1-distribution\quartz-2.2.1\examples\lib\quartz-examples-2.2.1\
 * Qualified Name:     org.quartz.examples.example15.ClusterExample
 * JD-Core Version:    0.6.2
 */