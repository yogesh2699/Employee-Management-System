package com.emp.crud;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.emp.crud.model.FileData;
import com.emp.crud.repository.FilesDatarepository;
import com.emp.crud.service.FileService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileTests {

	
	@Autowired
	FilesDatarepository fdr;
	
	FileService mock = org.mockito.Mockito.mock(FileService.class);
	
	@Test
	public void getFileTest() throws IOException
	{
		  Path path = Paths.get("D:/test2.txt");
	      byte[] data = Files.readAllBytes(path);
		when(mock.getFile("D:/test2.txt")).thenReturn(new FileData("test2.txt","text/plain",data));
	       
		FileData file = mock.getFile("D:/test2.txt");
		assertEquals("text/plain", file.getType());
	}
	
	@Test
	public void saveFile() throws IOException
	{
		
	
		Path path = Paths.get("D:/test2.txt");
	      byte[] data = Files.readAllBytes(path);
	      
		FileData fd = new FileData("1","test2.txt","text/plain",data);
		
		
		   FileData fd1 = fdr.save(fd);
		   assertEquals("text/plain", fd.getType());
		  
	}
	
	

	
	
	
}
