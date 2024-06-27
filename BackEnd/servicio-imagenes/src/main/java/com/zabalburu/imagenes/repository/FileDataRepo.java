package com.zabalburu.imagenes.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.zabalburu.imagenes.model.FileData;


public interface FileDataRepo extends JpaRepository<FileData, Integer> {

	Optional <FileData> findByNombre(String nombre);
}
