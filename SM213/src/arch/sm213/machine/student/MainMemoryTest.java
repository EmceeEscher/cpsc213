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
	
	@Test
	public void testGetSet(){
		byte[] test3 = {byte00, byte7F, byteFF};
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
			test.set(0, test3);
			assertTrue(true);
			byte[] result = test.get(0, 3);
			assertArrayEquals(test3, test.get(0, 3));
		}catch(InvalidAddressException e){
			assertTrue(false);
		}	
	}
}
