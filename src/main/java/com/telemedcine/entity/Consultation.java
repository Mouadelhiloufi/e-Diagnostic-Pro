package com.telemedcine.entity;

import com.telemedcine.entity.enums.ConsultationStatus;
import com.telemedcine.entity.enums.PriorityLevel;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "consultations")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private User patient;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "generaliste_id", nullable = false)
    private User generaliste;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slot_id", unique = true)
    private Slot slot;

    @Column(name = "date_consultation", nullable = false)
    private LocalDateTime dateConsultation;

    @Column(columnDefinition = "text")
    private String motif;

    @Column(columnDefinition = "text")
    private String diagnostic;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private ConsultationStatus status = ConsultationStatus.PLANIFIEE;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PriorityLevel priorite = PriorityLevel.NORMALE;

    public Consultation() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getPatient() { return patient; }
    public void setPatient(User patient) { this.patient = patient; }
    public User getGeneraliste() { return generaliste; }
    public void setGeneraliste(User generaliste) { this.generaliste = generaliste; }
    public Slot getSlot() { return slot; }
    public void setSlot(Slot slot) { this.slot = slot; }
    public LocalDateTime getDateConsultation() { return dateConsultation; }
    public void setDateConsultation(LocalDateTime dateConsultation) { this.dateConsultation = dateConsultation; }
    public String getMotif() { return motif; }
    public void setMotif(String motif) { this.motif = motif; }
    public String getDiagnostic() { return diagnostic; }
    public void setDiagnostic(String diagnostic) { this.diagnostic = diagnostic; }
    public ConsultationStatus getStatus() { return status; }
    public void setStatus(ConsultationStatus status) { this.status = status; }
    public PriorityLevel getPriorite() { return priorite; }
    public void setPriorite(PriorityLevel priorite) { this.priorite = priorite; }
}
