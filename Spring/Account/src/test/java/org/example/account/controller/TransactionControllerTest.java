package org.example.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.account.dto.CancelBalance;
import org.example.account.dto.TransactionDto;
import org.example.account.dto.UseBalance;
import org.example.account.exception.AccountException;
import org.example.account.service.TransactionService;
import org.example.account.type.ErrorCode;
import org.example.account.type.TransactionResultType;
import org.example.account.type.TransactionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.example.account.type.ErrorCode.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TransactionController.class)
class TransactionControllerTest {

    @MockBean
    private TransactionService transactionService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void successUseBalance() throws Exception {
        given(transactionService.useBalance(anyLong(), anyString(), anyLong()))
                .willReturn(getTransactionDto("1234567890", 12345L, "transactionId", TransactionResultType.S, TransactionType.USE));

        mockMvc.perform(post("/transaction/use")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new UseBalance.Request(1L, "1234567890", 3000L)
                        )))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountNumber").value("1234567890"))
                .andExpect(jsonPath("$.amount").value(12345L))
                .andExpect(jsonPath("$.transactionId").value("transactionId"))
                .andExpect(jsonPath("$.transactedAt").value("2024-11-21T12:00:00"))
                .andExpect(jsonPath("$.transactionResult").value("S"));
    }

    @Test
    void FailedUseBalance() throws Exception {
        doThrow(new AccountException())
                .when(transactionService)
                .useBalance(anyLong(), anyString(), anyLong());

        mockMvc.perform(post("/transaction/use")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new UseBalance.Request(1L, "1234567890", 3000000L)
                        )))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(transactionService,times(1)).saveFailedUseTransaction("1234567890",3000000L);
    }


    @Test
    void FailedValidUseBalance() throws Exception {
        mockMvc.perform(post("/transaction/use")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new UseBalance.Request(1L, "1234567890", 30000000000L)
                        )))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void successCancelBalance() throws Exception {
        given(transactionService.cancelBalance(anyString(), anyString(), anyLong()))
                .willReturn(getTransactionDto("1234567890", 12345L, "transactionId", TransactionResultType.S, TransactionType.CANCEL));

        mockMvc.perform(post("/transaction/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new CancelBalance.Request("transactionId", "1234567890", 3000L)
                        )))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountNumber").value("1234567890"))
                .andExpect(jsonPath("$.amount").value(12345L))
                .andExpect(jsonPath("$.transactionId").value("transactionId"))
                .andExpect(jsonPath("$.transactedAt").value("2024-11-21T12:00:00"))
                .andExpect(jsonPath("$.transactionResult").value("S"));
    }

    @Test
    void FailedCancelBalance() throws Exception {
        doThrow(new AccountException())
                .when(transactionService)
                .cancelBalance(anyString(), anyString(), anyLong());

        mockMvc.perform(post("/transaction/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new CancelBalance.Request("transactionId", "1234567890", 3000000L)
                        )))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(transactionService,times(1)).saveFailedCancelTransaction("1234567890",3000000L);
    }

    @Test
    void FailedValueCancelBalance() throws Exception {
        mockMvc.perform(post("/transaction/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new UseBalance.Request(1L, "1234567890", 30000000000L)
                        )))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void successQueryTransaction() throws Exception {
        given(transactionService.queryTransaction(anyString()))
                .willReturn(getTransactionDto("1234567890", 12345L, "transactionId", TransactionResultType.S, TransactionType.USE));

        mockMvc.perform(get("/transaction/12345"))
                .andDo(print())
                .andExpect(jsonPath("$.accountNumber").value("1234567890"))
                .andExpect(jsonPath("$.amount").value(12345L))
                .andExpect(jsonPath("$.transactionId").value("transactionId"))
                .andExpect(jsonPath("$.transactionType").value("USE"))
                .andExpect(jsonPath("$.transactedAt").value("2024-11-21T12:00:00"))
                .andExpect(jsonPath("$.transactionResult").value("S"));
    }

    private TransactionDto getTransactionDto(String accountNumber, Long amount, String transactionId,
                                             TransactionResultType resultType, TransactionType type) {
        return TransactionDto.builder()
                .accountNumber(accountNumber)
                .transactedAt(LocalDateTime.of(2024, 11, 21, 12, 0))
                .amount(amount)
                .transactionId(transactionId)
                .transactionResult(resultType)
                .transactionType(type)
                .build();
    }
}