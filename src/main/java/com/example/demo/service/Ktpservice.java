package com.example.demo.service;

import com.example.demo.dto.KtpRequestDto;
import com.example.demo.dto.KtpResponseDto;

import java.util.List;

public interface Ktpservice {

    KtpResponseDto create(KtpRequestDto requestDto);

    List<KtpResponseDto> findAll();

    KtpResponseDto findById(Integer id);

    KtpResponseDto update(Integer id, KtpRequestDto requestDto);

    // DELETE
    void delete(Integer id);
}

