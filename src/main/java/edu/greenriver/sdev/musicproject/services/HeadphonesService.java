package edu.greenriver.sdev.musicproject.services;

import edu.greenriver.sdev.musicproject.models.Headphones;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Headphones Service Class
 *
 * @author Daniel Svirida
 * @version 1
 */
@Service
public class HeadphonesService {
    private List<Headphones> headphones = new ArrayList<>(List.of(
            Headphones.builder()
                    .brand("Bose")
                    .model("Quietcomfort 35")
                    .price(235)
                    .rating(4.8)
                    .build(),
            Headphones.builder()
                    .brand("Skullcandy")
                    .model("Crusher")
                    .price(200)
                    .rating(4.2)
                    .build()
    ));

    //GET requests (read)

    /**
     * Gives records
     * @return headphones records
     */
    public List<Headphones> allHeadphones(){
        return headphones;
    }


    /**
     * Finds specific headphone by model
     * @param modelName headphone model name
     * @return headphone object
     */
    public Headphones findHeadphonesByModel(String modelName){
        return headphones.stream()
                .filter(rec -> rec.getModel().equalsIgnoreCase(modelName))
                .findFirst()
                .orElse(null);
    }

    //POST requests (create)

    /**
     * Adds Headphones to the records
     * @param newHeadphones headphone object to be added
     * @return added headphone object
     */
    public Headphones addHeadphones(Headphones newHeadphones){
        headphones.add(newHeadphones);
        return newHeadphones;
    }

    //PUT requests (update)

    /**
     * Updates headphone object in records
     * @param updatedHeadphones headphones to be updated
     * @return updated headphones
     */
    public Headphones updateHeadphones(Headphones updatedHeadphones){
        Headphones found = findHeadphonesByModel(updatedHeadphones.getModel());

        if(found != null){
            found.setBrand(updatedHeadphones.getBrand());
            found.setModel(updatedHeadphones.getModel());
            found.setPrice(updatedHeadphones.getPrice());
            found.setRating(updatedHeadphones.getRating());
        }

        return found;
    }

    //DELETE requests (delete)

    /**
     * Deletes headphones
     * @param modelName headphone model to be deleted
     * @return deleted headphones
     */
    public Headphones deleteHeadphones(String modelName){
        //filter out just the matching model name
        headphones = headphones.stream()
                .filter(rec -> !rec.getModel().equalsIgnoreCase(modelName))
                .toList();

        Headphones found = findHeadphonesByModel(modelName);
        return found;
    }

    /**
     * Checks if headphones are valid
     * @param headphones Headphones to be checked
     * @return true if valid <br> false if not valid
     */
    public boolean areValidHeadphones(Headphones headphones) {
        return headphones.getModel() != null && !headphones.getModel().isEmpty();
    }

    @Override
    public String toString() {
        return "HeadphonesService{" +
                "headphones=" + headphones.toString() +
                '}';
    }
}
