package com.example.api.patient.service;

import java.util.List;
import java.util.Optional;

import com.example.api.common.pojo.Patient;

public interface IPatientService {

	public String saveData(List<Patient> patientList);
	
	public Optional<Patient> getPatientData(int patientId);

	public String updatePatientData(Patient patient);
	
}
