package edu.greenriver.sdev.musicproject.controllers;

import edu.greenriver.sdev.musicproject.models.Headphones;
import edu.greenriver.sdev.musicproject.services.HeadphonesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Headphones API
 * @author Daniel Svirida
 * @version 1.2
 */
@RestController
@RequestMapping("headphones")
public class HeadphonesAPI {
    private HeadphonesService service;

    /**
     * Instantiate API with Service Layer
     * @param service service
     */
    public HeadphonesAPI(HeadphonesService service){
        this.service = service;
    }

    /**
     * Gets you all the headphone records
     * @return all Headphones as json
     */
    @GetMapping("")
    public ResponseEntity<List<Headphones>> allHeadphones(){
        return new ResponseEntity<>(service.allHeadphones(), HttpStatus.OK);
    }

    /**
     * Gets you a single headphone record
     * @param modelID headphone id
     * @return single headphone as json and http status
     */
    @GetMapping("{modelID}")
    public ResponseEntity<Headphones> headphonesByID(@PathVariable long modelID){
        if(service.findHeadphonesByID(modelID) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.findHeadphonesByID(modelID), HttpStatus.OK);
    }

    /**
     * Adds Headphone object
     * @param headphones Headphone object as json
     * @return added headphones and http status
     */
    @PostMapping("")
    public ResponseEntity<Headphones> addHeadphones(@RequestBody Headphones headphones){
        if(!service.areValidHeadphones(headphones)){
            //No response body, status code 400
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        //response body is saved, status code 201
        return new ResponseEntity<>(service.addHeadphones(headphones), HttpStatus.CREATED);
    }

    /**
     * Updates headphones object
     * @param updatedHeadphones Updated headphones object with unchanged ID
     * @return updated headphones and http status
     */
    @PutMapping("")
    public ResponseEntity<Headphones> updateHeadphones(@RequestBody Headphones updatedHeadphones){
        //not found
        if(service.findHeadphonesByID(updatedHeadphones.getId()) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(service.updateHeadphones((updatedHeadphones)), HttpStatus.OK);
    }

    /**
     * Deletes headphones object
     * @param modelID id of headphones to be deleted
     * @return deleted headphones and http status
     */
    @DeleteMapping("{modelID}")
    public ResponseEntity<Headphones> deleteHeadphones(@PathVariable long modelID){
        //not found
        if(service.findHeadphonesByID(modelID) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(service.deleteHeadphones(modelID), HttpStatus.OK);
    }

    @Override
    public String toString() {
        return "HeadphonesAPI{" +
                "service=" + service +
                '}';
    }
}
