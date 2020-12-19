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

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
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

import com.tabeyo.domain.TimeFeedAttachFileDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

//@Controller
@Log4j
public class TimeFeedUploadController {
	//파일 다운로드 구현
		@GetMapping(value="/feedDownload",
					produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
		@ResponseBody
		public ResponseEntity<Resource> downloadFile(String fileName, 
								@RequestHeader("User-Agent")String userAgent){
			log.info("download file : " + fileName);
			Resource resource 
				= new FileSystemResource("c:\\upload\\" + fileName);
			log.info("resource : " + resource);
			
			if(resource.exists() == false) {	//해당 파일이 없으면
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			String resourceName = resource.getFilename();	//파일명 가져오기
			
			//UUID 잘라내기
			String resourceOriginalName
				= resourceName.substring(resourceName.indexOf("_") + 1);
			
			HttpHeaders header = new HttpHeaders();
			try {
				String downloadName = null;
				
				if(userAgent.contains("Trident")) {		//IE의 경우
					downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8")
											 .replaceAll("\\+", " ");
				} else if(userAgent.contains("Edge")) {	//Edge의 경우
					downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8");
				} else {
					downloadName = new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");
				}
				
				header.add("Content-Disposition",
						   "attachment; filename=" + downloadName); 
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return new ResponseEntity<>(resource, header, HttpStatus.OK);
		}
		
		
		//입력받은 파일명의 섬네일 이미지 데이터 전송
		@GetMapping("/feedDisplay")
		@ResponseBody
		public ResponseEntity<byte[]> getFile(String fileName){
			log.info("fileName : " + fileName);
			File file = new File("c:\\upload\\" + fileName);
			ResponseEntity<byte[]> result = null;
			
			try {
				HttpHeaders header = new HttpHeaders();
				header.add("Content-Type", 
						   Files.probeContentType(file.toPath()));
				result = new ResponseEntity<>(
							FileCopyUtils.copyToByteArray(file),
							header,
							HttpStatus.OK
						);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return result;
		}
		
		//현재 시점의 연/월/일 폴더 경로 문자열 생성
		private String getFolder() {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String str = sdf.format(date);
			
			return str.replace("-", File.separator);
		}//END getFolder()
		
		//업로드 파일 검사 - 이미지 파일 여부
		private boolean checkImageType(File file) {
			try {
				String contentType = Files.probeContentType(file.toPath());
				
				//이미지 파일이면 true 반환
				return contentType.startsWith("image");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}//END checkImageType()

		//첨부 파일 삭제
		@PostMapping("/feedDeleteFile")
//		@PreAuthorize("isAuthenticated()")
		@ResponseBody
		public ResponseEntity<String> deleteFile(String fileName, String type){
			log.info("deleteFile : " + fileName);
			File file = null;
			try {
				file = new File("c:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));
				file.delete();		//파일 삭제
				
				if(type.equals("image")) { //이미지이면 - s_ 제거
					String largeFileName = file.getAbsolutePath().replace("s_", "");
					file = new File(largeFileName);
					file.delete();			//원본 이미지 삭제
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<String>("deleted", HttpStatus.OK);
		}
		
		
		@PostMapping("/feedUploadAjaxAction")
	//	@PreAuthorize("isAuthenticated()")
		@ResponseBody
		public ResponseEntity<List<TimeFeedAttachFileDTO>> 
				uploadAjaxAction(MultipartFile[] uploadFile) {
			log.info("UploadController uploadAjaxAction()");
			List<TimeFeedAttachFileDTO> list = new ArrayList<TimeFeedAttachFileDTO>();
			
			for (MultipartFile m : uploadFile) {
				log.info("------------------");
				log.info("upload file name : " + m.getOriginalFilename());
				log.info("upload file size : " + m.getSize());
				
				String uploadFileName = m.getOriginalFilename();
				TimeFeedAttachFileDTO attachDTO = new TimeFeedAttachFileDTO();
				
				// 연/월/일 폴더 생성
				File uploadPath = new File("c:\\upload", getFolder());
				
				// 생성된 폴더 존재여부 확인
				if(!uploadPath.exists()) {	//없으면
					uploadPath.mkdirs();	//디렉토리 일괄 생성
				}
				
				//IE의 경우 - 전체 경로에서 파일명만 가져오기
				uploadFileName = uploadFileName.substring(
									uploadFileName.lastIndexOf("\\") + 1);
				
				attachDTO.setFileName(uploadFileName);
				
				//UUID 이용 파일명 중복 방지 처리
				UUID uuid = UUID.randomUUID();
				uploadFileName = uuid.toString() + "_" + uploadFileName;
				
				//파일 객체 생성
				File saveFile = new File(uploadPath, uploadFileName);
				
				try {
					m.transferTo(saveFile);	//파일 업로드
					
					attachDTO.setUuid(uuid.toString());
					attachDTO.setUploadPath(getFolder());
					
					//이미지 파일이면 썸네일 생성
					if(checkImageType(saveFile) == true) {
						attachDTO.setFileType(true);
						
						FileOutputStream thumbnail 
							= new FileOutputStream(
									new File(uploadPath, "s_" + uploadFileName)
						);
						
						Thumbnailator.createThumbnail(
								m.getInputStream(), thumbnail, 100, 100
						);
						thumbnail.close();
					}//END  썸네일 생성
					
					list.add(attachDTO);	
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}//END for
			
			return new ResponseEntity<>(list, HttpStatus.OK);
		}//END uploadAjaxAction
		
		@GetMapping("/feedUploadAjax")
		public void uploadAjax() {
			log.info("UploadController uploadAjax()");
		}
		
		@PostMapping("/feedUploadFormAction")
		public void uploadFormPost(MultipartFile[] uploadFile, 
								   Model model) {
			log.info("UploadController uploadFormPost()");
			
			for (MultipartFile m : uploadFile) {
				log.info("------------------");
				log.info("upload file name : " + m.getOriginalFilename());
				log.info("upload file size : " + m.getSize());
				
				//파일 객체 생성
				File saveFile = new File("c:\\upload",
										 m.getOriginalFilename());
				
				try {
					m.transferTo(saveFile);	//파일 업로드
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		@GetMapping("/feedUploadForm")
		public void uploadForm() {
			log.info("UploadController uploadForm()");
		}
}
