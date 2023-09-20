package br.com.clicanicaodontologica.clinica.domain.service.impl;

import br.com.clicanicaodontologica.clinica.domain.repository.ContatoRepository;
import org.springframework.stereotype.Service;

public class ContatoServiceImpl {
    @Service
    public class ContatoService implements ContatoService {
        private final ContatoRepository contatoRepository;
        @Autowired
        public ContatoServiceImpl(ContatoRepository contatoRepository) {
            this.contatoRepository = contatoRepository;
        }

        @Override
        public List<Contato> criarContato(List<Contato> contato) {

            return contatoRepository.saveAll(contato);
        }

        @Override
        public List<Contato> buscarContato() {

            return contatoRepository.findAll();
        }

        @Override
        public Contato buscarContatoPorId(UUID id) {

            return contatoRepository.findById(id).orElseThrow();
        }

        @Override
        public Contato atualizarContato(Contato contato) {

            return contatoRepository.save(contato);
        }

        @Override
        public void deleteContato(UUID id) {
            contatoRepository.deleteById(id);

        }
}
