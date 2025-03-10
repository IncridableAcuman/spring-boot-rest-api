package com.javaboot.spring.controller;

import com.javaboot.spring.model.Transaction;
import com.javaboot.spring.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TransactionController {
    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService){
        this.transactionService=transactionService;
    }
    @GetMapping("/transactions/units")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(transactionService.getMessage());
    }
    @PostMapping("/transactions")
    public ResponseEntity create(@RequestBody Transaction transaction){
        return ResponseEntity.ok(transactionService.save(transaction).getBody());
    }
    @PutMapping("/transactions")
    public ResponseEntity update(@RequestBody Transaction transaction){
        return ResponseEntity.ok(transactionService.update(transaction));
    }

    @DeleteMapping("/transactions/{id}")
    public void delete(@PathVariable Long id){
        transactionService.delete(id);
    }
}
