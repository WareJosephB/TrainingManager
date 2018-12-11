package com.qa.TrainingManagerAPI.service;

import java.util.Optional;

import com.qa.TrainingManagerAPI.persistence.domain.TrainingManager;

public interface ITrainingManagerService {

	String create(TrainingManager trainee, String password);

	Optional<TrainingManager> get(String username);

	Iterable<TrainingManager> getAll();

	String update(String username, TrainingManager updatedDetails);

	String delete(String username);

}
