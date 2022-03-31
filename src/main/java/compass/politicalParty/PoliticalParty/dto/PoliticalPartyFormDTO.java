package compass.politicalParty.PoliticalParty.dto;

import java.util.Date;

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
	private Date date;

	public PoliticalParty convertToParty(PoliticalPartyRepository partyRepository) {
		return new PoliticalParty(name, acronym, ideology, date);
	}
}
