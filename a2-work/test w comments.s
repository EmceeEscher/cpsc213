						//next chunk tests storing values in registers

ld $0x00000000, r0		//stores number of register as each register's values
ld $0x00000001, r1		//except for r7, which has 8, to make things easier to get
ld $0x00000002, r2		//into power of 4 format
ld $0x00000003, r3
ld $0x00000004, r4
ld $0x00000005, r5
ld $0x00000006, r6
ld $0x00000008, r7

						//next chunk tests the store and load functions

st r1, 4(r0) 			//should store 0x1 in mem[0x4] (0x4 = 0 + 4)
st r2, (r4,r3,4) 		//should store 0x2 in mem[0x10] (0x10 = 4 + 3*4)
ld 0(r4), r5			//r5 should now have 0x1 (from mem[0x4])
ld (r7,r2,4), r6		//r6 should now have 0x2 (from mem[0x10]) 
st r1, (r1,r0,4)		//should create invalid address message (not aligned)

						//next chunk tests moving, adding, incrementing, decrementing
						//not, and shifting, all with positive numbers

ld$0x00000011, r7		//changes r7 to 0x00000011
						//expected values:
mov r7, r0				//r0 = 0x11
add r1, r0				//r0 = 0x12
and r7, r0				//r0 = 0x10
inc r1					//r1 = 0x02
inca r1					//r1 = 0x06
dec r1					//r1 = 0x05
deca r1					//r1 = 0x01	
not r0					//r0 = 0xFFFFFFEF
shl $0x08, r2			//r2 = 0x0200
shr $0x04, r2			//r2 = 0x020

ld $0xFFFFFFFF, r5
						
						
						//next chunk tests increment/decrement when moving from
						//negative to positive and back
						//expected values:
inc r5					//r5 = 0x0
inc r5					//r5 = 0x01
dec r5					//r5 = 0x0
dec r5					//r5 = 0xFFFFFFFF
inca r5					//r5 = 0x03
deca r5					//r5 = 0xFFFFFFFF

						//next chunk tests increment/decrement and shifting 
						//for negative numbers
						//expected values:
dec r5					//r5 = 0xFFFFFFFE
deca r5					//r5 = 0xFFFFFFFA
inc r5					//r5 = 0xFFFFFFFB
inca r5					//r5 = 0xFFFFFFFF
shl $0x2, r5			//should set r5 to 0xFFFFFFFC
shr $0x1, r5			//should set r5 to 0xFFFFFFFE

ld $0x0000000D, r4
						//next chunk tests adding a positive and negative, and 
						//not-ing a negative.
						//expected values;
add r5, r4				//r4 = 0xB 
not r5					//r5 = 0x00000001

halt