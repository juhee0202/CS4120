	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$64, $0
	pushq	%rdi
	pushq	%rsi
	pushq	%rbx
	pushq	%r12
	pushq	%r13
	pushq	%r14
	pushq	%r15
	movq	$100, %rbx
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%rbx, %rdi
	subq	$8, %rsp
	callq	_Iisprime_bi
	movq	%rax, %rbx
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
	testq	%rbx, %rbx
	jnz	l0
	movq	$0, %rbx
l2:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%rbx, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %rbx
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
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%rbx, %rdi
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
	movq	-8(%rbp), %rdi
	movq	-16(%rbp), %rsi
	movq	-24(%rbp), %rbx
	movq	-32(%rbp), %r12
	movq	-40(%rbp), %r13
	movq	-48(%rbp), %r14
	movq	-56(%rbp), %r15
	leave
	ret
l0:
	movq	$1, %rbx
	jmp	l2

	.globl	_Igcd_iii
	.align	4
_Igcd_iii:
	enter	$64, $0
	pushq	%rdi
	pushq	%rsi
	pushq	%rbx
	pushq	%r12
	pushq	%r13
	pushq	%r14
	pushq	%r15
l3:
	movq	$0, %rbx
	cmpq	%rdi, %rbx
	jne	l4
	movq	%rsi, %rax
	movq	-8(%rbp), %rdi
	movq	-16(%rbp), %rsi
	movq	-24(%rbp), %rbx
	movq	-32(%rbp), %r12
	movq	-40(%rbp), %r13
	movq	-48(%rbp), %r14
	movq	-56(%rbp), %r15
	leave
	ret
l4:
	cmpq	%rsi, %rdi
	jl	l6
	subq	%rsi, %rdi
l8:
	jmp	l3
l6:
	subq	%rdi, %rsi
	jmp	l8

	.globl	_Iisprime_bi
	.align	4
_Iisprime_bi:
	enter	$64, $0
	pushq	%rdi
	pushq	%rsi
	pushq	%rbx
	pushq	%r12
	pushq	%r13
	pushq	%r14
	pushq	%r15
	movq	%rdi, %r15
	movq	$2, %r13
l9:
	movq	%r13, %rbx
	imulq	%r13, %rbx
	cmpq	%r15, %rbx
	jle	l10
	movq	$1, %rax
label2:
	movq	-8(%rbp), %rdi
	movq	-16(%rbp), %rsi
	movq	-24(%rbp), %rbx
	movq	-32(%rbp), %r12
	movq	-40(%rbp), %r13
	movq	-48(%rbp), %r14
	movq	-56(%rbp), %r15
	leave
	ret
l10:
	movq	%r13, %r14
	movq	%r15, %rbx
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%rbx, %rsi
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_Igcd_iii
	movq	%rax, %rbx
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
	movq	$1, %r14
	cmpq	%rbx, %r14
	jne	l12
	movq	%r13, %rbx
	addq	$1, %rbx
	movq	%rbx, %r13
	jmp	l9
l12:
	movq	$0, %rax
	jmp	label2
