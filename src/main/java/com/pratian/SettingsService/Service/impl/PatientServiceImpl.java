package com.pratian.SettingsService.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratian.SettingsService.Entities.Patient;
import com.pratian.SettingsService.Exception.PatientAlreadyExistException;
import com.pratian.SettingsService.Exception.PatientNotFoundException;
import com.pratian.SettingsService.Repository.PatientRepository;
import com.pratian.SettingsService.Repository.ProfilePicRepository;
import com.pratian.SettingsService.Service.PatientService;

@Service
public class PatientServiceImpl implements PatientService{

	@Autowired
	private PatientRepository patRepo;
	
	@Autowired
	private ProfilePicRepository pprepo;

	@Override
	public List<Patient> viewAllPatients() {
		// TODO Auto-generated method stub
		List<Patient> patients = patRepo.findAll();
		return patients;
	}

	@Override
	public Patient addPatientDetails(Patient patprof) throws PatientAlreadyExistException {
		// TODO Auto-generated method stub
		Patient pp = null;
		if (patRepo.existsById(patprof.getPatientId())) {
			throw new PatientAlreadyExistException("Patient is Already there !");
		} else {
			pp = patRepo.save(patprof);
		}
		return pp;
	}
	
	
	@Override
	public Patient getPatientbyId(long id) throws PatientNotFoundException {

		Patient patient = null;
		if (!patRepo.existsById(id)) {
			throw new PatientNotFoundException("Patient Id not exists !");

		} else {
			patient = patRepo.findById(id).get();
		}

		return patient;
	}
	
	@Override
	public Patient editPatient(Patient patient) throws PatientNotFoundException {
		Patient patient1 = null;
		if (patRepo.existsById(patient.getPatientId())) {
			patient1 = patRepo.findById(patient.getPatientId()).get();
			patient1.setPatientName(patient.getPatientName());
			patient1.setPatientBloodGroup(patient.getPatientBloodGroup());
			patient1.setDOB(patient.getDOB());
			patient1.setPatientGender(patient.getPatientGender());
			patient1.setAllergies(patient.getAllergies());
			patient1.setActiveIssues(patient.getActiveIssues());
			patient1.setPatientMobileNumber(patient.getPatientMobileNumber());
		} else
			throw new PatientNotFoundException("Patient ID doesn't Exists");
		return patRepo.save(patient1);
	}
	
	@Override
	public Patient viewProfileInfoPatient(long patientId) throws PatientNotFoundException {

		Patient patient = patRepo.findById(patientId)
				.orElseThrow(() -> new PatientNotFoundException("Patient not found by id"));
		return patient;
	}
	
	@Override
	public String deletePatient(long id) throws PatientNotFoundException {
		if (patRepo.existsById(id)) {
			patRepo.deleteById(id);
		} else {
			throw new PatientNotFoundException("Sorry! You aren't there to remove..");
		}
		return "Patient deleted";
	}
	
	@Override
	public String deletePatientProfilePic(long patient_id) throws PatientNotFoundException {
		try {
			long id = pprepo.existsByPatientId(patient_id);
			if (pprepo.existsById(id)) {
				pprepo.deleteById(id);
			}
		} catch (Exception e) {
			throw new PatientNotFoundException("Oops! no profile picture with this id exists..");
		}
		return "Patient profile picture deleted";
	}
}
