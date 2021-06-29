package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double saldo;

    @ManyToOne
    private Cliente cliente;
}
