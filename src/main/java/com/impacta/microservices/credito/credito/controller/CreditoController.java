package com.impacta.microservices.credito.credito.controller;

import com.impacta.microservices.credito.credito.domain.Credito;
import com.impacta.microservices.credito.credito.repository.CreditoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.util.List;

@RestController
@RequestMapping("/credito")
public class CreditoController {

    @Autowired
    private CreditoRepository repository;

    @GetMapping(path = "/status_aplicacao")
    public String checarStatusAplicacao(){
        return "Aplicação online";
    }

    @PostMapping(path = "/creditar_valor")
    public Credito salvar(@RequestBody Credito credito){
        return repository.save(credito);
    }

    //Tratativa de erro quando não localizar o id_transacao
    @GetMapping(path = "/consulta/{id_transacao}")
    public Credito consultarCredito (@PathVariable("id_transacao") Integer id_transacao ){

        return  repository.findById(id_transacao)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Crédito não encontrado para o id informado."));
    }

    /*
    @GetMapping(path = "/lista")
    public List<Credito> list() throws UnknownHostException {
        System.out.println("Hostname: " + InetAddress.getLocalHost().getHostName());
        List<Credito> creditoList = creditoService.list();
        return creditoList;
    }
    */

}
