package org.exam.final_exam.entity;


import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    private int id;
    private int userId;
    private Date orderDate;
    private double totalAmount;
    private String status;
    private Date createAt;
    private String type;

    public Orders( int userId, double totalAmount,String status,String type) {
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createAt = new Date();
        this.type = type;

    }


}
