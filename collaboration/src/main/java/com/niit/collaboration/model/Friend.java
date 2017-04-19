package com.niit.collaboration.model;


	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.Table;

	import org.springframework.stereotype.Component;

	@Entity
	@Table
	@Component
	public class Friend extends BaseDomain{

		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private int id;
		private String userID;
		private String friendID; 
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getUserID() {
			return userID;
		}
		public void setUserID(String userID) {
			this.userID = userID;
		}
		public String getFriendID() {
			return friendID;
		}
		public void setFriendID(String friendID) {
			this.friendID = friendID;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public char getIsOnline() {
			return isOnline;
		}
		public void setIsOnline(char isOnline) {
			this.isOnline = isOnline;
		}
		private String status;
		private char isOnline;

}
