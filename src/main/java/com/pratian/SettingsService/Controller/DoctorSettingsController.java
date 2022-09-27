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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pratian.SettingsService.Entities.Doctor;
import com.pratian.SettingsService.Entities.ProfilePic;
import com.pratian.SettingsService.Exception.DoctorNotFoundException;
import com.pratian.SettingsService.ProfilePicUtil.ProfilePicUtility;
import com.pratian.SettingsService.Repository.DoctorRepository;

import com.pratian.SettingsService.Repository.ProfilePicRepository;
import com.pratian.SettingsService.Service.DoctorService;

@RestController
@RequestMapping("/settings_doctor")
public class DoctorSettingsController {


	@Autowired
	private DoctorService docService;

	@Autowired
	private ProfilePicRepository pprepo;

	@Autowired
	private DoctorRepository drepo;

	@GetMapping("/doctor_profile")
	public ResponseEntity<?> viewProfileInfoDoctor(long doctorId) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(docService.viewProfileInfoDoctor(doctorId), HttpStatus.OK);
		} catch (DoctorNotFoundException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
		return response;
	}

	@PutMapping("/editdoctor")
	public ResponseEntity<?> put(@RequestBody Doctor doctor) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(docService.editDoctor(doctor), HttpStatus.OK);
		} catch (DoctorNotFoundException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
		return response;
	}

	@DeleteMapping("/deletedoctor/{id}")
	public ResponseEntity<?> deleteDoctor(@PathVariable(value = "id") long id) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(docService.deleteDoctor(id), HttpStatus.OK);
		} catch (DoctorNotFoundException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}

		return response;
	}

	@PostMapping("/upload/doctor/profilepicture")
	public ResponseEntity<ProfilePicUploadResponse> uploadImageDoctor(@RequestParam("doctor_id") long doctor_id,
			@RequestParam("image") MultipartFile file) throws IOException {
		if (drepo.existsById(doctor_id)) {
//		Doctor Doctor=null;
			// Doctor.setDoctorId(doctor_id);
			pprepo.save(ProfilePic.builder().image_name(file.getOriginalFilename()).image_type(file.getContentType())
					.image(ProfilePicUtility.compressImage(file.getBytes())).doctorId(doctor_id).build());
//                .setDoctor(Doctor.getDoctorId());
			return ResponseEntity.status(HttpStatus.OK).body(new ProfilePicUploadResponse(
					"Profile picture uploaded successfully: " + file.getOriginalFilename()));
		} else {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ProfilePicUploadResponse("Doctor not found by id.. " + file.getOriginalFilename()));
		}
	}

	@GetMapping("/get/doctor/profilepicture/{did}")
	public ResponseEntity<byte[]> getImageDoctor(@PathVariable("did") long did) throws IOException {

		final Optional<ProfilePic> dbImage = pprepo.findByDoctorId(did);

		return ResponseEntity.ok().contentType(MediaType.valueOf(dbImage.get().getImage_type()))
				.body(ProfilePicUtility.decompressImage(dbImage.get().getImage()));
		// throw new DoctorNotFoundException("Doctor doesnot have a profile picture..");
	}

	@DeleteMapping("/deleteDoctorProfilePic")
	public ResponseEntity<?> deleteDoctorProfilePic(@RequestParam(value = "doctor_id") long doctor_id) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(docService.deleteDoctorProfilePic(doctor_id), HttpStatus.OK);
		} catch (DoctorNotFoundException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
		return response;
	}

	@PostMapping("/change/doctor/profilepicture")
	public ResponseEntity<ProfilePicUploadResponse> changeImageDoctor(@RequestParam("doctor_id") long doctor_id,
			@RequestParam("image_id") long image_id, @RequestParam("image") MultipartFile file) throws IOException {
		if (drepo.existsById(doctor_id) && pprepo.existsById(image_id)) {
//			Doctor Doctor=null;
			// Doctor.setDoctorId(doctor_id);
			pprepo.save(ProfilePic.builder().image_id(image_id).image_name(file.getOriginalFilename())
					.image_type(file.getContentType()).image(ProfilePicUtility.compressImage(file.getBytes()))
					.doctorId(doctor_id).build());
//	                .setDoctor(Doctor.getDoctorId());
			return ResponseEntity.status(HttpStatus.OK).body(new ProfilePicUploadResponse(
					"Profile picture changed successfully: " + file.getOriginalFilename()));
		} else {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ProfilePicUploadResponse("No profile pic for doctor.. " + file.getOriginalFilename()));
		}
	}

//	    @GetMapping("/logindoctor/{user_name}/{password}")
//		public ResponseEntity<?> doctorLogin(@PathVariable (value="user_name") String uname, @PathVariable (value="password") String pass){
//			ResponseEntity<?> response=null;
//			try {
//			response= new ResponseEntity<>(service.doctorLogin(uname, pass),HttpStatus.OK);
//			}
//		catch(DoctorNotFoundException e) {
//			response=new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
//		}
//			return response;
//		}

//	    @PutMapping("/logoutdoctor")
//		public ResponseEntity<?> patientLogout(@RequestParam (value="doctor_id") long id){
//			ResponseEntity<?> response=null;
//			try {
//			response= new ResponseEntity<>(service.doctorLogout(id),HttpStatus.OK);
//			}
//		catch(DoctorNotFoundException e) {
//			response=new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
//		}
//			return response;
//		}

	@PostMapping("/addDoctor")
	public ResponseEntity<?> addDoc(@RequestBody Doctor doctor) {
		ResponseEntity<?> response = null;

		response = new ResponseEntity<>(docService.addDoctorDetails(doctor), HttpStatus.OK);

		return response;
	}

	@GetMapping("/getDoctor")
	public ResponseEntity<?> get() {
		ResponseEntity<?> response = null;
		response = new ResponseEntity<>(docService.viewAllDoctors(), HttpStatus.OK);
		return response;
	}

}
