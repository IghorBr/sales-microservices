package com.microservices.bank.domain;

import com.microservices.bank.exception.ObjectNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public abstract class BaseController<T extends BaseDomain, K extends BaseDTO> {

    private BaseService<T> baseService;
    protected ModelMapper mapper = new ModelMapper();
    private Class<T> entityTarget;
    private Class<K> dtoTarget;

    private final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    public BaseController(Class<T> entity, Class<K> dto, BaseService<T> baseService) {
        this.entityTarget = entity;
        this.dtoTarget = dto;
        this.baseService = baseService;
    }

    @GetMapping
    @Operation(description = "Lista todos os objetos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Object not found")
    })
    public ResponseEntity<List<K>> findAll() {
        LOGGER.info("Buscando todos os {}", entityTarget.getName());

        List<T> entities = baseService.findAll();
        List<K> dtos = this.mapList(entities);

        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping(value = "/{id}")
    @Operation(description = "Lista um objeto por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Object not found")
    })
    public ResponseEntity<K> findById(@PathVariable Long id) {
        LOGGER.info("Buscando um {} pelo id", entityTarget.getName());
        T entity = baseService.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
        K dto = mapper.map(entity, dtoTarget);
        return ResponseEntity.ok().body(dto);
    }

    protected List<K> mapList(List<T> source) {
        return source
                .stream()
                .map(element -> mapper.map(element, dtoTarget))
                .collect(Collectors.toList());
    }
}
