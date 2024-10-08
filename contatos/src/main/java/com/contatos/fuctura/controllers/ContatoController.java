package com.contatos.fuctura.controllers;

import com.contatos.fuctura.dtos.ContatoDto;
import com.contatos.fuctura.models.Contato;
import com.contatos.fuctura.services.ContatoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contato")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ContatoDto> findById(@PathVariable Long id) {
        Contato contato = contatoService.findById(id);
        ContatoDto contatoDto = modelMapper.map(contato, ContatoDto.class);
        return ResponseEntity.ok().body(contatoDto);
    }

    @GetMapping
    public ResponseEntity<List<ContatoDto>> findAll() {
        List<Contato> list = contatoService.findAll();
        return ResponseEntity.ok().body(list.stream().map(obj -> modelMapper.map(obj, ContatoDto.class)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<ContatoDto> save(@RequestBody ContatoDto contatoDto) {
        Contato contato = contatoService.save(contatoDto);
        return ResponseEntity.ok().body(modelMapper.map(contato, ContatoDto.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContatoDto> update(@PathVariable Long id, @RequestBody ContatoDto contatoDto) {
        contatoDto.setId(id);
        Contato contato = contatoService.update(contatoDto);
        return ResponseEntity.ok().body(modelMapper.map(contato, ContatoDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contatoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}