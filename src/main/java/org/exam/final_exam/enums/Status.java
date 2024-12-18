package org.exam.final_exam.enums;

import java.util.HashMap;

public enum Status {
    INCART,
    PENDING,
    PROCESSING,
    SHIPPED,
    DELIVERED;

    public static HashMap<String, String> getValues() {
        HashMap<String, String> values = new HashMap<>();
        values.put("INCART", "Giỏ hàng");
        values.put("PENDING", "Đang chờ");
        values.put("PROCESSING", "Đang chuẩn bị");
        values.put("SHIPPED", "Đã giao");
        values.put("DELIVERED", "Đã nhận");
        return values;
    }
}