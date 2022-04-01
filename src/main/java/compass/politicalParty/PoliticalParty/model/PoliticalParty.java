package compass.politicalParty.PoliticalParty.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import compass.politicalParty.PoliticalParty.serializer.DateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="political_party")
public class PoliticalParty {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private String acronym;
	
	@Enumerated(EnumType.STRING)
	private TypeIdeology ideology;
	
	private Date date;

	public PoliticalParty(String name, String acronym, TypeIdeology ideology, Date date) {
		this.name = name;
		this.acronym = acronym;
		this.ideology = ideology;
		this.date = date;
	}
}
