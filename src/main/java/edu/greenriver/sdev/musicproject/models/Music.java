package edu.greenriver.sdev.musicproject.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Music Object
 *
 * @author Daniel Svirida
 * @version 1.2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Music {
    private long id;
    private String name;
    private String songWriter;
    private double length;
    private int year;
}
