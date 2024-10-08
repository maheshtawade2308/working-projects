package com.codewithdurgesh.blog.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.codewithdurgesh.blog.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		//file name
		String name = file.getOriginalFilename();
		
//		if some one uploads other file with diff names so we need to change the name
//		random name generate file
		String randomId = UUID.randomUUID().toString();
		String filename1 = randomId.concat(name.substring(name.lastIndexOf(".")));
		
//		fullpath
		String filePath = path+File.separator+filename1;
		
//		create folder if not created
		File f = new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
//		copy file
		Files.copy(file.getInputStream(), Paths.get(filePath));
		return  filename1;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath = path+ File.separator + fileName;
		InputStream is = new FileInputStream(fullPath);
		
		return is;
	}

}
