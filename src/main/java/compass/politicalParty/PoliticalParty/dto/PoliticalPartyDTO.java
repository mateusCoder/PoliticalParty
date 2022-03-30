package compass.politicalParty.PoliticalParty.dto;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import compass.politicalParty.PoliticalParty.model.TypeIdeology;
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
	
	private Date date;
}
