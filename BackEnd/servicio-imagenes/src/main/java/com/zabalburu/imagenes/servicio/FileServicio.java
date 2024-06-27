package com.zabalburu.imagenes.servicio;

import java.io.*;
import java.nio.file.Files;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.zabalburu.imagenes.model.FileData;
import com.zabalburu.imagenes.repository.FileDataRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileServicio {

	private final FileDataRepo repo;
	
	//private final String FOLDER_PATH="D:\\MyWorkspace-Angular\\";
	private final String FOLDER_PATH= "D:\\MyWorkspace-Angular\\myworkspace\\projects\\curso-teca\\src\\assets\\img\\";
	
	public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath= FOLDER_PATH + file.getOriginalFilename();

        FileData fileData = repo.save(FileData.builder()
                .nombre(file.getOriginalFilename())
                .tipo(file.getContentType())
                .path(filePath).build());

        file.transferTo(new File(filePath));

        if (fileData != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }
	
	public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional <FileData> fileData = repo.findByNombre(fileName);
        String filePath = fileData.get().getPath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }
	
	
}
