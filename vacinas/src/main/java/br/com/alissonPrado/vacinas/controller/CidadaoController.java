package br.com.alissonPrado.vacinas.controller;

import java.net.URI;
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

import br.com.alissonPrado.vacinas.controller.dto.CidadaoDto;
import br.com.alissonPrado.vacinas.controller.form.CidadaoForm;
import br.com.alissonPrado.vacinas.modelo.Cidadao;
import br.com.alissonPrado.vacinas.repository.CidadaoRepository;

@RestController
@RequestMapping("/cidadao")
public class CidadaoController {

	@Autowired
	private CidadaoRepository cidadaoRepository;

	@GetMapping("/{id}")
	public ResponseEntity<CidadaoDto> buscaById(@PathVariable("id") Long id) {

		Optional<Cidadao> cidadao = cidadaoRepository.findById(id);

		if (cidadao.isPresent()) {
			return ResponseEntity.ok(new CidadaoDto(cidadao.get()));
		}

		return ResponseEntity.notFound().build();

	}

//	@PostMapping
//	@Transactional
//	public ResponseEntity<CidadaoDto> cadastro(@RequestBody @Valid CidadaoForm form, UriComponentsBuilder uriBuilder) {
//
//		Cidadao cidadao = new Cidadao(form.getNome(), form.getEmail(), form.getCpf());
//		cidadaoRepository.save(cidadao);
//
//		URI uri = uriBuilder.path("/cidadao/{id}").buildAndExpand(cidadao.getId()).toUri();
//
//		return ResponseEntity.created(uri).body(new CidadaoDto(cidadao));
//
//	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastro(@RequestBody @Valid CidadaoForm form, UriComponentsBuilder uriBuilder) {

		try {

			Optional<Cidadao> optionalCpf = cidadaoRepository.findByCpf(form.getCpf());

			if (!optionalCpf.isPresent()) {
				Optional<Cidadao> optionalEmail = cidadaoRepository.findByEmail(form.getEmail());

				if (!optionalEmail.isPresent()) {
					Cidadao cidadao = new Cidadao(form.getNome(), form.getEmail(), form.getCpf(), form.getDataNascimento());

					cidadaoRepository.save(cidadao);

					URI uri = uriBuilder.path("/cidadao/{id}").buildAndExpand(cidadao.getId()).toUri();

					return ResponseEntity.created(uri).body(new CidadaoDto(cidadao));
				} else {
					// throw new Exception("Cidadão já cadastrado no sistema com o e-mail
					// informado.");
					return ResponseEntity.badRequest().body("Cidadão já cadastrado no sistema com o e-mail informado.");
				}
			} else
				// throw new Exception("Cidadão já cadastrado no sistema com o CPF informado.");
				return ResponseEntity.badRequest().body("Cidadão já cadastrado no sistema com o CPF informado.");

//			return new ResponseEntity<>(HttpStatus.CREATED);

		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			// return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

}
