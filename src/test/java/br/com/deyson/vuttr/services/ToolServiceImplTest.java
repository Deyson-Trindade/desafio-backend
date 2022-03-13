package br.com.deyson.vuttr.services;

import br.com.deyson.vuttr.entities.TagEntity;
import br.com.deyson.vuttr.entities.ToolEntity;
import br.com.deyson.vuttr.exceptions.ToolNotFoundException;
import br.com.deyson.vuttr.models.TagModel;
import br.com.deyson.vuttr.models.ToolModel;
import br.com.deyson.vuttr.repositories.ToolRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ToolServiceImplTest {


    private ToolService toolService;

    private ToolRepository toolRepository;

    private ModelMapper modelMapper;

    public ToolServiceImplTest() {
        toolRepository = Mockito.mock(ToolRepository.class);
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        toolService = new ToolServiceImpl(toolRepository, modelMapper);
    }

    @Test
    public void whenCreateTool_thenAllDataMatches() {

        List<TagModel> tags = new ArrayList<>();
        tags.add(this.createTagModel("tag1"));
        tags.add(this.createTagModel("tag2"));
        tags.add(this.createTagModel("tag3"));
        tags.add(this.createTagModel("tag4"));
        tags.add(this.createTagModel("tag5"));

        ToolModel tagModel = new ToolModel();
        tagModel.setTitle("title here");
        tagModel.setLink("https://www.linhere.com");
        tagModel.setDescription("description here");
        tagModel.setTags(tags);

        ToolEntity returnFromRepository = this.from(tagModel);
        when(toolRepository.save(any(ToolEntity.class))).thenReturn(returnFromRepository);

        ToolModel persistedObject = toolService.create(tagModel);
        Assertions.assertEquals(tags.size(), persistedObject.getTags().size());
        Assertions.assertEquals(tagModel.getTitle(), persistedObject.getTitle());
        Assertions.assertEquals(tagModel.getDescription(), persistedObject.getDescription());
        Assertions.assertEquals(tagModel.getLink(), persistedObject.getLink());

    }

    @Test
    public void whenDeletingTool_thenFluxGoesOn() throws Exception {
        final UUID id = UUID.randomUUID();

        final ToolEntity toolEntity = new ToolEntity();

        List<TagEntity> tags = new ArrayList<>();
        tags.add(this.createTagEntity("tag1", toolEntity));
        tags.add(this.createTagEntity("tag2", toolEntity));
        tags.add(this.createTagEntity("tag3", toolEntity));
        tags.add(this.createTagEntity("tag4", toolEntity));
        tags.add(this.createTagEntity("tag5", toolEntity));

        toolEntity.setId(id);
        toolEntity.setTitle("title");
        toolEntity.setDescription("description");
        toolEntity.setLink("link");
        toolEntity.setTags(tags);

        when(toolRepository.findById(any(UUID.class))).thenReturn(Optional.of(toolEntity));
        toolService.delete(id);
    }

    @Test
    public void whenDeletingToolAndIdNotExists_thenThrowNotFoundException() throws Exception {
        final UUID id = UUID.randomUUID();

        when(toolRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
        Assertions.assertThrows(ToolNotFoundException.class, () -> toolService.delete(id));
    }


    private TagModel createTagModel(String name) {
        final TagModel tag = new TagModel();
        tag.setName(name);
        return tag;
    }


    private TagEntity createTagEntity(String name, ToolEntity toolEntity) {
        final TagEntity tag = new TagEntity();
        tag.setId(UUID.randomUUID());
        tag.setName(name);
        tag.setTool(toolEntity);
        return tag;
    }

    private ToolEntity from(ToolModel model) {
        ToolEntity entity =  modelMapper.map(model, ToolEntity.class);
        entity.getTags().forEach(tagEntity -> tagEntity.setTool(entity));
        return entity;
    }

}
