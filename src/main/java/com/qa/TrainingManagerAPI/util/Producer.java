package com.qa.TrainingManagerAPI.util;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import com.qa.TrainingManagerAPI.util.Constants;
import com.qa.persistence.domain.TrainingManager;
import com.qa.persistence.domain.UserRequest;

@Component
public class Producer {

	@Autowired
	private JmsMessagingTemplate jmsTemplate;

	public Optional<TrainingManager> produceTrainingManager(UserRequest request) {
		return jmsTemplate.convertSendAndReceive(Constants.INCOMING_TRAINING_MANAGER_QUEUE_NAME, request,
				Optional.class);
	}

	public Iterable<TrainingManager> produceTrainingManagers(UserRequest request) {
		return jmsTemplate.convertSendAndReceive(Constants.INCOMING_TRAINING_MANAGER_QUEUE_NAME, request, List.class);
	}

	public String produceMessage(UserRequest request) {
		return jmsTemplate.convertSendAndReceive(Constants.INCOMING_TRAINING_MANAGER_QUEUE_NAME, request, String.class);
	}

}
