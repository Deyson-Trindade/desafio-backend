package br.com.deyson.vuttr.controllers;

import br.com.deyson.vuttr.dto.ToolRequest;
import br.com.deyson.vuttr.dto.ToolResponse;
import br.com.deyson.vuttr.models.TagModel;
import br.com.deyson.vuttr.models.ToolModel;
import br.com.deyson.vuttr.services.ToolService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("tools")
@AllArgsConstructor
public class ToolController {

    private final ToolService toolsService;

    private final ModelMapper modelMapper;

    @GetMapping
    public List<ToolResponse> getAllTools() {
         List<ToolModel> toolModels = toolsService.listAll();
         return toolModels.stream().map(toolModel -> {
             final ToolResponse toolResponse = modelMapper.map(toolModel, ToolResponse.class);
             List<String> tags = toolModel.getTags().stream().map(TagModel::getName).collect(Collectors.toList());
             toolResponse.setTags(tags);
             return toolResponse;
         }).collect(Collectors.toList());
    }

    @GetMapping("/{tag}")
    public ResponseEntity<?> getByTag() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ToolResponse> create(@RequestBody @Valid final ToolRequest toolRequest) {

        ToolModel convertedToolModel = modelMapper.map(toolRequest, ToolModel.class);

        ToolModel persistedToolModel = toolsService.create(convertedToolModel);

        return new ResponseEntity<>(modelMapper.map(persistedToolModel, ToolResponse.class), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final UUID id) {
        toolsService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
