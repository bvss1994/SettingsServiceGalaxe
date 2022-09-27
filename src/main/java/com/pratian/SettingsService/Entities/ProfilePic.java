package com.pratian.SettingsService.Entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data//getters and setters methods
@AllArgsConstructor //parameter constructor
@NoArgsConstructor//default constructor
@Table(name = "Profile_Picture")
public class ProfilePic {
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long image_id;
		
	private String image_name;

	private String image_type;

	@Column(name = "image", length = 100000)
	private byte[] image;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name="patient_id")
	private long patientId;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name="doctor_id")
	private long doctorId;

	

	

	

	
	
	
}
