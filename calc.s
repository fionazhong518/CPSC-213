.pos 0x100
                ld $z, r0               #r0 = address of z
                ld (r0), r0             #r0 = value of z
                ld $u, r1               #r1 = address of u
                #ld (r1), r1             #r1 = value of u
                ld $c, r2               #r2 = address of c
                #ld (r2), r7             #r2 = value of c
                ld $b, r3               #r3 = address of b[0]

                ld (r3, r0, 4), r4      #r4 = b[z]
                inc r0                  #r0 = z + 1
                inc r0                  #r0 = z + 2
                ld (r3, r0, 4), r5      #r5 = b[z+2].....
                add r4, r5
                #add 0x0(r1), 0x8(r1)              #r5 = b[z] + b[z+2].....
                st r5, 0x0(r2)             #r2 = c = b[z] + b[z+2]
                ld $0xf0, r6            #r0 = 0xf0
                and r6, r5              #r0 = c & 0xf0
                st r5, 0x0(r1)             #r1 = u = c & 0xf0
                halt                    #halt

.pos 0x1000
z:               .long 0x00000003         # z
u:               .long 0x00000006         # u
c:               .long 0x00000007         # c
.pos 0x2000
b:               .long 0x00000004         # b[0]
                 .long 0x00000000         # b[1]
                 .long 0x00000003         # b[2]
                 .long 0x00000010         # b[3]
                 .long 0xffffffff         # b[4]
                 .long 0x00000004         # b[5]
                 .long 0xffffffff         # b[6]
                 .long 0x00000001         # b[7]
