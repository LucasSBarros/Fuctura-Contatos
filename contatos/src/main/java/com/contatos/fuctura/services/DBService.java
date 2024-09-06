package com.contatos.fuctura.services;

import com.contatos.fuctura.models.Contato;
import com.contatos.fuctura.repositories.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {
    

    @Autowired
    private ContatoRepository contatoRepository;

    public void instanciaDB() {

        Contato contato1 = new Contato(null, "Lucas", "lucas@email,com", "8199991111");
        Contato contato2 = new Contato(null, "Maria", "maria@email,com", "8199991112");
        Contato contato3 = new Contato(null, "João", "joao@email,com", "8199991113");

        //cat1.getLivros().addAll(Arrays.asList(l1,l2));
        //cat2.getLivros().addAll(Arrays.asList(l3,l4,l5));

        this.contatoRepository.saveAll(Arrays.asList(contato1,contato2,contato3));
    }
}
