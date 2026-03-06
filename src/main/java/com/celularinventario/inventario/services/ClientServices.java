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

        Phone phone = phoneRepository.findById(id).orElseThrow(() -> new RuntimeException("Phone not found"));

        if (phone.getStock() < quantity) {
            throw new RuntimeException("Not enough stock available");
        }

        phone.setStock(phone.getStock() - quantity);
        phoneRepository.save(phone);
        PhoneResponseDTO responseDTO = new PhoneResponseDTO();
        responseDTO.setMarca(phone.getMarca());
        responseDTO.setModelo(phone.getModelo());
        responseDTO.setAnio(phone.getAnio());
        responseDTO.setStock(phone.getStock());
        responseDTO.setPrecio(phone.getPrecio());
        responseDTO.setDisponibilidad(phone.getDisponibilidad());
        return responseDTO;
    } 

}
