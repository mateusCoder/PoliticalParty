package compass.politicalParty.PoliticalParty.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import compass.politicalParty.PoliticalParty.model.PoliticalParty;
import compass.politicalParty.PoliticalParty.model.TypeIdeology;
import compass.politicalParty.PoliticalParty.repository.PoliticalPartyRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoliticalPartyFormDTO {

	@NotEmpty(message = "Nome é requisitado")
	private String name;
	
	@NotNull(message = "Sigla é requisitado")
	private String acronym;
	
	@NotNull(message = "Ideologia é requisitada")
	private TypeIdeology ideology;
	
	@NotNull(message = "Data é requisitada")
	private LocalDate date;

	public PoliticalParty update(Integer id, PoliticalPartyRepository partyRepository) {
		PoliticalParty party = partyRepository.getById(id);
		party.setName(this.name);
		party.setAcronym(this.acronym);
		party.setIdeology(this.ideology);
		party.setDate(this.date);
		
		return party;
	}
}
