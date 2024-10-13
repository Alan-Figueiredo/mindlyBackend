package com.project.mindly.model.paciente;


import com.project.mindly.model.agendamento.Agendamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_paciente")
public class Paciente {

    @Id
    @Column(name = "cpf_paciente",length = 20, nullable = false, unique = true)
    @NotNull
    @Size(min = 11, max = 20)
    private String cpfPaciente;

    @Column(name = "nome_paciente", length = 155, nullable = false)
    @NotNull
    @Email
    private String nomePaciente;

    @Column(name = "email_paciente", length = 155, nullable = false)
    @NotNull
    @Size(min = 11, max = 155)
    private String emailPaciente;

    @Column(name = "senha",nullable = false)
    @NotNull
    @Size(min = 6, max = 255)
    private String senha;

    @Column(name = "nascimento",nullable = false)
    @NotNull
    private LocalDate nascimento;

    @Column(name = "medicacao")
    private String medicacao;

    @Column(name = "endereco_paciente")
    private String endPaciente;

    @Column(name = "telefone_paciente")
    private String telPaciente;

    @OneToMany(mappedBy = "cpfPacienteAgendamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Agendamento> agendamentoPaciente = new HashSet<>();

    public @NotNull @Size(min = 11, max = 20) String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(@NotNull @Size(min = 11, max = 20) String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }

    public @NotNull @Email String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(@NotNull @Email String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public @NotNull @Size(min = 11, max = 155) String getEmailPaciente() {
        return emailPaciente;
    }

    public void setEmailPaciente(@NotNull @Size(min = 11, max = 155) String emailPaciente) {
        this.emailPaciente = emailPaciente;
    }

    public @NotNull @Size(min = 6, max = 255) String getSenha() {
        return senha;
    }

    public void setSenha(@NotNull @Size(min = 6, max = 255) String senha) {
        this.senha = senha;
    }

    public @NotNull LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(@NotNull LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getMedicacao() {
        return medicacao;
    }

    public void setMedicacao(String medicacao) {
        this.medicacao = medicacao;
    }

    public String getEndPaciente() {
        return endPaciente;
    }

    public void setEndPaciente(String endPaciente) {
        this.endPaciente = endPaciente;
    }

    public String getTelPaciente() {
        return telPaciente;
    }

    public void setTelPaciente(String telPaciente) {
        this.telPaciente = telPaciente;
    }

    public Set<Agendamento> getAgendamentoPaciente() {
        return agendamentoPaciente;
    }

    public void setAgendamentoPaciente(Set<Agendamento> agendamentoPaciente) {
        this.agendamentoPaciente = agendamentoPaciente;
    }
}
