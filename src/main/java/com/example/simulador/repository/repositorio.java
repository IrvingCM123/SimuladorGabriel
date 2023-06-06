/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.simulador.repository;
import org.springframework.data.repository.CrudRepository;
import com.example.simulador.models.modelos;
/**
 *
 * @author eli
 */
public interface repositorio  extends CrudRepository<modelos, Integer>{
    
}
