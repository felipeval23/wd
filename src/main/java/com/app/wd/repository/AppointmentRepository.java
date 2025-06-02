package com.app.wd.repository;

import com.app.wd.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByTitleContainingIgnoreCase(String title);
    List<Appointment> findByLocationContainingIgnoreCase(String location);
}
