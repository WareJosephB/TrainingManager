package com.qa.TrainingManagerAPI.persistence.domain;

import org.springframework.data.annotation.TypeAlias;

@TypeAlias("TrainingManager")
public class TrainingManager extends User {

	public TrainingManager(String userName) {
		this.setUsername(userName);
	}

	public TrainingManager() {

	}

}
