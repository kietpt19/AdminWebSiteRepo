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
import com.lvtech.model.Subject;
import com.lvtech.model.Employee;
import com.lvtech.model.ImageLogo;
import com.lvtech.model.ImageOtherInfo;
import com.lvtech.repository.EmployeeRepository;
import com.lvtech.repository.ImageLogoRepository;
import com.lvtech.repository.ImageOtherInfoRepository;
import com.lvtech.repository.OtherInfoRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/imageOtherInfo")
public class RestApiImageOtherInfo {
	@Autowired
	private ImageOtherInfoRepository imgOtherInfoRepository;

	@Autowired
	private ImageLogoRepository imgLogoRepository;

	@Autowired
	private OtherInfoRepository otherInfoRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@PostMapping("")
	public OtherInfo OtherInfoImage(@RequestParam("imageFile") MultipartFile file,
			@RequestParam("imageFileLogo") MultipartFile imageFileLogo, @RequestParam("idOtherInfo") String idOtherInfo)
			throws IOException {
		OtherInfo otherInfo = otherInfoRepository.findById(Long.parseLong(idOtherInfo)).get();
		ImageOtherInfo imgOtherInfo = new ImageOtherInfo(otherInfo.getIdOtherInfo() + file.getOriginalFilename(),
				file.getContentType(), compressBytes(file.getBytes()));
		ImageLogo imgLogo = new ImageLogo(otherInfo.getIdOtherInfo() + imageFileLogo.getOriginalFilename(),
				imageFileLogo.getContentType(), compressBytes(imageFileLogo.getBytes()));
		otherInfo.setImgOtherInfo(imgOtherInfoRepository.save(imgOtherInfo));
		otherInfo.setImgLogo(imgLogoRepository.save(imgLogo));

		return otherInfoRepository.save(otherInfo);
	}

	@PutMapping("/updateImageFile")
	public OtherInfo updateImageFile(@RequestParam("imageFile") MultipartFile file,
			@RequestParam("imageName") String imgName, 
			@RequestParam("idOtherInfo") String idOtherInfo,
			@RequestParam("address") String address, 
			@RequestParam("phone") String phone,
			@RequestParam("email") String email,
			@RequestParam("facebook") String facebook, 
			@RequestParam("google") String google,
			@RequestParam("tweeter") String tweeter,
			@RequestParam("introduce") String introduce, 
			@RequestParam("nameCompany") String nameCompany) throws IOException {
		OtherInfo otherInfo = otherInfoRepository.findById(Long.parseLong(idOtherInfo)).get();
		ImageOtherInfo imgOtherInfo = new ImageOtherInfo(otherInfo.getIdOtherInfo() + file.getOriginalFilename(),
				file.getContentType(), compressBytes(file.getBytes()));

		otherInfo.setImgOtherInfo(imgOtherInfoRepository.save(imgOtherInfo));
		otherInfo.setAddress(address);
		otherInfo.setEmail(email);
		otherInfo.setPhone(phone);
		otherInfo.setFacebook(facebook);
		otherInfo.setGoogle(google);
		otherInfo.setTweeter(tweeter);
		otherInfo.setIntroduce(introduce);
		otherInfo.setNameCompany(nameCompany);
		

		imgOtherInfoRepository.delete(imgOtherInfoRepository.searchByName(imgName));
		return otherInfoRepository.save(otherInfo);
	}

