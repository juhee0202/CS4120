(COMPUNIT
 Program
 (FUNC
  _Imain_paai
  (SEQ
   (MOVE (TEMP args) (TEMP _ARG0))
   (MOVE (TEMP x) (CONST 5))
   (MOVE (TEMP temp0) (TEMP x))
   (EXP (CALL (NAME _If1_t2iii) (TEMP temp0)))
   (MOVE (TEMP y1) (TEMP _RET0))
   (MOVE (TEMP y2) (TEMP _RET1))
   (MOVE (TEMP temp2) (TEMP y1))
   (MOVE (TEMP temp3) (TEMP y2))
   (EXP (CALL (NAME _If2_t4iiiiii) (TEMP temp2) (TEMP temp3)))
   (MOVE (TEMP z1) (TEMP _RET0))
   (MOVE (TEMP z2) (TEMP _RET1))
   (MOVE (TEMP z3) (TEMP _RET2))
   (MOVE (TEMP z4) (TEMP _RET3))
   (RETURN)))
 (FUNC
  _If1_t2iii
  (SEQ
   (MOVE (TEMP x) (TEMP _ARG0))
   (CJUMP ((TEMP x) (CONST 3)) l0)
   (LABEL l1)
   (MOVE (TEMP _RET0) (CONST 5))
   (MOVE (TEMP _RET1) (CONST 6))
   (RETURN)
   (LABEL l0)
   (MOVE (TEMP _RET0) (CONST 3))
   (MOVE (TEMP _RET1) (CONST 1))
   (RETURN)))
 (FUNC
  _If2_t4iiiiii
  (SEQ
   (MOVE (TEMP x) (TEMP _ARG0))
   (MOVE (TEMP y) (TEMP _ARG1))
   (MOVE (TEMP _RET0) (TEMP x))
   (MOVE (TEMP _RET1) (TEMP y))
   (MOVE (TEMP _RET2) ((TEMP x) (TEMP y)))
   (MOVE (TEMP _RET3) (((TEMP x) (TEMP y)) (CONST 3)))
   (RETURN))))
FUNC(Imain_paai):
	enter	$0 $0
	movq	%RDI %args
	movq	$5 %x
	movq	%x %temp0
	pushq	%RAX
	pushq	%RCX
	pushq	%RDX
	pushq	%R8
	pushq	%R9
	pushq	%R10
	pushq	%R11
	movq	%temp0 %RDI
	subq	$8 %rsp
	callq	FUNC(If1_t2iii)
	movq	48(%rsp) %RAX
	movq	40(%rsp) %RCX
	movq	32(%rsp) %RDX
	movq	24(%rsp) %R8
	movq	16(%rsp) %R9
	movq	8(%rsp) %R10
	movq	0(%rsp) %R11
	addq	$64 %rsp
	movq	%rax %y1
	movq	%rdx %y2
	movq	%y1 %temp2
	movq	%y2 %temp3
	pushq	%RAX
	pushq	%RCX
	pushq	%RDX
	pushq	%R8
	pushq	%R9
	pushq	%R10
	pushq	%R11
	movq	%temp3 %RSI
	movq	%temp2 %RDI
	subq	$8 %rsp
	callq	FUNC(If2_t4iiiiii)
	movq	48(%rsp) %RAX
	movq	40(%rsp) %RCX
	movq	32(%rsp) %RDX
	movq	24(%rsp) %R8
	movq	16(%rsp) %R9
	movq	8(%rsp) %R10
	movq	0(%rsp) %R11
	addq	$64 %rsp
	movq	%rax %z1
	movq	%rdx %z2
	movq	0(%rdi) %z3
	movq	8(%rdi) %z4
	leave
	ret
FUNC(If1_t2iii):
	enter	$0 $0
	movq	%RDI %x
	cmpq	$3 %x
	jl	l0
l1:
	movq	$5 %rax
	movq	$6 %rdx
	leave
	ret
l0:
	movq	$3 %rax
	movq	$1 %rdx
	leave
	ret
FUNC(If2_t4iiiiii):
	enter	$0 $0
	movq	%RSI %x
	movq	%RDX %y
	movq	%x %rax
	movq	%y %rdx
	movq	%x %tileRegister0
	addq	%y %tileRegister0
	movq	%tileRegister0 0(%rdi)
	movq	%tileRegister1 %tileRegister2
	addq	$3 %tileRegister2
	movq	%tileRegister2 8(%rdi)
	leave
	ret
