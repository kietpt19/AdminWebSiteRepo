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

import com.lvtech.model.ImageEvent;
import com.lvtech.model.Event;
import com.lvtech.repository.EventRepository;
import com.lvtech.repository.ImageEventRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/imageEvent")
public class RestApiImageEvent {
	@Autowired
	private ImageEventRepository imgEventRepository;
	
	@Autowired
	private EventRepository eventRepository;
	
	@PostMapping("")
	public Event EventImage(@RequestParam("imageFile") MultipartFile file,
			@RequestParam("idEvent") String idEvent) throws IOException {
		Event event = eventRepository.findById(Long.parseLong(idEvent)).get();
		System.out.println(event.getTitleEvent());
		ImageEvent img = new ImageEvent(event.getIdEvent() + file.getOriginalFilename(), file.getContentType(), compressBytes(file.getBytes()));
		event.setImg(imgEventRepository.save(img));
		return eventRepository.save(event);
	}
	
	@PutMapping("/update")
	public Event updateImage(@RequestParam("imageFile") MultipartFile file,
			@RequestParam("imageName") String imgName,
			@RequestParam("idEvent") String idEvent,
			@RequestParam("titleEvent") String titleEvent,
			@RequestParam("mainContent") String mainContent,
			@RequestParam("content") String content) throws IOException {	
		Event event = eventRepository.findById(Long.parseLong(idEvent)).get();
		ImageEvent img = new ImageEvent(event.getIdEvent() + file.getOriginalFilename(), file.getContentType(), compressBytes(file.getBytes()));
		event.setImg(imgEventRepository.save(img));
		event.setTitleEvent(titleEvent);
		event.setContent(content);
		event.setMainContent(mainContent);
		
		imgEventRepository.delete(imgEventRepository.searchByName(imgName));
		return eventRepository.save(event);					
	}
	
	@PutMapping("/update/event/newImage")
	public Event updateImage(@RequestParam("imageFile") MultipartFile file,
			@RequestParam("idEvent") String idEvent,
			@RequestParam("titleEvent") String titleEvent,
			@RequestParam("mainContent") String mainContent,
			@RequestParam("content") String content) throws IOException {	
		Event event = eventRepository.findById(Long.parseLong(idEvent)).get();
		ImageEvent img = new ImageEvent(event.getIdEvent() + file.getOriginalFilename(), file.getContentType(), compressBytes(file.getBytes()));
		event.setImg(imgEventRepository.save(img));
		event.setTitleEvent(titleEvent);
		event.setContent(content);
		event.setMainContent(mainContent);
		
		return eventRepository.save(event);					
	}
	
	@PutMapping("/update/event")
	public Event updateEvent(@RequestParam("idEvent") String idEvent,
			@RequestParam("titleEvent") String titleEvent,
			@RequestParam("mainContent") String mainContent,
			@RequestParam("content") String content) throws IOException {	
		Event event = eventRepository.findById(Long.parseLong(idEvent)).get();
		event.setTitleEvent(titleEvent);
		event.setContent(content);
		event.setMainContent(mainContent);
		
		return eventRepository.save(event);					
	}
	
	@GetMapping("/get/{imgName}")
	public ImageEvent getImage(@PathVariable("imgName") String imgName) throws IOException {
		final ImageEvent retrievedImage = imgEventRepository.searchByName(imgName);
		ImageEvent img = new ImageEvent(retrievedImage.getName(), retrievedImage.getType(),
				decompressBytes(retrievedImage.getPicByte()));
		return img;
	}
	
	@GetMapping(value = "")
	public List<ImageEvent> getAllImage() {
		return (List<ImageEvent>) imgEventRepository.findAll();
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