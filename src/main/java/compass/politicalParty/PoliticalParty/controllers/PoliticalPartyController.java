package compass.politicalParty.PoliticalParty.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

import compass.politicalParty.PoliticalParty.dto.AssociateDTO;
import compass.politicalParty.PoliticalParty.dto.PoliticalPartyDTO;
import compass.politicalParty.PoliticalParty.dto.PoliticalPartyFormDTO;
import compass.politicalParty.PoliticalParty.model.Associate;
import compass.politicalParty.PoliticalParty.model.PoliticalParty;
import compass.politicalParty.PoliticalParty.model.TypeIdeology;
import compass.politicalParty.PoliticalParty.repository.AssociateRepository;
import compass.politicalParty.PoliticalParty.repository.PoliticalPartyRepository;

@RestController
@RequestMapping("/api/politicalParty")
public class PoliticalPartyController {
	
	@Autowired 
	PoliticalPartyRepository partyRepository;
	
	@Autowired 
	AssociateRepository associateRepository;
	
	@Autowired
	ModelMapper mapper;

	@GetMapping
	public Page<PoliticalPartyDTO> list(@RequestParam(required = false) TypeIdeology ideology,
			@PageableDefault(sort = "id", direction = Direction.ASC, size = 100) Pageable pagination){
		Page<PoliticalParty> party; 
		
		if(ideology == null) {
			party = partyRepository.findAll(pagination);
		} else {
			party = partyRepository.findByIdeology(ideology, pagination);
		}	
		Page<PoliticalPartyDTO> partyDTO = new PageImpl<>(party.stream()
				.map(e -> mapper.map(e, PoliticalPartyDTO.class)).collect(Collectors.toList()));
		
		return partyDTO;
	}	
	
	@GetMapping("/{id}")
	public ResponseEntity<PoliticalPartyDTO> check(@PathVariable Integer id) {
		Optional<PoliticalParty> party = partyRepository.findById(id);
		if(party.isPresent()) {
			return ResponseEntity.ok(new PoliticalPartyDTO(party.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}/associates")
	public List<AssociateDTO> checkAssociatesOfParty(@PathVariable Integer id, PoliticalParty party) {
		List<Associate> associate = associateRepository.findByPoliticalParty(party);
		List<AssociateDTO> associateDTO = associate.stream()
				.map(e -> mapper.map(e, AssociateDTO.class)).collect(Collectors.toList());
		return associateDTO;
	}	
	
	@Transactional
	@PostMapping
	public ResponseEntity<PoliticalPartyDTO> add(@RequestBody @Valid PoliticalPartyFormDTO partyFormDTO, UriComponentsBuilder uriBuilder) {
		PoliticalParty party = mapper.map(partyFormDTO, PoliticalParty.class);
		partyRepository.save(party);
		URI uri = uriBuilder.path("/api/politicalParty/{id}").buildAndExpand(party.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new PoliticalPartyDTO(party));
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<PoliticalPartyDTO> update(@PathVariable Integer id, @RequestBody @Valid PoliticalPartyFormDTO partyFormDTO){
		Optional<PoliticalParty> partyOptional = partyRepository.findById(id);
		if(partyOptional.isPresent()) {
			PoliticalParty party = partyFormDTO.update(id, partyRepository);
			return ResponseEntity.ok(new PoliticalPartyDTO(party));
		}
		return ResponseEntity.notFound().build();
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		Optional<PoliticalParty> party = partyRepository.findById(id);
		if(party.isPresent()) {
			partyRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
