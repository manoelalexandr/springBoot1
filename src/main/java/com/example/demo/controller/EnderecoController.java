package com.example.demo.controller;

import com.example.demo.model.Endereco;
import com.example.demo.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public List<Endereco> listar(){
        return enderecoService.listarEndereco();
    }

    @GetMapping (path = ("{id}"))
    public Endereco getEnderecoById(@PathVariable ("id") Long id) {
        return enderecoService.selectEnderecoById(id).orElse(null);
    }

    @PostMapping
    public Endereco adicionaEndereco(Endereco endereco){
        return enderecoService.addEndereco(endereco);
    }

    @DeleteMapping (path = ("{id}"))
    public Endereco deleteEndereco(@PathVariable("id") Long id){
        return enderecoService.deleteEndereco(id);
    }

}
