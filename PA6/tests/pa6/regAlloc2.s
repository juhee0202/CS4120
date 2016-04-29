	.text
	.globl	_If_p
	.align	4
_If_p:
	enter	$40, $0
	movq	$24, %rcx
	movq	%rcx, -8(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-8(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -16(%rbp)
	movq	72(%rsp), %rax
	movq	64(%rsp), %rcx
	movq	56(%rsp), %rdx
	movq	48(%rsp), %r8
	movq	40(%rsp), %r9
	movq	32(%rsp), %r10
	movq	24(%rsp), %r11
	movq	16(%rsp), %rdi
	movq	8(%rsp), %rsi
	addq	$80, %rsp
	movq	-16(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -24(%rbp)
	movq	-24(%rbp), %rcx
	movq	$2, (%rcx)
	movq	-24(%rbp), %rcx
	movq	$104, 8(%rcx)
	movq	-24(%rbp), %rcx
	movq	$105, 16(%rcx)
	movq	-24(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -32(%rbp)
	movq	-32(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -32(%rbp)
	movq	-24(%rbp), %rcx
	movq	-32(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -24(%rbp)
	movq	-24(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -40(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-40(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_Iprint_pai
	movq	72(%rsp), %rax
	movq	64(%rsp), %rcx
	movq	56(%rsp), %rdx
	movq	48(%rsp), %r8
	movq	40(%rsp), %r9
	movq	32(%rsp), %r10
	movq	24(%rsp), %r11
	movq	16(%rsp), %rdi
	movq	8(%rsp), %rsi
	addq	$80, %rsp
	leave
	ret

	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$32, $0
	movq	%rdi, %rcx
	movq	%rcx, -8(%rbp)
	movq	$5, %rcx
	movq	%rcx, -16(%rbp)
	movq	$6, %rcx
	movq	%rcx, -24(%rbp)
	movq	$7, %rcx
	movq	%rcx, -32(%rbp)
	leave
	ret
