package com.shamna.Aesthetic.Shop.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shamna.Aesthetic.Shop.Entities.CartData;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartData,Integer>{
    List<CartData> findAllByUserId(String userId);
    List<CartData> findByUserId(String userId);
    Optional<CartData> findByUserIdAndPid(String userId, int pid);
    List<CartData> deleteByUserIdAndPid(String userId, int pid);
}
