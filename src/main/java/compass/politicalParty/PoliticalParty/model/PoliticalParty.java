package compass.politicalParty.PoliticalParty.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	private LocalDate date;

	public PoliticalParty(String name, String acronym, TypeIdeology ideology, LocalDate date) {
		this.name = name;
		this.acronym = acronym;
		this.ideology = ideology;
		this.date = date;
	}
}
