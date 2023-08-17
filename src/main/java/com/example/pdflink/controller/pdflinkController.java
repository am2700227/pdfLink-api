package com.example.pdflink.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.pdflink.entity.pdflinkEntity;
import com.example.pdflink.repo.pdflinkRepo;

@RestController
@CrossOrigin("*")
public class pdflinkController {
	
	@Autowired
	private pdflinkRepo pdfImgRep;
	
	@PostMapping(value = "/uploadimage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	
	public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
		try {
			pdflinkEntity bookImg = new pdflinkEntity();
			bookImg.setName(file.getOriginalFilename());
			bookImg.setType(file.getContentType());
			bookImg.setData(file.getBytes());

			pdfImgRep.save(bookImg);

			return ResponseEntity.ok("Image uploaded successfully!!!");

		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload the image.");
		}
	}
		
		@GetMapping(value = "/getImage/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
		public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) {
			Optional<pdflinkEntity> imageOptional = pdfImgRep.findById(id);
			if (imageOptional.isPresent()) {
				pdflinkEntity image = imageOptional.get();
				return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image.getData());
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
}
