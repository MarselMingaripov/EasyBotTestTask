package ru.min.easybottesttask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.webjars.NotFoundException;
import ru.min.easybottesttask.EasyBotTestTaskApplication;
import ru.min.easybottesttask.exception.MyValidationException;
import ru.min.easybottesttask.model.Monitor;
import ru.min.easybottesttask.model.NoteBook;
import ru.min.easybottesttask.model.enums.ScreenSize;
import ru.min.easybottesttask.service.NoteBookService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {EasyBotTestTaskApplication.class})
@RequiredArgsConstructor
class NoteBookControllerTest {

    private final Long ID = 1L;
    private final ScreenSize SIZE = ScreenSize.FIFTEEN;
    private final Long SERIAL_NUMBER = 11111111L;
    private final String PRODUCER = "producer";
    private final int PRICE = 1000;
    private final int STOCK_COUNT = 2000;
    private NoteBook noteBook;
    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private NoteBookService serviceMock;
    private MockMvc mockMvc;
    private final WebApplicationContext context;

    @BeforeEach
    public void init(){
        noteBook = new NoteBook(ID, SIZE, SERIAL_NUMBER, PRODUCER, PRICE, STOCK_COUNT);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    @DisplayName("Получение статуса 200 при поиске всех")
    void shouldReturn200WhenFindAll() throws Exception {
        List<NoteBook> noteBooks = new ArrayList<>(List.of(noteBook));
        when(serviceMock.findAll()).thenReturn(noteBooks);
        mockMvc.perform(get("http://localhost:8080/notebook")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isOk());
    }

    @Test
    @DisplayName("Получение статуса 200 при создании")
    void shouldReturn200WhenCreateCorrectFieldsNoteBook() throws Exception {
        when(serviceMock.addNoteBook(any())).thenReturn(noteBook);
        mockMvc.perform(post("http://localhost:8080/notebook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(noteBook)))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.id").value(ID),
                        jsonPath("$.serialNumber").value(SERIAL_NUMBER),
                        jsonPath("$.producer").value(PRODUCER),
                        jsonPath("$.price").value(PRICE),
                        jsonPath("$.stockCount").value(STOCK_COUNT)
                );
    }

    @Test
    @DisplayName("Получение статуса 422 при создании")
    void shouldReturn422WhenCreateIncorrectFieldsNoteBook() throws Exception {
        when(serviceMock.addNoteBook(any())).thenThrow(MyValidationException.class);
        mockMvc.perform(post("http://localhost:8080/notebook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(noteBook)))
                .andExpectAll(
                        status().isUnprocessableEntity()
                );
    }

    @Test
    @DisplayName("Получение статуса 200 при обновлении")
    void shouldReturn200WhenUpdateNoteBook() throws Exception {
        when(serviceMock.updateNoteBook(any(), any(noteBook.getClass()))).thenReturn(noteBook);
        mockMvc.perform(patch("http://localhost:8080/notebook").param("id", String.valueOf(ID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(noteBook)))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.id").value(ID),
                        jsonPath("$.serialNumber").value(SERIAL_NUMBER),
                        jsonPath("$.producer").value(PRODUCER),
                        jsonPath("$.price").value(PRICE),
                        jsonPath("$.stockCount").value(STOCK_COUNT)
                );
    }

    @Test
    @DisplayName("Получение статуса 422 при обновлении")
    void shouldReturn422WhenUpdateNoteBook() throws Exception {
        when(serviceMock.updateNoteBook(any(), any(noteBook.getClass()))).thenThrow(MyValidationException.class);
        mockMvc.perform(patch("http://localhost:8080/notebook").param("id", String.valueOf(ID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(noteBook)))
                .andExpectAll(
                        status().isUnprocessableEntity()
                );
    }

    @Test
    @DisplayName("Получение статуса 200 при поиске по идентификатору")
    void shouldReturn200WhenFindById() throws Exception {
        when(serviceMock.findById(any())).thenReturn(noteBook);
        mockMvc.perform(get("http://localhost:8080/notebook/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.id").value(ID),
                        jsonPath("$.serialNumber").value(SERIAL_NUMBER),
                        jsonPath("$.producer").value(PRODUCER),
                        jsonPath("$.price").value(PRICE),
                        jsonPath("$.stockCount").value(STOCK_COUNT)
                );
    }

    @Test
    @DisplayName("Получение статуса 404 при поиске по идентификатору")
    void shouldReturn404WhenFindById() throws Exception {
        when(serviceMock.findById(any())).thenThrow(NotFoundException.class);
        mockMvc.perform(get("http://localhost:8080/notebook/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isNotFound()
                );
    }

    /*@Test
    @DisplayName("Получение статуса 200 при поиске по размеру")
    void shouldReturn200WhenFindAllBySize() throws Exception {
        List<NoteBook> noteBooks = new ArrayList<>(List.of(noteBook));
        when(serviceMock.findAllBySize(SIZE)).thenReturn(noteBooks);
        mockMvc.perform(get("http://localhost:8080/notebook/size").param("size", String.valueOf(SIZE))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isOk());
    }*/
}