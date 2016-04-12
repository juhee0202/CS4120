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
    (CALL (NAME _If_t2iiiiiiiiii) (TEMP temp0) (TEMP temp1) (TEMP temp2)
     (TEMP temp3) (TEMP temp4) (TEMP temp5) (TEMP temp6) (TEMP temp7)))
   (MOVE (TEMP x1) (TEMP _RET0))
   (MOVE (TEMP x2) (TEMP _RET1))
   (RETURN)))
 (FUNC
  _If_t2iiiiiiiiii
  (SEQ
   (MOVE (TEMP a1) (TEMP _ARG0))
   (MOVE (TEMP a2) (TEMP _ARG1))
   (MOVE (TEMP a3) (TEMP _ARG2))
   (MOVE (TEMP a4) (TEMP _ARG3))
   (MOVE (TEMP a5) (TEMP _ARG4))
   (MOVE (TEMP a6) (TEMP _ARG5))
   (MOVE (TEMP a7) (TEMP _ARG6))
   (MOVE (TEMP a8) (TEMP _ARG7))
   (MOVE (TEMP _RET0) (TEMP a1))
   (MOVE (TEMP _RET1) (TEMP a2))
   (RETURN))))
	.text
	.global	FUNC(Imain_paai)
	.align	4
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
	pushq	%temp6
	movq	%temp5 %R9
	movq	%temp4 %R8
	movq	%temp3 %RCX
	movq	%temp2 %RDX
	movq	%temp1 %RSI
	movq	%temp0 %RDI
	subq	$8 %rsp
	callq	FUNC(If_t2iiiiiiiiii)
	movq	%rax %x1
	movq	%rdx %x2
	movq	64(%rsp) %RAX
	movq	56(%rsp) %RCX
	movq	48(%rsp) %RDX
	movq	40(%rsp) %R8
	movq	32(%rsp) %R9
	movq	24(%rsp) %R10
	movq	16(%rsp) %R11
	addq	$80 %rsp
	leave
	ret

	.global	FUNC(If_t2iiiiiiiiii)
	.align	4
FUNC(If_t2iiiiiiiiii):
	enter	$0 $0
	movq	%RDI %a1
	movq	%RSI %a2
	movq	%RDX %a3
	movq	%RCX %a4
	movq	%R8 %a5
	movq	%R9 %a6
	movq	16(%rbp) %a7
	movq	24(%rbp) %a8
	movq	%a1 %rax
	movq	%a2 %rdx
	leave
	ret
