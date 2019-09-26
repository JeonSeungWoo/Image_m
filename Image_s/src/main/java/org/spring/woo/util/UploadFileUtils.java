package org.spring.woo.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ResponseBody;

public class UploadFileUtils {
	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);
	
	//이미지를 보여준다.(경로와 파일이름을 파라미터로 넣어 줘야 한다.)
	public @ResponseBody byte[] show(String path,String fileName)throws Exception {
		InputStream in;
		String noImg = "C:\\TEMP\\noImage.png";
		//이미지가 있을 때
		if (fileName != null) {
			in = new FileInputStream(path + fileName);
		} else {
			//이미지가 없을 때.
			in = new FileInputStream(noImg);
		}
		//IO 스트림 조작 유틸.(toByteArray는 in을 인코딩.)
		byte[] result = IOUtils.toByteArray(in);
		in.close();
		return result;
	}
	
	//등록
	public static String uploadFile(String uploadPath,
	                              String originalName,
	                              byte[] fileData)throws Exception{
	    UUID uid = UUID.randomUUID();
	    String savedName = uid.toString() +"_"+originalName;
	    String savedPath = calcPath(uploadPath);
	    File target = new File(uploadPath +savedPath,savedName);
	    
	     //byte[] input, File output (자동 close;)
	    FileCopyUtils.copy(fileData, target);
	    String formatName = originalName.substring(originalName.lastIndexOf(".")+1);
	    String uploadedFileName = null;
	    
	    //이미지이면 
	    if(getMediaType(formatName) != null){
	      uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
	    }else{
	      uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
	    }
	    return uploadedFileName;
	  }

	
      //파일처리	
	  private static  String makeIcon(String uploadPath,
	      String path,
	      String fileName)throws Exception{
	    String iconName = uploadPath + path + File.separator+ fileName;
	    return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	  }

      //썸네일 처리
	  private static  String makeThumbnail(
	              String uploadPath,
	              String path,
	              String fileName)throws Exception{
	    BufferedImage sourceImg =
	        ImageIO.read(new File(uploadPath + path, fileName));
	    BufferedImage destImg =
	        Scalr.resize(sourceImg,
	            Scalr.Method.AUTOMATIC,
	            Scalr.Mode.FIT_TO_HEIGHT,100);
	    String thumbnailName =
	        uploadPath + path + File.separator +"s_"+ fileName;
	    File newFile = new File(thumbnailName);
	    String formatName =
	        fileName.substring(fileName.lastIndexOf(".")+1);
	    ImageIO.write(destImg, formatName.toUpperCase(), newFile);
	    return thumbnailName.substring(
	        uploadPath.length()).replace(File.separatorChar, '/');
	  }

      //날짜 처리
	  public static String calcPath(String uploadPath){
	    Calendar cal = Calendar.getInstance();
	    String yearPath = File.separator+cal.get(Calendar.YEAR);
	    String monthPath = yearPath +
	        File.separator +
	        new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
	    String datePath = monthPath +
	        File.separator +
	        new DecimalFormat("00").format(cal.get(Calendar.DATE));
	     makeDir(uploadPath, yearPath,monthPath,datePath); 
	    return datePath;
	  }

    //퐁더 만들기
	    private static void makeDir(String uploadPath, String... paths){
	    if(new File(paths[paths.length-1]).exists()){
	      return;
	    }
	    for (String path : paths) {
	      File dirPath = new File(uploadPath + path);
	      if(! dirPath.exists() ){
	        dirPath.mkdir();
	      }
	    }
	  }
	  
	  //Img type check
		public static MediaType getMediaType(String type){
			
			Map<String, MediaType> mediaMap = new HashMap<String, MediaType>();		
			mediaMap.put("JPG", MediaType.IMAGE_JPEG);
			mediaMap.put("GIF", MediaType.IMAGE_GIF);
			mediaMap.put("PNG", MediaType.IMAGE_PNG);
			
			return mediaMap.get(type.toUpperCase());
		}
	  
		//삭제 처리
		public void deleteFile(String location,String fileName) {
			File file = new File(location + fileName);	
			if(file.exists()) {
				if(file.delete()) {
					logger.info("파일삭제 성공");
				}else {
					logger.info("파이삭제 실패");
				}
			}else {
				logger.info("파일이 존재하지 않습니다.");
			}
		}
		
}
