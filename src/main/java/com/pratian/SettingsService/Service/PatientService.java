package com.pratian.SettingsService.Service;

import java.util.List;
import com.pratian.SettingsService.Entities.Patient;
import com.pratian.SettingsService.Exception.PatientAlreadyExistException;
import com.pratian.SettingsService.Exception.PatientNotFoundException;


public interface PatientService {
	
	public List<Patient> viewAllPatients();
	public Patient addPatientDetails(Patient patProf) throws PatientAlreadyExistException;
	public Patient getPatientbyId(long id) throws PatientNotFoundException;
	public Patient editPatient(Patient patient) throws PatientNotFoundException;
	Patient viewProfileInfoPatient(long patientId) throws PatientNotFoundException;
	String deletePatient(long id) throws PatientNotFoundException;
	String deletePatientProfilePic(long patient_id) throws PatientNotFoundException;
}
