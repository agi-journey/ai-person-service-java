package com.aiperson.service.domain.util;

public class CpfValidator {

    private CpfValidator() {
    }

    public static boolean isValid(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            return false;
        }

        if (!cpf.matches("\\d{11}")) {
            return false;
        }

        if (cpf.equals("00000000000") || cpf.equals("11111111111") ||
            cpf.equals("22222222222") || cpf.equals("33333333333") ||
            cpf.equals("44444444444") || cpf.equals("55555555555") ||
            cpf.equals("66666666666") || cpf.equals("77777777777") ||
            cpf.equals("88888888888") || cpf.equals("99999999999")) {
            return false;
        }

        int firstDigit = calculateDigit(cpf.substring(0, 9), 10);
        int secondDigit = calculateDigit(cpf.substring(0, 10), 11);

        return cpf.charAt(9) == Character.forDigit(firstDigit, 10) &&
               cpf.charAt(10) == Character.forDigit(secondDigit, 10);
    }

    private static int calculateDigit(String base, int weight) {
        int sum = 0;
        for (int i = 0; i < base.length(); i++) {
            sum += Character.getNumericValue(base.charAt(i)) * weight;
            weight--;
        }
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }

    public static String clean(String cpf) {
        if (cpf == null) {
            return null;
        }
        return cpf.replaceAll("[^0-9]", "");
    }
}