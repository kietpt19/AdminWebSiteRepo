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

import com.lvtech.model.ImagePost;
import com.lvtech.model.Post;
import com.lvtech.model.Subject;
import com.lvtech.repository.ImagePostRepository;
import com.lvtech.repository.PostRepository;
import com.lvtech.repository.SubjectRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/imagePost")
public class RestApiImagePost {
	@Autowired
	private ImagePostRepository imgPostRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private SubjectRepository subjectRepository;

	@PostMapping("")
	public Post postImage(@RequestParam("imageFile") MultipartFile file,
			@RequestParam("idPost") String idPost) throws IOException {
		Post post = postRepository.findById(Long.parseLong(idPost)).get();
		System.out.println(post.getTitlePost());
		ImagePost img = new ImagePost(post.getIdPost() 
				+ file.getOriginalFilename(), file.getContentType(), compressBytes(file.getBytes()));
		post.setImg(imgPostRepository.save(img));
		return postRepository.save(post);
	}
	
	@PutMapping("/update")
	public Post updateImage(@RequestParam("imageFile") MultipartFile file,
			@RequestParam("imageName") String imgName,
			@RequestParam("idPost") String idPost,
			@RequestParam("titlePost") String titlePost,
			@RequestParam("content") String content,
			@RequestParam("mainContent") String mainContent,
			@RequestParam("subject") String subject) throws IOException {	
		Post post = postRepository.findById(Long.parseLong(idPost)).get();
		ImagePost img = new ImagePost(post.getIdPost() + file.getOriginalFilename(), file.getContentType(), compressBytes(file.getBytes()));
		post.setImg(imgPostRepository.save(img));
		post.setTitlePost(titlePost);
		post.setContent(content);
		post.setMainContent(mainContent);
		
		Subject sub = subjectRepository.findById(Long.parseLong(subject)).get();
		post.setSubject(sub);
		
		imgPostRepository.delete(imgPostRepository.searchByName(imgName));
		return postRepository.save(post);					
	}
	
	@PutMapping("/update/post/newImage")
	public Post updateImage(@RequestParam("imageFile") MultipartFile file,
			@RequestParam("idPost") String idPost,
			@RequestParam("titlePost") String titlePost,
			@RequestParam("content") String content,
			@RequestParam("mainContent") String mainContent,
			@RequestParam("subject") String subject) throws IOException {	
		Post post = postRepository.findById(Long.parseLong(idPost)).get();
		ImagePost img = new ImagePost(post.getIdPost() + file.getOriginalFilename(), file.getContentType(), compressBytes(file.getBytes()));
		post.setImg(imgPostRepository.save(img));
		post.setTitlePost(titlePost);
		post.setContent(content);
		post.setMainContent(mainContent);
		
		Subject sub = subjectRepository.findById(Long.parseLong(subject)).get();
		post.setSubject(sub);
		
		return postRepository.save(post);					
	}
	
	@PutMapping("/update/post")
	public Post updatePost(@RequestParam("idPost") String idPost,
			@RequestParam("titlePost") String titlePost,
			@RequestParam("content") String content,
			@RequestParam("mainContent") String mainContent,
			@RequestParam("subject") String subject) throws IOException {	
		Post post = postRepository.findById(Long.parseLong(idPost)).get();
		post.setTitlePost(titlePost);
		post.setContent(content);
		post.setMainContent(mainContent);
		
		Subject sub = subjectRepository.findById(Long.parseLong(subject)).get();
		post.setSubject(sub);
		return postRepository.save(post);					
	}
	
	@GetMapping("/get/{imgName}")
	public ImagePost getImage(@PathVariable("imgName") String imgName) throws IOException {
		final ImagePost retrievedImage = imgPostRepository.searchByName(imgName);
		ImagePost img = new ImagePost(retrievedImage.getName(), retrievedImage.getType(),
				decompressBytes(retrievedImage.getPicByte()));
		return img;
	}
	
	@GetMapping(value = "")
	public List<ImagePost> getAllImage() {
		return (List<ImagePost>) imgPostRepository.findAll();
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