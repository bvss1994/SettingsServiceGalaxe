package com.pratian.SettingsService.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long patientId;
	private String patientName;
	private int patientAge;
	private String email;
	private String patientGender;
	private String Address;
	private String DOB;
	private long patientMobileNumber;
	private int patientHeight;
	private String patientBloodGroup;
	private String allergies;
	private String activeIssues;
	private String medicalProblems;

}