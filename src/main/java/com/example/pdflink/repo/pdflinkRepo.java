package com.example.pdflink.repo;

import com.example.pdflink.entity.pdflinkEntity;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface pdflinkRepo extends JpaRepository<pdflinkEntity,Long>{

}
	