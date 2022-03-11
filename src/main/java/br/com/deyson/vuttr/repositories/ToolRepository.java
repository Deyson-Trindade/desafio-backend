package br.com.deyson.vuttr.repositories;

import br.com.deyson.vuttr.entities.ToolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ToolRepository extends JpaRepository<ToolEntity, UUID> {

    void deleteById(Integer id);

}
