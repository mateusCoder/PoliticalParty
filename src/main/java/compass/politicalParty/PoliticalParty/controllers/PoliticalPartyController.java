package compass.politicalParty.PoliticalParty.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import compass.politicalParty.PoliticalParty.dto.PoliticalPartyDTO;
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
	
			
}
