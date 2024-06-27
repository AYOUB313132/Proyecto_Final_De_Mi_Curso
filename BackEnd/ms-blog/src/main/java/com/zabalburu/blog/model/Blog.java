package com.zabalburu.blog.model;

import java.util.Date;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "blogs")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Blog {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    private String titulo;
    
    private String parrafo;
    
    private String descripcion;
    
    private String imagen;
    
    private Date fecha;
    @ManyToOne()
    @JoinColumn(name = "categoria-id")
    private Categoria categoria;
    
	
}
