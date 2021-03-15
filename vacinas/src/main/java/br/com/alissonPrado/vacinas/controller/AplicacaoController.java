package br.com.alissonPrado.vacinas.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alissonPrado.vacinas.controller.dto.AplicacaoDto;
import br.com.alissonPrado.vacinas.controller.form.AplicacaoForm;
import br.com.alissonPrado.vacinas.modelo.Aplicacao;
import br.com.alissonPrado.vacinas.modelo.Cidadao;
import br.com.alissonPrado.vacinas.modelo.Vacina;
import br.com.alissonPrado.vacinas.repository.AplicacaoRepository;
import br.com.alissonPrado.vacinas.repository.CidadaoRepository;
import br.com.alissonPrado.vacinas.repository.VacinaRepository;

@RestController
@RequestMapping("/aplicacao")
public class AplicacaoController {

	@Autowired
	private AplicacaoRepository aplicacaoRepository;

	@Autowired
	private CidadaoRepository cidadaoRepository;

	@Autowired
	private VacinaRepository vacinaRepository;

	// @RequestMapping(value = "/topicos", method = RequestMethod.GET)
	@GetMapping
	public List<AplicacaoDto> listaAplicacaoVacina() {

		List<Aplicacao> listAplicacaoVacina = aplicacaoRepository.findAll();

		return AplicacaoDto.converteListaAplicacaoVacinaDto(listAplicacaoVacina);

	}

	@GetMapping("/{cpf}")
	public List<AplicacaoDto> listaAplicacaoVacinaByCpf(@PathVariable("cpf") String cpf) {

		List<Aplicacao> listAplicacaoVacina = aplicacaoRepository.findByCidadao_Cpf(cpf);

		return AplicacaoDto.converteListaAplicacaoVacinaDto(listAplicacaoVacina);

	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastro(@RequestBody @Valid AplicacaoForm form, UriComponentsBuilder uriBuilder) {
		try {
			Optional<Cidadao> optionalCpf = cidadaoRepository.findByCpf(form.getCpf());

			if (optionalCpf.isPresent()) {
				Optional<Vacina> vacinaOptional = vacinaRepository.findById(form.getIdVacina());

				if (vacinaOptional.isPresent()) {
					// Verifica se já existe um cadastro com os dados informados
					int estaCadastrado = aplicacaoRepository.findOcorrencia(Long.valueOf(form.getIdVacina()),
							form.getCpf(), form.getDose());

					if (estaCadastrado > 0) {
						return ResponseEntity.badRequest().body("Cidadao já cadastrado com vacina e dose informados.");
					} else {
						Cidadao cidadao = optionalCpf.get();
						Vacina vacina = vacinaOptional.get();

						Aplicacao aplicacaoVacina = new Aplicacao(vacina, cidadao, form.getDose(),
								form.getDataAplicacao());

						aplicacaoRepository.save(aplicacaoVacina);

						URI uri = uriBuilder.path("/aplicacaoVacina/{cpf}").buildAndExpand(cidadao.getCpf()).toUri();

						return ResponseEntity.created(uri).body(new AplicacaoDto(aplicacaoVacina));
					}
				} else {
					return ResponseEntity.badRequest().body("O id da vacina está incorreto.");
				}
			} else
				return ResponseEntity.badRequest()
						.body("O cidadão não está cadastrado no sistema com o CPF informado.");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
