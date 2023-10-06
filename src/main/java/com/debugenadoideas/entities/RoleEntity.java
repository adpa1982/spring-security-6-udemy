package com.debugenadoideas.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author Alberto Puche Algarin
 * @version 1.0
 * @description RoleEntity
 * @date
 */
@Entity
@Table(name = "roles")
@Data
public class RoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "role_name")
    private String name;

    private String description;
}
