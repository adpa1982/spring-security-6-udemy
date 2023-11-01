package com.debugenadoideas.repositories;

import com.debugenadoideas.entities.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

/**
 * @author Alberto Puche Algarin
 * @version 1.0
 * @description PartnerRepository
 * @date
 */
public interface PartnerRepository extends JpaRepository<PartnerEntity, BigInteger> {

    Optional<PartnerEntity> findByClientId(String clientId);
}
