package com.qa.TrainingManagerAPI.util;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.qa.TrainingManagerAPI.persistence.domain.TrainingManager;

@Component
public class TrainingManagerConsumer {

	@JmsListener(destination = Constants.OUTGOING_TRAINING_MANAGER_QUEUE_NAME, containerFactory = "myFactory")
	public TrainingManager trainingManagerReceiveMessage(TrainingManager trainingManager) {
		return trainingManager;
	}

	public Iterable<TrainingManager> trainingManagerReceiveMessage(Iterable<TrainingManager> trainingManagers) {
		return trainingManagers;
	}

}