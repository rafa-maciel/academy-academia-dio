package me.dio.academia.digital.service.impl;

import lombok.AllArgsConstructor;
import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.MatriculaForm;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.MatriculaRepository;
import me.dio.academia.digital.service.IMatriculaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MatriculaServiceImpl implements IMatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final AlunoRepository alunoRepository;

    @Override
    public Matricula create(MatriculaForm form) {
        Aluno aluno = alunoRepository.getById(form.getAlunoId());
        Matricula matricula = Matricula.builder()
                .aluno(aluno)
                .build();
        return matriculaRepository.save(matricula);
    }

    @Override
    public Matricula get(Long id) {
        return matriculaRepository.getById(id);
    }

    @Override
    public List<Matricula> getAll(String alunoBairro) {
        if (alunoBairro == null)
            return matriculaRepository.findAll();

        return matriculaRepository.findByAlunoBairro(alunoBairro);
    }

    @Override
    public void delete(Long id) {
        Matricula matricula = matriculaRepository.getById(id);
        matriculaRepository.delete(matricula);
    }
}
