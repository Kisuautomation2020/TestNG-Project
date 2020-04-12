package selenium;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Testing3 {

	// import org.testng.asserts.SoftAssert;
	SoftAssert softAssert = new SoftAssert(); // we can use Assert and sortAssert to gather also..in following example

	@Test(priority = 1)
	public void homeTest() {
		System.out.println("I am in home test");
		System.out.println("Before assertion");
		// Assert.assertTrue(2>3, "Verifying Element");
		Assert.assertTrue(4 > 3, "Verifying Element"); // [for pass the following test depends on it]
		System.out.println("After assertion");
		Assert.assertEquals("abc", "abc"); // hard assertions
	}

	@Test(priority = 2, dependsOnMethods = { "homeTest" }, groups = { "smoke", "sanity" }) // both use priority and
																							// dependency for sure
																							// result
	public void mainTest() {
		System.out.println("I am in main test");
		System.out.println("Before assertion");
		softAssert.assertTrue(2 > 3, "Verifying Element"); // test will pass though it's wrong condition, you should
															// write assertall() for fail it
		System.out.println("After assertion");
		softAssert.assertAll();// similar to error collector of JUnit
		// Note: you have to compulsory write assert.all() method to fail the test
	}

	@Test(priority = 3, dependsOnMethods = { "mainTest", "homeTest" }, groups = "xyz") // this will skip the test
																						// instead of fail if the depend
																						// test is fail
	public void endTest() {
		System.out.println("I am in end test");
		System.out.println("Before assertion");
		softAssert.assertTrue(2 > 3, "Verifying Element");
		System.out.println("After assertion");
		Assert.assertEquals("abc", "abc1");
		System.out.println("After assertion");
		softAssert.assertAll();
	}
}
