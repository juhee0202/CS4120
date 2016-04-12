(COMPUNIT
 Program
 (FUNC
  _Imain_paai
  (SEQ
   (MOVE (TEMP args) (TEMP _ARG0))
   (MOVE (TEMP temp0) (CONST 1))
   (MOVE (TEMP temp1) (CONST 2))
   (MOVE (TEMP temp2) (CONST 3))
   (MOVE (TEMP temp3) (CONST 4))
   (MOVE (TEMP temp4) (CONST 5))
   (MOVE (TEMP temp5) (CONST 6))
   (MOVE (TEMP temp6) (CONST 7))
   (MOVE (TEMP temp7) (CONST 8))
   (EXP
    (CALL (NAME _If_piiiiiiii) (TEMP temp0) (TEMP temp1) (TEMP temp2)
     (TEMP temp3) (TEMP temp4) (TEMP temp5) (TEMP temp6) (TEMP temp7)))
   (RETURN)))
 (FUNC
  _If_piiiiiiii
  (SEQ
   (MOVE (TEMP x1) (TEMP _ARG0))
   (MOVE (TEMP x2) (TEMP _ARG1))
   (MOVE (TEMP x3) (TEMP _ARG2))
   (MOVE (TEMP x4) (TEMP _ARG3))
   (MOVE (TEMP x5) (TEMP _ARG4))
   (MOVE (TEMP x6) (TEMP _ARG5))
   (MOVE (TEMP x7) (TEMP _ARG6))
   (MOVE (TEMP x8) (TEMP _ARG7))
   (RETURN))))
FUNC(Imain_paai):
	enter	$0 $0
	movq	%RDI %args
	movq	$1 %temp0
	movq	$2 %temp1
	movq	$3 %temp2
	movq	$4 %temp3
	movq	$5 %temp4
	movq	$6 %temp5
	movq	$7 %temp6
	movq	$8 %temp7
	pushq	%RAX
	pushq	%RCX
	pushq	%RDX
	pushq	%R8
	pushq	%R9
	pushq	%R10
	pushq	%R11
	pushq	%temp7
	movq	%temp5 %R9
	movq	%temp4 %R8
	movq	%temp3 %RCX
	movq	%temp2 %RDX
	movq	%temp1 %RSI
	movq	%temp0 %RDI
	callq	FUNC(If_piiiiiiii)
	movq	64(%rsp) %RAX
	movq	56(%rsp) %RCX
	movq	48(%rsp) %RDX
	movq	40(%rsp) %R8
	movq	32(%rsp) %R9
	movq	24(%rsp) %R10
	movq	16(%rsp) %R11
	addq	$0 %rsp
	leave
	ret
FUNC(If_piiiiiiii):
	enter	$0 $0
	movq	%RDI %x1
	movq	%RSI %x2
	movq	%RDX %x3
	movq	%RCX %x4
	movq	%R8 %x5
	movq	%R9 %x6
	movq	16(%rbp) %x7
	movq	24(%rbp) %x8
	leave
	ret
