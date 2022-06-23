package proposta.mv.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proposta.mv.desafio.models.ClientModel;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Long>, JpaSpecificationExecutor<ClientModel> {
    @Query(nativeQuery = true, value = "select * from tb_cliente where nm_cliente like %:clientName%")
    List<ClientModel> findByNameComplete(@Param("clientName") String clientName);
}
