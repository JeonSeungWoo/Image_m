package org.spring.woo.util;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
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
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ResponseBody;

public class UploadFileUtils {
	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);

	// byte type show
//	public @ResponseBody byte[] show(String path, String fileName) throws Exception {
//		InputStream in;
//		in = new FileInputStream(path + fileName);
//		// IO ��Ʈ�� ���� ��ƿ.(toByteArray�� in�� ���ڵ�.)
//		byte[] result = IOUtils.toByteArray(in);
//		in.close();
//		return result;
//	}

	// ResponseEntity show 
	public ResponseEntity<Resource> fileShow(String reqHeader, String path, String fileName) throws Exception {
		Resource resource = new FileSystemResource(path + fileName);
		if (resource.exists() == false) {
			return new ResponseEntity<Resource>(HttpStatus.OK);
		}
		String resorceName = resource.getFilename();
		String resorceOriName = resorceName.substring(resorceName.indexOf("_") + 1);
		HttpHeaders headers = new HttpHeaders();
		// ���� ó��
		try {
			String downloadName = null;
			// @RequestHeader�� �̿��ؼ� ������ ������ ����� ����ũ�� ���α׷� ���� ����.
			// IE Browser
			if (reqHeader.contains("Trident")) {
				downloadName = URLEncoder.encode(resorceOriName, "UTF-8").replaceAll("\\+", " ");
			} else if (reqHeader.contains("Edge")) {
				downloadName = URLEncoder.encode(resorceOriName, "UTF-8");
			} else if (reqHeader.contains("Chrome")) {
				downloadName = new String(resorceOriName.getBytes("UTF-8"), "ISO-8859-1");
			} else {
				// ���ܴ� �̸��� �������� Chrome ����.
				downloadName = new String(resorceOriName.getBytes("UTF-8"), "ISO-8859-1");
			}

			headers.add("Content-Disposition", "attachment; filename=" + downloadName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}

	
	
	//���� ����.
	public static String saveFile(String uploadPath, String originalName, byte[] fileData) throws Exception {
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + originalName;
		String savedPath = calcPath(uploadPath);
		File target = new File(uploadPath + savedPath, savedName);

		// byte[] input, File output (�ڵ� close;)
		FileCopyUtils.copy(fileData, target);
		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);
		String uploadedFileName = null;

		// �̹����̸�
		if (getMediaType(formatName) != null) {
			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
			// �̹����� �ƴ� ������ ��� ���� ��
		} else {
			uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
		}

		return uploadedFileName;
	}

	// ����ó��
	private static String makeIcon(String uploadPath, String path, String fileName) throws Exception {
		String iconName = uploadPath + path + File.separator + fileName;
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

	// ����� ó��
	private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception {
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
		String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;
		File newFile = new File(thumbnailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

	// ��¥ ó��
	public static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		makeDir(uploadPath, yearPath, monthPath, datePath);
		return datePath;
	}

	// ���� �����
	private static void makeDir(String uploadPath, String... paths) {
		if (new File(paths[paths.length - 1]).exists()) {
			return;
		}
		for (String path : paths) {
			File dirPath = new File(uploadPath + path);
			if (!dirPath.exists()) {
				dirPath.mkdir();
			}
		}
	}

	// Img type check
	public static MediaType getMediaType(String type) {

		Map<String, MediaType> mediaMap = new HashMap<String, MediaType>();
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("GIF", MediaType.IMAGE_GIF);
		mediaMap.put("PNG", MediaType.IMAGE_PNG);

		return mediaMap.get(type.toUpperCase());
	}

	// ���� ó��
	public void deleteFile(String location, String fileName) {
		File file = new File(location + fileName);
		if (file.exists()) {
			if (file.delete()) {
				logger.info("���ϻ��� ����");
			} else {
				logger.info("���̻��� ����");
			}
		} else {
			logger.info("������ �������� �ʽ��ϴ�.");
		}
	}

}
