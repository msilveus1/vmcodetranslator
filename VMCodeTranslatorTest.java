
// TODO: add imports as needed

import static org.junit.jupiter.api.Assertions.*; // org.junit.Assert.*; 

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

/** TODO: add class header comments here*/
/**
 * Class: CS$00
 * Semester: Spring
 * Due Date: 3/14/19                          
 * @author Matthew Silveus 
 * Sources: https://www.baeldung.c
 * om/junit-before-beforeclass-beforeeach-beforeall
 */
public class VMCodeTranslatorTest {

	// TODO: add other fields that will be used by multiple tests
	HashTable<Integer, String> htIntegerKey;
	HashTable<Integer, String> htIntegerKey2;

	// TODO: add code that runs before each test method
	/**
	 * This creates the Hashtable to be used for the
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		//
		htIntegerKey = new HashTable<Integer, String>();
		htIntegerKey2 = new HashTable<Integer, String>(5, .5);
	}

	// TODO: add code that runs after each test method
	@After
	public void tearDown() throws Exception {
		htIntegerKey = null;
		htIntegerKey2 = null;
	}

	/**
	 * Tests that a HashTable returns an integer code indicating which collision
	 * resolution strategy is used. REFER TO HashTableADT for valid collision scheme
	 * codes.
	 */
	@Test
	public void test000_collision_scheme() {
		int scheme = htIntegerKey.getCollisionResolution();
		if (scheme < 1 || scheme > 9)
			fail("collision resolution must be indicated with 1-9");
	}

	/**
	 * IMPLEMENTED AS EXAMPLE FOR YOU Tests that insert(null,null) throws
	 * IllegalNullKeyException
	 */
	@Test
	public void test001_IllegalNullKey() {
		try {
			htIntegerKey.insert(null, null);
			fail("should not be able to insert null key");
		} catch (IllegalNullKeyException e) {
			/* expected */ } catch (Exception e) {
			fail("insert null key should not throw exception " + e.getClass().getName());
		}
	}

	/**
	 * This method tests that zero inserts works correctly and it does not return
	 * any exception and checks the size after 0 keys are inserted
	 */
	@Test
	public void test002_test_insert_no_key() {
		try {
			// Tests the size after initialization.
			assertTrue(htIntegerKey.numKeys() == 0);
		} catch (Exception e) {
			// Fails if some exception is thrown.
			fail("an improper exception was thrown for test 2");
		}
	}

	/**
	 * Tests the size after the insertion of one key
	 */
	@Test
	public void test003_test_insert_one_key() {
		try {
			htIntegerKey.insert(1, "1");
			assertTrue(htIntegerKey.numKeys() == 1);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			fail("An improper exception was thrown");
		}
	}

	/**
	 * Tests Capacity and get function for it
	 */
	@Test
	public void test004_test_get_capacity() {
		try {
			// Checks capacity for both hash tables declared
			assertTrue(htIntegerKey.getCapacity() == 11);
			assertTrue(htIntegerKey2.getCapacity() == 5);
		} catch (Exception e) {
			fail("improper Exception thrown test 004");
			// Fails when improper exception is thrown.
		}
	}

	/**
	 * Checks the load factor of and that it retrieves a proper load factor
	 */
	@Test
	public void test005_test_get_Load_Factor_when_empty() {
		try {
			// Checks the load factor for both integer constructors
			assertTrue(htIntegerKey.getLoadFactor() == 0);
			assertTrue(htIntegerKey.getLoadFactor() == 0);
		} catch (Exception e) {
			// This method failed because it threw and exception
			fail("an improper exception is thrown");
		}
	}

	/**
	 * Tests load factor when there is one insert
	 */
	@Test
	public void test006_test_get_Load_Factor_after_one_insert() {
		try {
			htIntegerKey2.insert(1, "1");
			// This is the getting load factor
			assertTrue(htIntegerKey2.getLoadFactor() == .2);
		} catch (Exception e) {
			// This fails because an improper exception was thrown.
			fail("an improper exception was thrown for test 6");
		}
	}

