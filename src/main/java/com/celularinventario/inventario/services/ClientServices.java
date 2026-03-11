package com.celularinventario.inventario.services;

import org.springframework.stereotype.Service;

import com.celularinventario.inventario.dto.PhoneResponseDTO;
import com.celularinventario.inventario.entity.Phone;
import com.celularinventario.inventario.repository.PhoneRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ClientServices {
    
    private final PhoneRepository phoneRepository;

    public PhoneResponseDTO buyPhone(Integer id, Integer quantity) {

        Phone phone = phoneRepository.findById(id).orElseThrow(() -> new RuntimeException("Celular no encontrado"));

        if (phone.getStock() < quantity) {
            throw new RuntimeException("No hay suficiente stock disponible");
        }

        if (!phone.getDisponibilidad()) {
            throw new RuntimeException("El celular no está disponible para la venta");
        }

        phone.setStock(phone.getStock() - quantity);
        if (phone.getStock() == 0) {
            phone.setDisponibilidad(false);
        }
        phoneRepository.save(phone);
        PhoneResponseDTO responseDTO = new PhoneResponseDTO();
        responseDTO.setMessage("Compra exitosa");
        return responseDTO;
    } 

}
