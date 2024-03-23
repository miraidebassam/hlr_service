package com.mpisi.hlrWebService.repositorys;

import com.mpisi.hlrWebService.entitys.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    Optional<Subscriber> findByNumber(String number);
}
