package com.impacta.microservices.credito.credito.controller;

import com.impacta.microservices.credito.credito.domain.Credito;
import com.impacta.microservices.credito.credito.repository.CreditoRepository;
import com.impacta.microservices.credito.credito.service.CreditoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@RestController
@RequestMapping("/credito")
public class CreditoController {

    @Autowired
    private CreditoRepository repository;

    private final CreditoService creditoService;

    public CreditoController(CreditoService creditoService) {
        this.creditoService = creditoService;
    }

    @GetMapping(path = "/status_aplicattion")
    public String check(){
        return "Aplicação online";
    }

    @GetMapping(path = "/consulta/{id}")
    public  ResponseEntity<Credito> consultarCredito (@PathVariable("id") Integer id ){
        return repository.findById(id)
        .map(record -> ResponseEntity.ok().body(record))
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/creditar_valor")
    public Credito salvar(@RequestBody Credito credito){
        return repository.save(credito);
    }

    @GetMapping(path = "/lista")
    public List<Credito> list() throws UnknownHostException {
        System.out.println("Hostname: " + InetAddress.getLocalHost().getHostName());
        List<Credito> creditoList = creditoService.list();
        return creditoList;
    }

}
