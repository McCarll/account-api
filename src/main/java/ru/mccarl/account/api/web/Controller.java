package ru.mccarl.account.api.web;

import io.swagger.annotations.ApiOperation;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mccarl.account.api.repository.AccountRepository;
import ru.mccarl.account.api.entity.Account;

/**
 * Created by vrudometkin on 24/11/2017.
 */
@RestController
@Configuration
@RequestMapping(produces = "application/json")
public class Controller {

    @Autowired
    private AccountRepository accountRepository;

    @ApiOperation(value = "Получение списка счетов")
    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public ResponseEntity getAccounts(){
        return ResponseEntity.ok(accountRepository.findAll());
    }

    @ApiOperation(value = "Получение счета по id")
    @RequestMapping(value = "/accounts", method = RequestMethod.GET, params = "id")
    public ResponseEntity getAccount(@RequestParam String id){
        return ResponseEntity.ok(accountRepository.findOne(id));
    }

    @ApiOperation(value = "Добавление счета")
    @PostMapping("/accounts")
    public ResponseEntity addAccount(@RequestBody Account account){
        return ResponseEntity.ok(accountRepository.save(account));
    }

    @ApiOperation(value = "Добавление параметров счета")
    @PutMapping("/accounts")
    public ResponseEntity putAccount(
            @RequestBody Account account){
        Account oldAccount = accountRepository.findOne(account.get_id().toString());
        BeanUtils.copyProperties(oldAccount, account);
        accountRepository.save(oldAccount);
        return ResponseEntity.ok(oldAccount);
    }

    @ApiOperation(value = "Изменение свойств счета")
    @PatchMapping("/accounts")
    public ResponseEntity changeAccountInfo(@RequestBody Account account){
        Account oldAccount = accountRepository.findOne(account.get_id().toString());
        BeanUtils.copyProperties(oldAccount, account);
        accountRepository.save(oldAccount);
        return ResponseEntity.ok(oldAccount);
    }

    @ApiOperation(value = "Удаление счета")
    @DeleteMapping("/accounts")
    public ResponseEntity deleteAccount(@RequestParam ObjectId id){
        accountRepository.delete(id.toString());
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity handleValidationException(Throwable e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

}
