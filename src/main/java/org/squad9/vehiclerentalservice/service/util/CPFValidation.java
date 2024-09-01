package org.squad9.vehiclerentalservice.service.util;

public class CPFValidation {

    public static boolean isCPF(String CPF) {
        String cleanedCPF = cleanCPF(CPF);
        if (cleanedCPF.length() != 11) return false;

        int firstCheckDigit = calculateCheckDigit(cleanedCPF, new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2}, 9);
        int secondCheckDigit = calculateCheckDigit(cleanedCPF, new int[]{11, 10, 9, 8, 7, 6, 5, 4, 3, 2}, 10);

        return firstCheckDigit == getDigitFromCPF(cleanedCPF, 9) && secondCheckDigit == getDigitFromCPF(cleanedCPF, 10);
    }

    private static String cleanCPF(String CPF) {
        return CPF.replace(".", "").replace("-", "");
    }

    private static int calculateCheckDigit(String cpf, int[] multipliers, int length) {
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += Integer.parseInt(cpf.substring(i, i + 1)) * multipliers[i];
        }
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }

    private static int getDigitFromCPF(String cpf, int position) {
        return Integer.parseInt(cpf.substring(position, position + 1));
    }
}
