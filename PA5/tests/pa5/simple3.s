	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$0, $0
	movq	%rdi, %args
	movq	$8, %temp0
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	%temp0, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %temp1
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	%temp1, %t0
	movq	$0, (%t0)
	movq	%t0, %tileRegister0
	addq	$8, %tileRegister0
	movq	%tileRegister0, %t0
	movq	%t0, %x
	movq	$16, %temp2
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	%temp2, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %temp3
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	%temp3, %t1
	movq	$1, (%t1)
	movq	$8, %tileRegister1
	addq	%t1, %tileRegister1
	movq	$97, (%tileRegister1)
	movq	%t1, %tileRegister2
	addq	$8, %tileRegister2
	movq	%tileRegister2, %t1
	movq	%t1, %y
	movq	%x, %tileRegister3
	subq	$8, %tileRegister3
	movq	%y, %tileRegister4
	subq	$8, %tileRegister4
	movq	(%tileRegister3), %tileRegister5
	addq	(%tileRegister4), %tileRegister5
	movq	%tileRegister5, %t2
	movq	%t2, %tileRegister6
	addq	$1, %tileRegister6
	movq	%tileRegister6, %tileRegister7
	imulq	$8, %tileRegister7
	movq	%tileRegister7, %temp4
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	%temp4, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %temp5
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	%temp5, %t3
	movq	%t2, (%t3)
	movq	%t3, %tileRegister8
	addq	$8, %tileRegister8
	movq	%tileRegister8, %t3
	movq	$0, %t4
l0:
	movq	%x, %tileRegister9
	subq	$8, %tileRegister9
	movq	(%tileRegister9), %tileRegister10
	cmpq	%tileRegister10, %t4
	jl	l1
l2:
	movq	$0, %t5
	movq	%x, %tileRegister11
	subq	$8, %tileRegister11
	movq	(%tileRegister11), %t6
l3:
	movq	%y, %tileRegister12
	subq	$8, %tileRegister12
	movq	(%tileRegister12), %tileRegister13
	cmpq	%tileRegister13, %t5
	jl	l4
l5:
	movq	%t3, %temp6
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	%temp6, %rdi
	subq	$8, %rsp
	callq	_Iprintln_pai
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	leave
	ret
l1:
	movq	%t4, %tileRegister14
	imulq	$8, %tileRegister14
	movq	%x, %tileRegister15
	addq	%tileRegister14, %tileRegister15
	movq	%t4, %tileRegister16
	imulq	$8, %tileRegister16
	movq	%t3, %tileRegister17
	addq	%tileRegister16, %tileRegister17
	movq	(%tileRegister15), %rcx
	movq	%rcx, (%tileRegister17)
	movq	%t4, %tileRegister18
	addq	$1, %tileRegister18
	movq	%tileRegister18, %t4
	jmp	l0
l4:
	movq	%t5, %tileRegister19
	imulq	$8, %tileRegister19
	movq	%y, %tileRegister20
	addq	%tileRegister19, %tileRegister20
	movq	%t5, %tileRegister21
	addq	%t6, %tileRegister21
	movq	%tileRegister21, %tileRegister22
	imulq	$8, %tileRegister22
	movq	%t3, %tileRegister23
	addq	%tileRegister22, %tileRegister23
	movq	(%tileRegister20), %rcx
	movq	%rcx, (%tileRegister23)
	movq	%t5, %tileRegister24
	addq	$1, %tileRegister24
	movq	%tileRegister24, %t5
	jmp	l3
