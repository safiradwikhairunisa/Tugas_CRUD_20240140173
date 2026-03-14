package com.example.demo.impl;

import com.example.demo.dto.KtpRequestDto;
import com.example.demo.dto.KtpResponseDto;
import com.example.demo.entity.Ktpentity;
import com.example.demo.mapper.KtpMapper;
import com.example.demo.repository.KtpRepository;
import com.example.demo.service.Ktpservice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Ktpserviceimpl implements Ktpservice {

    private final KtpRepository ktpRepository;
    private final KtpMapper ktpMapper;

    @Override
    public KtpResponseDto create(KtpRequestDto requestDto) {
        // Validasi: nomor KTP tidak boleh duplikat
        if (ktpRepository.existsByNomorKtp(requestDto.getNomorKtp())) {
            throw new RuntimeException("Nomor KTP " + requestDto.getNomorKtp() + " sudah terdaftar!");
        }

        Ktpentity entity = ktpMapper.toEntity(requestDto);
        Ktpentity saved = ktpRepository.save(entity);
        return ktpMapper.toResponseDto(saved);
    }

    @Override
    public List<KtpResponseDto> findAll() {
        return ktpRepository.findAll()
                .stream()
                .map(ktpMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public KtpResponseDto findById(Integer id) {
        Ktpentity entity = ktpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data KTP dengan ID " + id + " tidak ditemukan!"));
        return ktpMapper.toResponseDto(entity);
    }

    @Override
    public KtpResponseDto update(Integer id, KtpRequestDto requestDto) {
        // Pastikan data ada
        Ktpentity existing = ktpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data KTP dengan ID " + id + " tidak ditemukan!"));

        // Validasi: nomor KTP tidak boleh dipakai ID lain
        if (ktpRepository.existsByNomorKtpAndIdNot(requestDto.getNomorKtp(), id)) {
            throw new RuntimeException("Nomor KTP " + requestDto.getNomorKtp() + " sudah digunakan oleh data lain!");
        }

        // Update field
        existing.setNomorKtp(requestDto.getNomorKtp());
        existing.setNamaLengkap(requestDto.getNamaLengkap());
        existing.setAlamat(requestDto.getAlamat());
        existing.setTanggalLahir(requestDto.getTanggalLahir());
        existing.setJenisKelamin(requestDto.getJenisKelamin());

        Ktpentity updated = ktpRepository.save(existing);
        return ktpMapper.toResponseDto(updated);
    }

    @Override
    public void delete(Integer id) {
        if (!ktpRepository.existsById(id)) {
            throw new RuntimeException("Data KTP dengan ID " + id + " tidak ditemukan!");
        }
        ktpRepository.deleteById(id);
    }
}

