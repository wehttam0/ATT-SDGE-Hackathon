package com.cognition.model;

public class User {

	private int id;

	private String googleToken;

	private String username;

	private String firstName;

	private String lastName;

	private String fullName;

	public User() {

	}

	public int getId() {

		return id;

	}

	public void setId(int id) {

		this.id = id;

	}

	public String getGoogleToken() {

		return googleToken;

	}

	public void setGoogleToken(String googleToken) {

		this.googleToken = googleToken;

	}

	public String getUsername() {

		return username;

	}

	public void setUsername(String username) {

		this.username = username;

	}

	public String getFirstName() {

		return firstName;

	}

	public void setFirstName(String firstName) {

		this.firstName = firstName;

	}

	public String getLastName() {

		return lastName;

	}

	public void setLastName(String lastName) {

		this.lastName = lastName;

	}

	public String getFullName() {

		return fullName;

	}

	public void setFullName(String fullName) {

		this.fullName = fullName;

	}

}
