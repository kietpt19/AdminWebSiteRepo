package com.lvtech.rest.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lvtech.model.OtherInfo;
import com.lvtech.model.ImageLogo;
import com.lvtech.repository.ImageLogoRepository;
import com.lvtech.repository.OtherInfoRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/imageLogo")
public class RestApiImageLogo {
	@Autowired
	private ImageLogoRepository imgLogoRepository;
	
	@Autowired
	private OtherInfoRepository otherInfoRepository;
	
//	@PostMapping("")
//	public OtherInfo OtherInfoImage(@RequestParam("imageFileLogo") MultipartFile imageFileLogo,
//			@RequestParam("idOtherInfo") String idOtherInfo) throws IOException {
//		OtherInfo otherInfo = otherInfoRepository.findById(Long.parseLong(idOtherInfo)).get();
//		ImageLogo imgLogo = new ImageLogo(otherInfo.getIdOtherInfo() 
//				+ imageFileLogo.getOriginalFilename(), imageFileLogo.getContentType(), compressBytes(imageFileLogo.getBytes()));
//		otherInfo.setImgLogo(imgLogoRepository.save(imgLogo));
//		if(otherInfo.getImgOtherInfo() == null) {
//			otherInfo.setImgOtherInfo(null);
//		}
//		return otherInfoRepository.save(otherInfo);
//	}
	
//	@PutMapping("/update")
//	public OtherInfo updateImage(@RequestParam("imageFileLogo") MultipartFile imageFileLogo,
//			@RequestParam("imageName") String imgName,
//			@RequestParam("idOtherInfo") String idOtherInfo,
//			@RequestParam("address") String address,
//			@RequestParam("phone") String phone,
//			@RequestParam("email") String email) throws IOException {	
//		OtherInfo otherInfo = otherInfoRepository.findById(Long.parseLong(idOtherInfo)).get();
//		ImageLogo imgLogo = new ImageLogo(otherInfo.getIdOtherInfo() 
//				+ imageFileLogo.getOriginalFilename(), 
//				imageFileLogo.getContentType(), compressBytes(imageFileLogo.getBytes()));
//		otherInfo.setImgLogo(imgLogoRepository.save(imgLogo));
//		otherInfo.setAddress(address);
//		otherInfo.setEmail(email);
//		otherInfo.setPhone(phone);
//		
//		imgLogoRepository.delete(imgLogoRepository.searchByName(imgName));
//		return otherInfoRepository.save(otherInfo);					
//	}
//	
//	@PutMapping("/update/otherInfo/newImage")
//	public OtherInfo updateImage(@RequestParam("imageFileLogo") MultipartFile imageFileLogo,
//			@RequestParam("idOtherInfo") String idOtherInfo,
//			@RequestParam("address") String address,
//			@RequestParam("phone") String phone,
//			@RequestParam("email") String email) throws IOException {	
//		OtherInfo otherInfo = otherInfoRepository.findById(Long.parseLong(idOtherInfo)).get();
//		ImageLogo imgLogo = new ImageLogo(otherInfo.getIdOtherInfo() 
//				+ imageFileLogo.getOriginalFilename(), 
//				imageFileLogo.getContentType(), compressBytes(imageFileLogo.getBytes()));
//		otherInfo.setImgLogo(imgLogoRepository.save(imgLogo));
//		otherInfo.setAddress(address);
//		otherInfo.setEmail(email);
//		otherInfo.setPhone(phone);
//		
//		return otherInfoRepository.save(otherInfo);					
//	}
//	
//	@PutMapping("/update/otherInfo")
//	public OtherInfo updateOtherInfo(@RequestParam("idOtherInfo") String idOtherInfo,
//			@RequestParam("address") String address,
//			@RequestParam("phone") String phone,
//			@RequestParam("email") String email) throws IOException {	
//		OtherInfo otherInfo = otherInfoRepository.findById(Long.parseLong(idOtherInfo)).get();
//		otherInfo.setAddress(address);
//		otherInfo.setEmail(email);
//		otherInfo.setPhone(phone);
//		
//		return otherInfoRepository.save(otherInfo);					
//	}
	
	@GetMapping("/get/{imgName}")
	public ImageLogo getImage(@PathVariable("imgName") String imgName) throws IOException {
		final ImageLogo retrievedImage = imgLogoRepository.searchByName(imgName);
		ImageLogo img = new ImageLogo(retrievedImage.getNameLogo(), retrievedImage.getTypeLogo(),
				decompressBytes(retrievedImage.getPicByteLogo()));
		return img;
	}
	
	@GetMapping(value = "")
	public List<ImageLogo> getAllImage() {
		return (List<ImageLogo>) imgLogoRepository.findAll();
	}

	
	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while(!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		
		try {
			outputStream.close();
		}catch(IOException e) {
		}
		return outputStream.toByteArray();
	}
	
	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		
		try {
			while(!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		}catch(IOException e) {
		} catch (DataFormatException e) {
		}
		
		return outputStream.toByteArray();
	}
}
