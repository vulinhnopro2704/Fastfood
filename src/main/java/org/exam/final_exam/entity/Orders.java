package org.exam.final_exam.entity;

import lombok.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm dd/MM/yyyy");

    public Orders(int userId, double totalAmount, String status, String type) {
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createAt = new Date();
        this.type = type;
    }

    public Orders(int userId, double totalAmount, String status, String type, String orderDateStr, String createAtStr) throws ParseException {
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.type = type;
        this.orderDate = DATE_FORMAT.parse(orderDateStr);
        this.createAt = DATE_FORMAT.parse(createAtStr);
    }

    public String getFormattedOrderDate() {
        return DATE_FORMAT.format(orderDate);
    }

    public String getFormattedCreateAt() {
        return DATE_FORMAT.format(createAt);
    }
}