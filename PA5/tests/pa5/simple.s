(COMPUNIT
 Program
 (FUNC
  _Imain_paai
  (SEQ
   (MOVE (TEMP args) (TEMP _ARG0))
   (MOVE (TEMP x) (CONST 5))
   (MOVE (TEMP temp0) (TEMP x))
   (MOVE (TEMP temp1) (CALL (NAME _If_ii) (TEMP temp0)))
   (MOVE (TEMP y) (TEMP temp1))
   (RETURN)))
 (FUNC
  _If_ii
  (SEQ
   (MOVE (TEMP x) (TEMP _ARG0))
   (MOVE (TEMP _RET0) ((TEMP x) (CONST 1)))
   (RETURN))))
FUNC(Imain_paai):
	enter	$0 $0
	movq	%rdi %args
	movq	$5 %x
	movq	%x %temp0
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	%temp0 %rdi
	callq	FUNC(If_ii)
	movq	%rax %temp1
	movq	$48(%rsp) %rax
	movq	$40(%rsp) %rcx
	movq	$32(%rsp) %rdx
	movq	$24(%rsp) %r8
	movq	$16(%rsp) %r9
	movq	$8(%rsp) %r10
	movq	$0(%rsp) %r11
	addq	$56 %rsp
	movq	%temp1 %y
	leave
	ret
FUNC(If_ii):
	enter	$0 $0
	movq	%rdi %x
	addq	$1 %x
	movq	%x %_RET0
	leave
	ret
