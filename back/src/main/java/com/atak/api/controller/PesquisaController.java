package com.atak.api.controller;
import com.atak.api.dto.DadosPesquisa;
import com.atak.api.service.PesquisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@CrossOrigin("*")
public class PesquisaController {

    @Autowired
    PesquisaService pesquisaService;

    @GetMapping("{termoBusca}")
    @CrossOrigin("https://localhost:3000")
    public DadosPesquisa pesquisa(@PathVariable String termoBusca) throws InterruptedException {
        return pesquisaService.buscaGoogle(termoBusca);
    }
}