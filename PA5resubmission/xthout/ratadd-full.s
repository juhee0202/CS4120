	.text
	.globl	_Igcd_iii
	.align	4
_Igcd_iii:
	enter	$32, $0
	movq	%rdi, %rcx
	movq	%rcx, -8(%rbp)
	movq	%rsi, %rcx
	movq	%rcx, -16(%rbp)
l0:
	movq	$0, %r9
	movq	-8(%rbp), %rdx
	cmpq	%rdx, %r9
	jne	l1
l2:
	movq	-16(%rbp), %rdx
	movq	%rdx, %rax
	leave
	ret
l1:
	movq	-8(%rbp), %rcx
	movq	-16(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -8(%rbp)
	jl	l3
l4:
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
l5:
	jmp	l0
l3:
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
	jmp	l5

	.globl	_Iratadd_t2iiiiii
	.align	4
_Iratadd_t2iiiiii:
	enter	$104, $0
	movq	%rdi, %rcx
	movq	%rcx, -8(%rbp)
	movq	%rsi, %rcx
	movq	%rcx, -16(%rbp)
	movq	%rdx, %rcx
	movq	%rcx, -24(%rbp)
	movq	%rcx, %rcx
	movq	%rcx, -32(%rbp)
	movq	-16(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -40(%rbp)
	movq	-32(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -48(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-48(%rbp), %rdx
	movq	%rdx, %rsi
	movq	-40(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_Igcd_iii
	movq	%rax, %rcx
	movq	%rcx, -56(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-56(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -64(%rbp)
	movq	$0, %rdx
	movq	-32(%rbp), %rdx
	movq	%rdx, %rax
	movq	-64(%rbp), %rcx
	idivq	%rcx
	movq	-8(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -72(%rbp)
	movq	-72(%rbp), %rcx
	imulq	%rax, %rcx
	movq	%rcx, -72(%rbp)
	movq	$0, %rdx
	movq	-16(%rbp), %rdx
	movq	%rdx, %rax
	movq	-64(%rbp), %rcx
	idivq	%rcx
	movq	-24(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	movq	-80(%rbp), %rcx
	imulq	%rax, %rcx
	movq	%rcx, -80(%rbp)
	movq	-72(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rcx
	movq	-80(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -96(%rbp)
	movq	-96(%rbp), %rdx
	movq	%rdx, %rax
	movq	$0, %rdx
	movq	-16(%rbp), %rdx
	movq	%rdx, %rax
	movq	-64(%rbp), %rcx
	idivq	%rcx
	movq	%rax, %rcx
	movq	%rcx, -104(%rbp)
	movq	-104(%rbp), %rcx
	movq	-32(%rbp), %rdx
	imulq	%rdx, %rcx
	movq	%rcx, -104(%rbp)
	movq	-104(%rbp), %rdx
	movq	%rdx, %rdx
	leave
	ret
