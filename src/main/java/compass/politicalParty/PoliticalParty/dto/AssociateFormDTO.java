package compass.politicalParty.PoliticalParty.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import compass.politicalParty.PoliticalParty.model.Associate;
import compass.politicalParty.PoliticalParty.model.PoliticalParty;
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
	
	@NotEmpty(message = "Nome é requisitado")
	private String name;
	
	@NotNull(message = "Cargo é requisitado")
	private TypeOffice politicalOffice;
	
	@NotNull(message = "Data é requisitada")
	private LocalDate date;
	
	@NotNull(message = "Gênero é requisitado")
	private TypeGender gender;
	
	private PoliticalParty politicalParty;

	public Associate updateForm(Integer id, AssociateRepository associateRepository) {
		Associate associate = associateRepository.getById(id);
		associate.setName(this.name);
		associate.setPoliticalOffice(this.politicalOffice);
		associate.setDate(this.date);
		associate.setGender(this.gender);
		
		return associate;
	}
}
