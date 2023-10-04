package ru.s21school.calculatorapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.s21school.calculatorapi.controller.request.ExpressionRequest;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class MockMvcCalculatorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void Calculator_Add_ShouldReturnCorrectResult() throws Exception {
        String expression = "1.54837495+1.34521343";
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(new ExpressionRequest(expression));
        mockMvc.perform(post("/api/v1/calculate/")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.expression").value(expression));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1&3", "1+", "1*", "*1", "1(", "1)", "asin", "()", "1.3.4", "tan()+3", "sqrt3", "-*3", "% 3", "90-3*()*3", "123-34-", "sin(3+4-)", "sssin(2)+1", "cooos(2)+2", "aacos(0.3)"})
    void Calculate_WrongInput_WrongExpressionException(String expression) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(new ExpressionRequest(expression));
        mockMvc.perform(post("/api/v1/calculate/")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ERROR"));
    }

}
