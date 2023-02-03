package edu.greenriver.sdev.musicproject.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Music {
    private String name;
    private String songWriter;
    private double length;
    private int year;
}
