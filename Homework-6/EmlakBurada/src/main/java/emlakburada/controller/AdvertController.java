package main.java.emlakburada.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import emlakburada.dto.AdvertRequest;
import emlakburada.dto.response.AdvertResponse;
import emlakburada.service.AdvertService;

@RestController
public class AdvertController {

    @Autowired
    private AdvertService advertService;

    @GetMapping(value = "/adverts")
    public ResponseEntity<List<AdvertResponse>> getAllAdvert() {
        return new ResponseEntity<>(advertService.getAllAdverts(), HttpStatus.OK);
    }

    @PostMapping(value = "/adverts")
    public ResponseEntity<AdvertResponse> createAdvert(@RequestBody AdvertRequest request) {
        return new ResponseEntity<>(advertService.saveAdvert(request), HttpStatus.CREATED);
    }

    @PutMapping(value = "/adverts")
    public ResponseEntity<AdvertResponse> updateAdvertById(@RequestBody AdvertRequest request, @RequestParam int advertId) {
        return new ResponseEntity<>(advertService.updateAdvertById(request, advertId), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/adverts")
    public ResponseEntity<String> deleteAdvertById(@RequestParam int advertId) {
        return new ResponseEntity<>(advertService.deleteAdvertById(advertId), HttpStatus.CREATED);
    }

    @GetMapping(value = "/adverts/{id}")
    public ResponseEntity<AdvertResponse> findAdvertByAdvertId(@PathVariable int id) {
        return new ResponseEntity<>(advertService.findAdvertByAdvertId(id), HttpStatus.OK);
    }


}
