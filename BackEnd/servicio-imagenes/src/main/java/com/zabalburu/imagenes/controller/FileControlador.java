package com.zabalburu.imagenes.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zabalburu.imagenes.servicio.FileServicio;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/imagenes")
@RequiredArgsConstructor
public class FileControlador {

	private final FileServicio servicio;
	
	@PostMapping("")
	public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("imagen")MultipartFile file) throws IOException {
		String uploadImage = servicio.uploadImageToFileSystem(file);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(uploadImage);
	}

	@GetMapping("/{fileName}")
	public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
		byte[] imageData = servicio.downloadImageFromFileSystem(fileName);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);

	}
}
