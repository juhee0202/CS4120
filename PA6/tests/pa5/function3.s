(COMPUNIT
 Program
 (FUNC
  _Imain_paai
  (SEQ
   (MOVE (TEMP args) (TEMP _ARG0))
   (MOVE (TEMP x) (CONST 5))
   (MOVE (TEMP y) (CONST 6))
   (MOVE (TEMP temp0) ((((TEMP x) (TEMP y)) (CONST 3)) (TEMP y)))
   (EXP (CALL (NAME _If_pi) (TEMP temp0)))
   (RETURN)))
 (FUNC _If_pi (SEQ (MOVE (TEMP x) (TEMP _ARG0)) (RETURN))))
FUNC(Imain_paai):
	enter	$0 $0
	movq	%RDI %args
	movq	$5 %x
	movq	$6 %y
	movq	%x %tileRegister0
	addq	%y %tileRegister0
	movq	%tileRegister0 %tileRegister1
	addq	$3 %tileRegister1
	movq	%tileRegister1 %tileRegister2
	addq	%y %tileRegister2
	movq	%tileRegister2 %temp0
	pushq	%RAX
	pushq	%RCX
	pushq	%RDX
	pushq	%R8
	pushq	%R9
	pushq	%R10
	pushq	%R11
	movq	%temp0 %RDI
	subq	$8 %rsp
	callq	FUNC(If_pi)
	movq	48(%rsp) %RAX
	movq	40(%rsp) %RCX
	movq	32(%rsp) %RDX
	movq	24(%rsp) %R8
	movq	16(%rsp) %R9
	movq	8(%rsp) %R10
	movq	0(%rsp) %R11
	addq	$64 %rsp
	leave
	ret
FUNC(If_pi):
	enter	$0 $0
	movq	%RDI %x
	leave
	ret
