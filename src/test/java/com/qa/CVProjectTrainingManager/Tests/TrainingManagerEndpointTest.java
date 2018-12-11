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

import com.qa.TrainingManagerAPI.persistence.domain.CV;
import com.qa.TrainingManagerAPI.persistence.domain.TrainingManager;
import com.qa.TrainingManagerAPI.rest.Endpoint;
import com.qa.TrainingManagerAPI.service.TrainingManagerService;
import com.qa.TrainingManagerAPI.util.Constants;


@RunWith(MockitoJUnitRunner.class)
public class TrainingManagerEndpointTest {
	
	@InjectMocks
	private Endpoint endpoint;

	@Mock
	private TrainingManagerService service;
	
	@Before
	public void setup() {
		endpoint.setService(service);
	}

	@Test
	public void testGetAllCVs() {
		CV cv = new CV();
		List<CV >listCV=new ArrayList<CV>();
		listCV.add(cv);
		Mockito.when(service.getAllCVs()).thenReturn(listCV);
		Assert.assertEquals(listCV, endpoint.getAllCVs());
	}
	
	@Test
	public void testCreateTrainingManager() {
		TrainingManager mockTrainingManager = new TrainingManager();
		Mockito.when(service.createTrainingManager(mockTrainingManager)).thenReturn(Constants.TRAINING_MANAGER);
		Assert.assertEquals(Constants.TRAINING_MANAGER, endpoint.createTrainingManager(mockTrainingManager));
	}
	
	@Test
	public void testFindTrainingManager() {
		TrainingManager mockTrainingManager = new TrainingManager();
		Mockito.when(service.findTrainingManagerByUsername(mockTrainingManager.getUsername())).thenReturn(Optional.of(mockTrainingManager));
		Assert.assertEquals(Optional.of(mockTrainingManager), endpoint.findTrainingManagerByUsername(mockTrainingManager.getUsername()));
	}
	
	@Test
	public void testGetAllTrainingManagers() {
		TrainingManager mockTrainingManager = new TrainingManager();
		List<TrainingManager> listTrainingManagers = new ArrayList<TrainingManager>();
		listTrainingManagers.add(mockTrainingManager);
		Mockito.when(service.getAllTrainingManagers()).thenReturn(listTrainingManagers);
		Assert.assertEquals(listTrainingManagers, endpoint.getAllTrainingManagers());
	}
	
	@Test
	public void testUpdateTrainingManager() {
		TrainingManager mockTrainingManager = new TrainingManager();
		mockTrainingManager.setUsername("user");
		mockTrainingManager.setID(1L);
		TrainingManager newTrainingManager = new TrainingManager();
		newTrainingManager.setFirstName("1st");
		newTrainingManager.setLastName("end");
		newTrainingManager.setUsername("name");
		newTrainingManager.setID(mockTrainingManager.getID());
		Mockito.when(service.updateTrainingManager(mockTrainingManager.getUsername(),newTrainingManager)).thenReturn(newTrainingManager);
		Assert.assertEquals(newTrainingManager.getFirstName(), endpoint.updateTrainingManager(mockTrainingManager.getUsername(),newTrainingManager).getFirstName());
		Assert.assertEquals(newTrainingManager.getLastName(), endpoint.updateTrainingManager(mockTrainingManager.getUsername(),newTrainingManager).getLastName());
		Assert.assertEquals(newTrainingManager.getUsername(), endpoint.updateTrainingManager(mockTrainingManager.getUsername(),newTrainingManager).getUsername());
		Assert.assertEquals(mockTrainingManager.getID(), endpoint.updateTrainingManager(mockTrainingManager.getUsername(),newTrainingManager).getID());
	}


}
