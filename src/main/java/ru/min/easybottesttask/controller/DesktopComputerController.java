package ru.min.easybottesttask.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.min.easybottesttask.model.DesktopComputer;
import ru.min.easybottesttask.model.enums.Form;
import ru.min.easybottesttask.service.DesktopComputerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/desktop")
@Tag(name = "API для работы с настольными компьютерами",
        description = "Сохранение, изменение, получение")
public class DesktopComputerController {

    private final DesktopComputerService service;

    @GetMapping()
    @Operation(summary = "Получение всех компьютеров")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<List<DesktopComputer>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    @Operation(summary = "Добавление компьютера")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен")
    @ApiResponse(responseCode = "422", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<DesktopComputer> addComputer(@RequestBody DesktopComputer computer){
        return ResponseEntity.ok(service.addComputer(computer));
    }

    @PatchMapping
    @Operation(summary = "Обновление компьютера")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен")
    @ApiResponse(responseCode = "422", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<DesktopComputer> updateComputer(@RequestParam(required = true) Long id, @RequestBody DesktopComputer computer){
        return ResponseEntity.ok(service.updateComputer(id, computer));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение компьютера по идентификатору")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен")
    @ApiResponse(responseCode = "404", description = "Компьютер не найден")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<DesktopComputer> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/form")
    @Operation(summary = "Получение всех компьютеров по форме")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<List<DesktopComputer>> findAllByForm(@RequestParam(required = true)Form form){
        return ResponseEntity.ok(service.findAllByForm(form));
    }
}
