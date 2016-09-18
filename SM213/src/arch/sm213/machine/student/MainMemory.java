package arch.sm213.machine.student;

import machine.AbstractMainMemory;


/**
 * Main Memory of Simple CPU.
 *
 * Provides an abstraction of main memory (DRAM).
 */

public class MainMemory extends AbstractMainMemory {
  private byte [] mem;
  
  /**
   * Allocate memory.
   * @param byteCapacity size of memory in bytes.
   */
  public MainMemory (int byteCapacity) {
    mem = new byte [byteCapacity];
  }
  
  /**
   * Determine whether an address is aligned to specified length.
   * @param address memory address.
   * @param length byte length.
   * @return true iff address is aligned to length.
   */
  @Override protected boolean isAccessAligned (int address, int length) {
    return (address % length == 0);
  }
  
  /**
   * Convert an sequence of four bytes into a Big Endian integer.
   * @param byteAtAddrPlus0 value of byte with lowest memory address (base address).
   * @param byteAtAddrPlus1 value of byte at base address plus 1.
   * @param byteAtAddrPlus2 value of byte at base address plus 2.
   * @param byteAtAddrPlus3 value of byte at base address plus 3 (highest memory address).
   * @return Big Endian integer formed by these four bytes.
   */
  @Override public int bytesToInteger (byte byteAtAddrPlus0, byte byteAtAddrPlus1, byte byteAtAddrPlus2, byte byteAtAddrPlus3) {
    byte[] mem = new byte[4];
    mem[0] = byteAtAddrPlus0;
    mem[1] = byteAtAddrPlus1;
    mem[2] = byteAtAddrPlus2;
    mem[3] = byteAtAddrPlus3;
    
    /*
     * I have copied my implementation of big-Endian conversion from
     * Endianness.java. I copied it instead of calling the function from Endianness
     * directly, because I wasn't sure how all of my files would be 
     * arranged when I turned in my work through handin, and I was
     * worried that it wouldn't be able to find the Endianness file
     * if I tried to reference it directly.
     */
    int byte0 = mem[0] << 6*4;
	int byte1 = (mem[1] & 0xFF) << 4*4;
	int byte2 = (mem[2] & 0xFF) << 2*4;
	int byte3 = (mem[3] & 0xFF);
	return byte0+byte1+byte2+byte3;
  }
  
  /**
   * Convert a Big Endian integer into an array of 4 bytes organized by memory address.
   * @param  i an Big Endian integer.
   * @return an array of byte where [0] is value of low-address byte of the number etc.
   */
  @Override public byte[] integerToBytes (int i) {
    // TODO
    return null;
  }
  
  /**
   * Fetch a sequence of bytes from memory.
   * @param address address of the first byte to fetch.
   * @param length  number of bytes to fetch.
   * @throws InvalidAddressException  if any address in the range address to address+length-1 is invalid.
   * @return an array of byte where [0] is memory value at address, [1] is memory value at address+1 etc.
   */
  @Override protected byte[] get (int address, int length) throws InvalidAddressException {
    if(address < 0 || (address+length-1) > (mem.length-1)){
    	throw new InvalidAddressException();
    }
    byte[] values = new byte[length];
    for(int i = 0; i < length; i++){
    	values[i] = mem[address+i];
    }
    return values;
  }
  
  /**
   * Store a sequence of bytes into memory.
   * @param  address                  address of the first byte in memory to recieve the specified value.
   * @param  value                    an array of byte values to store in memory at the specified address.
   * @throws InvalidAddressException  if any address in the range address to address+value.length-1 is invalid.
   */
  @Override protected void set (int address, byte[] value) throws InvalidAddressException {
	if(address < 0 || (address+value.length-1) > (mem.length-1)){
	    	throw new InvalidAddressException();
	}
	for(int i = 0; i < value.length; i++){
		mem[address+i] = value[i];
	}
  }
  
  /**
   * Determine the size of memory.
   * @return the number of bytes allocated to this memory.
   */
  @Override public int length () {
    return mem.length;
  }
}
