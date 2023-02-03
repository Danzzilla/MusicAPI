package edu.greenriver.sdev.musicproject.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Headphones {
    private String name;
    private double price;
    private double overallRating;
    private double bassRating;
}
