package com.mpisi.hlrWebService.services;

import com.mpisi.hlrWebService.entitys.Subscriber;
import com.mpisi.hlrWebService.enumerations.Services;
import com.mpisi.hlrWebService.enumerations.SubscriberType;
import com.mpisi.hlrWebService.repositorys.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class HLRService {
    @Autowired
    private SubscriberRepository subscriberRepository;

    public void activateSubscriber(String number, SubscriberType subscriberType, List<String> servicesToActivate) {
        // Logique pour activer un abonné dans le HLR
        Subscriber subscriber = new Subscriber();
        subscriber.setNumber(number);
        subscriber.setImsi(generateImsi());  // Méthode à implémenter pour générer un IMSI unique
        subscriber.setCreationDate(new Date());
        subscriber.setSubscriberType(subscriberType);
        subscriber.setActive(true);

        // Ajoutez les services par défaut
//        subscriber.setActiveServices(Arrays.asList(Services.SERV_VOICE.name(), Services.SERV_SMS.name()));

        // Ajouter les services à activer
        if (servicesToActivate != null && !servicesToActivate.isEmpty()) {
            subscriber.setActiveServices(servicesToActivate);
        }
        subscriberRepository.save(subscriber);
    }

    public void activateSubscriberOne(String number) {
        // Logique pour activer un abonné dans le HLR
        subscriberRepository.findByNumber(number).ifPresent(subscriber -> {
            subscriber.setActive(true);
            subscriberRepository.save(subscriber);
        });
    }


    public void deactivateSubscriber(String number) {
        // Logique pour désactiver un abonné dans le HLR
        //subscriberRepository.findByNumber(number).ifPresent(subscriberRepository::delete);
        subscriberRepository.findByNumber(number).ifPresent(subscriber -> {
            subscriber.setActive(false);
            subscriberRepository.save(subscriber);
        });
    }

    public Subscriber displaySubscriber(String number) {
        // Logique pour récupérer les informations d'un abonné dans le HLR
        return subscriberRepository.findByNumber(number).orElse(null);
    }

    public void modifyServiceSubscriber(String number, boolean activate, List<String> servicesToActivate) {
//        // Logique pour activer/désactiver un service d'un abonné dans le HLR
//        subscriberRepository.findByNumber(number).ifPresent(subscriber -> {
//            List<String> activeServices = subscriber.getActiveServices();
//            if (activate) {
//                activeServices.addAll(servicesToActivate);
//           }
////          else {
////                activeServices.removeAll(servicesToActivate);
////            }
//            subscriberRepository.save(subscriber);
//        });

        subscriberRepository.findByNumber(number).ifPresent(subscriber -> {
            List<String> activeServices = subscriber.getActiveServices();
            if (activate) {
                for (String service : servicesToActivate) {
                    if (!activeServices.contains(service)) {
                        activeServices.add(service);
                    }
                }
            } else {
                activeServices.removeAll(servicesToActivate);
            }
            subscriberRepository.save(subscriber);
        });
    }

    // Méthode pour générer un IMSI unique (à adapter selon vos besoins)
    private String generateImsi() {
        return UUID.randomUUID().toString();
    }

    public List<Subscriber> displayAllSubscriber() {
        List<Subscriber> subscribers = subscriberRepository.findAll();
        return subscribers;
    }
    // d'autres méthodes a venir
}
