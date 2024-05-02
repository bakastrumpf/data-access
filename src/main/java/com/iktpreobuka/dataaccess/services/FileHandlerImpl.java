package com.iktpreobuka.dataaccess.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
//import java.io.Writer;
//import java.net.MalformedURLException;
//import java.util.stream.Stream;

import com.iktpreobuka.dataaccess.entities.UserEntity;

import com.iktpreobuka.dataaccess.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.UrlResource;
//import org.springframework.stereotype.Service;
//import org.springframework.util.FileSystemUtils;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.multipart.MultipartFile;

public class FileHandlerImpl implements FileHandler {

    private static String UPLOAD_FOLDER="C:\\SpringTemp\\";

    @Autowired
    UserRepository userRepository;
    @Autowired
    public FileHandler fileHandler;
/*
@Override
	public String singleFileUpload(MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
		if(file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message","Please select file to upload");
			return "redirect:uploadStatus";
		}
			byte[] bytes=file.getBytes();
			Path path=Paths.get(UPLOAD_FOLDER +file.getOriginalFilename());
			Files.write(path, bytes);
			redirectAttributes.addFlashAttribute("message","file"+file.getOriginalFilename()+"successfully uploaded");

		return "redirect:/uploadStatus";
	}*/

//Logger log = LoggerFactory.getLogger(this.getClass().getName());
//	private final Path rootLocation = Paths.get("storages");

    @Override
    public void store(InputStream file) {
        try {
            List<UserEntity> users =ApacheCommonsCsvUtil.parseCsvFile(file);
            byte[] bytes=((MultipartFile) file).getBytes();
            Path path=Paths.get(UPLOAD_FOLDER +((MultipartFile) file).getOriginalFilename());
            Files.write(path, bytes);
            userRepository.saveAll(users);
            ApacheCommonsCsvUtil.usersToCsv((List<UserEntity>) userRepository.saveAll(users));
        } catch (Exception e) {
            throw new RuntimeException("Error! -> message = " + e.getMessage());
        }
    }
    //ova metoda bi trebala da cita iz baze korisnike i da ih prebaci u csv
    @Override
    public ByteArrayInputStream loadFile() throws IOException{
        try {
            List<UserEntity> users= (List<UserEntity>) userRepository.findAll();
            ByteArrayInputStream in =ApacheCommonsCsvUtil.usersToCsv(users);
            return in;
        } catch (Exception e) {
            throw new RuntimeException("Fail!"+e.getMessage());
        }

    }

/*	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		try {
			Files.createDirectory(rootLocation);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize file storage!");
		}
	}

	@Override
	public Stream<Path> getFiles() {
		try {
			return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
					.map(this.rootLocation::relativize);
		} catch (IOException e) {
			throw new RuntimeException("Error! -> message = " + e.getMessage());
		}
	}
	*/
}
