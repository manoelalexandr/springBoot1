package com.example.demo.controller;

import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listar(){
    return clienteService.listar();
    }

    @GetMapping (path = "{id}")
    public Cliente getClienteById(@PathVariable("id") Long id) {
        return clienteService.selectById(id).orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@RequestBody Cliente cliente) {
        return clienteService.addCliente(cliente);
    }

    @DeleteMapping (path = "{id}")
    public Cliente deleteCliente(@PathVariable("id") Long id) {
        return clienteService.deleteCliente(id);
    }

    @PutMapping (path = "{id}")
    public Cliente updateCliente(@PathVariable("id") Long id,@RequestBody Cliente novo) {
        return clienteService.updateCliente(id, novo);
    }





}
