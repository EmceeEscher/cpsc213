ld $0x00000000, r0		//stores number of register as each register's values
ld $0x00000001, r1		//except for r7, which has 8, to make things easier to get
ld $0x00000002, r2		//into power of 4 format
ld $0x00000003, r3
ld $0x00000004, r4
ld $0x00000005, r5
ld $0x00000006, r6
ld $0x00000008, r7

st r1, 4(r0) 			//should store 0x1 in mem[0x4] (0x4 = 0 + 4)
st r2, (r4,r3,4) 		//should store 0x2 in mem[0x10] (0x10 = 4 + 3*4)
ld 0(r4), r5			//r5 should now have 0x1 (from mem[0x4])
ld (r7,r2,4), r6		//r6 should now have 0x2 (from mem[0x10]) 