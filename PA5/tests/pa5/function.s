(COMPUNIT
 Program
 (FUNC
  _Imain_paai
  (SEQ
   (MOVE (TEMP args) (TEMP _ARG0))
   (MOVE (TEMP x) (CONST 5))
   (MOVE (TEMP y) (CONST 6))
   (MOVE (TEMP temp0) (((TEMP x) (TEMP y)) (CONST 3)))
   (EXP (CALL (NAME _If_pi) (TEMP temp0)))
   (RETURN)))
 (FUNC _If_pi (SEQ (MOVE (TEMP x) (TEMP _ARG0)) (RETURN))))
FUNC(Imain_paai):
	enter	$0 $0
	movq	%RDI %args
	movq	$5 %x
	movq	$6 %y
	movq	%tileRegister0 %tileRegister1
	addq	$3 %tileRegister1
	movq	%tileRegister1 %temp0
	pushq	%RAX
	pushq	%RCX
	pushq	%RDX
	pushq	%R8
	pushq	%R9
	pushq	%R10
	pushq	%R11
	movq	%temp0 %RDI
	callq	FUNC(If_pi)
	movq	48(%RSP) %RAX
	movq	40(%RSP) %RCX
	movq	32(%RSP) %RDX
	movq	24(%RSP) %R8
	movq	16(%RSP) %R9
	movq	8(%RSP) %R10
	movq	0(%RSP) %R11
	addq	$56 %RSP
	leave
	ret
FUNC(If_pi):
	enter	$0 $0
	movq	%RDI %x
	leave
	ret
