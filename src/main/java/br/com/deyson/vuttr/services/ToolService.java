package br.com.deyson.vuttr.services;

import br.com.deyson.vuttr.models.ToolModel;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ToolService {

    List<ToolModel> listAll();

    void delete(UUID uuid);

    ToolModel create(ToolModel toolModel);

}
