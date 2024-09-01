package org.squad9.vehiclerentalservice.service.util;

public class CPFValidation {
    public static boolean isCPF(String cpf) {
        try {
            String cleanedCpf = cpf.replace(".", "").replace("-", "");
            if (cleanedCpf.length() != 11) {
                return false;
            }

            int[] firstMultipliers = {10, 9, 8, 7, 6, 5, 4, 3, 2};
            int[] secondMultipliers = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum += Integer.parseInt(cleanedCpf.substring(i, i + 1)) * firstMultipliers[i];
            }
            int remainder = sum % 11;
            int firstDigit = remainder < 2 ? 0 : 11 - remainder;

            sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += Integer.parseInt(cleanedCpf.substring(i, i + 1)) * secondMultipliers[i];
            }
            remainder = sum % 11;
            int secondDigit = remainder < 2 ? 0 : 11 - remainder;

            return firstDigit == Integer.parseInt(cleanedCpf.substring(9, 10)) && secondDigit == Integer.parseInt(cleanedCpf.substring(10));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String formatCPF(String cpf) {
        String cleanedCpf = cpf.replaceAll("[^0-9]", "");

        return (cleanedCpf.substring(0, 3) + "." + cleanedCpf.substring(3, 6) + "." + cleanedCpf.substring(6, 9) + "-" + cleanedCpf.substring(9, 11));
    }
}
