package com.iktpreobuka.dataaccess.services;

import java.io.IOException;
import java.io.InputStream;
//import java.io.Writer;
//import java.nio.file.Path;
//import java.util.stream.Stream;
//
//import org.springframework.core.io.Resource;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface FileHandler {

    //public String singleFileUpload(MultipartFile file,RedirectAttributes redirectAttributes) throws IOException;
    public void store(InputStream file);
    public InputStream loadFile() throws IOException;

/*	public void deleteAll();

	public void init();

	public Stream<Path> getFiles();*/

}
