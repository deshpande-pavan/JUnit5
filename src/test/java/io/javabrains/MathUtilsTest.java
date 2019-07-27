package io.javabrains;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("When running MathUtils")
class MathUtilsTest {

	MathUtils utils = null;

	@BeforeAll
	void beforeAllInit() {
//		System.out.println("This needs to rum before all");
	}

	@BeforeEach
	void init() {
		utils = new MathUtils();
	}

	@AfterEach
	void cleanUp() {
//		System.out.println("cleaning up...");
	}

	@Nested
	@DisplayName("Add method")
	class AddMethod {
		@Test
		@DisplayName("when adding two numbers for positive")
		void testAddPositive() {
			assertEquals(5, utils.add(2, 3), () -> "should return sum " + 0 + " but returned " + utils.add(2, 3));
		}

		@Test
		@DisplayName("when adding two numbers for negative")
		void testAddNegative() {
			assertEquals(1, utils.add(-2, 3), "should return the right sum");
		}
	}

	@Test
	@DisplayName("multiply method")
	void testMultiply() {
		assertAll(() -> assertEquals(4, utils.multiply(2, 2)), () -> assertEquals(0, utils.multiply(2, 0)),
				() -> assertEquals(-2, utils.multiply(2, -1))

		);
	}

	@Test
	@Disabled
	void testDisabled() {
		fail("this test should be disabled");
	}

	
	@RepeatedTest(5)
	void testComputeCircleArea(RepetitionInfo repetitionInfo) {
		int getCurrent=repetitionInfo.getCurrentRepetition();
		assertEquals(314.1592653589793, utils.computeCircleArea(10.0), "should return right circle area at repetition step " + getCurrent);
	}

	@Test
	@EnabledOnOs(OS.WINDOWS)
	void testDivide() {
		boolean isServerUp = true;
		assumeTrue(isServerUp);
		assertThrows(ArithmeticException.class, () -> utils.divide(1, 0), "should throw divide by zero");
	}
}