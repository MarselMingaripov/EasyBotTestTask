package ru.min.easybottesttask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.min.easybottesttask.model.DesktopComputer;
import ru.min.easybottesttask.service.DesktopComputerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/desktop")
public class DesktopComputerController {

    private final DesktopComputerService service;

    @GetMapping()
    public ResponseEntity<List<DesktopComputer>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<DesktopComputer> addComputer(@RequestBody DesktopComputer computer){
        return ResponseEntity.ok(service.addComputer(computer));
    }

    @PatchMapping
    public ResponseEntity<DesktopComputer> updateComputer(@RequestParam(required = true) Long id, @RequestBody DesktopComputer computer){
        return ResponseEntity.ok(service.updateComputer(id, computer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DesktopComputer> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }
}
