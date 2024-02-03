.pos 0x100
                ld $t, r0           #r0 = address of t
                ld $array, r1       #r1 = address of array
                ld $0x7, r2         #r2 = 7
                ld $0x8, r3         #r3 = 8
                ld (r1, r2, 4), r4  #r4 = array[7]
                ld (r1, r3, 4), r5  #r5 = array[8]
                st r4, (r0)         #t = array[7]

                ld $array, r1       #r1 = address of array
                ld 0x1c(r1), r4     #r4 = address of array[7]
                ld 0x20(r1), r5     #r5 = address of array[8]
                st r5, 0x1c(r1)     #array[7] = array[8]

                ld $t, r0           # r0 = address of t
                ld (r0), r2         # r2 = t
                ld $array, r1       # r1 = address of array
                st r2, 0x20(r1)     #array[8] = t
                halt                #halt

.pos 0x1000
t:               .long 0x00000000         # t
.pos 0x2000
array:           .long 0xffffffff         # array[0]
                 .long 0xffffffff         # array[1]
                 .long 0xffffffff         # array[2]
                 .long 0xffffffff         # array[3]
                 .long 0xffffffff         # array[4]
                 .long 0xffffffff         # array[5]
                 .long 0xffffffff         # array[6]
                 .long 0x00000001         # array[7]
                 .long 0x00000002         # array[8]
                 .long 0xffffffff         # array[9]



