package com.exposure101.geo.converters;

public class LatZones {
	private char[] letters = { 'A', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K',
			'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Z' };

	private int[] degrees = { -90, -84, -72, -64, -56, -48, -40, -32, -24, -16,
			-8, 0, 8, 16, 24, 32, 40, 48, 56, 64, 72, 84 };

	private char[] negLetters = { 'A', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K',
			'L', 'M' };

	private int[] negDegrees = { -90, -84, -72, -64, -56, -48, -40, -32, -24,
			-16, -8 };

	private char[] posLetters = { 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Z' };

	private int[] posDegrees = { 0, 8, 16, 24, 32, 40, 48, 56, 64, 72, 84 };

	private int arrayLength = 22;

	public LatZones() {
	}

	public int getLatZoneDegree(String letter) {
		char ltr = letter.charAt(0);
		for (int i = 0; i < arrayLength; i++) {
			if (letters[i] == ltr) {
				return degrees[i];
			}
		}
		return -100;
	}

	public String getLatZone(double latitude) {
		int latIndex = -2;
		int lat = (int) latitude;

		if (lat >= 0) {
			int len = posLetters.length;
			for (int i = 0; i < len; i++) {
				if (lat == posDegrees[i]) {
					latIndex = i;
					break;
				}

				if (lat > posDegrees[i]) {
					continue;
				} else {
					latIndex = i - 1;
					break;
				}
			}
		} else {
			int len = negLetters.length;
			for (int i = 0; i < len; i++) {
				if (lat == negDegrees[i]) {
					latIndex = i;
					break;
				}

				if (lat < negDegrees[i]) {
					latIndex = i - 1;
					break;
				} else {
					continue;
				}

			}

		}

		if (latIndex == -1) {
			latIndex = 0;
		}
		if (lat >= 0) {
			if (latIndex == -2) {
				latIndex = posLetters.length - 1;
			}
			return String.valueOf(posLetters[latIndex]);
		} else {
			if (latIndex == -2) {
				latIndex = negLetters.length - 1;
			}
			return String.valueOf(negLetters[latIndex]);

		}
	}
}
