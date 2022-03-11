package br.com.deyson.vuttr.services;

import br.com.deyson.vuttr.entities.ToolEntity;
import br.com.deyson.vuttr.models.ToolModel;
import br.com.deyson.vuttr.repositories.ToolRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ToolServiceImpl implements ToolService {

    final ToolRepository toolRepository;

    final ModelMapper modelMapper;


    @Override
    public List<ToolModel> listAll() {
        return toolRepository.findAll()
                .stream()
                .map(toolEntity -> modelMapper.map(toolEntity, ToolModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(final UUID id) {
        toolRepository.deleteById(id);
    }

    @Override
    public ToolModel create(final ToolModel toolModel) {

        ToolEntity toolEntity = modelMapper.map(toolModel, ToolEntity.class);
        toolRepository.save(toolEntity);

        return null;
    }
}
