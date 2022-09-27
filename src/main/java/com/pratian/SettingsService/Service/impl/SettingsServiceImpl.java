package com.pratian.SettingsService.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratian.SettingsService.Entities.Doctor;
import com.pratian.SettingsService.Entities.Patient;
import com.pratian.SettingsService.Exception.DoctorAlreadyExistException;
import com.pratian.SettingsService.Exception.DoctorNotFoundException;
import com.pratian.SettingsService.Exception.PatientAlreadyExistException;
import com.pratian.SettingsService.Exception.PatientNotFoundException;

import com.pratian.SettingsService.Repository.DoctorRepository;
import com.pratian.SettingsService.Repository.PatientRepository;
import com.pratian.SettingsService.Repository.ProfilePicRepository;
import com.pratian.SettingsService.Service.ISettingsService;

@Service
public  class SettingsServiceImpl implements ISettingsService {
	@Autowired
	private PatientRepository pRepo;

	public SettingsServiceImpl(PatientRepository pRepo) {
		
		this.pRepo=pRepo;
	}

	@Autowired
	private DoctorRepository dRepo;

	public void SettingServiceImpl(DoctorRepository dRepo) {
		this.dRepo = dRepo;
	}

	@Autowired
	private ProfilePicRepository pprepo;

	public void SettingServiceImpl(ProfilePicRepository pprepo) {
		this.pprepo = pprepo;
	}

	@Override
	public Patient viewProfileInfoPatient(long patientId) throws PatientNotFoundException {

		Patient patient = pRepo.findById(patientId)
				.orElseThrow(() -> new PatientNotFoundException("Patient not found by id"));
		return patient;
	}

	public Patient editPatient(Patient patient) throws PatientNotFoundException {
		Patient patient1;
		if (pRepo.existsById(patient.getPatientId())) {
			patient1 = pRepo.findById(patient.getPatientId()).get();

			patient1.setPatientBloodGroup(patient.getPatientBloodGroup());
			patient1.setDOB(patient.getDOB());
			patient1.setAddress(patient.getAddress());
			patient1.setMedicalProblems(patient.getMedicalProblems());
			patient1.setPatientName(patient.getPatientName());
			patient1.setPatientMobileNumber(patient.getPatientMobileNumber());
			patient1.setActiveIssues(patient.getActiveIssues());
			patient1.setAllergies(patient.getAllergies());
			patient1.setEmail(patient.getEmail());
			patient1.setAddress(patient.getAddress());
			patient1.setPatientAge(patient.getPatientAge());
			patient1.setPatientHeight(patient.getPatientHeight());
		} else
			throw new PatientNotFoundException("Oops! We cannot edit you.Please check your id..");
		return pRepo.save(patient1);
	}

	@Override
	public String deletePatient(long id) throws PatientNotFoundException {
		if (pRepo.existsById(id)) {
			pRepo.deleteById(id);
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

	@Override
	public Doctor viewProfileInfoDoctor(long doctorId) throws DoctorNotFoundException {
		Doctor doctor = dRepo.findById(doctorId)
				.orElseThrow(() -> new DoctorNotFoundException("Doctor not found by id"));
		return doctor;
	}

	@Override
	public Doctor saveDoctorInfo(Doctor Doctor) throws DoctorAlreadyExistException {
		Doctor doctor = null;
		if (dRepo.existsById(Doctor.getDoctorId())) {
			throw new DoctorAlreadyExistException("Oops! You already exist..");
		} else {
			doctor = dRepo.save(Doctor);
		}
		return doctor;
	}

	@Override
	public Doctor editDoctor(Doctor doctor) throws DoctorNotFoundException {
		Doctor doctor1;
		if (dRepo.existsById(doctor.getDoctorId())) {
			doctor1 = dRepo.findById(doctor.getDoctorId()).get();
			doctor1.setDoctorName(doctor.getDoctorName());
			doctor1.setDoctorMobileSNumber(doctor.getDoctorMobileSNumber());
			doctor1.setSpeciality(doctor.getSpeciality());
			doctor1.setNpiNumber(doctor.getNpiNumber());
			doctor1.setDoctorEmail(doctor.getDoctorEmail());
			doctor1.setWorkLocation(doctor.getWorkLocation());
		} else
			throw new DoctorNotFoundException("Ouch! We cannot edit you.Please check your id..");
		return dRepo.save(doctor1);
	}

	@Override
	public String deleteDoctor(long id) throws DoctorNotFoundException {
		if (dRepo.existsById(id)) {
			dRepo.deleteById(id);
		} else {
			throw new DoctorNotFoundException("Sorry! You aren't there to remove..");
		}
		return "Doctor deleted";
	}

	public String deleteDoctorProfilePic(long doctor_id) throws DoctorNotFoundException {
		try {
			long image_id = pprepo.existsByDoctorId(doctor_id);

			if (pprepo.existsById(image_id)) {
				pprepo.deleteById(image_id);
			}
		} catch (Exception e) {
			throw new DoctorNotFoundException("Oops! no profile picture with this id exists..");
		}
		return "Doctor profile picture deleted";
	}

	@Override
	public List<Patient> patientLogin(String uname, String pass) throws PatientNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String patientLogout(long id) throws PatientNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Doctor> doctorLogin(String uname, String pass) throws DoctorNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String doctorLogout(long id) throws DoctorNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

//@Override
//public List<Patient> patientLogin(String uname, String pass) throws PatientNotFoundException {
//	List<Patient> patients=pRepo.patientLogin(uname, pass);
//	Patient p=new Patient();
//	if(patients.isEmpty()) {
//		throw new PatientNotFoundException("Wrong Patient credentials");
//	}
//	else {
//		 p.setPloggedIn(true);
//		 pRepo.save(p);
//	}
//	return patients;
//}

//@Override
//public String patientLogout(long id) throws PatientNotFoundException {
//	Patient p=new Patient();
//	// && p.isPloggedIn()==true
//	if(pRepo.existsById(id)) {
//		p.setPloggedIn(false);
//		pRepo.save(p);
//		return "Patient logged out";
//	}
//	else {
//		throw new PatientNotFoundException("Sorry! You aren't logged in..");
//	}
//	
//}

//@Override
//public List<Doctor> doctorLogin(String uname, String pass) throws DoctorNotFoundException {
//	List<Doctor> doctors=dRepo.doctorLogin(uname, pass);
//	Doctor d=new Doctor();
//	if(doctors.isEmpty()) {
//		throw new DoctorNotFoundException("Wrong Doctor's credentials");
//	}
//	else {
//		 d.setDloggedIn(true);
//		 dRepo.save(d);
//	}
//	return doctors;
//}

//@Override
//public String doctorLogout(long id) throws DoctorNotFoundException {
//	Doctor d=new Doctor();
//	if(dRepo.existsById(id)) {
//		d.setDloggedIn(false);
//		dRepo.save(d);
//		return "Doctor logged out";
//	}
//	else {
//		throw new DoctorNotFoundException("Sorry! You aren't logged in..");
//	}
//	
//}

}
