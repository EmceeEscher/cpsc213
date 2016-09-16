import static java.lang.System.out;

public class Endianness {

  public static int bigEndianValue (Byte[] mem) {
    int byte1 = mem[0].intValue() * 0x01000000;
    int byte2 = mem[1].intValue() * 0x00010000;
    int byte3 = mem[2].intValue() * 0x00000100;
    int byte4 = mem[3].intValue() * 0x00000001;
    return byte1+byte2+byte3+byte4;
  }
  
  public static int littleEndianValue (Byte[] mem) {
	//String byte0str = mem[0].toString();
	//int byte0 = Integer.parseInt(byte0str, 16);
	//byte0 = mem[0].intValue() * 0x00000001;
	int byte0 = mem[0].intValue() * 0x00000001;
	int byte1 = mem[1].intValue() * 0x00000100;
	int byte2 = mem[2].intValue() * 0x00010000;
	int byte3 = mem[3].intValue() * 0x01000000;
    return byte0+byte1+byte2+byte3;
  }
  
  public static void main (String[] args) {
    Byte mem[] = new Byte[4];
    try {
      for (int i=0; i<4; i++)
        mem [i] = Integer.valueOf (args[i], 16) .byteValue();
    } catch (Exception e) {
      out.printf ("usage: java Endianness n0 n1 n2 n3\n");
      out.printf ("where: n0..n3 are byte values in memory at addresses 0..3 respectively, in hex (no 0x).\n");
      return;
    }
  
    int bi = bigEndianValue    (mem);
    int li = littleEndianValue (mem);
    
    out.printf ("Memory Contents\n");
    out.printf ("  Addr   Value\n");
    for (int i=0; i<4; i++)
      out.printf ("  %3d:   0x%-5x\n", i, mem[i]);
    out.printf ("The big    endian integer value at address 0 is %d\n", bi);
    out.printf ("The little endian integer value at address 0 is %d\n", li);
  }
}