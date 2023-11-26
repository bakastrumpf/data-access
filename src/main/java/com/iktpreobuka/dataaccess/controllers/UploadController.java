package com.iktpreobuka.dataaccess.controllers;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iktpreobuka.dataaccess.services.ReadCSVFile;

@Controller
@RequestMapping("/")
public class UploadController {
	
	@Autowired 
	private ReadCSVFile readCsvFile;
	
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FileHandler fileHandler;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		try {
			readCsvFile.ReadCSV();
		} catch (IOException e) {
			e.printStackTrace();			
		}
		return "upload";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String uploadStatus() {
		return "uploadStatus";
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/upload")
	public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
		logger.debug("This is a debug message!");
		logger.info("This is an info message!");
		logger.warn("This is a warning message!");
		logger.error("This is an error message!");
		String result = null;
		try {
			result = ((UploadController) fileHandler).singleFileUpload(file, redirectAttributes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
			
	}


}
