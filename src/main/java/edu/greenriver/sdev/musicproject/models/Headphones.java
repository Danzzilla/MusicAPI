package edu.greenriver.sdev.musicproject.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Headphones Objects
 *
 * @author Daniel Svirida
 * @version 1.2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Headphones {
    private long id;
    private String brand;
    private String model;
    private double price;
    private double rating;
}
