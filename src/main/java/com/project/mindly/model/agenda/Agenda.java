package com.project.mindly.model.agenda;

import com.project.mindly.model.profissional.Profissional;
import jakarta.persistence.*;


import java.time.LocalTime;


@Entity
@Table(name = "tb_agenda")
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "duracao", nullable = false)
    private int duracao;

    @Column(name = "dia_da_semana", nullable = false, length = 15)
    private String diaDaSemana;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fim", nullable = false)
    private LocalTime horaFim;

    @Enumerated(EnumType.STRING)
    @Column(name = "ativo", nullable = false)
    private AgendaAtivo ativo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cpf_prof", nullable = false)
    private Profissional profissional;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public AgendaAtivo getAtivo() {
        return ativo;
    }

    public void setAtivo(AgendaAtivo ativo) {
        this.ativo = ativo;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }
}
