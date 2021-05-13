package com.sw.s1.util;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component //객체 만들어줌
public class FileManager {
	
	//resources 까지의 경로를 가지고 있는 객체
	@Autowired
	private ResourceLoader resourceLoader;
	
	public String save(MultipartFile multipartFile, String filePath) throws Exception {
		//filePath : /resources/static/ 제외한 하위경로
		
		//1. 경로 설정				
		
/**
 * 	저장할 폴더가 시스템에 고정일 경우
 *  String filePath = "c:/files";
 *  File file = new File(path, filePath);
 **/
		
		
/** classpath 경로 받아오는 2가지 방법
			
	 *  ResourceLoader
	 * classpath 경로를 받아오기 위해 사용
	 * 				//classpath 하위 폴더(static)가 없으면 에러
		String path = "classpath:/static/";
		
		//파일객체 -정보를 담고있음					//이 주소를 가지는 파일을 객체로
		File file = new File(resourceLoader.getResource(path).getFile(), filePath);
**/
		
/**
 *  * ClassPathResource
 *  * classpath 경로를 받아오기 위해 사용
 *  * ResourceLoader와 같지만 
 *  * 시작 경로에서 classpath를 제외
 **/
		String path="static";
		ClassPathResource classPathResource = new ClassPathResource(path);
		File file = new File(classPathResource.getFile(), filePath);
		
		System.out.println(file.getAbsolutePath());		//폴더까지 절대경로
		
		//폴더가 없다면 생성, 여러개의 디렉토리일 수도 있으니까 mkdirs
		if(!file.exists()) {
			file.mkdirs();
		}
		
		//2. 저장할 파일명 생성
		String fileName = UUID.randomUUID().toString()+"_"+multipartFile.getOriginalFilename();
		
		file = new File(file, fileName);
		
		//transfer
		multipartFile.transferTo(file);
		//FileCopyUtils.copy(multipartFile.getBytes(), file);
		
		return fileName; //저장한 파일명
	}

}
