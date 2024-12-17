package org.exam.final_exam.entity;

import lombok.*;

import java.util.Date;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class foodOrderDetails {

    // orderdetail
    private int orderDetailId;
    private int orderId;
    private int quantity;
    private double subtotal;
    private String message;
    // food
    private String name;
    private double price;
    private String imageLink;


}
