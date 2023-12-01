package net.ebserh.hctm.util;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Standardize {

	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public static final DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	public static String formatarDataHoraParaBR(LocalDateTime data) {
		if (data == null)
			return "";

		return data.format(formatterDateTime);
	}

	public static String formatarDataParaBR(LocalDate data) {
		if (data == null)
			return "";

		return data.format(formatter);
	}

	public static String truncaENormalizaString(String s, int maxLength) {
		if (s == null)
			return " ".repeat(maxLength);

		String ret = s.length() > maxLength ? s.substring(0, maxLength) : s;
		ret = Normalizer.normalize(ret.toUpperCase(), Normalizer.Form.NFD);
		ret = ret.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

		return ret;
	}

	public static String truncaInteiro(Integer i, int maxLength) {
		if (i == null)
			return "0".repeat(maxLength);

		String mascara = String.format("%%0%dd", maxLength);
		String s = String.format(mascara, i);

		if (s.length() > maxLength)
			s = s.substring(0, maxLength);

		return s;
	}

	public static String removeAcentos(String original) {
		String s = Normalizer.normalize(original, Normalizer.Form.NFD);
		return s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "").toUpperCase();
	}

	public static Integer converteCor(String cor) {
		switch (cor) {
			case "B":
				return 1;
			case "P":
				return 2;
			case "M":
				return 3;
			case "A":
				return 4;
			case "I":
				return 5;
			case "O":
			default:
				// Demanda recebida em reunião de alinhamento (faturamento)
				// A partir de 03/2023, BPA não aceitará mais cor indefinida (99)
				// Por default, retorna 3 (pardo)
				return 3;
		}
	}

}
