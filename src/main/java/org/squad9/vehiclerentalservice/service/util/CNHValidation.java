package org.squad9.vehiclerentalservice.service.util;

public class CNHValidation {
    public static boolean isCNH(String CNH) {
        try {
            String cnh = CNH.replace(".", "").replace("-", "");
            if (cnh.length() != 10) {
                return false;
            }

            Long.parseLong(cnh);

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
