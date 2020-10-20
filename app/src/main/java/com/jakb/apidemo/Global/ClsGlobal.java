package com.jakb.apidemo.Global;

import android.annotation.SuppressLint;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;

public class ClsGlobal {

    @SuppressLint("NewApi")
    public static void getStartNetworkJOB(Context context) {

        @SuppressLint({"NewApi", "LocalSuppress"}) JobInfo myJob = new JobInfo.Builder(
                78,
                new ComponentName(context, CheckNetworkJob.class))
                .setMinimumLatency(1000)
                .setOverrideDeadline(2000)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .build();

        if (!isJobServiceOn(context)) {
            Log.d("--isJobServiceOn--", "Job is not Scheduled");
            @SuppressLint({"NewApi", "LocalSuppress"}) JobScheduler jobScheduler = (JobScheduler)
                    context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
            jobScheduler.schedule(myJob);
        } else {
            Log.d("--isJobServiceOn--", "Job is Scheduled");
        }
    }

    @SuppressLint("NewApi")
    static boolean isJobServiceOn(Context context) {
        @SuppressLint({"NewApi", "LocalSuppress"})
        JobScheduler scheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        boolean hasBeenScheduled = false;
        for (JobInfo jobInfo : scheduler.getAllPendingJobs()) {
            if (jobInfo.getId() == 78) {
                hasBeenScheduled = true;
                break;
            }
        }
        return hasBeenScheduled;
    }

}
