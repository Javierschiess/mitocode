package com.mitocode.model;

import lombok.Data;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "nombre", nullable = false, unique = true )
    private String username;

    @Column(name = "clave", nullable = false)
    private String password;

    @Column(name = "estado", nullable = false)
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol",
                        joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario"),
                        inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "id_rol"))
    private List<Rol> roles;

}
