package com.qa.persistence.domain;

public class UserRequest {

	public enum requestType {
		CREATE, UPDATE, DELETE, READ, READALL, PROMOTE, DELETEALL, ADDCV
	}

	private requestType howToAct;
	private User userToAddOrUpdate;

	public UserRequest(User user) {
		this.setUserToAddOrUpdate(user);
	}

	public UserRequest() {

	}

	public requestType getHowToAct() {
		return howToAct;
	}

	public void setHowToAct(requestType howToAct) {
		this.howToAct = howToAct;
	}

	public User getUserToAddOrUpdate() {
		return userToAddOrUpdate;
	}

	public void setUserToAddOrUpdate(User userToAddOrUpdate) {
		this.userToAddOrUpdate = userToAddOrUpdate;
	}

}
