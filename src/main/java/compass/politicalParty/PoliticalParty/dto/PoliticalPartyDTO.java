package compass.politicalParty.PoliticalParty.dto;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import compass.politicalParty.PoliticalParty.model.PoliticalParty;
import compass.politicalParty.PoliticalParty.model.TypeIdeology;
import compass.politicalParty.PoliticalParty.serializer.DateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoliticalPartyDTO {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private String acronym;
	
	@Enumerated(EnumType.STRING)
	private TypeIdeology ideology;
	
	@JsonSerialize(using = DateSerializer.class)
	private LocalDate date;	
	
	public PoliticalPartyDTO(PoliticalParty party) {
		this.id = party.getId();
		this.name = party.getName();
		this.acronym = party.getAcronym();
		this.ideology= party.getIdeology();
		this.date = party.getDate();
	}
}
