package com.niit.collaboration.dao;

import com.niit.collaboration.model.FileUpload;

public interface FileUploadDAO 
{

	void save(FileUpload fileUpload);
	FileUpload getFile(String username);
}

