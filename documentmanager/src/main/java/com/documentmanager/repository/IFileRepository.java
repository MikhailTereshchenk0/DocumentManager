package com.documentmanager.repository;

import com.documentmanager.model.FileMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface IFileRepository extends JpaRepository<FileMeta, UUID> {
    @Query("SELECT f FROM FileMeta f WHERE f.userId = :userId")
    List<FileMeta> findAllByUserId(@Param("userId") Long userId);
}
