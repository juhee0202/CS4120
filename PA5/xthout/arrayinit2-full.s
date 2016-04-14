	.text
	.globl	_Igcd_iii
	.align	4
_Igcd_iii:
	enter	$32, $0
	movq	%rdi, %rcx
	movq	%rcx, -8(%rbp)
	movq	%rsi, %rcx
	movq	%rcx, -16(%rbp)
l6:
	movq	$0, %r9
	movq	-8(%rbp), %rdx
	cmpq	%rdx, %r9
	jne	l7
l8:
	movq	-16(%rbp), %rdx
	movq	%rdx, %rax
	leave
	ret
l7:
	movq	-8(%rbp), %rcx
	movq	-16(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -8(%rbp)
	jl	l9
l10:
	movq	-8(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -24(%rbp)
	movq	-24(%rbp), %rcx
	movq	-16(%rbp), %rdx
	subq	%rdx, %rcx
	movq	%rcx, -24(%rbp)
	movq	-8(%rbp), %rcx
	movq	-24(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -8(%rbp)
l11:
	jmp	l6
l9:
	movq	-16(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -32(%rbp)
	movq	-32(%rbp), %rcx
	movq	-8(%rbp), %rdx
	subq	%rdx, %rcx
	movq	%rcx, -32(%rbp)
	movq	-16(%rbp), %rcx
	movq	-32(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -16(%rbp)
	jmp	l11

	.globl	_Ifoo_p
	.align	4
_Ifoo_p:
	enter	$152, $0
	movq	$10, %rcx
	movq	%rcx, -8(%rbp)
	movq	$2, %rcx
	movq	%rcx, -16(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-16(%rbp), %rdx
	movq	%rdx, %rsi
	movq	-8(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_Igcd_iii
	movq	%rax, %rcx
	movq	%rcx, -24(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-24(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -32(%rbp)
	movq	-32(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -40(%rbp)
	movq	-40(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -48(%rbp)
	movq	-48(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -48(%rbp)
	movq	-48(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -56(%rbp)
	movq	-56(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -56(%rbp)
	movq	-56(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -64(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-64(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -72(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-72(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	movq	-40(%rbp), %r11
	movq	-80(%rbp), %rcx
	movq	%r11, 0(%rcx)
	movq	-80(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -88(%rbp)
	movq	-80(%rbp), %rcx
	movq	-88(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	movq	$0, %rcx
	movq	%rcx, -96(%rbp)
l0:
	movq	-96(%rbp), %rcx
	movq	-40(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -96(%rbp)
	jl	l1
l2:
	movq	-80(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -104(%rbp)
l3:
	movq	-32(%rbp), %rcx
	cmpq	$0, %rcx
	movq	%rcx, -32(%rbp)
	jg	l4
l5:
	leave
	ret
l1:
	movq	-96(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -112(%rbp)
	movq	-112(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -112(%rbp)
	movq	-80(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -120(%rbp)
	movq	-120(%rbp), %rcx
	movq	-112(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -120(%rbp)
	movq	-120(%rbp), %rcx
	movq	$97, 0(%rcx)
	movq	-96(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -128(%rbp)
	movq	-128(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -128(%rbp)
	movq	-96(%rbp), %rcx
	movq	-128(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -96(%rbp)
	jmp	l0
l4:
	movq	-32(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -136(%rbp)
	movq	-136(%rbp), %rcx
	subq	$1, %rcx
	movq	%rcx, -136(%rbp)
	movq	-32(%rbp), %rcx
	movq	-136(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -32(%rbp)
	movq	$8, %rcx
	movq	%rcx, -144(%rbp)
	movq	-144(%rbp), %rcx
	movq	-32(%rbp), %rdx
	imulq	%rdx, %rcx
	movq	%rcx, -144(%rbp)
	movq	-104(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -152(%rbp)
	movq	-152(%rbp), %rcx
	movq	-144(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -152(%rbp)
	movq	-32(%rbp), %r11
	movq	-152(%rbp), %rcx
	movq	%r11, 0(%rcx)
	jmp	l3
