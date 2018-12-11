package com.qa.TrainingManagerAPI.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import com.qa.TrainingManagerAPI.persistence.domain.TrainingManager;
import com.qa.TrainingManagerAPI.persistence.domain.UserRequest;

@Component
public class Producer {

	@Autowired
	private JmsTemplate jmsTemplate;

	public String produce(TrainingManager trainee) {
		jmsTemplate.convertAndSend(Constants.INCOMING_TRAINING_MANAGER_QUEUE_NAME, trainee);
		return Constants.TRAINING_MANAGER_QUEUED_MESSAGE;
	}

	public String produce(Iterable<TrainingManager> trainees) {
		jmsTemplate.convertAndSend(Constants.INCOMING_TRAINING_MANAGER_QUEUE_NAME, trainees);
		return Constants.TRAINING_MANAGERS_QUEUED_MESSAGE;
	}

	public String produce(UserRequest request) {
		jmsTemplate.convertAndSend(Constants.INCOMING_TRAINING_MANAGER_QUEUE_NAME, request);
		return Constants.REQUEST_QUEUED_MESSAGE;

	}

}
