package com.quartz.weather.model.quartz;

import com.quartz.weather.model.data_base_entities.Location;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.util.UUID;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

public class WeatherScheduler {

    private Scheduler scheduler;

    public WeatherScheduler() throws SchedulerException {
        scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
    }


    public void addJob(Location location, Integer frequency) throws SchedulerException {

        JobDetail jobDetail = buildJobDetail(location);
        Trigger trigger = buildJobTrigger(jobDetail,frequency);
        scheduler.scheduleJob(jobDetail, trigger);

    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    private JobDetail buildJobDetail(Location location) {
        JobDataMap jobDataMap = new JobDataMap();

        jobDataMap.put("location", location);

        return JobBuilder.newJob(QueryJob.class)
                .withIdentity(UUID.randomUUID().toString(), "query-jobs")
                .withDescription("get temperature")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }
    private Trigger buildJobTrigger(JobDetail jobDetail, Integer frequency) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "freq-triggers")
                .withDescription("get temp trigger")
                .startNow()
                .withSchedule(simpleSchedule().withIntervalInSeconds(frequency).repeatForever())
                .build();
    }
}
