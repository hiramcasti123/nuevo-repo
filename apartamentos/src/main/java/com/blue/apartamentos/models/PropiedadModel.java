package com.blue.apartamentos.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "propiedades")
public class PropiedadModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String direccion;
    private String tipo;
    private Double precio_noche;
    private Long propietarioId;
    
    public PropiedadModel() {}
    
    public PropiedadModel(String direccion, String tipo, Double precio_noche, Long propietarioId) {
        this.direccion = direccion;
        this.tipo = tipo;
        this.precio_noche = precio_noche;
        this.propietarioId = propietarioId;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    public Double getPrecio_noche() { return precio_noche; }
    public void setPrecio_noche(Double precio_noche) { this.precio_noche = precio_noche; }
    
    public Long getPropietarioId() { return propietarioId; }
    public void setPropietarioId(Long propietarioId) { this.propietarioId = propietarioId; }
}
