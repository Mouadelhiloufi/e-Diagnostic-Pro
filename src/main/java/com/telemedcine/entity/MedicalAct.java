package com.telemedcine.entity;

import com.telemedcine.entity.enums.ActType;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "medical_acts")
public class MedicalAct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "expertise_request_id", nullable = false)
    private ExpertiseRequest expertiseRequest;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ActType type;

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "resultat_url", length = 255)
    private String resultatUrl;

    @Column(name = "date_realisation")
    private LocalDateTime dateRealisation;

    public MedicalAct() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public ExpertiseRequest getExpertiseRequest() { return expertiseRequest; }
    public void setExpertiseRequest(ExpertiseRequest expertiseRequest) { this.expertiseRequest = expertiseRequest; }
    public ActType getType() { return type; }
    public void setType(ActType type) { this.type = type; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getResultatUrl() { return resultatUrl; }
    public void setResultatUrl(String resultatUrl) { this.resultatUrl = resultatUrl; }
    public LocalDateTime getDateRealisation() { return dateRealisation; }
    public void setDateRealisation(LocalDateTime dateRealisation) { this.dateRealisation = dateRealisation; }
}
