package br.com.deyson.vuttr.services;

import br.com.deyson.vuttr.models.ToolModel;

import java.util.List;
import java.util.UUID;

public interface ToolService {

    List<ToolModel> listAllByTag(String tag);

    void delete(UUID uuid);

    ToolModel create(ToolModel toolModel);

}
