package com.example.api.patient.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.patient.pojo.Patient;
import com.example.api.patient.service.IPatientService;

@RestController
public class PatientController {
	
	@Autowired
	private IPatientService patientServices;
	
	@PostMapping(path = "/save", consumes="application/json")
	public String savePatientData(@RequestBody ArrayList<Patient> patientList){
		return patientServices.saveData(patientList);
	}
	
	@GetMapping(path = "/getData", produces="application/json")
	public List<Patient> getPatientData(@RequestBody Patient patient){
		return patientServices.getPatientData(patient);
	}
	
	@PutMapping(path = "/updateData", consumes="application/json")
	public String updatePatientData(@RequestBody Patient patient) {
		return patientServices.updatePatientData(patient);
	}
}
