package com.pratian.SettingsService.Controller;

import java.io.IOException;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pratian.SettingsService.Entities.Patient;

import com.pratian.SettingsService.Exception.PatientAlreadyExistException;
import com.pratian.SettingsService.Exception.PatientNotFoundException;
import com.pratian.SettingsService.ProfilePicUtil.ProfilePicUtility;
import com.pratian.SettingsService.Entities.ProfilePic;
import com.pratian.SettingsService.Repository.PatientRepository;
import com.pratian.SettingsService.Repository.ProfilePicRepository;
import com.pratian.SettingsService.Service.PatientService;

//import nonapi.io.github.classgraph.utils.StringUtils;

@RestController
@RequestMapping("/settings_patient")
public class PatientSettingsController {

	@Autowired
	private PatientService patService;

	@Autowired
	private ProfilePicRepository pprepo;

	@Autowired
	private PatientRepository prepo;

	@GetMapping("/patient_profile")
	public ResponseEntity<?> viewProfileInfoPatient(long patientId) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(patService.viewProfileInfoPatient(patientId), HttpStatus.OK);
		} catch (PatientNotFoundException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
		return response;
	}

	@PutMapping("/editpatient")
	public ResponseEntity<?> put(@RequestBody Patient patient) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(patService.editPatient(patient), HttpStatus.OK);
		} catch (PatientNotFoundException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
		return response;
	}

	@DeleteMapping("/deletepatient/{id}")
	public ResponseEntity<?> deletePatient(@PathVariable(value = "id") long id) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(patService.deletePatient(id), HttpStatus.OK);
		} catch (PatientNotFoundException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
		return response;
	}

	// Image controller

	@PostMapping(value = "/upload/patient/profilepicture")
	// @RequestMapping(value="/upload/ppic", method=RequestMethod.POST)
	public ResponseEntity<ProfilePicUploadResponse> uploadImagePatient(@RequestParam("patient_id") long patient_id,
			@RequestParam("image") MultipartFile file) throws IOException {
//           Patient pa=new Patient();
		if (prepo.existsById(patient_id)) {
//			Patient Patient=prepo.getOne(patient_id);

			pprepo.save(ProfilePic.builder().image_name(file.getOriginalFilename()).image_type(file.getContentType())
					.image(ProfilePicUtility.compressImage(file.getBytes())).patientId(patient_id).build());
//			        .getPatient().setPatientId(Patient.getPatientId());

//			pprepo.save(ProfilePic.builder().Patient(Patient));

			return ResponseEntity.status(HttpStatus.OK).body(new ProfilePicUploadResponse(
					"Profile picture uploaded successfully: " + file.getOriginalFilename()));
		} else {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ProfilePicUploadResponse("Patient not found by id.. "));

		}
	}

	@GetMapping(path = { "/get/patient/profilepicture/{pid}" })
	public ResponseEntity<byte[]> getImagePatient(@PathVariable("pid") long pid) throws IOException {

		final Optional<ProfilePic> dbImage = pprepo.findByPatientId(pid);

		return ResponseEntity.ok().contentType(MediaType.valueOf(dbImage.get().getImage_type()))
				.body(ProfilePicUtility.decompressImage(dbImage.get().getImage()));
	}

	@DeleteMapping("/deletePatientProfilePic/{patient_id}")
	public ResponseEntity<?> deletePatientProfilePic(@PathVariable(value = "patient_id") long patient_id) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(patService.deletePatientProfilePic(patient_id), HttpStatus.OK);
		} catch (PatientNotFoundException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
		return response;
	}

	@PostMapping("/change/patient/profilepicture")
	public ResponseEntity<ProfilePicUploadResponse> changeImagePatient(@RequestParam("patient_id") long patient_id,
			@RequestParam("image_id") long image_id, @RequestParam("image") MultipartFile file) throws IOException {
		if (prepo.existsById(patient_id) && pprepo.existsById(image_id)) {
//			Doctor Doctor=null;
			// Doctor.setDoctorId(doctor_id);
			pprepo.save(ProfilePic.builder().image_id(image_id).image_name(file.getOriginalFilename())
					.image_type(file.getContentType()).image(ProfilePicUtility.compressImage(file.getBytes()))
					.patientId(patient_id).build());
///	                .setDoctor(Doctor.getDoctorId());
			return ResponseEntity.status(HttpStatus.OK).body(new ProfilePicUploadResponse(
					"Profile picture changed successfully: " + file.getOriginalFilename()));
		} else {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ProfilePicUploadResponse("No profile pic for doctor.. " + file.getOriginalFilename()));
		}
	}

//	    @GetMapping("/loginpatient/{user_name}/{password}")
//		public ResponseEntity<?> patientLogin (@PathVariable (value="user_name") String uname, @PathVariable (value="password") String pass){
//
//			ResponseEntity<?> response=null;
//			try {
//			response= new ResponseEntity<>(service.patientLogin(uname, pass),HttpStatus.OK);
//			}
//		catch(PatientNotFoundException e) {
//			response=new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
//		}
//			return response;
//		}

//		@PutMapping("/logoutpatient")
//		public ResponseEntity<?> patientLogout(@RequestParam (value="patient_id") long id){
//			ResponseEntity<?> response=null;
//			try {
//			response= new ResponseEntity<>(service.patientLogout(id),HttpStatus.OK);
//			}
//		catch(PatientNotFoundException e) {
//			response=new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
//		}
//			return response;
//		}

	@PostMapping("/addPatient")
	public ResponseEntity<?> addPat(@RequestBody Patient pat) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(patService.addPatientDetails(pat), HttpStatus.OK);
		} catch (PatientAlreadyExistException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
		return response;
	}

	
	
	@GetMapping("/getPatients")
	public ResponseEntity<?> get() {
		ResponseEntity<?> response = null;
		response = new ResponseEntity<>(patService.viewAllPatients(), HttpStatus.OK);
		return response;
	}
	
	

}
