package com.qa.TrainingManagerAPI.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qa.TrainingManagerAPI.util.Producer;
import com.qa.persistence.domain.TrainingManager;
import com.qa.persistence.domain.User;
import com.qa.persistence.domain.UserRequest;
import com.qa.persistence.domain.UserRequest.requestType;

@Component
public class TrainingManagerService implements ITrainingManagerService {

	@Autowired
	private Producer producer;

	public String create(TrainingManager trainingManager) {
		trainingManager.setType("TrainingManager");
		UserRequest thisRequest = new UserRequest(trainingManager);
		thisRequest.setHowToAct(requestType.CREATE);
		return producer.produceMessage(thisRequest);
	}

	public Optional<TrainingManager> get(String userName) {
		User dummyUser = new TrainingManager(userName);
		UserRequest thisRequest = new UserRequest(dummyUser);
		thisRequest.setHowToAct(requestType.READ);
		return producer.produceTrainingManager(thisRequest);
	}

	public Iterable<TrainingManager> getAll() {
		UserRequest thisRequest = new UserRequest();
		thisRequest.setHowToAct(requestType.READALL);
		return producer.produceTrainingManagers(thisRequest);
	}

	public String delete(String userName) {
		User dummyUser = new TrainingManager(userName);
		UserRequest thisRequest = new UserRequest(dummyUser);
		thisRequest.setHowToAct(requestType.DELETE);
		return producer.produceMessage(thisRequest);

	}

	public String update(String userName, TrainingManager updatedTrainingManager) {
		updatedTrainingManager.setUsername(userName);
		UserRequest thisRequest = new UserRequest(updatedTrainingManager);
		thisRequest.setHowToAct(requestType.UPDATE);
		return producer.produceMessage(thisRequest);
	}

}
