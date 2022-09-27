package com.pratian.SettingsService.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratian.SettingsService.Entities.Doctor;
import com.pratian.SettingsService.Exception.DoctorNotFoundException;
import com.pratian.SettingsService.Repository.DoctorRepository;
import com.pratian.SettingsService.Repository.ProfilePicRepository;
import com.pratian.SettingsService.Service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorRepository docrepo;

	@Autowired
	private ProfilePicRepository pprepo;

	@Override
	public List<Doctor> viewAllDoctors() {
		List<Doctor> doctors = docrepo.findAll();
		return doctors;
	}

	@Override
	public Doctor viewProfileInfoDoctor(long doctorId) throws DoctorNotFoundException {
		Doctor doctor = docrepo.findById(doctorId)
				.orElseThrow(() -> new DoctorNotFoundException("Doctor not found by id"));
		return doctor;
	}

	@Override
	public Doctor addDoctorDetails(Doctor doctor) {
		Doctor doc = null;
		doc = docrepo.save(doctor);
		return doc;
	}

	@Override
	public Doctor editDoctor(Doctor doctor) throws DoctorNotFoundException {
		Doctor doctor1;
		if (docrepo.existsById(doctor.getDoctorId())) {
			doctor1 = docrepo.findById(doctor.getDoctorId()).get();
			doctor1.setDoctorName(doctor.getDoctorName());
			doctor1.setDoctorMobileSNumber(doctor.getDoctorMobileSNumber());
			doctor1.setSpeciality(doctor.getSpeciality());
			doctor1.setNpiNumber(doctor.getNpiNumber());
			doctor1.setDoctorEmail(doctor.getDoctorEmail());
			doctor1.setWorkLocation(doctor.getWorkLocation());
		} else
			throw new DoctorNotFoundException("Ouch! We cannot edit you.Please check your id..");
		return docrepo.save(doctor1);
	}

	@Override
	public String deleteDoctor(long id) throws DoctorNotFoundException {
		if (docrepo.existsById(id)) {
			docrepo.deleteById(id);
		} else {
			throw new DoctorNotFoundException("Sorry! You aren't there to remove..");
		}
		return "Doctor deleted";
	}

	@Override
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
}
