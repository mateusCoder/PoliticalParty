package compass.politicalParty.PoliticalParty.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import compass.politicalParty.PoliticalParty.model.Associate;
import compass.politicalParty.PoliticalParty.model.PoliticalParty;
import compass.politicalParty.PoliticalParty.model.TypeOffice;

public interface AssociateRepository extends JpaRepository<Associate, Integer> {

	Page<Associate> findByPoliticalOffice(TypeOffice politicalOffice, Pageable pagination);

	List<Associate> findByPoliticalParty(PoliticalParty party);

}
