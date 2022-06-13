package com.charminseok.settlement.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class SettlementScheduler {
    private final Job job;
    private final JobLauncher jobLauncher;

//    매일 새벽 1시 실행
    @Scheduled(cron = "0 0 1 * * ?", zone = "Asia/Seoul")
    public void executeJob() {
        JobExecution execution;
        try {
            log.info("start Job");
            execution = jobLauncher.run(
                    job,
                    new JobParametersBuilder()
                            .addString("clickDate", LocalDateTime.now().toString())
                            .toJobParameters()

            );
            log.info("Job finished with status : " + execution.getStatus());
            log.info("Current Thread: {}", Thread.currentThread().getName());
        } catch (JobExecutionException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
