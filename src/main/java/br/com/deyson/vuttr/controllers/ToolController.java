package br.com.deyson.vuttr.controllers;

import br.com.deyson.vuttr.dto.ToolRequest;
import br.com.deyson.vuttr.dto.ToolResponse;
import br.com.deyson.vuttr.models.TagModel;
import br.com.deyson.vuttr.models.ToolModel;
import br.com.deyson.vuttr.services.ToolService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("tools")
@AllArgsConstructor
@Tag(name = "Tool-Operations")
public class ToolController {

    private final ToolService toolsService;

    private final ModelMapper modelMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ToolResponse> getAllTools(@RequestParam(name = "tag", defaultValue = "", required = false) String tag) {
        List<ToolModel> toolModels = toolsService.listAllByTag(tag);
        return createToolResponses(toolModels);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ToolResponse create(@RequestBody @Valid final ToolRequest toolRequest) {
        ToolModel convertedToolModel = modelMapper.map(toolRequest, ToolModel.class);
        return createToolResponse(toolRequest, convertedToolModel);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final UUID id) throws Exception {
        toolsService.delete(id);
    }

    private List<ToolResponse> createToolResponses(List<ToolModel> toolModels) {
        return toolModels.stream().map(toolModel -> {
            final ToolResponse toolResponse = modelMapper.map(toolModel, ToolResponse.class);
            List<String> tags = toolModel.getTags().stream().map(TagModel::getName).collect(Collectors.toList());
            toolResponse.setTags(tags);
            return toolResponse;
        }).collect(Collectors.toList());
    }

    private ToolResponse createToolResponse(ToolRequest toolRequest, ToolModel convertedToolModel) {
        List<TagModel> tagsModel = toolRequest.getTags().stream().map(tag -> {
            final TagModel tagModel = new TagModel();
            tagModel.setName(tag);
            tagModel.setToolId(convertedToolModel.getId());
            return tagModel;
        }).collect(Collectors.toList());
        convertedToolModel.setTags(tagsModel);

        final ToolModel persistedToolModel = toolsService.create(convertedToolModel);
        final ToolResponse toolResponse = modelMapper.map(persistedToolModel, ToolResponse.class);
        toolResponse.setTags(toolRequest.getTags());
        return toolResponse;
    }
}
