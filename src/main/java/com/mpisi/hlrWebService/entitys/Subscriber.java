package com.mpisi.hlrWebService.entitys;

import com.mpisi.hlrWebService.enumerations.SubscriberType;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_subscriber")
public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String number;

    @Column(nullable = false)
    private String imsi;

    @Column(nullable = false)
    private Date creationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubscriberType subscriberType;

    @ElementCollection
    private List<String> activeServices = new ArrayList<>();

    @Column(nullable = false)
    private boolean isActive;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public SubscriberType getSubscriberType() {
        return subscriberType;
    }

    public void setSubscriberType(SubscriberType subscriberType) {
        this.subscriberType = subscriberType;
    }

    public List<String> getActiveServices() {
        return activeServices;
    }

    public void setActiveServices(List<String> activeServices) {
        this.activeServices = activeServices;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
