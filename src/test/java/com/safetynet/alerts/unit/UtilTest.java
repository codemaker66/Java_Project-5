package com.safetynet.alerts.unit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import com.safetynet.alerts.utils.Util;

@WebMvcTest(Util.class)
class UtilTest {

	@Autowired
	private Util util;

	@Test
	void dateIsValid() {

		// Given
		String dateStr = "01/02/1980";

		// When
		boolean result = util.isValid(dateStr);

		// Then
		assertThat(result).isTrue();
	}

	@Test
	void dateIsNotValid() {

		// Given
		String dateStr = "01/02/3000";

		// When
		boolean result = util.isValid(dateStr);

		// Then
		assertThat(result).isFalse();
	}

	@Test
	void toLocalDateWithSuccess() {

		// Given
		String dateStr = "01/02/1980";

		// When
		int result = util.toLocalDate(dateStr);

		// Then
		assertThat(result).isEqualTo(40);

	}

	@Test
	void toLocalDateWithException() {

		// When
		String wrongDate = "01021980";

		// Then
		Assertions.assertThrows(NullPointerException.class, () -> {
			util.toLocalDate(wrongDate);
		});

	}

}
