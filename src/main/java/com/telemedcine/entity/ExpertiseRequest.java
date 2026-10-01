package com.telemedcine.entity;

import com.telemedcine.entity.enums.ExpertiseStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "expertise_requests")
public class ExpertiseRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "consultation_id", nullable = false)
    private Consultation consultation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "specialiste_id", nullable = false)
    private User specialiste;

    @Column(nullable = false, columnDefinition = "text")
    private String question;

    @Column(name = "avis_specialiste", columnDefinition = "text")
    private String avisSpecialiste;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ExpertiseStatus status = ExpertiseStatus.EN_ATTENTE;

    @Column(name = "date_demande")
    private LocalDateTime dateDemande = LocalDateTime.now();

    @Column(name = "date_reponse")
    private LocalDateTime dateReponse;

    public ExpertiseRequest() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Consultation getConsultation() { return consultation; }
    public void setConsultation(Consultation consultation) { this.consultation = consultation; }
    public User getSpecialiste() { return specialiste; }
    public void setSpecialiste(User specialiste) { this.specialiste = specialiste; }
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
    public String getAvisSpecialiste() { return avisSpecialiste; }
    public void setAvisSpecialiste(String avisSpecialiste) { this.avisSpecialiste = avisSpecialiste; }
    public ExpertiseStatus getStatus() { return status; }
    public void setStatus(ExpertiseStatus status) { this.status = status; }
    public LocalDateTime getDateDemande() { return dateDemande; }
    public void setDateDemande(LocalDateTime dateDemande) { this.dateDemande = dateDemande; }
    public LocalDateTime getDateReponse() { return dateReponse; }
    public void setDateReponse(LocalDateTime dateReponse) { this.dateReponse = dateReponse; }
}
