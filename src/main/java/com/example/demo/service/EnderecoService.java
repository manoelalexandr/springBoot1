package com.example.demo.service;

import com.example.demo.model.Endereco;
import com.example.demo.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Endereco> listarEndereco(){
        return enderecoRepository.findAll();
    }

    public Endereco addEndereco(Endereco endereco){
        return enderecoRepository.save(endereco);
    }
    public Optional<Endereco> selectEnderecoById(Long id){
        return enderecoRepository.findById(id);
    }

    public Endereco deleteEndereco(Long id){
        Optional<Endereco> toDelete = selectEnderecoById(id);
        if(toDelete.isEmpty())
            return null;
        enderecoRepository.delete(toDelete.get());
        return toDelete.get();
    }


}
