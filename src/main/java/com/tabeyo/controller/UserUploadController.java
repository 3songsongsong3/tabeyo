package com.tabeyo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.core.io.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.tabeyo.domain.UserAttachFileDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
public class UserUploadController {
	
	private boolean checkImageType(File file) {
		String contentType;
		try {
			contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private String getFolder() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(date);
		return str.replace("-", File.separator);
		
	}
	
	@GetMapping("/userUploadForm")
	public void uploadForm() {
		log.info("upload form");
	}//END uploadForm()
	
	@PostMapping("/userUploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
		for (MultipartFile m : uploadFile) {
			log.info("---------------------");
			log.info("Upload File Name : " + m.getOriginalFilename());
			log.info("Upload File Size : " + m.getSize());
			
			//파일객체생성
			File saveFile = new File("c:\\upload", m.getOriginalFilename());
			
		
			try {
				m.transferTo(saveFile);	//파일업로드
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}//END catch
		}//END for
		
	}//END uploadForm()
	
	@GetMapping("/userUploadAjax")
	public void uploadAjax() {
		log.info("upload ajax");
	}
	
	@PostMapping("/userUploadAjaxAction")
	@ResponseBody
	public ResponseEntity<List<UserAttachFileDTO>> uploadAjaxAction(MultipartFile[] uploadFile) {
		
		log.info("UploadController uploadAjaxAction(");
		List<UserAttachFileDTO> list = new ArrayList<UserAttachFileDTO>();
		//연월일 폴더생서
		File uploadPath = new File("c:\\upload", getFolder());
		
		//생성된 폴더 존재여부 확인
		if(!uploadPath.exists()) {	//없으면
			uploadPath.mkdirs();//디렉토리 생성
		}
		
		for (MultipartFile m : uploadFile) {
			log.info("---------------------");
			log.info("Upload File Name : " + m.getOriginalFilename());
			log.info("Upload File Size : " + m.getSize());
			String uploadFileName = m.getOriginalFilename();
			UserAttachFileDTO attachDTO = new UserAttachFileDTO();
			
			//IE의 경우 - 전체 경로에서 파일명만 가져오기
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			log.info("only file name : " + uploadFileName);
			
			attachDTO.setFileName(uploadFileName);
			
			UUID uuid = UUID.randomUUID();
			
			//UUID이용 파일명중복방지
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			//파일객체생성
			File saveFile = new File(uploadPath, uploadFileName);
		
			try {
				m.transferTo(saveFile);	//파일업로드
				
				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadPath(getFolder());
				
				if(checkImageType(saveFile) == true ) {
					attachDTO.setImage(true);
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath,"s_"+uploadFileName));
					Thumbnailator.createThumbnail(m.getInputStream(), thumbnail, 100, 100);
					
					thumbnail.close();
				}//thumbnail 생성
				
				list.add(attachDTO);
			} catch (IllegalStateException | IOException e) {
				log.error(e.getMessage());
			}//END catch
		}//END for
		return new ResponseEntity<>(list, HttpStatus.OK);
	}//END uploadAjaxAction()
	
	
	//입력받은 파일명의 섬네일 이미지 데이터 전송
	@GetMapping("/userDisplay")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName){
		
		log.info("fileName: " + fileName);
		File file = new File("c:\\upload\\"+fileName);
		log.info("file: " + file);
		ResponseEntity<byte[]> result = null;
		try {
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", Files.probeContentType(file.toPath()));
		
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}//END getFile()
	
	@GetMapping(value="/userDownload", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent, String fileName){
		log.info("download file: " + fileName);
		
		Resource resource = new FileSystemResource("c:\\upload\\"+fileName);
		if(resource.exists() == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		String resourceName = resource.getFilename();
		
		//UUID 잘라내기
		
		String resourceOriginalName = resourceName.substring(resourceName.indexOf("_")+1);
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			String downloadName = null;
			if(userAgent.contains("Trident")) {
				log.info("IE browser");
				downloadName = URLEncoder.encode(resourceOriginalName,"UTF-8").replaceAll("\\+", " ");
			} else if(userAgent.contains("Edge")) {
				log.info("Edge browser");
				downloadName = URLEncoder.encode(resourceOriginalName,"UTF-8");
				log.info("Edge name: " + downloadName );	
			} else {
				log.info("Chrome browser");
				downloadName = new String(resourceOriginalName.getBytes("UTF-8"),"ISO-8859-1");
			}
			headers.add("Content-Disposition", "attachment;  filename=" + downloadName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}//END downloadFile
	
	//첨부파일삭제
	@PostMapping("/userDeleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String type){
		log.info("deleteFile : " + fileName);
		
		File file = null;
		
		try {
			file = new File("c:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));
			file.delete();	//파일삭제
			
			if(type.equals("image")) {//이미지이면 - s_제거
				String largeFileName = file.getAbsolutePath().replace("s_", "");
				file = new File(largeFileName);
				file.delete();//원본이미지삭제
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("deleted",HttpStatus.OK);
	}

}
