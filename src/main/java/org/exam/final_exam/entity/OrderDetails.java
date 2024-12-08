package org.exam.final_exam.entity;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
    private int id;
    private int orderId;
    private int foodId;
    private int quantity;
    private double subtotal;
    private String message;

    public OrderDetails(int orderId, int foodId, int quantity, double subtotal, String message) {
        this.orderId = orderId;
        this.foodId = foodId;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.message = message;

    }
}
