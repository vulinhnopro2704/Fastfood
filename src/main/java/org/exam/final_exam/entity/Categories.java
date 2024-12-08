package org.exam.final_exam.entity;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Categories {
    private int id;
    private String name;
    private String description;

    public Categories(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
