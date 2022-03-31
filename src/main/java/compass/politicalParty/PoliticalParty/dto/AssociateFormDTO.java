package compass.politicalParty.PoliticalParty.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import compass.politicalParty.PoliticalParty.model.Associate;
import compass.politicalParty.PoliticalParty.model.TypeGender;
import compass.politicalParty.PoliticalParty.model.TypeOffice;
import compass.politicalParty.PoliticalParty.repository.AssociateRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociateFormDTO {
	
	@NotEmpty
	private String name;
	
	@NotNull
	private TypeOffice politicalOffice;
	
	@NotNull
	private Date date;
	
	@NotEmpty
	private TypeGender gender;

	public Associate convertToAssociate(AssociateRepository associateRepository) {
		return new Associate(name, politicalOffice, date, gender);
	}
}
