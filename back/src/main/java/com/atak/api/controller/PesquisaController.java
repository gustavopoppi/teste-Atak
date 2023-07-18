package com.atak.api.controller;
import com.atak.api.service.PesquisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class PesquisaController {

    @Autowired
    PesquisaService pesquisaService;

    @GetMapping("{termoBusca}")
    public String pesquisa(@PathVariable String termoBusca) throws InterruptedException {
        pesquisaService.buscaGoogle(termoBusca);

        return null;
    }
}