package ru.min.easybottesttask.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.min.easybottesttask.model.NoteBook;
import ru.min.easybottesttask.model.enums.ScreenSize;
import ru.min.easybottesttask.service.NoteBookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notebook")
@Tag(name = "API для работы с ноутбуками",
        description = "Сохранение, изменение, получение")
public class NoteBookController {

    private final NoteBookService service;

    @GetMapping()
    @Operation(summary = "Получение всех ноутбуков")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<List<NoteBook>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    @Operation(summary = "Добавление ноутбука")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен")
    @ApiResponse(responseCode = "422", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<NoteBook> addNoteBook(@RequestBody NoteBook noteBook){
        return ResponseEntity.ok(service.addNoteBook(noteBook));
    }

    @PatchMapping
    @Operation(summary = "Обновление ноутбука")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен")
    @ApiResponse(responseCode = "422", description = "Ошибка валидации")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<NoteBook> updateNoteBook(@RequestParam(required = true) Long id, @RequestBody NoteBook noteBook){
        return ResponseEntity.ok(service.updateNoteBook(id, noteBook));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение ноутбука по идентификатору")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен")
    @ApiResponse(responseCode = "404", description = "Компьютер не найден")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<NoteBook> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/size")
    @Operation(summary = "Получение всех ноутбуков по размеру экрана")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<List<NoteBook>> findAllBySize(@RequestParam(required = true) ScreenSize screenSize){
        return ResponseEntity.ok(service.findAllBySize(screenSize));
    }
}
