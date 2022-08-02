package br.com.alura.forum.repository;

import br.com.alura.forum.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//No primeira parametro passamos a classe, no segunda passamos o tipo do ID
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    //consulta especifica
    List<Topico> findByCursoNome(String cursoNome);

    //caso tenha um relacionamento Curso e eu quero o nome desse curso, porém eu tenho dentro da classe topico
//    um campo chamado cursoNome vai dar ambiguidade, caso eu queira diferenciar para o Spring, eu colocaria
//    dessa forma:
//    List<Topico> findByCurso_Nome(String cursoNome);

    //caso eu não queira seguir a nomenclatura em ingles, posso criar o nome do método de outra forma,
//    nesse caso acima da chamada do método, eu preciso passar a anotação @Query
//    @Query("SELECT * FROM Topico t WHERE t.curso = :nomeCurso")
//    List<Topico> buscandoNomeDoCurso(@Param("nomeCurso") String nomeCurso);
}
