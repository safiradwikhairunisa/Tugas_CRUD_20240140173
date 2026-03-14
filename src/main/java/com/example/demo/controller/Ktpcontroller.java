package com.example.demo.controller;

import com.example.demo.dto.Apiresponsedto;
import com.example.demo.dto.KtpRequestDto;
import com.example.demo.dto.KtpResponseDto;
import com.example.demo.service.Ktpservice;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ktp")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Izinkan request dari frontend (CORS)
public class Ktpcontroller {

    private final Ktpservice ktpService;

    @PostMapping
    public ResponseEntity<Apiresponsedto<KtpResponseDto>> create(
            @Valid @RequestBody KtpRequestDto requestDto) {
        try {
            KtpResponseDto result = ktpService.create(requestDto);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(Apiresponsedto.success("Data KTP berhasil ditambahkan", result));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Apiresponsedto.error(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<Apiresponsedto<List<KtpResponseDto>>> findAll() {
        try {
            List<KtpResponseDto> result = ktpService.findAll();
            return ResponseEntity.ok(
                    Apiresponsedto.success("Berhasil mengambil semua data KTP", result));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Apiresponsedto.error(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Apiresponsedto<KtpResponseDto>> findById(
            @PathVariable Integer id) {
        try {
            KtpResponseDto result = ktpService.findById(id);
            return ResponseEntity.ok(
                    Apiresponsedto.success("Berhasil mengambil data KTP", result));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Apiresponsedto.error(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Apiresponsedto<KtpResponseDto>> update(
            @PathVariable Integer id,
            @Valid @RequestBody KtpRequestDto requestDto) {
        try {
            KtpResponseDto result = ktpService.update(id, requestDto);
            return ResponseEntity.ok(
                    Apiresponsedto.success("Data KTP berhasil diperbarui", result));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Apiresponsedto.error(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Apiresponsedto<Void>> delete(
            @PathVariable Integer id) {
        try {
            ktpService.delete(id);
            return ResponseEntity.ok(
                    Apiresponsedto.success("Data KTP berhasil dihapus", null));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Apiresponsedto.error(e.getMessage()));
        }
    }
}