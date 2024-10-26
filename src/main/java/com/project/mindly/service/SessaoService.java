package com.project.mindly.service;

import com.project.mindly.model.agendamento.Agendamento;
import com.project.mindly.model.paciente.Paciente;
import com.project.mindly.model.profissional.Profissional;
import com.project.mindly.model.sessao.Sessao;
import com.project.mindly.model.sessao.SessaoDto;
import com.project.mindly.repository.AgendamentoRepository;
import com.project.mindly.repository.PacienteRepository;
import com.project.mindly.repository.ProfissionalRepository;
import com.project.mindly.repository.SessaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessaoService {

    private final SessaoRepository sessaoRepository;
    private final PacienteRepository pacienteRepository;
    private final ProfissionalRepository profissionalRepository;
    private final AgendamentoRepository agendamentoRepository;

    @Autowired
    public SessaoService(SessaoRepository sessaoRepository,
                         PacienteRepository pacienteRepository,
                         ProfissionalRepository profissionalRepository, AgendamentoRepository agendamentoRepository) {
        this.sessaoRepository = sessaoRepository;
        this.pacienteRepository = pacienteRepository;
        this.profissionalRepository = profissionalRepository;
        this.agendamentoRepository = agendamentoRepository;
    }

    public List<Sessao> findAllSessao() {
        return sessaoRepository.findAll();
    }

    public Optional<Sessao> findSessaoById(int id) {
        return sessaoRepository.findById(id);
    }

    public Sessao saveSessao(SessaoDto data) {

        Paciente cpfPaci = pacienteRepository.findByCpfPaciente(data.cpf_paciente());
        if (cpfPaci == null) {
            throw new EntityNotFoundException("Paciente não encontrado com CPF: " + data.cpf_paciente());
        }

        Profissional cpfProf = profissionalRepository.findByCpfProf(data.cpf_prof());
        if (cpfProf == null) {
            throw new EntityNotFoundException("Paciente não encontrado com CPF: " + data.cpf_paciente());
        }

        Agendamento idAgenda = agendamentoRepository.findByIdAgendamento(data.id_agendamento());
        if (idAgenda == null) {
            throw new EntityNotFoundException("Paciente não encontrado com CPF: " + data.id_agendamento());
        }

        Sessao sessao = new Sessao();
        sessao.setAgendamentoSessao(idAgenda);
        sessao.setCpfProfSessao(cpfProf);
        sessao.setCpfPacienteSessao(cpfPaci);
        sessao.setDataSessao(data.data_sessao());
        sessao.setQnt_total(data.quantidade_total());
        return sessaoRepository.save(sessao);
    }

    public Sessao updateSessao(String cpfPaciente,String cpfProfissional, SessaoDto data) {

        Paciente cpfPaci = pacienteRepository.findByCpfPaciente(data.cpf_paciente());
        if (cpfPaci == null) {
            throw new EntityNotFoundException("Paciente não encontrado com CPF: " + data.cpf_paciente());
        }

        Profissional cpfProf = profissionalRepository.findByCpfProf(data.cpf_prof());
        if (cpfProf == null) {
            throw new EntityNotFoundException("Paciente não encontrado com CPF: " + data.cpf_paciente());
        }

        Agendamento idAgenda = agendamentoRepository.findByIdAgendamento(data.id_agendamento());
        if (idAgenda == null) {
            throw new EntityNotFoundException("Paciente não encontrado com CPF: " + data.id_agendamento());
        }

        Sessao sessao = new Sessao();
        sessao.setAgendamentoSessao(idAgenda);
        sessao.setCpfProfSessao(cpfProf);
        sessao.setCpfPacienteSessao(cpfPaci);
        sessao.setDataSessao(data.data_sessao());
        sessao.setQnt_total(data.quantidade_total());
        return sessaoRepository.save(sessao);
    }

    public void deleteSessao(int id) {
        Sessao sessao = sessaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado com ID: " + id));
        sessaoRepository.delete(sessao);
    }
}
