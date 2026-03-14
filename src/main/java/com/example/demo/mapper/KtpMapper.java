package com.example.demo.mapper;

import com.example.demo.dto.KtpRequestDto;
import com.example.demo.dto.KtpResponseDto;
import com.example.demo.entity.Ktpentity;
import com.example.demo.model.Ktpmodel;
import org.springframework.stereotype.Component;

@Component
public class KtpMapper {

    // Entity → ResponseDto
    public KtpResponseDto toResponseDto(Ktpentity entity) {
        if (entity == null) return null;
        return new KtpResponseDto(
                entity.getId(),
                entity.getNomorKtp(),
                entity.getNamaLengkap(),
                entity.getAlamat(),
                entity.getTanggalLahir(),
                entity.getJenisKelamin()
        );
    }

    // RequestDto → Entity
    public Ktpentity toEntity(KtpRequestDto dto) {
        if (dto == null) return null;
        Ktpentity entity = new Ktpentity();
        entity.setNomorKtp(dto.getNomorKtp());
        entity.setNamaLengkap(dto.getNamaLengkap());
        entity.setAlamat(dto.getAlamat());
        entity.setTanggalLahir(dto.getTanggalLahir());
        entity.setJenisKelamin(dto.getJenisKelamin());
        return entity;
    }

    // Entity → Model
    public Ktpmodel toModel(Ktpentity entity) {
        if (entity == null) return null;
        return new Ktpmodel(
                entity.getId(),
                entity.getNomorKtp(),
                entity.getNamaLengkap(),
                entity.getAlamat(),
                entity.getTanggalLahir(),
                entity.getJenisKelamin()
        );
    }

    // Model → Entity
    public Ktpentity fromModel(Ktpmodel model) {
        if (model == null) return null;
        return new Ktpentity(
                model.getId(),
                model.getNomorKtp(),
                model.getNamaLengkap(),
                model.getAlamat(),
                model.getTanggalLahir(),
                model.getJenisKelamin()
        );
    }
}