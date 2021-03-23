package com.CsvFileReader.IOC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CsvFileReader.IOC.model.CsvInserter;
@Repository
public interface CSVRepository extends JpaRepository<CsvInserter, Integer>
{
	
}

