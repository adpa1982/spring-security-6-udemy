package com.debugenadoideas.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author Alberto Puche Algarin
 * @version 1.0
 * @description PartnerEntity
 * @date
 */
@Entity
@Table(name = "partners")
@Data
public class PartnerEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    private String clientId;

    private String clientName;

    private String clientSecret;

    private String scopes;

    private String grantTypes;

    private String authenticationMethods;

    private String redirectUri;

    private String redirectUriLogout;
}
