package com.contatos.fuctura.services;

import com.contatos.fuctura.dtos.ContatoDto;
import com.contatos.fuctura.exceptions.DataIntegrityViolationException;
import com.contatos.fuctura.exceptions.IllegalArgumentException;
import com.contatos.fuctura.exceptions.ObjectNotFoundException;
import com.contatos.fuctura.repositories.ContatoRepository;
import com.contatos.fuctura.models.Contato;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Contato findById(Long id) {
        Optional<Contato> contato = contatoRepository.findById(id);

        if (contato.isPresent()) {
            return contato.get();
        }
        throw new ObjectNotFoundException("contato não encontrado!");
    }

    public List<Contato> findAll() {
        return contatoRepository.findAll();
    }

    public Contato save(ContatoDto contatoDto) {
        findByName(contatoDto);
        contatoDto.setId(null);
        return contatoRepository.save(modelMapper.map(contatoDto, Contato.class));
    }

    public Contato update(ContatoDto contatoDto) {
        findById(contatoDto.getId());
        findByName(contatoDto);
        return contatoRepository.save(modelMapper.map(contatoDto, Contato.class));
    }

    public void delete(Long id) {
        findById(id);
        contatoRepository.deleteById(id);
    }

    private void findByName(ContatoDto contatoDto) {
        Optional<Contato> contato = contatoRepository.findByNome(contatoDto.getNome());

        if (contato.isPresent() && contato.get().getNome().equalsIgnoreCase(contatoDto.getNome())) {
            throw new IllegalArgumentException("Já existe uma contatoegoria com esse nome!" + contatoDto.getNome());

        }

    }
}