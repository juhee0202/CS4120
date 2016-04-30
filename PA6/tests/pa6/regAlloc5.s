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
	movq	$2, %r14
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_Ilargestprime_ii
	movq	%rax, %r14
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
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %r14
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
	movq	%r14, %rdi
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
l0:
	movq	$0, %r10
	cmpq	%rdi, %r10
	jne	l1
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
l1:
	cmpq	%rsi, %rdi
	jl	l3
	subq	%rsi, %rdi
l5:
	jmp	l0
l3:
	subq	%rdi, %rsi
	jmp	l5

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
	movq	$2, %r12
l6:
	movq	%r12, %r14
	imulq	%r12, %r14
	cmpq	%r15, %r14
	jle	l7
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
l7:
	movq	%r12, %r14
	movq	%r15, %r13
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%r13, %rsi
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_Igcd_iii
	movq	%rax, %r14
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
	movq	$1, %r13
	cmpq	%r14, %r13
	jne	l9
	movq	%r12, %r14
	addq	$1, %r14
	movq	%r14, %r12
	jmp	l6
l9:
	movq	$0, %rax
	jmp	label2

	.globl	_Ilargestprime_ii
	.align	4
_Ilargestprime_ii:
	enter	$64, $0
	pushq	%rdi
	pushq	%rsi
	pushq	%rbx
	pushq	%r12
	pushq	%r13
	pushq	%r14
	pushq	%r15
	movq	%rdi, %r12
	movq	$1, %r15
	movq	$1, %r13
l11:
	cmpq	%r12, %r15
	jl	l12
	movq	%r13, %rax
	movq	-8(%rbp), %rdi
	movq	-16(%rbp), %rsi
	movq	-24(%rbp), %rbx
	movq	-32(%rbp), %r12
	movq	-40(%rbp), %r13
	movq	-48(%rbp), %r14
	movq	-56(%rbp), %r15
	leave
	ret
l12:
	movq	%r15, %r14
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_Iisprime_bi
	movq	%rax, %r14
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
	testq	%r14, %r14
	jnz	l14
l15:
	movq	%r15, %r14
	addq	$1, %r14
	movq	%r14, %r15
	jmp	l11
l14:
	movq	%r15, %r13
	jmp	l15
