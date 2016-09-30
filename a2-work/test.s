
ld $0x00000000, r0
ld $0x00000001, r1
ld $0x00000002, r2
ld $0x00000003, r3
ld $0x00000004, r4
ld $0x00000005, r5
ld $0x00000006, r6
ld $0x00000008, r7

st r1, 4(r0)
st r2, (r4,r3,4)
ld 0(r4), r5
ld (r7,r2,4), r6
st r1, (r1,r0,4)

ld$0x00000011, r7

mov r7, r0
add r1, r0
and r7, r0
inc r1
inca r1
dec r1
deca r1	
not r0
shl $0x08, r2
shr $0x04, r2

ld $0xFFFFFFFF, r5

inc r5
inc r5
dec r5
dec r5
inca r5
deca r5

dec r5
deca r5
inc r5
inca r5					
shl $0x2, r5
shr $0x1, r5

ld $0x0000000D, r4

add r5, r4
not r5

halt