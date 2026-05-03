package application;

import framework.*;

import static framework.Asserts.*;

@TestClass
public class MyTest {

	@Inject
	Calculator calculator;

	@Before
	public void init() {
		calculator.reset();
	}

	@Test
	public void testMethod1() {
		assertEquals(calculator.add(3), 3);
		assertEquals(calculator.add(4), 7);
	}

	@Test
	public void testMethod2() {
		assertEquals(calculator.add(3), 3);
		assertEquals(calculator.subtract(6), -1);
	}
}