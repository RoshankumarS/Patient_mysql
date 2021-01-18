package com.example.api.patient.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.common.pojo.Patient;
import com.example.api.patient.service.IPatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	private IPatientService patientServices;
	
	@PostMapping(path = "/save", consumes="application/json")
	public String savePatientData(@RequestBody ArrayList<Patient> patientList){
		return patientServices.saveData(patientList);
	}
	
	@GetMapping(path = "/get/{patientId}", produces="application/json")
	public Optional<Patient> getPatientData(@PathVariable("patientId") int patientId){
		return patientServices.getPatientData(patientId);
	}
	
	@PutMapping(path = "/update", consumes="application/json")
	public String updatePatientData(@RequestBody Patient patient) {
		return patientServices.updatePatientData(patient);
	}
}
