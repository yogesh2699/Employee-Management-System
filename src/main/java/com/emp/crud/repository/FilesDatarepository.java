package com.emp.crud.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emp.crud.model.FileData;



@Repository
public interface FilesDatarepository extends JpaRepository<FileData, String> {

    @Query("SELECT f FROM FileData f WHERE f.name = ?2")
    public FileData getByName( String name);
}