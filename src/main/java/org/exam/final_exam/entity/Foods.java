package org.exam.final_exam.entity;


import lombok.*;

import java.sql.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Foods {
    private int id;
    private String name;
    private String description;
    private Long price;
    private String imageLink;
    private int categoryId;
    private Date createAt;

    public Foods( String name, String description, Long price, String imageLink, int categoryId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageLink = imageLink;
        this.categoryId = categoryId;
        this.createAt = new Date(System.currentTimeMillis());
    }
}
