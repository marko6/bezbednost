package com.main.app.controller;

import com.main.app.domain.dto.CertificateDTO;
import com.main.app.domain.dto.Entities;
import com.main.app.domain.model.Certificate;
import com.main.app.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.cert.X509Certificate;

@RestController
@RequestMapping("/api/cert")
public class CertificateController {

    private CertificateService certService;

    @Autowired
    public CertificateController(CertificateService certService) {
        this.certService = certService;
    }

    @GetMapping
    public ResponseEntity<Entities> getCerts(Pageable page) {
        Entities result = new Entities();

        Page<Certificate> certs = certService.getCerts(page);

        result.setEntities(certs.getContent());
        result.setTotal(certs.getTotalElements());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<String> createCert(@RequestBody CertificateDTO certificateDTO) {

        X509Certificate certificate = certService.generate(certificateDTO.getIssuerName(), certificateDTO.getName(),
                certificateDTO.getSerialNumber(), certificateDTO.getStartDate(), certificateDTO.getEndDate());

        return new ResponseEntity<>(certificate.toString(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        certService.delete(id);
    }
}
