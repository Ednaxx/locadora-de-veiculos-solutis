package org.squad9.vehiclerentalservice.service.util;

public class DriverValidations {

    public static boolean isCNHValid(String CNH) {
        try {
            if (CNH.length() != 10) return false;
            Long.parseLong(CNH);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isChassisValid(String chassis) {
        String chassisPattern = "^[A-HJ-NPR-Z0-9]{17}$";
        return chassis.toUpperCase().matches(chassisPattern);
    }

    public static boolean isStandardLicensePlateValid(String licensePlate) {
        String licensePlatePattern = "^[A-Z]{3}-?\\d{4}$";
        return licensePlate.toUpperCase().matches(licensePlatePattern);
    }

    public static boolean isMercosurLicensePlateValid(String licensePlate) {
        String licensePlatePattern = "^[A-Z]{3}\\d[A-Z]{2}\\d{2}$";
        return licensePlate.toUpperCase().matches(licensePlatePattern);
    }
}
