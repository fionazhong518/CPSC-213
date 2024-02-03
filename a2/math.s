.pos 0x100
                 ld $t, r0              # r0 = address of t
                 ld (r0), r1            # r1 = t
                 inc r1                 # r1 = r1 + 1
                 inca r1                # r1 = r1 + 4
                 shl $0x8, r1           # t = t << 8
                 ld (r0), r2            # r2 = address of t
                 and r2, r1             # r1 = r1 & t
                 shr $0x2, r1           # r1 = t >> 2
                 ld $m, r3              # r3 = address of m
                 st r1, (r3)            # m = r0
                 halt                   # halt

.pos 0x1000
m:               .long 0x00000000        # m

.pos 0x2000
t:               .long 0xfffffffa        # t