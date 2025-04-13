package com.shamna.Aesthetic.Shop.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shamna.Aesthetic.Shop.Entities.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login,Integer> {
    Optional<Login> findByUname(String uname);

}
