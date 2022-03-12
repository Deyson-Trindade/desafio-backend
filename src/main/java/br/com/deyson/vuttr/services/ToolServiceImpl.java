package br.com.deyson.vuttr.services;

import br.com.deyson.vuttr.entities.ToolEntity;
import br.com.deyson.vuttr.exceptions.ToolNotFoundException;
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
    public List<ToolModel> listAllByTag(String tag) {
        final List<ToolEntity> entities = tag.isBlank() ? toolRepository.findAll() : toolRepository.findAllByTagsIn(tag);
        return entities.stream().map(toolEntity -> modelMapper.map(toolEntity, ToolModel.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(final UUID id) throws ToolNotFoundException {
        ToolEntity entity = toolRepository.findById(id).orElseThrow(() -> new ToolNotFoundException("Tool cannot be found for this id"));
        entity.getTags().clear();
        toolRepository.delete(entity);
    }

    @Override
    public ToolModel create(final ToolModel toolModel) {

        ToolEntity toolEntity = modelMapper.map(toolModel, ToolEntity.class);
        toolEntity.getTags().forEach(tagEntity -> {
            tagEntity.setTool(toolEntity);
        });
        return modelMapper.map(toolRepository.save(toolEntity), ToolModel.class);
    }
}
