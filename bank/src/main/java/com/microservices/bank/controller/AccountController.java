package com.microservices.bank.controller;

import com.microservices.bank.domain.BaseController;
import com.microservices.bank.domain.BaseService;
import com.microservices.bank.dto.AccountDTO;
import com.microservices.bank.dto.NewBuyDTO;
import com.microservices.bank.entity.Account;
import com.microservices.bank.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("/accounts")
@Tag(name = "Account Controller", description = "Responsável por contas bancárias")
public class AccountController extends BaseController<Account, AccountDTO> {

    private final AccountService accountService;

    private final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    public AccountController(AccountService accountService) {
        super(Account.class, AccountDTO.class, accountService);
        this.accountService = accountService;
    }

    @PostMapping("/deposit/{username}")
    @Operation(description = "Permite fazer um deposito usando o nome de usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Success - No Content"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<Void> deposit(
            @PathVariable(value = "username", required = true) String username,
            @RequestParam(value = "amount", required = true) BigDecimal amount) {

        LOGGER.info("Depositando dinheiro");

        accountService.deposit(username, amount);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/withdraw/{username}")
    @Operation(description = "Permite fazer um saque usando o nome de usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Success - No Content"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<Void> withdraw(
            @PathVariable(value = "username", required = true) String username,
            @RequestParam(value = "amount", required = true) BigDecimal amount) {

        LOGGER.info("Sacando dinheiro");

        accountService.withdraw(username, amount);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/new-buy")
    @Operation(description = "Verifica se um usuário pode pagar uma nova compra, caso possa, diminui o valor da compra da conta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<Boolean> newBuy(@RequestBody NewBuyDTO dto) {
        LOGGER.info("Verificando se é possível ter uma nova compra");

        boolean canBuy = accountService.newBuy(dto);

        return ResponseEntity.ok(canBuy);
    }
}
