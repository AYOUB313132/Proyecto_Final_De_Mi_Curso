package org.zabalburu.modelo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "tipos_cursos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TipoCurso {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "tipo_id")
    private Integer tipoId;

    @Column(name = "tipo_nombre")
    private String tipoNombre;
    
    private String imagen;
    
    @OneToMany(mappedBy = "tipo")
    @JsonIgnore
    private List<Curso> cursos;
    
    
}
