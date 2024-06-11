package com.ms.pruebatecnica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PruebaTecnicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PruebaTecnicaApplication.class, args);
        // Generar Key para el token
//        byte[] keyBytes = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();
//        String keyString = Base64.getEncoder().encodeToString(keyBytes);
//        System.out.println("Clave generada: " + keyString);
    }

}
