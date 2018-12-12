package com.qa.TrainingManagerAPI.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.qa.TrainingManagerAPI.util.Constants;
import com.qa.TrainingManagerAPI.util.Producer;
import com.qa.persistence.domain.TrainingManager;
import com.qa.persistence.domain.User;
import com.qa.persistence.domain.UserRequest;
import com.qa.persistence.domain.UserRequest.requestType;

@Component
public class TrainingManagerService implements ITrainingManagerService {

	@Autowired
	private Producer producer;

	@Autowired
	private JmsTemplate jmsTemplate;

	public String create(TrainingManager trainingManager) {
		trainingManager.setType("TrainingManager");
		UserRequest thisRequest = new UserRequest(trainingManager);
		thisRequest.setHowToAct(requestType.CREATE);
		producer.produce(thisRequest);
		return Constants.TRAINING_MANAGER_QUEUED_MESSAGE;
	}

	public Optional<TrainingManager> get(String userName) {
		User dummyUser = new TrainingManager(userName);
		UserRequest thisRequest = new UserRequest(dummyUser);
		thisRequest.setHowToAct(requestType.READ);
		producer.produce(thisRequest);
		return (Optional<TrainingManager>) jmsTemplate
				.receiveAndConvert(Constants.OUTGOING_TRAINING_MANAGER_QUEUE_NAME);
	}

	public Iterable<TrainingManager> getAll() {
		UserRequest thisRequest = new UserRequest();
		thisRequest.setHowToAct(requestType.READALL);
		producer.produce(thisRequest);
		return (Iterable<TrainingManager>) jmsTemplate
				.receiveAndConvert(Constants.OUTGOING_TRAINING_MANAGER_QUEUE_NAME);
	}

	public String delete(String userName) {
		User dummyUser = new TrainingManager(userName);
		UserRequest thisRequest = new UserRequest(dummyUser);
		thisRequest.setHowToAct(requestType.DELETE);
		producer.produce(thisRequest);
		return Constants.REQUEST_QUEUED_MESSAGE;

	}

	public String update(String userName, TrainingManager updatedTrainingManager) {
		updatedTrainingManager.setUsername(userName);
		UserRequest thisRequest = new UserRequest(updatedTrainingManager);
		thisRequest.setHowToAct(requestType.UPDATE);
		producer.produce(thisRequest);
		return Constants.REQUEST_QUEUED_MESSAGE;
	}

}
