package proposta.mv.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import proposta.mv.desafio.models.StateModel;

import java.util.UUID;

@Repository
public interface StateRepository extends JpaRepository<StateModel, Long>, JpaSpecificationExecutor<StateModel> {
    boolean existsByNameState(String nameState);

    StateModel findByNameState(String stateName);
}
