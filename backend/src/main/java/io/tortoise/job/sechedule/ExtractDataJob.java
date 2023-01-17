package io.tortoise.job.sechedule;

import io.tortoise.commons.utils.CommonBeanFactory;
import io.tortoise.service.dataset.ExtractDataService;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Component
public class ExtractDataJob extends DeScheduleJob{
    private ExtractDataService extractDataService;

    public ExtractDataJob() {
        extractDataService = (ExtractDataService) CommonBeanFactory.getBean(ExtractDataService.class);
    }

    @Override
    void businessExecute(JobExecutionContext context) {
        extractDataService.extractData(datasetTableId, taskId, updateType, context);
    }

}
