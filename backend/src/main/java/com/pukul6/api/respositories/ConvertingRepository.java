package com.pukul6.api.respositories;

import com.pukul6.api.entities.Converting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvertingRepository extends JpaRepository<Converting, String> {

}
