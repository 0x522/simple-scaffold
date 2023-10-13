package com.frame.provider.task;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class SyncTask {

    /**
     * 同步
     *
     * @param
     */
    @Scheduled(cron = "0 */30 * ? * *")
    @Transactional(rollbackFor = Exception.class)
    public void doSync() {
        log.info("定时任务执行了！");

    }

}
