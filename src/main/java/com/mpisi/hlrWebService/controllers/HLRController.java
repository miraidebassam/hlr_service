package com.mpisi.hlrWebService.controllers;

import com.mpisi.hlrWebService.entitys.Subscriber;
import com.mpisi.hlrWebService.enumerations.Services;
import com.mpisi.hlrWebService.enumerations.SubscriberType;
import com.mpisi.hlrWebService.services.HLRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/hlr")
public class HLRController {
    @Autowired
    private HLRService hlrService;

    @PostMapping("/activate")
    public ResponseEntity<String> activateSubscriber(
            @RequestParam String number,
            @RequestParam SubscriberType subscriberType,
            @RequestParam List<String> servicesToActivate) {
        hlrService.activateSubscriber(number, subscriberType, servicesToActivate);
        return ResponseEntity.status(HttpStatus.CREATED).body("Abonné activé avec succès dans le HLR");
    }

    @PostMapping("/deactivate")
    public ResponseEntity<String> deactivateSubscriber(@RequestParam String number) {
        hlrService.deactivateSubscriber(number);
        return ResponseEntity.ok("Abonné désactivé avec succès dans le HLR");
    }

    @PostMapping("/activateOne")
    public ResponseEntity<String> deactivateSubscriberOne(@RequestParam String number) {
        hlrService.activateSubscriberOne(number);
        return ResponseEntity.ok("Abonné activé avec succès dans le HLR");
    }

    @GetMapping("/display/{number}")
    public ResponseEntity<Subscriber> displaySubscriber(@PathVariable String number) {
        Subscriber subscriber = hlrService.displaySubscriber(number);
        if (subscriber != null) {
            return ResponseEntity.ok(subscriber);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/display")
    public ResponseEntity<List<Subscriber>> displayAllSubscriber() {
        List<Subscriber> subscribers = hlrService.displayAllSubscriber();
        if (!subscribers.isEmpty()) {
            return ResponseEntity.ok(subscribers);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/modifyservice")
    public ResponseEntity<String> modifyServiceSubscriber(
            @RequestParam String number,
            @RequestParam boolean activate,
            @RequestParam List<String> servicesToActivate) {
        hlrService.modifyServiceSubscriber(number, activate, servicesToActivate);
        return ResponseEntity.ok("Service modifié avec succès pour l'abonné dans le HLR");
    }


    @GetMapping("/services")
    public List<String> getServices() {
        return Arrays.stream(Services.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }


    @PostMapping("/test")
    public ResponseEntity<String> test() {
        System.out.println("HLR Service est appele MI");
//        hlrService.activateSubscriber(number, subscriberType);
        return ResponseEntity.ok("Test reussit!");
    }

}
