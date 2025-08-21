package br.sigabem.controller;

import br.sigabem.dto.FreteRequestDto;
import br.sigabem.dto.FreteResponseDto;
import br.sigabem.service.FreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/frete")
@CrossOrigin(origins = "*")
public class FreteController {

    @Autowired
    private FreteService service;

    @PostMapping
    public FreteResponseDto calcularFrete(@RequestBody FreteRequestDto request) {
        return service.calcularFrete(request);
    }
}