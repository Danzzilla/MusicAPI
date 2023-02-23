package edu.greenriver.sdev.musicproject.services;

import edu.greenriver.sdev.musicproject.models.Music;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Music Service Class
 *
 * @author Daniel Svirida
 * @version 1.2
 */
@Service
public class MusicService {
    public static final int ID_MULTIPLIER = 532;
    public static final double LENGTH1 = 3.24;
    public static final int YEAR1 = 2013;
    public static final double LENGTH2 = 5.33;
    public static final int YEAR2 = 2017;
    private List<Music> music = new ArrayList<>(List.of(
            Music.builder()
                    .id((long)(Math.random() * ID_MULTIPLIER))
                    .name("Riptide")
                    .songWriter("Vance Joy")
                    .length(LENGTH1)
                    .year(YEAR1)
                    .build(),
            Music.builder()
                    .id((long)(Math.random() * ID_MULTIPLIER))
                    .name("Reckless Love")
                    .songWriter("Cory Asbury")
                    .length(LENGTH2)
                    .year(YEAR2)
                    .build()
    ));

    //GET requests (read)

    /**
     * Gives all music records
     * @return music records
     */
    public List<Music> allMusic(){
        return music;
    }

    /**
     * Finds song by its name
     * @param songID songs name
     * @return music object
     */
    public Music findMusicByID(long songID){
        return music.stream()
                .filter(rec -> (rec.getId() == songID))
                .findFirst()
                .orElse(null);
    }

    //POST requests (create)

    /**
     * Adds Music to the records
     * @param newMusic music to be added to the records
     * @return music added
     */
    public Music addMusic(Music newMusic){
        music.add(newMusic);
        return newMusic;
    }

    //PUT requests (update)

    /**
     * Updates song in the records
     * @param updatedMusic song to be updated
     * @return updated song
     */
    public Music updateMusic(Music updatedMusic){
        Music found = findMusicByID(updatedMusic.getId());

        if(found != null){
            found.setName(updatedMusic.getName());
            found.setSongWriter(updatedMusic.getSongWriter());
            found.setLength(updatedMusic.getLength());
            found.setYear(updatedMusic.getYear());
        }

        return found;
    }

    //DELETE requests (delete)

    /**
     * Deletes song from records
     * @param songID songs name
     * @return deleted song
     */
    public Music deleteMusic(long songID){
        //filter out just the matching song name
        music = music.stream()
                .filter(rec -> (rec.getId() != songID))
                .toList();

        return findMusicByID(songID);
    }

    /**
     * Checks if a song is valid
     * @param music song to be checked
     * @return true if valid <br> false if not valid
     */
    public boolean isValidMusic(Music music) {
        return music.getName() != null && !music.getName().isEmpty();
    }

    @Override
    public String toString() {
        return "MusicService{" +
                "music=" + music.toString() +
                '}';
    }
}
