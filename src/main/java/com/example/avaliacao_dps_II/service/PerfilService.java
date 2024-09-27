package com.example.avaliacao_dps_II.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.avaliacao_dps_II.Model.Perfil;

@Service
public class PerfilService {
    private final List<Perfil> perfis = new ArrayList<>();
    private long nextId = 1;

    public Perfil criarPerfil(Perfil perfil) {
        perfil.setId(nextId++);
        perfil.setDataCriacao(LocalDate.now());
        perfis.add(perfil);
        return perfil;
    }

    public List<Perfil> listarPerfis() {
        return new ArrayList<>(perfis);
    }

    public Optional<Perfil> obterPerfilPorId(Long id) {
        return perfis.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public List<Perfil> obterPerfisPorData(LocalDate data) {
        return perfis.stream()
                .filter(p -> p.getDataCriacao().equals(data))
                .toList();
    }

    public Perfil atualizarPerfil(Long id, Perfil perfilAtualizado) {
        perfilAtualizado.setId(id);
        perfis.removeIf(p -> p.getId().equals(id));
        perfis.add(perfilAtualizado);
        return perfilAtualizado;
    }

    public void excluirPerfil(Long id) {
        perfis.removeIf(p -> p.getId().equals(id));
    }
}
