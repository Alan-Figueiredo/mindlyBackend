package com.project.mindly.model.agendamento;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.mindly.enums.AgendamentoStatus;
import com.project.mindly.model.paciente.Paciente;
import com.project.mindly.model.profissional.Profissional;
import com.project.mindly.model.sessao.Sessao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_agendamento")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @NotNull
    private int idAgendamento;

    @Column(name = "data_agendamento", nullable = false)
    @NotNull
    private LocalDate dataAgendamento;

    @Column(name = "hora_inicio", nullable = false)
    @NotNull
    private LocalTime horaInicio;

    @Column(name = "duracao", nullable = false)
    @NotNull
    private int duracao;

    @Column(name = "link_video")
    private String linkVideo ;

    @Column(name = "lembrete_enviado")
    private int lembreteEnviado = 0;

    @Column(name = "observacoes", length = 500)
    private String observacoes;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    @NotNull
    private AgendamentoStatus status = AgendamentoStatus.PENDENTE;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cpf_prof", nullable = false)
    @NotNull
    @JsonManagedReference
    private Profissional cpfProfAgendamento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cpf_paciente", nullable = false)
    @NotNull
    @JsonManagedReference
    private Paciente cpfPacienteAgendamento;

    @JsonBackReference
    @OneToMany(mappedBy = "agendamentoSessao", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Sessao> sessoes = new HashSet<>();

    @NotNull
    public int getIdAgendamento() {
        return idAgendamento;
    }

    public @NotNull LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(@NotNull LocalDate dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public @NotNull LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(@NotNull LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    @NotNull
    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(@NotNull int duracao) {
        this.duracao = duracao;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }

    public int getLembreteEnviado() {
        return lembreteEnviado;
    }

    public void setLembreteEnviado(int lembreteEnviado) {
        this.lembreteEnviado = lembreteEnviado;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public @NotNull AgendamentoStatus getStatus() {
        return status;
    }

    public void setStatus(@NotNull AgendamentoStatus status) {
        this.status = status;
    }

    public @NotNull Profissional getCpfProfAgendamento() {
        return cpfProfAgendamento;
    }

    public void setCpfProfAgendamento(@NotNull Profissional cpfProfAgendamento) {
        this.cpfProfAgendamento = cpfProfAgendamento;
    }

    public @NotNull Paciente getCpfPacienteAgendamento() {
        return cpfPacienteAgendamento;
    }

    public void setCpfPacienteAgendamento(@NotNull Paciente cpfPacienteAgendamento) {
        this.cpfPacienteAgendamento = cpfPacienteAgendamento;
    }

    public Set<Sessao> getSessoes() {
        return sessoes;
    }

    public void setSessoes(Set<Sessao> sessoes) {
        this.sessoes = sessoes;
    }
}
