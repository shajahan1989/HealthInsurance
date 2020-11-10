package com.example.HealthInsurance.Repository;

import com.example.HealthInsurance.model.Premium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PremiumRepository extends JpaRepository<Premium,Long> {
       Premium findByName(String name);
}
