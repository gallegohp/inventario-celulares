package com.celularinventario.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.celularinventario.inventario.entity.Phone;

@Repository
public interface PhonesRepository extends JpaRepository<Phone, Integer> {

}