package com.example.api.patient.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.api.common.dao.PatientRepo;
import com.example.api.common.pojo.Patient;
import com.example.api.patient.exception.PatientException;
import com.example.api.patient.service.IPatientService;

@Service
public class PatientServiceImpl implements IPatientService {
	
	private String dateRegex = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
	
	private String phoneRegex = "^[7-9][0-9]{9}$";
	
	private String validMessageForSave = "Patient Data Submitted Successfully";
	
	private String validMessageForUpdate = "Patient Data Updated Successfully";
	
	private String invalidMessage = "Please Enter a Valid Data";
	
	@Autowired
	private PatientRepo patientRepo;
	
	@Override
	public String saveData(List<Patient> patientList) {
		try {
			patientList.forEach(p -> validateData(p));
			patientList.forEach(p -> patientRepo.save(p));
			return validMessageForSave;
		}
		catch(DataAccessException|NullPointerException e) {
			return invalidMessage;
		}
	}
	
	@Override
	public Optional<Patient> getPatientData(int patientId){
		Optional<Patient> patient = patientRepo.findById(patientId);
		return patient;
	}
	
//	@Override
//	public List<Patient> getPatientData(Patient patient){
//		List<Patient> patientList = patientRepo.findAll();
//		if(patient.getName() == null && patient.getDob() == null && patient.getPhone() == null) {
//			return patientList;
//		}else {
//			Predicate<Patient> filterPredicate = p -> p.getName().equals(patient.getName()) && p.getDob().equals(patient.getDob()) && p.getPhone().equals(patient.getPhone());
//			if(patient.getName() == null) {
//				filterPredicate = p -> p.getDob().equals(patient.getDob()) && p.getPhone().equals(patient.getPhone());
//				if(patient.getDob() == null) {
//					filterPredicate = p -> p.getPhone().equals(patient.getPhone());
//				} else if(patient.getPhone() == null){
//					filterPredicate = p -> p.getDob().equals(patient.getDob());
//				}
//			} else if(patient.getDob() == null) {
//				filterPredicate = p -> p.getName().equals(patient.getName()) && p.getPhone().equals(patient.getPhone());
//				if(patient.getPhone() == null) {
//					filterPredicate = p -> p.getName().equals(patient.getName());
//				}
//			} else if(patient.getPhone() == null){
//				filterPredicate = p -> p.getName().equals(patient.getName()) && p.getDob().equals(patient.getDob());
//			}
//			List<Patient> filterPatientList = patientList.stream().filter(filterPredicate).collect(Collectors.toList());
//			return filterPatientList;
//		}
//	}
	
	@Override
	public String updatePatientData(Patient patient) {
		validateData(patient);
		patientRepo.save(patient);
		return validMessageForUpdate;
	}
	
	private void validateData(Patient patient) {
		boolean dob = Pattern.matches(dateRegex, patient.getDob());
		boolean phone = Pattern.matches(phoneRegex, patient.getPhone());
		if(!dob && !phone) {
			throw new PatientException("Invalid Date of Birth and phone No. Entered : " + patient.getDob() + " and " + patient.getPhone());
		} else if(!dob) {
			throw new PatientException("Invalid Date of Birth : " + patient.getDob());
		} else if(!phone) {
			throw new PatientException("Invalid phone No. Entered : " + patient.getPhone());
		}
	}
}
