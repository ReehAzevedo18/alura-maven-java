package br.com.alura.forum.controller.dto;

import br.com.alura.forum.modelo.Topico;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TopicoDTO {
    private long id;
    private String tittle;
    private String message;
    private LocalDateTime creationDate;

    public TopicoDTO(Topico topico){
        this.id = topico.getId();
        this.tittle = topico.getTitulo();
        this.message = topico.getMensagem();
        this.creationDate = topico.getDataCriacao();
    }

    public static List<TopicoDTO> converter(List<Topico> topicos) {
//        Criei uma lista, dentro dessa lista vou buscar por MAP cada parametro da classe Topico
        return topicos.stream().map(TopicoDTO::new).collect(Collectors.toList());
    }

    public long getId() {
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


}
