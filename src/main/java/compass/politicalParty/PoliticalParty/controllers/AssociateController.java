package compass.politicalParty.PoliticalParty.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import compass.politicalParty.PoliticalParty.dto.AssociateDTO;
import compass.politicalParty.PoliticalParty.dto.AssociateFormDTO;
import compass.politicalParty.PoliticalParty.model.Associate;
import compass.politicalParty.PoliticalParty.model.TypeOffice;
import compass.politicalParty.PoliticalParty.repository.AssociateRepository;

@RestController
@RequestMapping("/api/associate")
public class AssociateController {

	@Autowired
	AssociateRepository associateRepository;
	
	@Autowired
	ModelMapper mapper;
	
	@GetMapping
	public List<AssociateDTO> list (@RequestParam(required = false) TypeOffice politicalOffice,
			@PageableDefault(sort = "name", direction = Direction.ASC, size = 100) Pageable pagination){
		Page<Associate> associate;
		
		if(politicalOffice == null) {
			associate = associateRepository.findAll(pagination);
		} else {
			associate = associateRepository.findByPoliticalOffice(politicalOffice, pagination);
		}
		List<AssociateDTO> associateDTO = associate.stream().map(e -> mapper.map(e, AssociateDTO.class)).collect(Collectors.toList());
		return associateDTO;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AssociateDTO> check(@PathVariable Integer id) {
		Optional<Associate> associate = associateRepository.findById(id);
		if(associate.isPresent()) {
			return ResponseEntity.ok(new AssociateDTO(associate.get()));
		}
		return ResponseEntity.notFound().build();
	}	
	
	@Transactional
	@PostMapping
	public ResponseEntity<AssociateDTO> add(@RequestBody AssociateFormDTO associateFormDTO, UriComponentsBuilder uriBuilder) {
		Associate associate = associateFormDTO.convertToAssociate(associateRepository);
		associateRepository.save(associate);

		URI uri = uriBuilder.path("/api/associate/{id}").buildAndExpand(associate.getId()).toUri();
		return ResponseEntity.created(uri).body(new AssociateDTO(associate));
	}
	
}