(COMPUNIT
 Program
 (FUNC
  _Imain_paai
  (SEQ
   (MOVE (TEMP args) (TEMP _ARG0))
   (MOVE (TEMP temp0) (CONST 1))
   (MOVE (TEMP temp1) (CONST 2))
   (EXP (CALL (NAME _If_t6iiiiiiii) (TEMP temp0) (TEMP temp1)))
   (MOVE (TEMP x2) (TEMP _RET1))
   (MOVE (TEMP x3) (TEMP _RET2))
   (MOVE (TEMP x4) (TEMP _RET3))
   (MOVE (TEMP x5) (TEMP _RET4))
   (MOVE (TEMP x6) (TEMP _RET5))
   (RETURN)))
 (FUNC
  _If_t6iiiiiiii
  (SEQ
   (MOVE (TEMP x1) (TEMP _ARG0))
   (MOVE (TEMP x2) (TEMP _ARG1))
   (MOVE (TEMP _RET0) (CONST 1))
   (MOVE (TEMP _RET1) (CONST 2))
   (MOVE (TEMP _RET2) (CONST 3))
   (MOVE (TEMP _RET3) (CONST 4))
   (MOVE (TEMP _RET4) (CONST 5))
   (MOVE (TEMP _RET5) (CONST 6))
   (RETURN))))
FUNC(Imain_paai):
	enter	$0 $0
	movq	%RDI %args
	movq	$1 %temp0
	movq	$2 %temp1
	pushq	%RAX
	pushq	%RCX
	pushq	%RDX
	pushq	%R8
	pushq	%R9
	pushq	%R10
	pushq	%R11
	movq	-8(%rsp) %rdi
	subq	$32 %rsp
	movq	%temp1 %RDX
	movq	%temp0 %RSI
	subq	$8 %rsp
	callq	FUNC(If_t6iiiiiiii)
	movq	%rdx %x2
	movq	0(%rdi) %x3
	movq	8(%rdi) %x4
	movq	16(%rdi) %x5
	movq	24(%rdi) %x6
	movq	48(%rsp) %RAX
	movq	40(%rsp) %RCX
	movq	32(%rsp) %RDX
	movq	24(%rsp) %R8
	movq	16(%rsp) %R9
	movq	8(%rsp) %R10
	movq	0(%rsp) %R11
	addq	$96 %rsp
FUNC(If_t6iiiiiiii):
	enter	$0 $0
	movq	%RSI %x1
	movq	%RDX %x2
	movq	$1 %rax
	movq	$2 %rdx
	movq	$3 0(%rdi)
	movq	$4 8(%rdi)
	movq	$5 16(%rdi)
	movq	$6 24(%rdi)
	leave
	ret
