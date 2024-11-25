package org.example.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.account.domain.Account;
import org.example.account.dto.AccountDto;
import org.example.account.dto.CreateAccount;
import org.example.account.dto.DeleteAccount;
import org.example.account.type.AccountStatus;
import org.example.account.service.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @MockBean
    private AccountService accountService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("계좌 생성 성공")
    void successCreateAccount() throws Exception {
        given(accountService.createAccount(anyLong(), anyLong()))
                .willReturn(mockAccountDto(1L, "1234567890", null, null));

        mockMvc.perform(post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new CreateAccount.Request(1L, 100L)
                        )))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.accountNumber").value("1234567890"))
                .andDo(print());
    }

    @Test
    @DisplayName("계좌 해지 성공")
    void successDeleteAccount() throws Exception {
        given(accountService.deleteAccount(anyLong(), anyString()))
                .willReturn(mockAccountDto(1L, "1234567890", null, null));

        mockMvc.perform(delete("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new DeleteAccount.Request(1L, "1234567890")
                        )))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.accountNumber").value("1234567890"))
                .andDo(print());
    }

    @Test
    @DisplayName("계좌 확인 성공")
    void successGetAccountByUserId() throws Exception {
        given(accountService.getAccountsByUserId(anyLong()))
                .willReturn(Arrays.asList(
                        mockAccountDto(1L, "1234567890", 1000L, null),
                        mockAccountDto(1L, "3456789012", 2000L, null),
                        mockAccountDto(1L, "2222222222", 3000L, null)
                ));


        mockMvc.perform(get("/account").param("user_id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].accountNumber").value("1234567890"))
                .andExpect(jsonPath("$[0].balance").value(1000))
                .andExpect(jsonPath("$[1].accountNumber").value("3456789012"))
                .andExpect(jsonPath("$[1].balance").value(2000))
                .andExpect(jsonPath("$[2].accountNumber").value("2222222222"))
                .andExpect(jsonPath("$[2].balance").value(3000));
    }

    private AccountDto mockAccountDto(Long userId, String accountNumber, Long balance, LocalDateTime unRegisteredAt) {
        return AccountDto.builder()
                .userId(userId)
                .accountNumber(accountNumber)
                .balance(balance)
                .registeredAt(LocalDateTime.now())
                .unRegisteredAt(unRegisteredAt)
                .build();
    }

    private Account mockAccount(String accountNumber, AccountStatus status) {
        return Account.builder()
                .accountNumber(accountNumber)
                .accountStatus(status)
                .build();
    }
}