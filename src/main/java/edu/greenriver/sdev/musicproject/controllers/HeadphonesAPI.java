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

    @GetMapping("{modelID}")
    public ResponseEntity<Headphones> headphonesByID(@PathVariable long modelID){
        if(service.findHeadphonesByID(modelID) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.findHeadphonesByID(modelID), HttpStatus.OK);
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
        if(service.findHeadphonesByID(updatedHeadphones.getId()) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(service.updateHeadphones((updatedHeadphones)), HttpStatus.OK);
    }

    @DeleteMapping("{modelID}")
    public ResponseEntity<Headphones> deleteHeadphones(@PathVariable long modelID){
        //not found
        if(service.findHeadphonesByID(modelID) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(service.deleteHeadphones(modelID), HttpStatus.OK);
    }
}
