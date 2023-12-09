package com.hommies.springbatch.repository;

import com.hommies.springbatch.model.FileDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDetailRepository extends JpaRepository<FileDetail, Long> {
}
