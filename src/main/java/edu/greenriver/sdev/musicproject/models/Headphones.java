package edu.greenriver.sdev.musicproject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
