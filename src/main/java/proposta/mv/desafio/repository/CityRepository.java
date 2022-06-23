package proposta.mv.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proposta.mv.desafio.models.CityModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<CityModel, Long>, JpaSpecificationExecutor<CityModel> {
    
    @Query(nativeQuery = true, value = "select * from tb_cidade where nm_cidade like %:nameCity%")
    Optional<CityModel> findByNameCity(@Param("nameCity") String nameCity);
    
    boolean existsByNameCity(String nameCity);

    @Query(nativeQuery = true, value = "select tc.* from tb_cidade tc " +
            "left join tb_estado te on te.id_estado = tc.id_estado " +
            "where te.nm_estado like %:stateName%")
    List<CityModel> findByNameState(@Param("stateName") String stateName);
}
