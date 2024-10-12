package com.project.mindly.model.profissional;


import com.project.mindly.model.agenda.Agenda;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "tb_profissional",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "crp"),
                @UniqueConstraint(columnNames = "email_prof")
        }
)
public class Profissional {

    @Id
    @Column(name = "cpf_prof", length = 20, nullable = false, unique = true)
    @NotNull
    @Size(min = 11, max = 20)
    private String cpfProf;

    @Column(name = "nome_prof", length = 155, nullable = false)
    @NotNull
    @Size(min = 1, max = 155)
    private String nomeProf;

    @Column(name = "crp", length = 10, nullable = false, unique = true)
    @NotNull
    @Size(min = 1, max = 10)
    private String crp;

    @Column(name = "email_prof", length = 155, nullable = false, unique = true)
    @NotNull
    @Email
    @Size(max = 155)
    private String emailProf;

    @Column(name = "senha", length = 255, nullable = false)
    @NotNull
    @Size(min = 6, max = 255)
    private String senha;

    @Column(name = "descricao_prof", length = 500)
    @Size(max = 500)
    private String descricaoProf;

    @Column(name = "especialidade", length = 255)
    @Size(max = 255)
    private String especialidade;

    @Column(name = "endereco_prof", length = 255)
    @Size(max = 255)
    private String enderecoProf;

    @Column(name = "telefone_prof", length = 20)
    @Size(max = 20)
    private String telefoneProf;

    @OneToMany(mappedBy = "profissional", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Agenda> agendas = new HashSet<>();

    //@OneToMany(mappedBy = "profissional", cascade = CascadeType.ALL, orphanRemoval = true)
    //private Set<Agendamento> agendamentos = new HashSet<>();

    //@OneToMany(mappedBy = "profissional", cascade = CascadeType.ALL, orphanRemoval = true)
    //private Set<Sessao> sessoes = new HashSet<>();


    public @NotNull @Size(min = 11, max = 20) String getCpfProf() {
        return cpfProf;
    }

    public void setCpfProf(@NotNull @Size(min = 11, max = 20) String cpfProf) {
        this.cpfProf = cpfProf;
    }

    public @NotNull @Size(min = 1, max = 155) String getNomeProf() {
        return nomeProf;
    }

    public void setNomeProf(@NotNull @Size(min = 1, max = 155) String nomeProf) {
        this.nomeProf = nomeProf;
    }

    public @NotNull @Size(min = 1, max = 10) String getCrp() {
        return crp;
    }

    public void setCrp(@NotNull @Size(min = 1, max = 10) String crp) {
        this.crp = crp;
    }

    public @NotNull @Email @Size(max = 155) String getEmailProf() {
        return emailProf;
    }

    public void setEmailProf(@NotNull @Email @Size(max = 155) String emailProf) {
        this.emailProf = emailProf;
    }

    public @NotNull @Size(min = 6, max = 255) String getSenha() {
        return senha;
    }

    public void setSenha(@NotNull @Size(min = 6, max = 255) String senha) {
        this.senha = senha;
    }

    public @Size(max = 500) String getDescricaoProf() {
        return descricaoProf;
    }

    public void setDescricaoProf(@Size(max = 500) String descricaoProf) {
        this.descricaoProf = descricaoProf;
    }

    public @Size(max = 255) String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(@Size(max = 255) String especialidade) {
        this.especialidade = especialidade;
    }

    public @Size(max = 255) String getEnderecoProf() {
        return enderecoProf;
    }

    public void setEnderecoProf(@Size(max = 255) String enderecoProf) {
        this.enderecoProf = enderecoProf;
    }

    public @Size(max = 20) String getTelefoneProf() {
        return telefoneProf;
    }

    public void setTelefoneProf(@Size(max = 20) String telefoneProf) {
        this.telefoneProf = telefoneProf;
    }

    public Set<Agenda> getAgendas() {
        return agendas;
    }

    public void setAgendas(Set<Agenda> agendas) {
        this.agendas = agendas;
    }
}
