package com.mpisi.hlrWebService.controllers;

import com.mpisi.hlrWebService.entitys.Cdr;
import com.mpisi.hlrWebService.entitys.Subscriber;
import com.mpisi.hlrWebService.repositorys.CDRRepository;
import com.mpisi.hlrWebService.services.CDRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hlr")
public class CDRController {
    @Autowired
    private CDRService cdrService; // Repository pour enregistrer les CDR

    @GetMapping("/displayCdr")
    public ResponseEntity<List<Cdr>> displayAllCdr() {
        List<Cdr> cdrs = cdrService.displayAllCdr();
        if (!cdrs.isEmpty()) {
            return ResponseEntity.ok(cdrs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/generateCDR")
    public ResponseEntity<String> generateCDR(@RequestBody Cdr cdr) {
        try {
            // Logique pour générer le CDR et l'enregistrer dans la base de données
            cdrService.createCdr(cdr.getCallerNumber(), cdr.getImsi(), cdr.getCallDuration(), cdr.getSubscriberType(), cdr.getCalleeNumber());
            return ResponseEntity.status(HttpStatus.CREATED).body("CDR generé avec succès dans le HLR");
//            return new ResponseEntity<>("CDR generated and saved successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to generate CDR: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
