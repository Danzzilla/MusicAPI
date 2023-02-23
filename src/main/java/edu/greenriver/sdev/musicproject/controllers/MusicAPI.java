package edu.greenriver.sdev.musicproject.controllers;

import edu.greenriver.sdev.musicproject.models.Music;
import edu.greenriver.sdev.musicproject.services.MusicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Music API
 * @author Daniel Svirida
 * @version 1.2
 */
@RestController
@RequestMapping("music")
public class MusicAPI {
    private MusicService service;

    /**
     * Instantiate API with Service Layer
     * @param service service
     */
    public MusicAPI(MusicService service){
        this.service = service;
    }

    /**
     * Gets you all the music records
     * @return all music as json
     */
    @GetMapping("")
    public ResponseEntity<List<Music>> allMusic(){
        return new ResponseEntity<>(service.allMusic(), HttpStatus.OK);
    }

    /**
     * Gets you a single music record
     * @param songID song id
     * @return single song as json and http status
     */
    @GetMapping("{songID}")
    public ResponseEntity<Music> musicByID(@PathVariable long songID){
        if(service.findMusicByID(songID) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.findMusicByID(songID), HttpStatus.OK);
    }

    /**
     * Adds music object
     * @param music Music object as json
     * @return added song and http status
     */
    @PostMapping("")
    public ResponseEntity<Music> addMusic(@RequestBody Music music){
        if(!service.isValidMusic(music)){
            //No response body, status code 400
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        //response body is saved, status code 201
        return new ResponseEntity<>(service.addMusic(music), HttpStatus.CREATED);
    }

    /**
     * Updates music object
     * @param updatedMusic Updated music object with unchanged ID
     * @return updated song and http status
     */
    @PutMapping("")
    public ResponseEntity<Music> updateMusic(@RequestBody Music updatedMusic){
        //not found
        if(service.findMusicByID(updatedMusic.getId()) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(service.updateMusic(updatedMusic), HttpStatus.OK);
    }

    /**
     * Deletes music object
     * @param songID id of music to be deleted
     * @return deleted song and http status
     */
    @DeleteMapping("{songID}")
    public ResponseEntity<Music> deleteMusic(@PathVariable long songID){
        //not found
        if(service.findMusicByID(songID) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.deleteMusic(songID), HttpStatus.OK);
    }

    @Override
    public String toString() {
        return "MusicAPI{" +
                "service=" + service +
                '}';
    }
}
