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
import ru.min.easybottesttask.model.DesktopComputer;
import ru.min.easybottesttask.model.enums.Form;
import ru.min.easybottesttask.service.DesktopComputerService;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {EasyBotTestTaskApplication.class})
@RequiredArgsConstructor
class DesktopComputerControllerTest {

    private final Long ID = 1L;
    private final Form FORM = Form.DESKTOP;
    private final Long SERIAL_NUMBER = 11111111L;
    private final String PRODUCER = "producer";
    private final int PRICE = 1000;
    private final int STOCK_COUNT = 2000;
    private DesktopComputer desktopComputer;
    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private DesktopComputerService serviceMock;
    private MockMvc mockMvc;
    private final WebApplicationContext context;

    @BeforeEach
    public void init(){
        desktopComputer = new DesktopComputer(ID, FORM, SERIAL_NUMBER, PRODUCER, PRICE, STOCK_COUNT);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    @DisplayName("Получение статуса 200 при поиске всех")
    void shouldReturn200WhenFindAll() throws Exception {
        List<DesktopComputer> computers = new ArrayList<>(List.of(desktopComputer));
        when(serviceMock.findAll()).thenReturn(computers);
        mockMvc.perform(get("http://localhost:8080/desktop")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isOk());
    }

    @Test
    @DisplayName("Получение статуса 200 при создании")
    void shouldReturn200WhenCreateCorrectFieldsDesktopComputer() throws Exception {
        when(serviceMock.addComputer(any())).thenReturn(desktopComputer);
        mockMvc.perform(post("http://localhost:8080/desktop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(desktopComputer)))
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
    void shouldReturn422WhenCreateIncorrectFieldsDesktopComputer() throws Exception {
        when(serviceMock.addComputer(any())).thenThrow(MyValidationException.class);
        mockMvc.perform(post("http://localhost:8080/desktop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(desktopComputer)))
                .andExpectAll(
                        status().isUnprocessableEntity()
                );
    }

    @Test
    @DisplayName("Получение статуса 200 при обновлении")
    void shouldReturn200WhenUpdateComputer() throws Exception {
        when(serviceMock.updateComputer(any(), any(desktopComputer.getClass()))).thenReturn(desktopComputer);
        mockMvc.perform(patch("http://localhost:8080/desktop").param("id", String.valueOf(ID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(desktopComputer)))
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
    void shouldReturn422WhenUpdateComputer() throws Exception {
        when(serviceMock.updateComputer(any(), any(desktopComputer.getClass()))).thenThrow(MyValidationException.class);
        mockMvc.perform(patch("http://localhost:8080/desktop").param("id", String.valueOf(ID))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(desktopComputer)))
                .andExpectAll(
                        status().isUnprocessableEntity()
                );
    }

    @Test
    @DisplayName("Получение статуса 200 при поиске по идентификатору")
    void shouldReturn200WhenFindById() throws Exception {
        when(serviceMock.findById(any())).thenReturn(desktopComputer);
        mockMvc.perform(get("http://localhost:8080/desktop/1")
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
        mockMvc.perform(get("http://localhost:8080/desktop/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isNotFound()
                );
    }

    @Test
    @DisplayName("Получение статуса 200 при поиске по форме")
    void shouldReturn200WhenFindAllByForm() throws Exception {
        List<DesktopComputer> computers = new ArrayList<>(List.of(desktopComputer));
        when(serviceMock.findAllByForm(FORM)).thenReturn(computers);
        mockMvc.perform(get("http://localhost:8080/desktop/form").param("form", String.valueOf(FORM))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isOk());
    }

}