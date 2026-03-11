package com.celularinventario.inventario.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.celularinventario.inventario.dto.PhoneRequestDTO;
import com.celularinventario.inventario.dto.PhoneResponseDTO;
import com.celularinventario.inventario.entity.Phone;
import com.celularinventario.inventario.repository.PhoneRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhoneService {

    private final PhoneRepository phoneRepository;

    /**
     * Crea un nuevo phone.
     * 
     * @param usersRequestDTO Objeto con los datos de entrada
     * 
     * @return MessageResponseDTO confirma la creación exitosaa
     */
    public String createPhone(PhoneRequestDTO phoneRequestDTO) {
        Phone phone = new Phone();
        phone.setMarca(phoneRequestDTO.getMarca());
        phone.setModelo(phoneRequestDTO.getModelo());
        phone.setAnio(phoneRequestDTO.getAnio());
        phone.setStock(phoneRequestDTO.getStock());
        phone.setPrecio(phoneRequestDTO.getPrecio());
        phone.setDisponibilidad(phoneRequestDTO.getDisponibilidad());

        phoneRepository.save(phone);

        return "Celular Registrado Correctamente";
    }

    /**
     * * @return
     */
    public List<PhoneResponseDTO> getPhones() {

        List<Phone> phone = phoneRepository.findAll();
        List<PhoneResponseDTO> listPhones = new ArrayList<>();

        for (Phone Aphone : phone) {
            PhoneResponseDTO phoneResponseDTO = new PhoneResponseDTO();
            phoneResponseDTO.setId(Aphone.getId());
            phoneResponseDTO.setMarca(Aphone.getMarca());
            phoneResponseDTO.setModelo(Aphone.getModelo());
            phoneResponseDTO.setAnio(Aphone.getAnio());
            phoneResponseDTO.setStock(Aphone.getStock());
            phoneResponseDTO.setPrecio(Aphone.getPrecio());
            phoneResponseDTO.setDisponibilidad(Aphone.getDisponibilidad());

            listPhones.add(phoneResponseDTO);
        }
        return listPhones;
    }

    /**
     * * @param id Identificador único
     * 
     * @return
     */
    public Optional<PhoneResponseDTO> getAPhone(Integer id) {
        Optional<Phone> optionalPhone = phoneRepository.findById(id);

        if (optionalPhone.isPresent()) {
            Phone phone = optionalPhone.get();
            PhoneResponseDTO response = new PhoneResponseDTO();

            response.setId(phone.getId());
            response.setMarca(phone.getMarca());
            response.setModelo(phone.getModelo());
            response.setAnio(phone.getAnio());
            response.setStock(phone.getStock());
            response.setPrecio(phone.getPrecio());
            response.setDisponibilidad(phone.getDisponibilidad());

            return Optional.of(response);
        } else {
            return Optional.empty();
        }
    }

    public String deletePhone(Integer id) {

        if (phoneRepository.existsById(id)) {
            phoneRepository.deleteById(id);

            return "Celular Eliminado Correctamente";
        } else {
            throw new RuntimeException("Celular NO Encontrado");
        }
        
    }

    public PhoneResponseDTO updatePhoneStock(Integer id, Integer quantity) {
        Optional<Phone> optionalPhone = phoneRepository.findById(id);

        if (optionalPhone.isPresent()) {
            Phone phone = optionalPhone.get();
            phone.setStock(phone.getStock() + quantity);
            if (phone.getStock() != 0) {
                phone.setDisponibilidad(true);
            }
            phoneRepository.save(phone);

            PhoneResponseDTO response = new PhoneResponseDTO();
            response.setMessage("Stock actualizado correctamente y estado de disponibilidad actualizado a true");

            return response;
        } else {
            throw new RuntimeException("Celular no encontrado");
        }
    }
    public String updatePhone(Integer id, PhoneRequestDTO phoneRequestDTO) {
    Phone phone = phoneRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Celular NO Encontrado"));

    phone.setMarca(phoneRequestDTO.getMarca());
    phone.setModelo(phoneRequestDTO.getModelo());
    phone.setAnio(phoneRequestDTO.getAnio());
    phone.setStock(phoneRequestDTO.getStock());
    phone.setPrecio(phoneRequestDTO.getPrecio());
    phone.setDisponibilidad(phoneRequestDTO.getDisponibilidad());

    phoneRepository.save(phone);

    return "Celular Actualizado Correctamente";
}

}
