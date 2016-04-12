main(args: int[][]) {
  x: int[] = {1,2};
}

(COMPUNIT
 Program
 (FUNC
  _Imain_paai
  (SEQ
   (MOVE (TEMP args) (TEMP _ARG0))
   (MOVE (TEMP temp0) (CONST 24))
   (MOVE (TEMP temp1) (CALL (NAME _I_alloc_i) (TEMP temp0)))
   (MOVE (TEMP t0) (TEMP temp1))
   (MOVE (MEM (TEMP t0)) (CONST 2))
   (MOVE (MEM ((CONST 8) (TEMP t0))) (CONST 1))
   (MOVE (MEM ((CONST 16) (TEMP t0))) (CONST 2))
   (MOVE (TEMP t0) ((TEMP t0) (CONST 8)))
   (MOVE (TEMP x) (TEMP t0))
   (RETURN))))