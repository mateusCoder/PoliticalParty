package compass.politicalParty.PoliticalParty.dto;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import compass.politicalParty.PoliticalParty.model.Associate;
import compass.politicalParty.PoliticalParty.model.PoliticalParty;
import compass.politicalParty.PoliticalParty.model.TypeGender;
import compass.politicalParty.PoliticalParty.model.TypeOffice;
import compass.politicalParty.PoliticalParty.serializer.DateSerializer;
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
	
	@JsonSerialize(using = DateSerializer.class)
	private LocalDate date;
	
	@Enumerated(EnumType.STRING)
	private TypeGender gender;
	
	@ManyToOne
	@JoinColumn(name="political_party")
	private PoliticalParty politicalParty;
	
	public AssociateDTO(Associate associate) {
		this.id = associate.getId();
		this.name = associate.getName();
		this.politicalOffice = associate.getPoliticalOffice();
		this.date = associate.getDate();
		this.gender = associate.getGender();
	}
}
