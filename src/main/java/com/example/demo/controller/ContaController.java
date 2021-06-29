package com.example.demo.controller;

import com.example.demo.exception.SaldoInsuficienteException;
import com.example.demo.model.Conta;
import com.example.demo.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping
    public List<Conta> listar(){
        return contaService.listarConta();
    }

    @GetMapping (path = "{id}")
    public Conta getContaById (@PathVariable("id") Long id) {
        return contaService.selectById(id).get();
    }

    @PostMapping
    public Conta adicionar (@RequestBody Conta conta) {
        return contaService.addConta(conta);
    }

    @DeleteMapping (path = "{id}")
    public Conta deletarConta (@PathVariable("id") long id) {
        return contaService.deleteConta(id);
    }

    @PutMapping (path = "{id}")
    public Conta deposito (@PathVariable("id") Long id, @RequestBody double valor){
        return contaService.deposito(id, valor);
    }

    @PutMapping (path = "{id}")
    public Conta saque (@PathVariable("id") Long id, @RequestBody double valor){
        try{
            return contaService.saque(id, valor);
        } catch (SaldoInsuficienteException e) {
            e.printStackTrace();
        }
        return null;
    }


}
