package org.squad9.vehiclerentalservice.service.util;

public class CPFValidation {
    public static boolean isCPF(String CPF) {
        try {
            String cpf = CPF.replace(".", "").replace("-", "");
            if (cpf.length() != 11) return false;

            int[] multiplicadores1 = {10, 9, 8, 7, 6, 5, 4, 3, 2};
            int[] multiplicadores2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

            // Calcula o primeiro dígito verificador
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += Integer.parseInt(cpf.substring(i, i + 1)) * multiplicadores1[i];
            }

            int resto = soma % 11;
            int digito1 = resto < 2 ? 0 : 11 - resto;

            // Calcula o segundo dígito verificador
            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += Integer.parseInt(cpf.substring(i, i + 1)) * multiplicadores2[i];
            }

            resto = soma % 11;
            int digito2 = resto < 2 ? 0 : 11 - resto;

            // Compara os dígitos calculados com os dígitos reais
            return digito1 == Integer.parseInt(cpf.substring(9, 10)) && digito2 == Integer.parseInt(cpf.substring(10));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String formatCPF(String CPF) {
        String cpf = CPF.replaceAll("[^0-9]", "");

        return(cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." +
                cpf.substring(6, 9) + "-" + cpf.substring(9, 11));
    }


}
