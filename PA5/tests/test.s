(COMPUNIT
 Program
 (FUNC _If2_i (SEQ (MOVE (TEMP _RET0) (CONST 2)) (RETURN)))
 (FUNC
  _Ifail_on_bad_decl_p
  (SEQ
   (MOVE (TEMP temp0) (CONST 32))
   (MOVE (TEMP temp1) (CALL (NAME _I_alloc_i) (TEMP temp0)))
   (MOVE (TEMP t0) (TEMP temp1))
   (MOVE (MEM (TEMP t0)) (CONST 3))
   (MOVE (MEM (ADD (CONST 8) (TEMP t0))) (CONST 1))
   (MOVE (MEM (ADD (CONST 16) (TEMP t0))) (CONST 2))
   (MOVE (MEM (ADD (CONST 24) (TEMP t0))) (CONST 3))
   (MOVE (TEMP t0) (ADD (TEMP t0) (CONST 8)))
   (MOVE (TEMP x) (TEMP t0))
   (RETURN)))
 (FUNC _Ifoo_p (SEQ (LABEL _Ifoo_p) (RETURN)))
 (FUNC _If3_ii
  (SEQ (MOVE (TEMP x) (TEMP _ARG0)) (MOVE (TEMP _RET0) (CONST 2)) (RETURN))))
	.text
	.global	FUNC(If2_i)
	.align	4
FUNC(If2_i):
	enter	$0 $0
	movq	$2 %rax
	leave
	ret

	.global	FUNC(Ifail_on_bad_decl_p)
	.align	4
FUNC(Ifail_on_bad_decl_p):
	enter	$0 $0
	movq	$32 %temp0
	pushq	%RAX
	pushq	%RCX
	pushq	%RDX
	pushq	%R8
	pushq	%R9
	pushq	%R10
	pushq	%R11
	movq	%temp0 %RDI
	subq	$8 %rsp
	callq	FUNC(I_alloc_i)
	movq	%rax %temp1
	movq	48(%rsp) %RAX
	movq	40(%rsp) %RCX
	movq	32(%rsp) %RDX
	movq	24(%rsp) %R8
	movq	16(%rsp) %R9
	movq	8(%rsp) %R10
	movq	0(%rsp) %R11
	addq	$64 %rsp
	movq	%temp1 %t0
	movq	$3 (%t0)
	movq	$8 %tileRegister0
	addq	%t0 %tileRegister0
	movq	$1 (%tileRegister0)
	movq	$16 %tileRegister1
	addq	%t0 %tileRegister1
	movq	$2 (%tileRegister1)
	movq	$24 %tileRegister2
	addq	%t0 %tileRegister2
	movq	$3 (%tileRegister2)
	movq	%t0 %tileRegister3
	addq	$8 %tileRegister3
	movq	%tileRegister3 %t0
	movq	%t0 %x
	leave
	ret

	.global	FUNC(Ifoo_p)
	.align	4
FUNC(Ifoo_p):
	enter	$0 $0
_Ifoo_p:
	leave
	ret

	.global	FUNC(If3_ii)
	.align	4
FUNC(If3_ii):
	enter	$0 $0
	movq	%RDI %x
	movq	$2 %rax
	leave
	ret
