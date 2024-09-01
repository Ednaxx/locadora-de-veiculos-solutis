package org.squad9.vehiclerentalservice.service.util;

public class CNHValidation {
    public static boolean isCNH(String cnh) {
        try {
            String cleanedCnh = cnh.replace(".", "").replace("-", "");
            return cleanedCnh.length() == 10;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String formatCNH(String cnh) {
        return cnh.replaceAll("[^0-9]", "");
    }
}
