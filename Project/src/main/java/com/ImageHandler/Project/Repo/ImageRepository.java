package com.ImageHandler.Project.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ImageHandler.Project.DTO.Image;
@Repository
public interface ImageRepository extends JpaRepository<Image, Integer>
{
public List<Image> findById(int imageID);
}
