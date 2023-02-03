package edu.greenriver.sdev.musicproject.controllers;

import edu.greenriver.sdev.musicproject.models.Headphones;
import edu.greenriver.sdev.musicproject.services.HeadphonesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("headphones")
public class HeadphonesAPI {
    private HeadphonesService service;

    public HeadphonesAPI(HeadphonesService service){
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<Headphones>> allHeadphones(){
        return new ResponseEntity<>(service.allHeadphones(), HttpStatus.OK);
    }

    @GetMapping("{headphonesName}")
    public ResponseEntity<Headphones> headphonesByModel(@PathVariable String modelName){
        if(service.findHeadphonesByModel(modelName) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.findHeadphonesByModel(modelName), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Headphones> addHeadphones(@RequestBody Headphones headphones){
        if(!service.areValidHeadphones(headphones)){
            //No response body, status code 400
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        //response body is saved, status code 201
        return new ResponseEntity<>(service.addHeadphones(headphones), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Headphones> updateHeadphones(@RequestBody Headphones updatedHeadphones){
        //not found
        if(service.findHeadphonesByModel(updatedHeadphones.getModel()) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //invalid data
        else if(!service.areValidHeadphones(updatedHeadphones)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(service.updateHeadphones((updatedHeadphones)), HttpStatus.OK);
    }

    @DeleteMapping("{modelName}")
    public void deleteHeadphones(@PathVariable String modelName){
        service.deleteHeadphones(modelName);
    }
}
