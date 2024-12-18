package org.exam.final_exam.enums;

import java.util.HashMap;

public enum OrderType {
    TAKEAWAY,
    DELIVERY,
    DINEIN;

    public static HashMap<String, String> getValues() {
        HashMap<String, String> values = new HashMap<>();
        values.put("TAKEAWAY", "Mang về");
        values.put("DELIVERY", "Giao hàng");
        values.put("DINEIN", "Đặt trước");
        return values;
    }

    public static String toDisplayString(String value) {
        return getValues().get(value);
    }
}
