package com.shamna.Aesthetic.Shop.Repositories;

import com.shamna.Aesthetic.Shop.Entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
    Optional<Address> findByUserId(String userId);
}
