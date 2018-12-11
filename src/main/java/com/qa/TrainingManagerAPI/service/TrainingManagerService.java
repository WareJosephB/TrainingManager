package com.qa.TrainingManagerAPI.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import com.qa.TrainingManagerAPI.persistence.domain.TrainingManager;
import com.qa.TrainingManagerAPI.persistence.domain.User;
import com.qa.TrainingManagerAPI.persistence.domain.UserRequest;
import com.qa.TrainingManagerAPI.persistence.domain.UserRequest.requestType;
import com.qa.TrainingManagerAPI.util.Constants;
import com.qa.TrainingManagerAPI.util.Producer;

public class TrainingManagerService implements ITrainingManagerService {

	@Autowired
	private Producer producer;

	@Autowired
	private JmsTemplate jmsTemplate;

	public String create(TrainingManager trainingManager, String password) {
		producer.produce(trainingManager);
		return Constants.TRAINING_MANAGER_QUEUED_MESSAGE;
	}

	public Optional<TrainingManager> get(String userName) {
		UserRequest thisRequest = new UserRequest();
		User dummyUser = new TrainingManager(userName);
		thisRequest.setHowToAct(requestType.READ);
		thisRequest.setUserToAddOrUpdate(dummyUser);
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
		UserRequest thisRequest = new UserRequest();
		User dummyUser = new TrainingManager(userName);
		thisRequest.setHowToAct(requestType.DELETE);
		thisRequest.setUserToAddOrUpdate(dummyUser);
		producer.produce(thisRequest);
		return Constants.REQUEST_QUEUED_MESSAGE;

	}

	public String update(String userName, TrainingManager updatedTrainingManager) {
		UserRequest thisRequest = new UserRequest();
		User dummyUser = updatedTrainingManager;
		dummyUser.setUsername(userName);
		thisRequest.setHowToAct(requestType.UPDATE);
		thisRequest.setUserToAddOrUpdate(dummyUser);
		producer.produce(thisRequest);
		return Constants.REQUEST_QUEUED_MESSAGE;
	}

}
