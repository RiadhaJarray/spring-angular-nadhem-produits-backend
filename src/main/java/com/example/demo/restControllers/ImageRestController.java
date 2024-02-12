package com.example.demo.restControllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entities.Image;
import com.example.demo.services.ImageService;

@RestController
@RequestMapping("/api/image")
@CrossOrigin(origins = "*")
public class ImageRestController {

	@Autowired
	ImageService imageService ;

	@PostMapping("/upload")
	public Image uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
		return imageService.uplaodImage(file);
	}

	@GetMapping("/get/info/{id}")
	public Image getImageDetails(@PathVariable("id") Long id) throws IOException {
		return imageService.getImageDetails(id) ;
	}

	@GetMapping("/load/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) throws IOException 
	{
		return imageService.getImage(id);
	}


	@DeleteMapping("/delete/{id}")
	public void deleteImage(@PathVariable("id") Long id){
		imageService.deleteImage(id);
	}

	@PutMapping("/update")
	public Image UpdateImage(@RequestParam("image")MultipartFile file) throws IOException {
		return imageService.uplaodImage(file);
	}

	@PostMapping(value = "/uplaodImageProd/{idProd}" )
	public Image uploadMultiImages(@RequestParam("image")MultipartFile file,
			@PathVariable("idProd") Long idProd) 
					throws IOException {
		return imageService.uplaodImageProd(file,idProd);
	}

	@GetMapping("/getImagesProd/{idProd}")
	public List<Image> getImagesProd(@PathVariable("idProd") Long idProd) 
			throws IOException {
		return imageService.getImagesParProd(idProd);
	}


}
