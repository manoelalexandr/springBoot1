package com.example.demo.service;

import com.example.demo.model.Cliente;
import com.example.demo.model.Movimentacao;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    GregorianCalendar gc = new GregorianCalendar();

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }

    public Cliente addCliente (Cliente cliente){
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setDataDaMovimentacao(gc.getTime());
        movimentacao.setTipo("Adicionando Cliente");
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> selectById(Long id){
        return clienteRepository.findById(id);
    }

    public Cliente deleteCliente(Long id){

        Optional<Cliente> toDelete =selectById(id);
        if(toDelete.isEmpty())
            return null;
    clienteRepository.delete(toDelete.get());
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setDataDaMovimentacao(gc.getTime());
        movimentacao.setTipo("Cliente" + toDelete.get().getNome() + "Deletado");
        
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



