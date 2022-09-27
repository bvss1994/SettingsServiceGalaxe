package com.pratian.SettingsService.Entities;
//imports
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data//getters and setters methods
@AllArgsConstructor //parameter constructor
@NoArgsConstructor//default constructor
public class Doctor {
     
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
    
    private long doctorId;
	private String doctorName;
	private long doctorMobileSNumber;
	private String speciality;
	private String workLocation;
	private String doctorEmail;
	private long npiNumber;

}