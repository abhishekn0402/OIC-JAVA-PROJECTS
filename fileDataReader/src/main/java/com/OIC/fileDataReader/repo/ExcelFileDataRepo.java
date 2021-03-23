package com.OIC.fileDataReader.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OIC.fileDataReader.dto.ExcelFileDataInserter;

@Repository
public interface ExcelFileDataRepo extends JpaRepository<ExcelFileDataInserter, Integer>
{

}
