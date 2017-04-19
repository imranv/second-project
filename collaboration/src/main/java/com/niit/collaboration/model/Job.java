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
public class Job extends BaseDomain {

	
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private int id;
		private String title;
	
		private String description;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
	
	
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
		public String getQualification() {
			return qualification;
		}
		public void setQualification(String qualification) {
			this.qualification = qualification;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public Date getInterviewdate() {
			return interviewdate;
		}
		public void setInterviewdate(Date interviewdate) {
			this.interviewdate = interviewdate;
		}
		private Date interviewdate;
		private String qualification;
		private String status;
}