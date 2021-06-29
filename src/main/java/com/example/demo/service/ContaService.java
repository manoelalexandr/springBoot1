package com.example.demo.service;

import com.example.demo.exception.SaldoInsuficienteException;
import com.example.demo.model.Conta;
import com.example.demo.model.Movimentacao;
import com.example.demo.repository.ContaRepository;
import com.example.demo.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    GregorianCalendar gc = new GregorianCalendar();

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private ContaRepository contaRepository;

    public List<Conta> listarConta() {
        return contaRepository.findAll();
    }

    public Conta addConta(Conta conta) {
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setTipo("Conta criada" + conta.getId() + "para esse ID" );
        movimentacao.setDataDaMovimentacao(gc.getTime());
        movimentacaoRepository.save(movimentacao);
        return contaRepository.save(conta);
    }

    public Optional<Conta> selectById (Long id) {
        return contaRepository.findById(id);
    }

    public Conta deleteConta (Long id) {
        Optional<Conta> toDelete = selectById(id);
        if(toDelete.isEmpty())
            return null;
        contaRepository.delete(toDelete.get());
        return toDelete.get();
    }

    public Conta deposito(Long id, double valor) {
        Optional<Conta> toUpdate = selectById(id);
        if(toUpdate.isEmpty())
            return null;
        toUpdate.get().setSaldo(toUpdate.get().getSaldo() + valor);
        return contaRepository.save(toUpdate.get());
    }

    public Conta saque(Long id, double valor) throws SaldoInsuficienteException {
        Optional<Conta> toUpdate = selectById(id);
        if(toUpdate.isEmpty())
        return null;
        if(toUpdate.get().getSaldo() - valor < 0){
            throw new SaldoInsuficienteException();
        }
        toUpdate.get().setSaldo(toUpdate.get().getSaldo() - valor);
        return contaRepository.save(toUpdate.get());

    }

}
