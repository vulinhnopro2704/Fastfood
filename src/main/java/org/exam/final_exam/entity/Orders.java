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
    private int status;
    private Date createAt;
    private String type;

    public Orders( int userId, Date orderDate, double totalAmount,int status,String type) {
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createAt = new Date();
        this.type = type;

    }


}
