package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.DetalhesDoTopicoDTO;
import br.com.alura.forum.controller.dto.TopicoDTO;
import br.com.alura.forum.controller.form.AtualizacaoTopicoForm;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

//    Arquivo com Final DTO chega na API e final Form a API envia

    @PostMapping
    @Transactional
//    A anotação @Valid verifica as anotações que estão na classe onde foram declaradas
    public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm topicoForm, UriComponentsBuilder uriComponentsBuilder){
        //recebendo um objeto topico de acordo com o que quero cadastrar
        Topico topico = topicoForm.converter(cursoRepository);
        topicoRepository.save(topico);
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }

//    Listando topicos
    @GetMapping
    //coloca a resposta em json e mostra no navegador
    @ResponseBody
    public List<TopicoDTO> lista(String nomeCurso){

        if(nomeCurso == null){
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDTO.converter(topicos);
        }else{
//            Criando uma consulta especifica, nesse caso Curso é um relacionamento na classe Topico
//            e dentro dele tem o parametro nome
            List<Topico> topico = topicoRepository.findByCursoNome(nomeCurso);
            return TopicoDTO.converter(topico);
        }
    }

    //    A anotação @PathVariable informa ao spring que esse campo "id" vai vir pela url
    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDoTopicoDTO> detalhar(@PathVariable Long id){
        Optional<Topico> topico = topicoRepository.findById(id);
        if(topico.isPresent()) {
            return ResponseEntity.ok(new DetalhesDoTopicoDTO(topico.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm topicoForm){
        Optional<Topico> optional = topicoRepository.findById(id);
        if(optional.isPresent()) {
            Topico topico = topicoForm.atualizar(id, topicoRepository);
            return ResponseEntity.ok(new TopicoDTO(topico));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id){
        Optional<Topico> optional = topicoRepository.findById(id);
        if(optional.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
