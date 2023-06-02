package ru.min.easybottesttask.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.min.easybottesttask.model.Monitor;
import ru.min.easybottesttask.model.enums.MonitorDiagonal;
import ru.min.easybottesttask.service.MonitorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/monitor")
@Tag(name = "API для работы с мониторами",
        description = "Сохранение, изменение, получение")
public class MonitorController {

    private final MonitorService service;

    @GetMapping()
    @Operation(summary = "Получение всех мониторов")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<List<Monitor>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    @Operation(summary = "Добавление монитора")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен")
    @ApiResponse(responseCode = "422", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Monitor> addMonitor(@RequestBody Monitor monitor){
        return ResponseEntity.ok(service.addMonitor(monitor));
    }

    @PatchMapping
    @Operation(summary = "Обновление монитора")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен")
    @ApiResponse(responseCode = "422", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Monitor> updateMonitor(@RequestParam(required = true) Long id, @RequestBody Monitor monitor){
        return ResponseEntity.ok(service.updateMonitor(id, monitor));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение монитора по идентификатору")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен")
    @ApiResponse(responseCode = "404", description = "Компьютер не найден")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Monitor> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/diagonal")
    @Operation(summary = "Получение всех мониторов по диагонали")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<List<Monitor>> findAllByDiagonal(@RequestParam(required = true) MonitorDiagonal diagonal){
        return ResponseEntity.ok(service.findAllByDiagonal(diagonal));
    }
}
