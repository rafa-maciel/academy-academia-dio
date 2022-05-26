package me.dio.academia.digital.controller;

import lombok.AllArgsConstructor;
import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.service.IAlunoService;
import me.dio.academia.digital.service.IAvaliacaoFisicaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/alunos")
@AllArgsConstructor
public class AlunoController {

    // Diferente da aula, estou usando a interface de service
    // e não a implementação desta, o spring trata de fazer
    // a inversão de controle
    private final IAlunoService alunoService;
    private final IAvaliacaoFisicaService avaliacaoFisicaService;

    @GetMapping
    public List<Aluno> getAll(@RequestParam(required = false) String dataDeNascimento) {
        return alunoService.getAll(dataDeNascimento);
    }

    @GetMapping("/{id}")
    public Aluno getById(@PathVariable Long id) {
        return alunoService.get(id);
    }

    @PostMapping
    public Aluno create(@RequestBody @Valid AlunoForm form) {
        return alunoService.create(form);
    }

    // Eu fiz diferente da aula, pois achei melhor
    // centralizar para o service de avaliação esta função
    // Obs. talvez, seja até melhor chamar esse metodo no controler de avaliação
    // mas, para manter os endpoints não vou alterar
    @GetMapping("/avaliacoes/{id}")
    public List<AvaliacaoFisica> getAllAvaliacaoFisicaByAlunoId(@PathVariable Long id) {
        return avaliacaoFisicaService.getAllByAluno(id);
    }

    @PutMapping("/{id}")
    public Aluno update(@PathVariable Long id,@RequestBody AlunoUpdateForm form) {
        return alunoService.update(id, form);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        alunoService.delete(id);
        return ResponseEntity.ok().build();
    }


}
