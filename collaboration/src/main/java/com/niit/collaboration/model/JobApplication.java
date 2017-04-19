package com.niit.collaboration.model;


	
	import java.util.Date;

	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.Table;

	import org.springframework.stereotype.Component;
	@Entity
	@Table
	@Component


	public class JobApplication extends BaseDomain{

		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private int id;
		private String userID;
		private int jobID;
		private Date App_date;
	    private String remarks;
	    private String status;
			
		
		public String getUserID() {
			return userID;
		}
		public void setUserID(String userID) {
			this.userID = userID;
		}
		public int getJobID() {
			return jobID;
		}
		public void setJobID(int jobID) {
			this.jobID = jobID;
		}
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public Date getApp_date() {
			return App_date;
		}
		public void setApp_date(Date app_date) {
			if(App_date==null)
			{
				App_date = new Date( System.currentTimeMillis());
			}
			
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		

}