	@PutMapping("/updateImageLogo")
	public OtherInfo updateImageLogo(@RequestParam("imageFileLogo") MultipartFile imageFileLogo,
			@RequestParam("imageNameLogo") String imageNameLogo, 
			@RequestParam("idOtherInfo") String idOtherInfo,
			@RequestParam("address") String address, 
			@RequestParam("phone") String phone,
			@RequestParam("email") String email,
			@RequestParam("facebook") String facebook, 
			@RequestParam("google") String google,
			@RequestParam("tweeter") String tweeter,
			@RequestParam("introduce") String introduce, 
			@RequestParam("nameCompany") String nameCompany) throws IOException {
		OtherInfo otherInfo = otherInfoRepository.findById(Long.parseLong(idOtherInfo)).get();
		ImageLogo imgLogo = new ImageLogo(otherInfo.getIdOtherInfo() + imageFileLogo.getOriginalFilename(),
				imageFileLogo.getContentType(), compressBytes(imageFileLogo.getBytes()));

		otherInfo.setImgLogo(imgLogoRepository.save(imgLogo));
		otherInfo.setAddress(address);
		otherInfo.setEmail(email);
		otherInfo.setPhone(phone);
		otherInfo.setFacebook(facebook);
		otherInfo.setGoogle(google);
		otherInfo.setTweeter(tweeter);
		otherInfo.setIntroduce(introduce);
		otherInfo.setNameCompany(nameCompany);

		imgLogoRepository.delete(imgLogoRepository.searchByName(imageNameLogo));
		return otherInfoRepository.save(otherInfo);
	}

	@PutMapping("/update")
	public OtherInfo updateImage(@RequestParam("imageFile") MultipartFile file,
			@RequestParam("imageFileLogo") MultipartFile imageFileLogo, 
			@RequestParam("imageName") String imgName,
			@RequestParam("imageNameLogo") String imageNameLogo, 
			@RequestParam("idOtherInfo") String idOtherInfo,
			@RequestParam("address") String address, 
			@RequestParam("phone") String phone,
			@RequestParam("email") String email,
			@RequestParam("facebook") String facebook, 
			@RequestParam("google") String google,
			@RequestParam("tweeter") String tweeter,
			@RequestParam("introduce") String introduce, 
			@RequestParam("nameCompany") String nameCompany) throws IOException {
		OtherInfo otherInfo = otherInfoRepository.findById(Long.parseLong(idOtherInfo)).get();
		ImageOtherInfo imgOtherInfo = new ImageOtherInfo(otherInfo.getIdOtherInfo() + file.getOriginalFilename(),
				file.getContentType(), compressBytes(file.getBytes()));
		ImageLogo imgLogo = new ImageLogo(otherInfo.getIdOtherInfo() + imageFileLogo.getOriginalFilename(),
				imageFileLogo.getContentType(), compressBytes(imageFileLogo.getBytes()));

		otherInfo.setImgOtherInfo(imgOtherInfoRepository.save(imgOtherInfo));
		otherInfo.setImgLogo(imgLogoRepository.save(imgLogo));
		otherInfo.setAddress(address);
		otherInfo.setEmail(email);
		otherInfo.setPhone(phone);
		otherInfo.setFacebook(facebook);
		otherInfo.setGoogle(google);
		otherInfo.setTweeter(tweeter);
		otherInfo.setIntroduce(introduce);
		otherInfo.setNameCompany(nameCompany);

		imgOtherInfoRepository.delete(imgOtherInfoRepository.searchByName(imgName));
		imgLogoRepository.delete(imgLogoRepository.searchByName(imageNameLogo));
		return otherInfoRepository.save(otherInfo);
	}

