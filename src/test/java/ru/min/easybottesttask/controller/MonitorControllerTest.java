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
import ru.min.easybottesttask.model.enums.MonitorDiagonal;
import ru.min.easybottesttask.service.MonitorService;
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
class MonitorControllerTest {

    private final Long ID = 1L;
    private final MonitorDiagonal DIAGONAL = MonitorDiagonal.TWENTY_FOUR;
    private final Long SERIAL_NUMBER = 11111111L;
    private final String PRODUCER = "producer";
    private final int PRICE = 1000;
    private final int STOCK_COUNT = 2000;
    private Monitor monitor;
    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private MonitorService serviceMock;
    private MockMvc mockMvc;
    private final WebApplicationContext context;

    @BeforeEach
    public void init(){
        monitor = new Monitor(ID, DIAGONAL, SERIAL_NUMBER, PRODUCER, PRICE, STOCK_COUNT);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    @DisplayName("Получение статуса 200 при поиске всех")
    void shouldReturn200WhenFindAll() throws Exception {
        List<Monitor> monitors = new ArrayList<>(List.of(monitor));
        when(serviceMock.findAll()).thenReturn(monitors);
        mockMvc.perform(get("http://localhost:8080/monitor")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isOk());
    }

    @Test
    @DisplayName("Получение статуса 200 при создании")
    void shouldReturn200WhenCreateCorrectFieldsMonitor() throws Exception {
        when(serviceMock.addMonitor(any())).thenReturn(monitor);
        mockMvc.perform(post("http://localhost:8080/monitor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(monitor)))
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
    void shouldReturn422WhenCreateIncorrectFieldsMonitor() throws Exception {
        when(serviceMock.addMonitor(any())).thenThrow(MyValidationException.class);
        mockMvc.perform(post("http://localhost:8080/monitor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(monitor)))
                .andExpectAll(
                        status().isUnprocessableEntity()
                );
    }

    @Test
    @DisplayName("Получение статуса 200 при обновлении")
    void shouldReturn200WhenUpdateMonitor() throws Exception {
        when(serviceMock.updateMonitor(any(), any(monitor.getClass()))).thenReturn(monitor);
        mockMvc.perform(patch("http://localhost:8080/monitor").param("id", String.valueOf(ID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(monitor)))
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
    void shouldReturn422WhenUpdateMonitor() throws Exception {
        when(serviceMock.updateMonitor(any(), any(monitor.getClass()))).thenThrow(MyValidationException.class);
        mockMvc.perform(patch("http://localhost:8080/monitor").param("id", String.valueOf(ID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(monitor)))
                .andExpectAll(
                        status().isUnprocessableEntity()
                );
    }

    @Test
    @DisplayName("Получение статуса 200 при поиске по идентификатору")
    void shouldReturn200WhenFindById() throws Exception {
        when(serviceMock.findById(any())).thenReturn(monitor);
        mockMvc.perform(get("http://localhost:8080/monitor/1")
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
        mockMvc.perform(get("http://localhost:8080/monitor/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isNotFound()
                );
    }

    @Test
    @DisplayName("Получение статуса 200 при поиске по диагонали")
    void shouldReturn200WhenFindAllByDiagonal() throws Exception {
        List<Monitor> monitors = new ArrayList<>(List.of(monitor));
        when(serviceMock.findAllByDiagonal(DIAGONAL)).thenReturn(monitors);
        mockMvc.perform(get("http://localhost:8080/monitor/diagonal").param("diagonal", String.valueOf(DIAGONAL))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isOk());
    }

}