package com.mpisi.hlrWebService.services;

import com.mpisi.hlrWebService.entitys.Cdr;
import com.mpisi.hlrWebService.repositorys.CDRRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CDRService {
    @Autowired
    private CDRRepository cdrRepository;

    public void createCdr(String number, String imsi, int duree, String subscriberType, String calleeNumber) {
        // Logique pour activer un abonné dans le HLR
        Cdr cdr = new Cdr();
        cdr.setCallerNumber(number);
        cdr.setImsi(imsi);  // Méthode à implémenter pour générer un IMSI unique
        cdr.setCallDuration(duree);
        cdr.setSubscriberType(subscriberType);
        cdr.setCallDateTime(new Date());
        cdr.setCalleeNumber(calleeNumber);

        cdrRepository.save(cdr);
    }

    public List<Cdr> displayAllCdr() {
        List<Cdr> cdrs = cdrRepository.findAll();
        return cdrs;
    }
}
