package uz.webbrain.appgreenshop.controller;

/*
 * project:  app-green-shop
 * author:   Jumanazar Said
 * created:  17/02/2022 1:13 PM
 */

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import uz.webbrain.appgreenshop.dto.request.IncomingDto;
import uz.webbrain.appgreenshop.dto.response.Response;
import uz.webbrain.appgreenshop.entity.Incoming;
import uz.webbrain.appgreenshop.service.IncomingService;
import uz.webbrain.appgreenshop.utils.ApiPageable;

@RestController
@RequestMapping("/api/v1/incoming")
@RequiredArgsConstructor
public class IncomingController {

    private final IncomingService incomingService;

    @PostMapping
    public HttpEntity<?> addIncoming(@RequestBody IncomingDto incomingDto){
        return incomingService.addIncoming(incomingDto);
    }

    @ApiPageable
    @GetMapping
    public HttpEntity<?> getAllAsPageable(@ApiIgnore Pageable pageable){
        return incomingService.findAllPageable(pageable);
    }

    @GetMapping("/list")
    public HttpEntity<?> getAll(){
        return incomingService.getAll();
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Long id){
        return incomingService.findOneById(id);
    }

    @PutMapping("/{incoming_id}")
    public HttpEntity<?> editIncoming(@PathVariable Long incoming_id, @RequestBody IncomingDto incomingDto){
        return incomingService.editIncoming(incoming_id, incomingDto);
    }

    @DeleteMapping("/{incoming_id}")
    public HttpEntity<?> deleteIncoming(@PathVariable Long incoming_id){
        return incomingService.deleteIncoming(incoming_id);
    }

}
