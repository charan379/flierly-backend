package com.ctytech.flierly;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@SpringBootTest
class FlierlyApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	@DisplayName("Simple Test : UOM")
	void simpleTest() {
		BigDecimal qty = BigDecimal.valueOf(105);

		String fromUom = "Pack Of 3";

		String toUom = "Each";

		BigDecimal conversionFactor = BigDecimal.valueOf(0.3333333330);

		BigDecimal qtyCon = qty.multiply(conversionFactor, new MathContext(10, RoundingMode.HALF_EVEN));

		System.out.printf("This converted qty to packs is : %f", qtyCon.stripTrailingZeros());

	}


}
