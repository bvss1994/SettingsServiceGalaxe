package com.pratian.SettingsService.Service;


import java.util.List;

import com.pratian.SettingsService.Entities.Doctor;
import com.pratian.SettingsService.Entities.Patient;
import com.pratian.SettingsService.Exception.DoctorAlreadyExistException;
import com.pratian.SettingsService.Exception.DoctorNotFoundException;
import com.pratian.SettingsService.Exception.PatientAlreadyExistException;
import com.pratian.SettingsService.Exception.PatientNotFoundException;


public interface ISettingsService {
	
	public Patient viewProfileInfoPatient(long patientId) throws PatientNotFoundException ;
	
	public Patient editPatient(Patient patient) throws PatientNotFoundException;
	
	public String deletePatient(long id) throws PatientNotFoundException;
	
	public String deletePatientProfilePic(long id) throws PatientNotFoundException;
	
	public List<Patient> patientLogin(String uname, String pass) throws PatientNotFoundException;
	
	public String patientLogout(long id) throws PatientNotFoundException;
	
	
	
	public Doctor viewProfileInfoDoctor(long doctorId) throws DoctorNotFoundException ;

	public Doctor saveDoctorInfo(Doctor Doctor) throws DoctorAlreadyExistException;
	
	public Doctor editDoctor(Doctor doctor) throws DoctorNotFoundException;
	
	public String deleteDoctor(long id) throws DoctorNotFoundException;
	
	public String deleteDoctorProfilePic(long id) throws DoctorNotFoundException;

	public List<Doctor> doctorLogin(String uname, String pass) throws DoctorNotFoundException;

	public String doctorLogout(long id) throws DoctorNotFoundException;

	
	
//	public String doctorLogout(Doctor doctor);
	

	
	
}
