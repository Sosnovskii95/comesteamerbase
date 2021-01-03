package Tasks;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class NewJobDateBorn {

    private static Scheduler sched;

    public void creatNewJob() throws SchedulerException {
        JobDetail job = JobBuilder
                .newJob(DateBornJob.class)
                .withIdentity("DateBornJob", "dateborn")
                .build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("DateBornTrigger", "dateborn")
                .withSchedule(CronScheduleBuilder
                        .cronSchedule("0 10 0 1/1 * ? *"))
                .build();


        SchedulerFactory schedFact = new StdSchedulerFactory();
        sched = schedFact.getScheduler();
        sched.start();
        sched.scheduleJob(job,trigger);
    }

    public void removeJob() throws SchedulerException {
        sched.shutdown();
    }

}
