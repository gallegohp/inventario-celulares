package com.celularinventario.inventario.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.celularinventario.inventario.dto.PhoneResponseDTO;
import com.celularinventario.inventario.services.ClientServices;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")

@RequiredArgsConstructor
@RequestMapping("/client")
@RestController
public class ClientController {
    
    private final ClientServices clientServices;

    @PatchMapping("/buy/{id}/{quantity}")
    public ResponseEntity<String> buyPhone(@PathVariable Integer id, @PathVariable Integer quantity) {
        try {
            PhoneResponseDTO response = clientServices.buyPhone(id, quantity);
            return ResponseEntity.ok(response.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }


}
