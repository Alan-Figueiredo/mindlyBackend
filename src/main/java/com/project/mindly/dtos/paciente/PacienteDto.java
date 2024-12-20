package com.project.mindly.dtos.paciente;
import java.time.LocalDate;

public record PacienteDto(String cpf_paciente, String nome_paciente, String email_paciente , String senha_paciente,
                          LocalDate nascimento_paciente, String endereco_paciente, String telefone_paciente) {
}

