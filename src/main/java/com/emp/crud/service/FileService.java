package com.emp.crud.service;

import java.io.IOException;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.emp.crud.model.FileData;
import com.emp.crud.repository.FilesDatarepository;




@Service
@Transactional
public class FileService {
    @Autowired
    private FilesDatarepository filerepo;
    
    public FileData store(MultipartFile file) throws IOException{
        //Cleaning the path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename()); 
        //Storing the file details
        FileData FileData = new FileData(fileName,file.getContentType(),file.getBytes());
        return filerepo.save(FileData);
    }

    public FileData getFile(String id) {
       return filerepo.findById(id).get();
    }

    public Stream<FileData> getAllFile() {
     return filerepo.findAll().stream();
    }

    public FileData getByName(String name)  {
       FileData file = filerepo.getByName(name);
           return file;
       
    }

}