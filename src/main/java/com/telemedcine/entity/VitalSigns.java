package com.telemedcine.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "vital_signs")
public class VitalSigns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "consultation_id", nullable = false)
    private Consultation consultation;

    @Column(length = 20)
    private String tension;

    @Column(precision = 4, scale = 1)
    private BigDecimal temperature;

    @Column(name = "frequence_cardiaque")
    private Integer frequenceCardiaque;

    @Column(name = "saturation_o2")
    private Integer saturationO2;

    @Column(name = "date_prise")
    private LocalDateTime datePrise = LocalDateTime.now();

    public VitalSigns() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Consultation getConsultation() { return consultation; }
    public void setConsultation(Consultation consultation) { this.consultation = consultation; }
    public String getTension() { return tension; }
    public void setTension(String tension) { this.tension = tension; }
    public BigDecimal getTemperature() { return temperature; }
    public void setTemperature(BigDecimal temperature) { this.temperature = temperature; }
    public Integer getFrequenceCardiaque() { return frequenceCardiaque; }
    public void setFrequenceCardiaque(Integer frequenceCardiaque) { this.frequenceCardiaque = frequenceCardiaque; }
    public Integer getSaturationO2() { return saturationO2; }
    public void setSaturationO2(Integer saturationO2) { this.saturationO2 = saturationO2; }
    public LocalDateTime getDatePrise() { return datePrise; }
    public void setDatePrise(LocalDateTime datePrise) { this.datePrise = datePrise; }
}
