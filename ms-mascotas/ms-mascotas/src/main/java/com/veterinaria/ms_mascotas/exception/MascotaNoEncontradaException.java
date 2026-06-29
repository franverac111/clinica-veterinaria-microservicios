package com.veterinaria.ms_mascotas.exception;

public class MascotaNoEncontradaException extends RuntimeException {

    public MascotaNoEncontradaException(String mensaje) {
        super(mensaje);
    }

}