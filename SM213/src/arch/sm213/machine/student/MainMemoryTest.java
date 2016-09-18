package arch.sm213.machine.student;

import static org.junit.Assert.*;

import org.junit.Test;

import machine.AbstractMainMemory.InvalidAddressException;

public class MainMemoryTest {
	int byteCapacity = 100;
	MainMemory test = new MainMemory(byteCapacity);
	byte byte00 = (byte) 0x00;
	byte byteFF = (byte) 0xFF;
	byte byte7F = (byte) 0x7F;
	byte byte80 = (byte) 0x80;
	byte byte01 = (byte) 0x01;
	byte byteAB = (byte) 0xAB;
	byte byteBA = (byte) 0xBA;
	
	/**
	 * test isAligned function.
	 * Test cases include: 
	 * -cases where access is aligned
	 * -cases where it isn't
	 * 		-both previous cases are tested with the same values for address and length
	 * 		 but in different situations
	 * -cases involving the boundaries (0 and maximum memory capacity)
	 * 
	 */
	@Test
	public void testIsAligned(){
		assertTrue(test.isAccessAligned(8, 4));
		assertFalse(test.isAccessAligned(8, 6));
		assertTrue(test.isAccessAligned(12, 6));
		assertTrue(test.isAccessAligned(1, 1));
		assertTrue(test.isAccessAligned(0, 71));
		assertTrue(test.isAccessAligned(byteCapacity, 1));
		assertTrue(test.isAccessAligned(byteCapacity, byteCapacity));
	}
	
	/**
	 * The following tests test the bytesToInteger function.
	 * The tests are subset of the tests I used in part 4.
	 * They include:
	 * - boundaries (max. positive number, min. negative number)
	 * - 0, -1, 1
	 * - a random positive and a random negative value
	 */
	@Test
	public void testByteToInt(){
		assertEquals(test.bytesToInteger(byte00, byte00, byte00, byte00), 0);
		assertEquals(test.bytesToInteger(byte7F, byteFF, byteFF, byteFF), 2147483647);
		assertEquals(test.bytesToInteger(byte80, byte00, byte00, byte00), -2147483648);
		assertEquals(test.bytesToInteger(byte01, byteAB, byteAB, byte01), 28027649);
		assertEquals(test.bytesToInteger(byte80, byteBA, byteBA, byte80), -2135246208);
		assertEquals(test.bytesToInteger(byteFF, byteFF, byteFF, byteFF), -1);
		assertEquals(test.bytesToInteger(byte00, byte00, byte00, byte01), 1);
	}
	
	/**
	 * The next block tests the get and set methods.
	 * Tests:
	 * - setting values out-of-bounds (both below and above) and 
	 *   looks for an InvalidAddressException. Tests for both partially
	 *   out-of-bounds and full out-of-bounds
	 * - tries setting arrays of differing lengths at the beginning,
	 *   middle, and end of the memory, and ensuring that the arrays
	 *   retrieved by the get method are the same
	 * - tries to get an array of length 0, expects an empty array of length 0
	 */
	@Test
	public void testGetSet(){
		byte[] test3 = {byte00, byte7F, byteFF};
		byte[] test5 = {byte00, byte7F, byteFF, byteAB, byteBA};
		try{
			test.set(100, test3);
			assertTrue(false);
		}catch(InvalidAddressException e){
			assertTrue(true);
		}
		try{
			test.set(-1, test3);
			assertTrue(false);
		}catch(InvalidAddressException e){
			assertTrue(true);
		}
		try{
			test.set(-7, test3);
			assertTrue(false);
		}catch(InvalidAddressException e){
			assertTrue(true);
		}
		try{
			test.set(96, test5);
			assertTrue(false);
		}catch(InvalidAddressException e){
			assertTrue(true);
		}
		try{
			test.set(0, test3);
			assertArrayEquals(test3, test.get(0, 3));
			test.set(95, test5);
			assertArrayEquals(test3, test.get(95, 3));
			assertArrayEquals(test5, test.get(95, 5));
			test.set(63, test3);
			assertArrayEquals(test3, test.get(63, 3));
			test.set(64, test5);
			assertArrayEquals(test5, test.get(64, 5));
			assertArrayEquals(new byte[0], test.get(7, 0));
		}catch(InvalidAddressException e){
			assertTrue(false);
		}
	}
	
	/**
	 * This next test set tests the integer to bytes conversion.
	 * The test suite is the same as for the byte to integer conversion.
	 * Including:
	 * - 0
	 * - Integer.MAX_VALUE and Integer.MIN_VALUE (boundary conditions)
	 * - 1 and -1
	 * - arbitrary positive and negative values
	 */
	
	@Test
	public void testIntToBytes(){
		byte[] test0 = {byte00, byte00, byte00, byte00};
		byte[] testPosMax = {byte7F, byteFF, byteFF, byteFF};
		byte[] testNegMin = {byte80, byte00, byte00, byte00};
		byte[] test1 = {byte00, byte00, byte00, byte01};
		byte[] testMinus1 = {byteFF, byteFF, byteFF, byteFF};
		byte[] testPosValue = {byte01, byteAB, byteAB, byte01};
		byte[] testNegValue = {byte80, byteBA, byteBA, byte80};
		
		assertArrayEquals(test0, test.integerToBytes(0));
		assertArrayEquals(testPosMax, test.integerToBytes(Integer.MAX_VALUE));
		assertArrayEquals(testNegMin, test.integerToBytes(Integer.MIN_VALUE));
		assertArrayEquals(test1, test.integerToBytes(1));
		assertArrayEquals(testMinus1, test.integerToBytes(-1));
		assertArrayEquals(testPosValue, test.integerToBytes(28027649));
		assertArrayEquals(testNegValue, test.integerToBytes(-2135246208));
	}
}
