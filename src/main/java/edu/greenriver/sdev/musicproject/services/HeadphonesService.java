package edu.greenriver.sdev.musicproject.services;

import edu.greenriver.sdev.musicproject.models.Headphones;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<Headphones> allHeadphones(){
        return headphones;
    }

    public Headphones findHeadphonesByModel(String modelName){
        return headphones.stream()
                .filter(rec -> rec.getModel().equalsIgnoreCase(modelName))
                .findFirst()
                .orElse(null);
    }

    //POST requests (create)

    public Headphones addHeadphones(Headphones newHeadphones){
        headphones.add(newHeadphones);
        return newHeadphones;
    }

    //PUT requests (update)

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

    public Headphones deleteHeadphones(String modelName){
        //filter out just the matching model name
        headphones = headphones.stream()
                .filter(rec -> !rec.getModel().equalsIgnoreCase(modelName))
                .toList();

        Headphones found = findHeadphonesByModel(modelName);
        return found;
    }

    public boolean areValidHeadphones(Headphones headphones) {
        return headphones.getModel() != null && !headphones.getModel().isEmpty();
    }
}
