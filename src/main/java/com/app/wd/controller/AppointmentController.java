package com.app.wd.controller;

import com.app.wd.model.Appointment;
import com.app.wd.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @PostMapping
    public Appointment create(@RequestBody Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @GetMapping
    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Appointment getById(@PathVariable Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Appointment update(@PathVariable Long id, @RequestBody Appointment updated) {
        return appointmentRepository.findById(id).map(app -> {
            app.setTitle(updated.getTitle());
            app.setDateTime(updated.getDateTime());
            app.setLocation(updated.getLocation());
            app.setDescription(updated.getDescription());
            app.setStatus(updated.getStatus());
            return appointmentRepository.save(app);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        appointmentRepository.deleteById(id);
    }

    @GetMapping("/search")
    public List<Appointment> search(@RequestParam String keyword) {
        return appointmentRepository.findByTitleContainingIgnoreCase(keyword);
    }
}
