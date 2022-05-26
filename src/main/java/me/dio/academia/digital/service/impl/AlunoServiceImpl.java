package me.dio.academia.digital.service.impl;

import lombok.AllArgsConstructor;
import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.infra.utils.JavaTimeUtils;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.service.IAlunoService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class AlunoServiceImpl implements IAlunoService {

    private final AlunoRepository repository;

    @Override
    public Aluno create(AlunoForm form) {
        Aluno aluno = Aluno.builder()
                .nome(form.getNome())
                .cpf(form.getCpf())
                .bairro(form.getBairro())
                .dataDeNascimento(form.getDataDeNascimento())
                .build();

        return repository.save(aluno);
    }

    @Override
    public Aluno get(Long id) {
        return repository.getById(id);
    }

    @Override
    public List<Aluno> getAll(String dataNascimento) {
        if (dataNascimento == null)
            return repository.findAll();

        LocalDate date = LocalDate.parse(dataNascimento, JavaTimeUtils.LOCAL_DATE_FORMATTER);
        return repository.findByDataDeNascimento(date);
    }

    @Override
    public Aluno update(Long id, AlunoUpdateForm formUpdate) {
        Aluno aluno = repository.getById(id);
        aluno.setBairro(formUpdate.getBairro());
        aluno.setNome(formUpdate.getNome());
        aluno.setDataDeNascimento(formUpdate.getDataDeNascimento());

        return repository.save(aluno);
    }

    @Override
    public void delete(Long id) {
        Aluno aluno = repository.getById(id);
        repository.delete(aluno);
    }
}
