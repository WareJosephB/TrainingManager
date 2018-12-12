package com.qa.persistence.domain;

public class TrainingManager extends User {

	public TrainingManager(String username) {
		super(username);
		this.type = "Trainer";
	}

	public TrainingManager() {
		super();
		this.type = "Trainer";
	}

}
