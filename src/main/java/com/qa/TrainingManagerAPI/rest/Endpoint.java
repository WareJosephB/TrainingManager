package com.qa.TrainingManagerAPI.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.TrainingManagerAPI.service.ITrainingManagerService;
import com.qa.persistence.domain.TrainingManager;

@RequestMapping("${URL.base}")
@RestController
@CrossOrigin
public class Endpoint {

	@Autowired
	private ITrainingManagerService service;

	@PostMapping("${URL.method.TrainingManager.add}")
	public String createTrainingManager(@RequestBody TrainingManager trainingManager) {
		return service.create(trainingManager);
	}

	@GetMapping("${URL.method.TrainingManager.getByUserName}/{userName}")
	public Optional<TrainingManager> getTrainingManager(@PathVariable("userName") String username) {
		return service.get(username);
	}

	@GetMapping("${URL.method.TrainingManager.getAll}")
	public Iterable<TrainingManager> getAllTrainingManagers() {
		return service.getAll();
	}

	@PutMapping("${URL.method.TrainingManager.update}/{userName}")
	public String updateTrainingManager(@PathVariable("userName") String username,
			@RequestBody TrainingManager trainingManager) {
		return service.update(username, trainingManager);
	}

	@DeleteMapping("${URL.method.TrainingManager.delete}/{userName}")
	public String deleteTrainingManager(@PathVariable("userName") String userName) {
		return service.delete(userName);
	}

}
