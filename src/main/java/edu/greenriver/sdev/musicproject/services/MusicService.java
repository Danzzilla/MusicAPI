package edu.greenriver.sdev.musicproject.services;

import edu.greenriver.sdev.musicproject.models.Music;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MusicService {
    private List<Music> music = new ArrayList<>(List.of(
            Music.builder()
                    .name("Riptide")
                    .songWriter("Vance Joy")
                    .length(3.24)
                    .year(2013)
                    .build(),
            Music.builder()
                    .name("Reckless Love")
                    .songWriter("Cory Asbury")
                    .length(5.33)
                    .year(2017)
                    .build()
    ));

    //GET requests (read)

    public List<Music> allMusic(){
        return music;
    }

    public Music findMusicByName(String songName){
        return music.stream()
                .filter(rec -> rec.getName().equalsIgnoreCase(songName))
                .findFirst()
                .orElse(null);
    }

    //POST requests (create)

    public Music addMusic(Music newMusic){
        music.add(newMusic);
        return newMusic;
    }

    //PUT requests (update)

    public Music updateMusic(Music updatedMusic){
        Music found = findMusicByName(updatedMusic.getName());

        if(found != null){
            found.setName(updatedMusic.getName());
            found.setSongWriter(updatedMusic.getSongWriter());
            found.setLength(updatedMusic.getLength());
            found.setYear(updatedMusic.getYear());
        }

        return found;
    }

    //DELETE requests (delete)

    public Music deleteMusic(String songName){
        //filter out just the matching song name
        music = music.stream()
                .filter(rec -> !rec.getName().equalsIgnoreCase(songName))
                .toList();

        Music found = findMusicByName(songName);
        return found;
    }

    public boolean isValidMusic(Music music) {
        return music.getName() != null && !music.getName().isEmpty();
    }
}
