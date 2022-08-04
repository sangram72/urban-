package com.gpch.login.repository;

import com.gpch.login.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("incidentRepository")
public interface IncidentRepository extends JpaRepository<Incident, Long> {
}