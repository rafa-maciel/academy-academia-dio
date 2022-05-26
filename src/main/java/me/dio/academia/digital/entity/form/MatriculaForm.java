package me.dio.academia.digital.entity.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaForm {

  @NotNull(message = "Preencha o campo corretamente")
  @Positive(message = "O ID '${validatedValue}' é invalido")
  private Long alunoId;

}
