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
	movq	%rdi %args
	movq	$24 %temp0
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	%temp0 %rdi
	callq	FUNC(I_alloc_i)
	movq	%RAX %temp1
	movq	$48(%RSP) %rax
	movq	$40(%RSP) %rcx
	movq	$32(%RSP) %rdx
	movq	$24(%RSP) %r8
	movq	$16(%RSP) %r9
	movq	$8(%RSP) %r10
	movq	$0(%RSP) %r11
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
