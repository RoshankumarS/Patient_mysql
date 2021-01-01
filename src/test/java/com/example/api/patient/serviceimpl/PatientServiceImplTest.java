package com.example.api.patient.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.api.patient.dao.PatientRepo;
import com.example.api.patient.exception.PatientException;
import com.example.api.patient.pojo.Patient;

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceImplTest {

	@InjectMocks
	private PatientServiceImpl patientServiceImpl;
	
	@Mock
	private PatientRepo patientRepo;
	
	@Test
	public void givenValidData_shouldSaveData() {
		
		//Given
		
		ArrayList<Patient> patientList = new ArrayList<Patient>();
		patientList.add(getPatientData());
		
		//When
		
		String message = patientServiceImpl.saveData(patientList);
		
		//Then
		
		Assert.assertFalse(message.isEmpty());
	}
	
	@Test(expected = PatientException.class)
	public void givenInvalidDobAndPhone_shouldThrowException() {
		
		//Given
		
		Patient patientData = new Patient();
		patientData.setName("abc");
		patientData.setDob("30-02-2020");
		patientData.setPhone("88888888888");
		ArrayList<Patient> patientList = new ArrayList<Patient>();
		patientList.add(patientData);
		
		//When
		
		patientServiceImpl.saveData(patientList);
	}
	
	@Test(expected = PatientException.class)
	public void givenInvalidDob_shouldThrowException() {
		
		//Given
		
		Patient patientData = new Patient();
		patientData.setName("abc");
		patientData.setDob("30-02-2020");
		patientData.setPhone("8888888888");
		ArrayList<Patient> patientList = new ArrayList<Patient>();
		patientList.add(patientData);
		
		//When
		
		patientServiceImpl.saveData(patientList);
	}
	
	@Test(expected = PatientException.class)
	public void givenInvalidPhone_shouldThrowException() {
		
		//Given
		
		Patient patientData = new Patient();
		patientData.setName("abc");
		patientData.setDob("23-02-2020");
		patientData.setPhone("88888888888");
		ArrayList<Patient> patientList = new ArrayList<Patient>();
		patientList.add(patientData);
		
		//When
		
		patientServiceImpl.saveData(patientList);
	}
	
	@Test
	public void givenInvalidData_shouldThrowException() {
		
		//Given
		
		Patient patientData = new Patient();
		patientData.setName("abc");
		ArrayList<Patient> patientList = new ArrayList<Patient>();
		patientList.add(patientData);
		
		//When
		
		patientServiceImpl.saveData(patientList);
	}
	
	@Test
	public void updateDataWithValidData_shouldUpdateData() {
		
		//Given
		
		
		//When
		
		String message = patientServiceImpl.updatePatientData(getPatientData());
		
		//Then
		
		Assert.assertFalse(message.isEmpty());
		
	}
	
	@Test(expected = PatientException.class)
	public void updateDataWithInvalidData_throwException() {
		
		//Given
		
		Patient patientData = new Patient();
		patientData.setName("abcd");
		patientData.setDob("23-02-2020");
		patientData.setPhone("88888888888");
		
		//When
		
		patientServiceImpl.updatePatientData(patientData);
				
	}
	
	@Test
	public void getData_returnListOfData() {
		
		//Given
		
		List<Patient> patientList = new ArrayList<Patient>();
		patientList.add(getPatientData());
		Mockito.doReturn(patientList).when(patientRepo).findAll();
		
		//When
		
		List<Patient> patientListReturned = patientServiceImpl.getPatientData(new Patient());
		
		//Then
		
		Assert.assertEquals(patientList.get(0).getDob(), patientListReturned.get(0).getDob());
	}
	
	@Test
	public void filterDataOnGivenName_returnFilterData() {
		
		//Given
		
		Patient patient = new Patient();
		patient.setName("abc");
		List<Patient> patientList = new ArrayList<Patient>();
		patientList.add(getPatientData());
		Mockito.doReturn(patientList).when(patientRepo).findAll();
		
		//When
		
		List<Patient> patientListReturned = patientServiceImpl.getPatientData(patient);
		
		//Then
		
		Assert.assertEquals(patient.getName(), patientListReturned.get(0).getName());
		
	}
	
	@Test
	public void filterDataOnGivenDob_returnFilterData() {
		
		//Given
		
		Patient patient = new Patient();
		patient.setDob("23-02-2020");
		List<Patient> patientList = new ArrayList<Patient>();
		patientList.add(getPatientData());
		Mockito.doReturn(patientList).when(patientRepo).findAll();
		
		//When
		
		List<Patient> patientListReturned = patientServiceImpl.getPatientData(patient);
		
		//Then
		
		Assert.assertEquals(patient.getDob(), patientListReturned.get(0).getDob());
		
	}
	
	@Test
	public void filterDataOnGivenPhone_returnFilterData() {
		
		//Given
		
		Patient patient = new Patient();
		patient.setPhone("8888888888");
		List<Patient> patientList = new ArrayList<Patient>();
		patientList.add(getPatientData());
		Mockito.doReturn(patientList).when(patientRepo).findAll();
		
		//When
		
		List<Patient> patientListReturned = patientServiceImpl.getPatientData(patient);
		
		//Then
		
		Assert.assertEquals(patient.getPhone(), patientListReturned.get(0).getPhone());
		
	}
	
	@Test
	public void filterDataOnGivenNameAndDob_returnFilterData() {
		
		//Given
		
		Patient patient = new Patient();
		patient.setName("abc");
		patient.setDob("23-02-2020");
		List<Patient> patientList = new ArrayList<Patient>();
		patientList.add(getPatientData());
		Mockito.doReturn(patientList).when(patientRepo).findAll();
		
		//When
		
		List<Patient> patientListReturned = patientServiceImpl.getPatientData(patient);
		
		//Then
		
		Assert.assertEquals(patient.getName(), patientListReturned.get(0).getName());
		Assert.assertEquals(patient.getDob(), patientListReturned.get(0).getDob());
		
	}
	
	@Test
	public void filterDataOnGivenNameAndPhone_returnFilterData() {
		
		//Given
		
		Patient patient = new Patient();
		patient.setName("abc");
		patient.setPhone("8888888888");
		List<Patient> patientList = new ArrayList<Patient>();
		patientList.add(getPatientData());
		Mockito.doReturn(patientList).when(patientRepo).findAll();
		
		//When
		
		List<Patient> patientListReturned = patientServiceImpl.getPatientData(patient);
		
		//Then
		
		Assert.assertEquals(patient.getName(), patientListReturned.get(0).getName());
		Assert.assertEquals(patient.getPhone(), patientListReturned.get(0).getPhone());
		
	}

	@Test
	public void filterDataOnGivenDobAndPhone_returnFilterData() {
		
		//Given
		
		Patient patient = new Patient();
		patient.setDob("23-02-2020");
		patient.setPhone("8888888888");
		List<Patient> patientList = new ArrayList<Patient>();
		patientList.add(getPatientData());
		Mockito.doReturn(patientList).when(patientRepo).findAll();
		
		//When
		
		List<Patient> patientListReturned = patientServiceImpl.getPatientData(patient);
		
		//Then
		
		Assert.assertEquals(patient.getDob(), patientListReturned.get(0).getDob());
		Assert.assertEquals(patient.getPhone(), patientListReturned.get(0).getPhone());
		
	}
	
	@Test
	public void filterDataOnGivenNameDobAndPhone_returnFilterData() {
		
		//Given
		
		Patient patient = new Patient();
		patient.setName("abc");
		patient.setDob("23-02-2020");
		patient.setPhone("8888888888");
		List<Patient> patientList = new ArrayList<Patient>();
		patientList.add(getPatientData());
		Mockito.doReturn(patientList).when(patientRepo).findAll();
		
		//When
		
		List<Patient> patientListReturned = patientServiceImpl.getPatientData(patient);
		
		//Then
		
		Assert.assertEquals(patient.getName(), patientListReturned.get(0).getName());
		Assert.assertEquals(patient.getDob(), patientListReturned.get(0).getDob());
		Assert.assertEquals(patient.getPhone(), patientListReturned.get(0).getPhone());
		
	}
	
	@Test
	public void filterDataGivenInvalidData_returnEmptyList() {
		
		//Given
		
		Patient patient = new Patient();
		patient.setName("xyz");
		List<Patient> patientList = new ArrayList<Patient>();
		patientList.add(getPatientData());
		Mockito.doReturn(patientList).when(patientRepo).findAll();
		
		//When
		
		List<Patient> patientListReturned = patientServiceImpl.getPatientData(patient);
		
		//Then
		
		Assert.assertEquals(0,patientListReturned.size());
		
	}
	
	private Patient getPatientData() {
		Patient patientData = new Patient();
		patientData.setName("abc");
		patientData.setDob("23-02-2020");
		patientData.setPhone("8888888888");
		return patientData;
	}
	
}
