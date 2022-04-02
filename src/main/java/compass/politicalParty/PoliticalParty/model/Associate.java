package compass.politicalParty.PoliticalParty.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="associate")
public class Associate {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	@Enumerated(EnumType.STRING)
	private TypeOffice politicalOffice;
	
	private LocalDate date;
	
	@Enumerated(EnumType.STRING)
	private TypeGender gender;
	
	@ManyToOne
	@Cascade(CascadeType.DELETE)
	private PoliticalParty politicalParty;

	public Associate(String name, TypeOffice politicalOffice, LocalDate date, TypeGender gender) {
		this.name = name;
		this.politicalOffice = politicalOffice;
		this.date = date;
		this.gender = gender;
	}

	public Associate(PoliticalParty politicalParty) {
		this.politicalParty = politicalParty;
	}
}
