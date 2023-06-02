package ru.min.easybottesttask.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.min.easybottesttask.model.HardDisk;
import ru.min.easybottesttask.model.enums.HardDiskVolume;
import ru.min.easybottesttask.service.HardDiskService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/harddisk")
@Tag(name = "API для работы с жесткими дисками",
        description = "Сохранение, изменение, получение")
public class HardDiskController {

    private final HardDiskService service;

    @GetMapping()
    @Operation(summary = "Получение всех жестких дисков")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<List<HardDisk>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    @Operation(summary = "Добавление жесткого диска")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен")
    @ApiResponse(responseCode = "422", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<HardDisk> addHardDisk(@RequestBody HardDisk hardDisk){
        return ResponseEntity.ok(service.addHardDisk(hardDisk));
    }

    @PatchMapping
    @Operation(summary = "Обновление жесткого диска")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен")
    @ApiResponse(responseCode = "422", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<HardDisk> updateHardDisk(@RequestParam(required = true) Long id, @RequestBody HardDisk hardDisk){
        return ResponseEntity.ok(service.updateHardDisk(id, hardDisk));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение жесткого диска по идентификатору")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен")
    @ApiResponse(responseCode = "404", description = "Компьютер не найден")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<HardDisk> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/volume")
    @Operation(summary = "Получение всех жестких дисков по объему")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<List<HardDisk>> findAllByVolume(@RequestParam(required = true) HardDiskVolume volume){
        return ResponseEntity.ok(service.findAllByVolume(volume));
    }
}
