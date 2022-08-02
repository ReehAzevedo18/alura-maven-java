package br.com.alura.forum.controller.dto;

import br.com.alura.forum.modelo.StatusTopico;
import br.com.alura.forum.modelo.Topico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DetalhesDoTopicoDTO {

    private Long id;
    private String tittle;
    private String message;
    private LocalDateTime creationDate;
    private String nomeAutor;
    private StatusTopico status;
    private List<RespostaDTO> respostas;

    public DetalhesDoTopicoDTO(Topico topico) {
        this.id = topico.getId();
        this.tittle = topico.getTitulo();
        this.message = topico.getMensagem();
        this.creationDate = topico.getDataCriacao();
        this.nomeAutor = topico.getAutor().getNome();
        this.status = topico.getStatus();
        this.respostas = new ArrayList<>();
        this.respostas.addAll(topico.getRespostas().stream().map(RespostaDTO::new).collect(Collectors.toList()));
    }

    public Long getId() {
        return id;
    }

    public String getTittle() {
        return tittle;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public StatusTopico getStatus() {
        return status;
    }

    public List<RespostaDTO> getRespostas() {
        return respostas;
    }


}
