package compass.politicalParty.PoliticalParty.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import compass.politicalParty.PoliticalParty.dto.PoliticalPartyDTO;
import compass.politicalParty.PoliticalParty.dto.PoliticalPartyFormDTO;
import compass.politicalParty.PoliticalParty.model.PoliticalParty;
import compass.politicalParty.PoliticalParty.model.TypeIdeology;
import compass.politicalParty.PoliticalParty.repository.PoliticalPartyRepository;

@RestController
@RequestMapping("/api/politicalParty")
public class PoliticalPartyController {
	
	@Autowired 
	PoliticalPartyRepository partyRepository;
	
	@Autowired
	ModelMapper mapper;

	@GetMapping
	public List<PoliticalPartyDTO> list(@RequestParam(required = false) TypeIdeology ideology,
			@PageableDefault(sort = "id", direction = Direction.ASC, size = 100) Pageable pagination){
		Page<PoliticalParty> party; 
		
		if(ideology == null) {
			party = partyRepository.findAll(pagination);
		} else {
			party = partyRepository.findByIdeology(ideology, pagination);
		}	
		
		List<PoliticalPartyDTO> partyDTO = party.stream().map(e -> mapper.map(e, PoliticalPartyDTO.class)).collect(Collectors.toList());
		return partyDTO;
	}	
	
	@Transactional
	@PostMapping
	public ResponseEntity<PoliticalPartyDTO> add(@RequestBody PoliticalPartyFormDTO partyFormDTO, UriComponentsBuilder uriBuilder) {
		PoliticalParty party = partyFormDTO.convertToParty(partyRepository);
		partyRepository.save(party);

		URI uri = uriBuilder.path("/api/politicalParty/{id}").buildAndExpand(party.getId()).toUri();
		return ResponseEntity.created(uri).body(new PoliticalPartyDTO(party));
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<PoliticalPartyDTO> update(@PathVariable Integer id, @RequestBody @Valid PoliticalPartyFormDTO partyFormDTO){
		PoliticalParty party = partyFormDTO.update(id, partyRepository);

		return ResponseEntity.ok(new PoliticalPartyDTO(party));
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		partyRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
