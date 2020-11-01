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
		String dateAsString = "01/02/1980";

		// When
		boolean validation = util.isValid(dateAsString);

		// Then
		assertThat(validation).isTrue();
	}

	@Test
	void dateIsNotValid() {

		// Given
		String dateAsString = "01/02/3000";

		// When
		boolean validation = util.isValid(dateAsString);

		// Then
		assertThat(validation).isFalse();
	}

	@Test
	void toLocalDateWithSuccess() {

		// Given
		String dateAsString = "01/02/1980";

		// When
		int age = util.toLocalDate(dateAsString);

		// Then
		assertThat(40).isEqualTo(age);

	}

	@Test
	void toLocalDateWithException() {

		// When
		String wrongDateFormat = "01021980";

		// Then
		Assertions.assertThrows(NullPointerException.class, () -> {
			util.toLocalDate(wrongDateFormat);
		});

	}

}
