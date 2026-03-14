package com.example.demo.util;


import org.springframework.stereotype.Component;

@Component
public class Validationutil {
    public boolean isValidNomorKtp(String nomorKtp) {
        if (nomorKtp == null || nomorKtp.isBlank()) return false;
        return nomorKtp.matches("\\d{16}");
    }

    public boolean isValidJenisKelamin(String jenisKelamin) {
        if (jenisKelamin == null || jenisKelamin.isBlank()) return false;
        return jenisKelamin.equalsIgnoreCase("Laki-laki") ||
                jenisKelamin.equalsIgnoreCase("Perempuan");
    }

    public String sanitize(String input) {
        return input == null ? "" : input.trim();
    }
}

