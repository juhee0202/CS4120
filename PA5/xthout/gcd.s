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
