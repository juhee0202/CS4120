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
FUNC(Imain_paai):
	enter	$0 $0
	movq	%RDI %args
	movq	$24 %temp0
	pushq	%RAX
	pushq	%RCX
	pushq	%RDX
	pushq	%R8
	pushq	%R9
	pushq	%R10
	pushq	%R11
	movq	%temp0 %RDI
	callq	FUNC(I_alloc_i)
	movq	%RAX %temp1
	movq	$48(%RSP) %RAX
	movq	$40(%RSP) %RCX
	movq	$32(%RSP) %RDX
	movq	$24(%RSP) %R8
	movq	$16(%RSP) %R9
	movq	$8(%RSP) %R10
	movq	$0(%RSP) %R11
	addq	$56 %RSP
	movq	%temp1 %t0
	movq	$2 %t0
	movq	$8 %tileRegister0
	addq	%t0 %tileRegister0
	movq	$1 %tileRegister0
	movq	$16 %tileRegister1
	addq	%t0 %tileRegister1
	movq	$2 %tileRegister1
	movq	%t0 %tileRegister2
	addq	$8 %tileRegister2
	movq	%tileRegister2 %t0
	movq	%t0 %x
	leave
	ret
