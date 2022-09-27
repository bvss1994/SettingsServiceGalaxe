package com.pratian.SettingsService.Controller;

public class ProfilePicUploadResponse {
	    private String message;

	    public ProfilePicUploadResponse(String message) {
	        this.message = message;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }
}
