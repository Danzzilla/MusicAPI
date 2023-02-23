package edu.greenriver.sdev.musicproject.controllers;

import edu.greenriver.sdev.musicproject.models.Music;
import edu.greenriver.sdev.musicproject.services.MusicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("music")
public class MusicAPI {
    private MusicService service;

    public MusicAPI(MusicService service){
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<Music>> allMusic(){
        return new ResponseEntity<>(service.allMusic(), HttpStatus.OK);
    }

    @GetMapping("{songID}")
    public ResponseEntity<Music> musicByID(@PathVariable long songID){
        if(service.findMusicByID(songID) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.findMusicByID(songID), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Music> addMusic(@RequestBody Music music){
        if(!service.isValidMusic(music)){
            //No response body, status code 400
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        //response body is saved, status code 201
        return new ResponseEntity<>(service.addMusic(music), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Music> updateMusic(@RequestBody Music updatedMusic){
        //not found
        if(service.findMusicByID(updatedMusic.getId()) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(service.updateMusic(updatedMusic), HttpStatus.OK);
    }

    @DeleteMapping("{songID}")
    public ResponseEntity<Music> deleteMusic(@PathVariable long songID){
        //not found
        if(service.findMusicByID(songID) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.deleteMusic(songID), HttpStatus.OK);
    }
}