	@PutMapping("/update/otherInfo/newImage")
	public OtherInfo updateImage(@RequestParam("imageFile") MultipartFile file,
			@RequestParam("imageFileLogo") MultipartFile imageFileLogo, 
			@RequestParam("idOtherInfo") String idOtherInfo,
			@RequestParam("address") String address, 
			@RequestParam("phone") String phone,
			@RequestParam("email") String email,
			@RequestParam("facebook") String facebook, 
			@RequestParam("google") String google,
			@RequestParam("tweeter") String tweeter,
			@RequestParam("introduce") String introduce, 
			@RequestParam("nameCompany") String nameCompany) throws IOException {
		OtherInfo otherInfo = otherInfoRepository.findById(Long.parseLong(idOtherInfo)).get();

		ImageOtherInfo imgOtherInfo = new ImageOtherInfo(otherInfo.getIdOtherInfo() + file.getOriginalFilename(),
				file.getContentType(), compressBytes(file.getBytes()));
		ImageLogo imgLogo = new ImageLogo(otherInfo.getIdOtherInfo() + imageFileLogo.getOriginalFilename(),
				imageFileLogo.getContentType(), compressBytes(imageFileLogo.getBytes()));

		otherInfo.setImgLogo(imgLogoRepository.save(imgLogo));
		otherInfo.setImgOtherInfo(imgOtherInfoRepository.save(imgOtherInfo));
		otherInfo.setAddress(address);
		otherInfo.setEmail(email);
		otherInfo.setPhone(phone);
		otherInfo.setFacebook(facebook);
		otherInfo.setGoogle(google);
		otherInfo.setTweeter(tweeter);
		otherInfo.setIntroduce(introduce);
		otherInfo.setNameCompany(nameCompany);

		return otherInfoRepository.save(otherInfo);
	}

	@PutMapping("/update/otherInfo")
	public OtherInfo updateOtherInfo(@RequestParam("idOtherInfo") String idOtherInfo,
			@RequestParam("address") String address, 
			@RequestParam("phone") String phone,
			@RequestParam("email") String email,
			@RequestParam("facebook") String facebook, 
			@RequestParam("google") String google,
			@RequestParam("tweeter") String tweeter,
			@RequestParam("introduce") String introduce, 
			@RequestParam("nameCompany") String nameCompany, 
			@RequestParam("employee") String employee) throws IOException {
		OtherInfo otherInfo = otherInfoRepository.findById(Long.parseLong(idOtherInfo)).get();
		otherInfo.setAddress(address);
		otherInfo.setEmail(email);
		otherInfo.setPhone(phone);
		otherInfo.setFacebook(facebook);
		otherInfo.setGoogle(google);
		otherInfo.setTweeter(tweeter);
		otherInfo.setIntroduce(introduce);
		otherInfo.setNameCompany(nameCompany);
		
		Employee emp = employeeRepository.findById(Long.parseLong(employee)).get();
		otherInfo.setEmployee(emp);
		return otherInfoRepository.save(otherInfo);
	}

//	@GetMapping("/getLogo/{imgName}")
//	public ImageLogo getImageLogo(@PathVariable("imgNameLogo") String imgNameLogo) throws IOException {
//		final ImageLogo retrievedImageLogo = imgLogoRepository.searchByName(imgNameLogo);
//		ImageLogo imgLogo = new ImageLogo(retrievedImageLogo.getNameLogo(), retrievedImageLogo.getTypeLogo(),
//				decompressBytes(retrievedImageLogo.getPicByteLogo()));
//		return imgLogo;
//	}

	@GetMapping("/get/{imgName}")
	public ImageOtherInfo getImage(@PathVariable("imgName") String imgName) throws IOException {
		final ImageOtherInfo retrievedImage = imgOtherInfoRepository.searchByName(imgName);
		ImageOtherInfo img = new ImageOtherInfo(retrievedImage.getName(), retrievedImage.getType(),
				decompressBytes(retrievedImage.getPicByte()));
		return img;
	}

//	@GetMapping(value = "s")
//	public List<ImageLogo> getAllImageLogo() {
//		return (List<ImageLogo>) imgLogoRepository.findAll();
//	}

	@GetMapping(value = "")
	public List<ImageOtherInfo> getAllImage() {
		return (List<ImageOtherInfo>) imgOtherInfoRepository.findAll();
	}

	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}

		try {
			outputStream.close();
		} catch (IOException e) {
		}
		return outputStream.toByteArray();
	}

	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];

		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException e) {
		} catch (DataFormatException e) {
		}

		return outputStream.toByteArray();
	}
}
