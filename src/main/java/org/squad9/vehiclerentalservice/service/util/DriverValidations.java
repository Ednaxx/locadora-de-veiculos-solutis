package org.squad9.vehiclerentalservice.service.util;

public class DriverValidations {
    public static boolean isCNH(String CNH) {
        try {
            if (CNH.length() != 10) return false;
            Long.parseLong(CNH);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isChassiValido(String chassi) {
        String chassiPadrao = "^[A-HJ-NPR-Z0-9]{17}$";

        return chassi.toUpperCase().matches(chassiPadrao);
    }

    public static boolean isPlacaComumValida(String placa) {
        String placaPadrao = "^[A-Z]{3}-?\\d{4}$";
        return placa.toUpperCase().matches(placaPadrao);
    }

    public static boolean isPlacaMercosulValida(String placa) {
        String placaPadrao = "^[A-Z]{3}\\d[A-Z]{2}\\d{2}$";
        return placa.toUpperCase().matches(placaPadrao);
    }
}
