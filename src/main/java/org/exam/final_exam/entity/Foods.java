package org.exam.final_exam.entity;


import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Foods {
    private int id;
    private String name;
    private String description;
    private double price;
    private String imageLink;
    private int categoryId;
    private Date createAt;

    public Foods( String name, String description, double price, String imageLink, int categoryId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageLink = imageLink;
        this.categoryId = categoryId;
        this.createAt = new Date();
    }
}
