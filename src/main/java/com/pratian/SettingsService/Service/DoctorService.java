package com.pratian.SettingsService.Service;

import java.util.List;

import com.pratian.SettingsService.Entities.Doctor;
import com.pratian.SettingsService.Exception.DoctorNotFoundException;

public interface DoctorService {
	
	public List<Doctor> viewAllDoctors();
	public Doctor addDoctorDetails(Doctor doctor);
	Doctor viewProfileInfoDoctor(long doctorId) throws DoctorNotFoundException;
	Doctor editDoctor(Doctor doctor) throws DoctorNotFoundException;
	String deleteDoctor(long id) throws DoctorNotFoundException;
	String deleteDoctorProfilePic(long doctor_id) throws DoctorNotFoundException;
}
