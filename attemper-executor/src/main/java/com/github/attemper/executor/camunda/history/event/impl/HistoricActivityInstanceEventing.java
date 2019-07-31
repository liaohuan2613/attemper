package com.github.attemper.executor.camunda.history.event.impl;

import com.github.attemper.common.enums.JobInstanceStatus;
import com.github.attemper.common.result.dispatch.instance.JobInstanceAct;
import com.github.attemper.config.base.bean.SpringContextAware;
import com.github.attemper.core.service.instance.JobInstanceService;
import com.github.attemper.executor.camunda.history.event.EndEventing;
import com.github.attemper.executor.camunda.history.event.EventingAdapter;
import com.github.attemper.executor.camunda.history.event.StartEventing;
import com.github.attemper.executor.util.CamundaUtil;
import org.camunda.bpm.engine.impl.history.event.HistoricActivityInstanceEventEntity;

public class HistoricActivityInstanceEventing extends EventingAdapter<HistoricActivityInstanceEventEntity> implements StartEventing<HistoricActivityInstanceEventEntity>, EndEventing<HistoricActivityInstanceEventEntity> {
    public HistoricActivityInstanceEventing(HistoricActivityInstanceEventEntity historyEvent) {
        super(historyEvent);
    }

    @Override
    public void start() {
        JobInstanceService jobInstanceService = SpringContextAware.getBean(JobInstanceService.class);
        JobInstanceAct jobInstanceAct = toJobInstanceAct(historyEvent);
        jobInstanceAct.setStatus(JobInstanceStatus.RUNNING.getStatus());
        jobInstanceAct.setStartTime(historyEvent.getStartTime());
        jobInstanceService.addAct(jobInstanceAct);
    }

    @Override
    public void end() {
        JobInstanceService jobInstanceService = SpringContextAware.getBean(JobInstanceService.class);
        JobInstanceAct jobInstanceAct = new JobInstanceAct().setId(CamundaUtil.extractIdFromActInstanceId(historyEvent.getActivityInstanceId()));
        jobInstanceAct.setStatus(JobInstanceStatus.SUCCESS.getStatus());
        jobInstanceAct.setEndTime(historyEvent.getEndTime());
        if (historyEvent.getEndTime() != null && historyEvent.getStartTime() != null) {
            jobInstanceAct.setDuration(historyEvent.getEndTime().getTime() - historyEvent.getStartTime().getTime());
        }
        jobInstanceService.updateAct(jobInstanceAct);
    }

    private JobInstanceAct toJobInstanceAct(HistoricActivityInstanceEventEntity historyEvent) {
        return new JobInstanceAct()
                .setId(CamundaUtil.extractIdFromActInstanceId(historyEvent.getId()))
                .setActInstId(historyEvent.getActivityInstanceId())
                .setParentActInstId(historyEvent.getParentActivityInstanceId())
                .setExecutionId(historyEvent.getExecutionId())
                .setProcInstId(historyEvent.getProcessInstanceId())
                .setRootProcInstId(historyEvent.getRootProcessInstanceId())
                .setActId(historyEvent.getActivityId())
                .setActName(historyEvent.getActivityName())
                .setActType(historyEvent.getActivityType());
    }

}
