package com.rememberme.rememberMe.repositories;

import com.rememberme.rememberMe.domain.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAlertRepository extends JpaRepository<Alert, Long> {
}
