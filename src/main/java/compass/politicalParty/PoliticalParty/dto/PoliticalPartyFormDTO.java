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

	@NotEmpty @NotEmpty
	private String name;
	
	@NotNull @NotEmpty
	private String acronym;
	
	@NotNull
	private TypeIdeology ideology;
	
	@NotNull
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
