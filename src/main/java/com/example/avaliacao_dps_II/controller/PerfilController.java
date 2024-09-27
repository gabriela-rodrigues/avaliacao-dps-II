package com.example.avaliacao_dps_II.controller;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.avaliacao_dps_II.Model.Perfil;
import com.example.avaliacao_dps_II.service.PerfilService;

@RestController
@RequestMapping("/perfis")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @PostMapping
    public ResponseEntity<Perfil> criarPerfil(@RequestBody Perfil perfil) {
        Perfil novoPerfil = perfilService.criarPerfil(perfil);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPerfil);
    }

    @GetMapping
    public ResponseEntity<List<Perfil>> listarPerfis() {
        List<Perfil> perfis = perfilService.listarPerfis();
        return ResponseEntity.ok(perfis);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfil> obterPerfilPorId(@PathVariable Long id) {
        return perfilService.obterPerfilPorId(id)
                .map(perfil -> ResponseEntity.ok(perfil))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/data/{data}")
    public ResponseEntity<List<Perfil>> obterPerfisPorData(@PathVariable String data) {
        LocalDate dataCriacao = LocalDate.parse(data);
        List<Perfil> perfis = perfilService.obterPerfisPorData(dataCriacao);
        return ResponseEntity.ok(perfis);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Perfil> atualizarPerfil(@PathVariable Long id, @RequestBody Perfil perfil) {
        Perfil perfilAtualizado = perfilService.atualizarPerfil(id, perfil);
        return ResponseEntity.ok(perfilAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPerfil(@PathVariable Long id) {
        perfilService.excluirPerfil(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

