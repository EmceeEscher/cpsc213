package arch.sm213.machine.student;

import static org.junit.Assert.*;

import org.junit.Test;

public class MainMemoryTest {
	int byteCapacity = 100;
	MainMemory test = new MainMemory(byteCapacity);
	
	//testing isAligned
	@Test
	public void testIsAligned(){
		assertTrue(test.isAccessAligned(8, 4));
		assertFalse(test.isAccessAligned(7, 3));
		assertTrue(test.isAccessAligned(1, 1));
		assertTrue(test.isAccessAligned(0, 71));
		assertTrue(test.isAccessAligned(byteCapacity, 1));
		assertTrue(test.isAccessAligned(byteCapacity, byteCapacity));
	}
}
