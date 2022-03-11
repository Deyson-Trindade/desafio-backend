package br.com.deyson.vuttr.controllers;

import br.com.deyson.vuttr.dto.ToolRequest;
import br.com.deyson.vuttr.dto.ToolResponse;
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

@RestController
@RequestMapping("tools")
@AllArgsConstructor
public class ToolController {

    private final ToolService toolsService;

    private final ModelMapper modelMapper;

    @GetMapping
    public List<ToolModel> getAllTools() {
        return toolsService.listAll();
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
