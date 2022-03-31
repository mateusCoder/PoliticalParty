package compass.politicalParty.PoliticalParty.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import compass.politicalParty.PoliticalParty.model.PoliticalParty;
import compass.politicalParty.PoliticalParty.model.TypeIdeology;

@Repository
public interface PoliticalPartyRepository extends JpaRepository<PoliticalParty, Integer>{

	Page<PoliticalParty> findByIdeology(TypeIdeology ideology, Pageable pagination);

}
