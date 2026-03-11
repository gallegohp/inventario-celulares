package com.celularinventario.inventario.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.celularinventario.inventario.dto.PhoneRequestDTO;
import com.celularinventario.inventario.dto.PhoneResponseDTO;
import com.celularinventario.inventario.services.PhoneService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;


@CrossOrigin(origins = "*")

@RestController
@RequiredArgsConstructor
@RequestMapping("/phone")
@Validated
public class PhoneController {
    private final PhoneService phoneService;

    @PostMapping 
    public ResponseEntity<?> createPhone(@Valid @RequestBody PhoneRequestDTO phoneRequestDTO, BindingResult bindingResult) {
    
    if (bindingResult.hasErrors()) { // booleano que indica si hay errores de validación
        
        Map<String, String> errores = new HashMap<>(); // mapa para almacenar los errores de validación
        
        bindingResult.getFieldErrors().forEach(err -> { 
            errores.put(err.getField(), err.getDefaultMessage()); // for para la lista, y se guarda (campo, error)
        }); 
        
        return ResponseEntity.badRequest().body(errores); 
    } 
    
    PhoneResponseDTO response = phoneService.createPhone(phoneRequestDTO);

    return ResponseEntity.status(HttpStatus.CREATED).body(response); 
}

    @GetMapping
    public ResponseEntity<List<PhoneResponseDTO>> getPhones() {
        try {
            List<PhoneResponseDTO> response = phoneService.getPhones();
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhoneResponseDTO> getAPhone(@PathVariable Integer id) {
        try {
            Optional<PhoneResponseDTO> optionalResponse = phoneService.getAPhone(id);

            if (optionalResponse.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(optionalResponse.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PhoneResponseDTO> deletePhone(@PathVariable Integer id) {
        try {
            Optional<PhoneResponseDTO> optionalResponse = phoneService.getAPhone(id);

            if (optionalResponse.isPresent()) {
                PhoneResponseDTO deletePhone = optionalResponse.get();

                phoneService.deletePhone(id);

                return ResponseEntity.status(HttpStatus.OK).body(deletePhone);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/subirStock/{id}/{quantity}")
    public ResponseEntity<String> updatePhoneStock(@PathVariable @Min(1) Integer id, @PathVariable  @Min(0) Integer quantity ) {
        try {
            PhoneResponseDTO response = phoneService.updatePhoneStock(id, quantity);
            return ResponseEntity.status(HttpStatus.OK).body(response.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}