	/**
	 * Tests load factor threshold get function
	 */
	@Test
	public void test007_test_get_load_factor_threshold() {
		try {
			// Tests the load factor for each initialized variable
			assertTrue(htIntegerKey.getLoadFactorThreshold() == .75);
			assertTrue(htIntegerKey2.getLoadFactorThreshold() == .5);
		} catch (Exception e) {
			// Fails because of improper exception
			fail("An improper exception was thrown");
		}
	}

	/**
	 * This tests the get functionality for one insert
	 */
	@Test
	public void test008_test_get_functionality_one_insert() {
		try {
			htIntegerKey.insert(1, "1");
			// Inserts the 1 and then tests the get function
			assertTrue(htIntegerKey.get(1).equals("1"));
		} catch (Exception e) {
			// Catches an improper exception
			fail("Improper exception is thrown for test 008");
		}
	}

	/**
	 * This inserts 2 numbers and checks if the size is 2
	 */
	@Test
	public void test009_test_get_functionality_2_insert() {
		try {
			// We insert two numbers
			htIntegerKey.insert(1, "1");
			htIntegerKey.insert(2, "2");
			// Checks the get functionality
			assertTrue(htIntegerKey.get(2).equals("2"));
		} catch (Exception e) {
			// Fails if an exception is thrown
			fail("improperException was thrown");
		}
	}	
	/**
	 * This tests the rehashing functionality of it and checks that it happens but
	 * adjusting the table size
	 */
	@Test
	public void test010_test_rehash_functionality() {
		try {
			// Insertion of three key value pairs
			htIntegerKey2.insert(1, "1");
			htIntegerKey2.insert(2, "2");
			htIntegerKey2.insert(3, "3");
			// This should trip up the rehash method because the load factor threshold is .5 for a table size of 5
			//and the table size will be now 11
			assertTrue(htIntegerKey2.getCapacity()==11);
		} catch (Exception e) {
			//An improper exception is thrown
			e.printStackTrace();
			fail("There is an improper exception that was thrown");
			
		}
	}
	/**
	 * This method tests the duplicate key exception
	 */
	@Test
	public void test011_test_duplicate_key_exception() {
		try { 
			htIntegerKey.insert(1, "1");
			//Inserting a duplicate key
			htIntegerKey.insert(1, "3");
			fail("the duplicate exception was not thrown");
		} catch(DuplicateKeyException e) {
			//The test passed when we have reached this point
		} catch (Exception e) {
			//An improper Exception was thrown.
			fail("The improper exception was thrown");
		}
	}
	/**
	 * This tests the remove function for when the key is null
	 */
	@Test
	public void test012_remove_key_null_empty_hashtable() {
		try {
			//We are removing a null key from an empty hash table
			htIntegerKey.remove(null);
			fail("This test did not throw an exception");
		} catch (IllegalNullKeyException e) {
			//This is when the test passes
		} catch (Exception e) {
			//This catches the improper exception
			e.printStackTrace();
			fail("An improper Exception was thrown");
		}
	}
	/**
	 * This test remove after null key after non-empty 
	 */
	@Test
	public void test013_remove_key_null_non_empty_table() {
		try {
			//Inserts one key
			htIntegerKey.insert(1,"1");
			//This then trys to remove a null key
			htIntegerKey.remove(null);
			fail("the test did not throw an exception");
		} catch(IllegalNullKeyException e) {
			//The test passed
		} catch (Exception e) {
			//An improper exception is thrown
			e.getMessage();
			fail("An improper Exception is thrown");
		}
	}
	/**
	 * Tests remove function for an empty array
	 */
	@Test
	public void test014_remove_key_empty_hash_table() {
		try {
			//This should return false because the table is empty
			assertTrue(!(htIntegerKey.remove(1)));
		}catch (Exception e) {
			//An improper exception was thrown
			e.printStackTrace();
			fail("There was an improper exception was thrown");
		}
	}
	/**
	 * This is a tests the remove function for a non-empty hash table when
	 * the value is not in the hash table
	 */
	@Test
	public void test015_remove_key_non_empty_non_existent() {
		try {
			htIntegerKey.insert(1, "1");
			assertTrue(!(htIntegerKey.remove(3)));
		}catch(Exception e) {
			e.printStackTrace();
			//Catches improper exception
			fail("There was an improper exc
