package com.javaboot.spring.service;

import com.javaboot.spring.model.Transaction;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Service
public class TransactionService {
    private final String URI="http://localhost:8081/api/transactions";
    public String getMessage(){
        RestTemplate restTemplate=new RestTemplate();
        String message=restTemplate.getForObject(URI + "/test",String.class);
        return message;
    }
    public ResponseEntity<Transaction> save(Transaction transaction){
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<Transaction> transactionResponseEntity=restTemplate.postForEntity(URI,transaction,Transaction.class);
        return transactionResponseEntity;
    }
    public List<Transaction>  getAll(){
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<List<Transaction>> entity=new HttpEntity<>(headers);
        RestTemplate template=new RestTemplate();
        return template.exchange(URI+"/test", HttpMethod.GET,entity,List.class).getBody();
    }
    public Transaction update(Transaction transaction){
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Transaction> entity=new HttpEntity<>(transaction,headers);
        RestTemplate template=new RestTemplate();
        return template.exchange(URI,HttpMethod.PUT,entity,Transaction.class).getBody();
    }
    public void delete(Long id){
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity=new HttpEntity<>(headers);
        RestTemplate template=new RestTemplate();
         ResponseEntity<String> template1= template.exchange(URI+"/{id}",HttpMethod.DELETE,entity,String.class);
         if(template1.getStatusCode()==HttpStatus.NO_CONTENT){
             System.out.println("Successfully deleted");
         } else{
             System.out.println("Failed");
         }
    }
}
