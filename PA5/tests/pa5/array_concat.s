	.text
	.globl	_Ig_iai
	.align	4
_Ig_iai:
	enter	$24, $0
	movq	%rdi, %rcx
	movq	%rcx, -8(%rbp)
	movq	$8, %rcx
	movq	%rcx, -16(%rbp)
	movq	-16(%rbp), %rcx
	imulq	$1, %rcx
	movq	%rcx, -16(%rbp)
	movq	-8(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -24(%rbp)
	movq	-24(%rbp), %rcx
	movq	-16(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -24(%rbp)
	movq	-24(%rbp), %rcx
	movq	$6, 0(%rcx)
	movq	$1, %rax
	leave
	ret

	.globl	_Ifoo_p
	.align	4
_Ifoo_p:
	enter	$96, $0
	movq	$32, %rcx
	movq	%rcx, -8(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-8(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -16(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-16(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -24(%rbp)
	movq	-24(%rbp), %rcx
	movq	$3, 0(%rcx)
	movq	$8, %rcx
	movq	%rcx, -32(%rbp)
	movq	-32(%rbp), %rcx
	movq	-24(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -32(%rbp)
	movq	-32(%rbp), %rcx
	movq	$1, 0(%rcx)
	movq	$16, %rcx
	movq	%rcx, -40(%rbp)
	movq	-40(%rbp), %rcx
	movq	-24(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -40(%rbp)
	movq	-40(%rbp), %rcx
	movq	$2, 0(%rcx)
	movq	$24, %rcx
	movq	%rcx, -48(%rbp)
	movq	-48(%rbp), %rcx
	movq	-24(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -48(%rbp)
	movq	-48(%rbp), %rcx
	movq	$3, 0(%rcx)
	movq	-24(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -56(%rbp)
	movq	-56(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -56(%rbp)
	movq	-24(%rbp), %rcx
	movq	-56(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -24(%rbp)
	movq	-24(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -64(%rbp)
	movq	$8, %rcx
	movq	%rcx, -72(%rbp)
	movq	-72(%rbp), %rcx
	imulq	$1, %rcx
	movq	%rcx, -72(%rbp)
	movq	-64(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	movq	-80(%rbp), %rcx
	movq	-72(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	movq	$8, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %r11
	movq	-80(%rbp), %rcx
	imulq	0(%rcx), %r11
	movq	%r11, -88(%rbp)
	movq	-64(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -96(%rbp)
	movq	-96(%rbp), %rcx
	movq	-88(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -96(%rbp)
	movq	-96(%rbp), %rcx
	movq	$1, 0(%rcx)
	leave
	ret

	.globl	_If_aiai
	.align	4
_If_aiai:
	enter	$8, $0
	movq	%rdi, %rcx
	movq	%rcx, -8(%rbp)
	movq	-8(%rbp), %rdx
	movq	%rdx, %rax
	leave
	ret
