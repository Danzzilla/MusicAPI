package edu.greenriver.sdev.musicproject.services;

import edu.greenriver.sdev.musicproject.models.Headphones;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Headphones Service Class
 *
 * @author Daniel Svirida
 * @version 1.2
 */
@Service
public class HeadphonesService {
    public static final int ID_MULTIPLIER = 532;
    public static final int PRICE1 = 235;
    public static final double RATING1 = 4.8;
    public static final int PRICE2 = 200;
    public static final double RATING2 = 4.2;
    private List<Headphones> headphones = new ArrayList<>(List.of(
            Headphones.builder()
                    .id((long)(Math.random() * ID_MULTIPLIER))
                    .brand("Bose")
                    .model("Quietcomfort 35")
                    .price(PRICE1)
                    .rating(RATING1)
                    .build(),
            Headphones.builder()
                    .id((long)(Math.random() * ID_MULTIPLIER))
                    .brand("Skullcandy")
                    .model("Crusher")
                    .price(PRICE2)
                    .rating(RATING2)
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
     * @param modelID headphone model name
     * @return headphone object
     */
    public Headphones findHeadphonesByID(long modelID){
        return headphones.stream()
                .filter(rec -> (rec.getId() == modelID))
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
        Headphones found = findHeadphonesByID(updatedHeadphones.getId());

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
     * @param modelID headphone model to be deleted
     * @return deleted headphones
     */
    public Headphones deleteHeadphones(long modelID){
        //filter out just the matching model name
        headphones = headphones.stream()
                .filter(rec -> (rec.getId() != modelID))
                .toList();

        return findHeadphonesByID(modelID);
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
