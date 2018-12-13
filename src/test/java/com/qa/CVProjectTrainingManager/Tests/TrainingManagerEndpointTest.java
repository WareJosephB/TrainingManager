package com.qa.CVProjectTrainingManager.Tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.TrainingManagerAPI.rest.Endpoint;
import com.qa.TrainingManagerAPI.service.TrainingManagerService;
import com.qa.TrainingManagerAPI.util.Constants;
import com.qa.persistence.domain.TrainingManager;

@RunWith(MockitoJUnitRunner.class)
public class TrainingManagerEndpointTest {

	@InjectMocks
	private Endpoint endpoint;

	@Mock
	private TrainingManagerService service;

	@Test
	public void testCreateTrainingManager() {
		TrainingManager mockTrainingManager = new TrainingManager();
		Mockito.when(service.create(mockTrainingManager)).thenReturn(Constants.USER_ADDED_MESSAGE);
		Assert.assertEquals(Constants.USER_ADDED_MESSAGE, endpoint.createTrainingManager(mockTrainingManager));
	}

	@Test
	public void testFindTrainingManager() {
		TrainingManager mockTrainingManager = new TrainingManager();
		Mockito.when(service.get(mockTrainingManager.getUsername())).thenReturn(Optional.of(mockTrainingManager));
		Assert.assertEquals(Optional.of(mockTrainingManager),
				endpoint.getTrainingManager(mockTrainingManager.getUsername()));
	}

	@Test
	public void testGetAllTrainingManagers() {
		TrainingManager mockTrainingManager = new TrainingManager();
		List<TrainingManager> listTrainingManagers = new ArrayList<TrainingManager>();
		listTrainingManagers.add(mockTrainingManager);
		Mockito.when(service.getAll()).thenReturn(listTrainingManagers);
		Assert.assertEquals(listTrainingManagers, endpoint.getAllTrainingManagers());
	}

}
