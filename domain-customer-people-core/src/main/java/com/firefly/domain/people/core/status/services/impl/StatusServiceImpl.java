package com.firefly.domain.people.core.status.services.impl;

import com.firefly.domain.people.core.status.commands.UpdateStatusCommand;
import com.firefly.domain.people.core.status.services.StatusService;
import com.firefly.domain.people.core.status.workflows.UpdateStatusSaga;
import com.firefly.transactional.saga.core.SagaResult;
import com.firefly.transactional.saga.engine.SagaEngine;
import com.firefly.transactional.saga.engine.StepInputs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class StatusServiceImpl implements StatusService {

    private final SagaEngine engine;

    @Autowired
    public StatusServiceImpl(SagaEngine engine) {
        this.engine = engine;
    }

    @Override
    public Mono<SagaResult> updateStatus(UpdateStatusCommand updateStatusCommand) {
        StepInputs inputs = StepInputs.builder()
                .forStep(UpdateStatusSaga::updateStatus, updateStatusCommand)
                .build();
        return engine.execute(UpdateStatusSaga.class, inputs);
    }
}