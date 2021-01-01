package com.example.api.patient.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.api.patient.pojo.Patient;
import com.example.api.patient.service.IPatientService;

@RunWith(MockitoJUnitRunner.class)
public class PatientControllerTest {
	
	@InjectMocks
	private PatientController patientController;
	
	@Mock
	private IPatientService patientServices;

	@Test
	public void givenValidInput_shouldSavePatientData() {
		
		//Given
		
		ArrayList<Patient> patientList = new ArrayList<Patient>();
		patientList.add(getPatientData());
		Mockito.doReturn("def").when(patientServices).saveData(patientList);
		
		//When
		
		String message = patientController.savePatientData(patientList);
		
		//Then
		
		Assert.assertFalse(message.isEmpty());
	}
	
	@Test
	public void getData_shouldReturnPatientData() {
		
		//Given
		
		Patient patient = new Patient();
		ArrayList<Patient> patientList = new ArrayList<Patient>();
		patientList.add(getPatientData());
		Mockito.doReturn(patientList).when(patientServices).getPatientData(patient);
		
		//When
		
		List<Patient> patientListReturned = patientController.getPatientData(patient);
		
		//Then
		
		Assert.assertEquals(patientList.get(0).getName(), patientListReturned.get(0).getName());
		
	}
	
	@Test
	public void updatePatientDataWithValidData_shouldUpdateData() {
		
		//Given
		
		Patient patient = getPatientData();
		Mockito.doReturn("abc").when(patientServices).updatePatientData(patient);
		
		//When
		
		String message = patientController.updatePatientData(patient);
		
		//Then
		
		Assert.assertFalse(message.isEmpty());
		
	}
	
	private Patient getPatientData() {
		Patient patientData = new Patient();
		patientData.setName("abc");
		patientData.setDob("23-02-2020");
		patientData.setPhone("8888888888");
		return patientData;
	}

}
