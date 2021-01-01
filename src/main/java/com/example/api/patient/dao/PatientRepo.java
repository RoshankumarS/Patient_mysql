package com.example.api.patient.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.patient.pojo.Patient;

public interface PatientRepo extends JpaRepository<Patient, Integer>{

}
