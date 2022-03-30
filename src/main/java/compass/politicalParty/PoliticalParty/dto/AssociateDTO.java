package compass.politicalParty.PoliticalParty.dto;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import compass.politicalParty.PoliticalParty.model.TypeGender;
import compass.politicalParty.PoliticalParty.model.TypeOffice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociateDTO {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	@Enumerated(EnumType.STRING)
	private TypeOffice politicalOffice;
	
	private Date date;
	
	@Enumerated(EnumType.STRING)
	private TypeGender gender;
}
