package com.example.demo.service;

import com.example.demo.model.Cliente;
import com.example.demo.model.Conta;
import com.example.demo.model.Movimentacao;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClienteService {

    GregorianCalendar gc = new GregorianCalendar();

    @Autowired
    private ContaService contaService;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }

    public Cliente addCliente (Cliente cliente) {
        clienteRepository.save(cliente);
        Conta conta = new Conta();
        conta.setSaldo(cliente.getDepositoInicial());
        conta.setCliente(cliente);
        contaService.addConta(conta);
        List<Conta> contas = new ArrayList<>();
        contas.add(conta);
        cliente.setContas(contas);
        return cliente;
    }

    public Optional<Cliente> selectById(Long id){
        return clienteRepository.findById(id);
    }

    public Cliente deleteCliente(Long id){

        Optional<Cliente> toDelete =selectById(id);
        if(toDelete.isEmpty())
            return null;
    clienteRepository.delete(toDelete.get());

    return toDelete.get();
    }

    public Cliente updateCliente(Long id, Cliente novo) {
        Optional<Cliente> toUpdate = selectById(id);
        if (toUpdate.isEmpty())
            return null;

        toUpdate.get().setNome(novo.getNome());
            return clienteRepository.save(toUpdate.get());
        }
    }



