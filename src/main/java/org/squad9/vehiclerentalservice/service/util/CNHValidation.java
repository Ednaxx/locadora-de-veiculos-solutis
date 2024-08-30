package org.squad9.vehiclerentalservice.service.util;

public class CNHValidation {
    public static boolean isCNH(String CNH) {
        try {
            if (CNH.length() != 10) return false;
            Long.parseLong(CNH);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
