package com.example.demo.repository;

import com.example.demo.entity.Ktpentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KtpRepository extends JpaRepository<Ktpentity, Integer> {
    boolean existsByNomorKtp(String nomorKtp);
    boolean existsByNomorKtpAndIdNot(String nomorKtp, Integer id);
    Optional<Ktpentity> findByNomorKtp(String nomorKtp);
}
