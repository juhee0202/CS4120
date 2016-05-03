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
	movq	$1000, %r15
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%r15, %rdi
	subq	$8, %rsp
	callq	_Ilargestprime_ii
	movq	%rax, %r15
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
	movq	%r15, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %r15
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
	movq	%r15, %rdi
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
	movq	$0, %rcx
	cmpq	%rdi, %rcx
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

	.globl	_Ilargestprime_ii
	.align	4
_Ilargestprime_ii:
	enter	$80, $0
	pushq	%rdi
	pushq	%rsi
	pushq	%rbx
	pushq	%r12
	pushq	%r13
	pushq	%r14
	pushq	%r15
	movq	%rdi, %r13
	pushq	%r12
	movq	-72(%rbp), %r12
	movq	$1, %r12
	movq	%r12, -72(%rbp)
	popq	%r12
	pushq	%r13
	movq	-64(%rbp), %r13
	movq	$1, %r13
	movq	%r13, -64(%rbp)
	popq	%r13
l6:
	pushq	%r12
	movq	-72(%rbp), %r12
	cmpq	%r13, %r12
	movq	-72(%rbp), %r12
	movq	$1, %r12
	movq	%r12, -72(%rbp)
	popq	%r12
	jl	l7
	pushq	%r13
	movq	%r13, %rax
	movq	-64(%rbp), %r13
	movq	$1, %r13
	movq	%r13, -64(%rbp)
	popq	%r13
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
	movq	$2, %r14
	movq	$1, %rbx
l9:
	movq	%r14, %r15
	imulq	%r14, %r15
	pushq	%r12
	movq	-72(%rbp), %r12
	cmpq	%r12, %r15
	movq	-72(%rbp), %r12
	cmpq	%r13, %r12
	movq	-72(%rbp), %r12
	movq	$1, %r12
	movq	%r12, -72(%rbp)
	popq	%r12
	jle	l10
	testq	%rbx, %rbx
	jnz	l14
l15:
	pushq	%r12
	movq	%r12, %r15
	movq	-72(%rbp), %r12
	cmpq	%r12, %r15
	movq	-72(%rbp), %r12
	cmpq	%r13, %r12
	movq	-72(%rbp), %r12
	movq	$1, %r12
	movq	%r12, -72(%rbp)
	popq	%r12
	addq	$1, %r15
	pushq	%r12
	movq	-72(%rbp), %r12
	movq	%r15, %r12
	movq	%r12, %r15
	movq	-72(%rbp), %r12
	cmpq	%r12, %r15
	movq	-72(%rbp), %r12
	cmpq	%r13, %r12
	movq	-72(%rbp), %r12
	movq	$1, %r12
	movq	%r12, -72(%rbp)
	popq	%r12
	jmp	l6
l10:
	movq	%r14, %r12
	pushq	%r12
	movq	%r12, %r15
	movq	-72(%rbp), %r12
	movq	%r15, %r12
	movq	%r12, %r15
	movq	-72(%rbp), %r12
	cmpq	%r12, %r15
	movq	-72(%rbp), %r12
	cmpq	%r13, %r12
	movq	-72(%rbp), %r12
	movq	$1, %r12
	movq	%r12, -72(%rbp)
	popq	%r12
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%r15, %rsi
	movq	%r12, %rdi
	subq	$8, %rsp
	callq	_Igcd_iii
	movq	%rax, %r15
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
	movq	$1, %r12
	cmpq	%r15, %r12
	jne	l12
l13:
	movq	%r14, %r15
	addq	$1, %r15
	movq	%r15, %r14
	jmp	l9
l12:
	movq	$0, %rbx
	jmp	l13
l14:
	pushq	%r13
	movq	-64(%rbp), %r13
	pushq	%r12
	movq	%r12, %r13
	movq	%r12, %r15
	movq	-72(%rbp), %r12
	movq	%r15, %r12
	movq	%r12, %r15
	movq	-72(%rbp), %r12
	cmpq	%r12, %r15
	movq	-72(%rbp), %r12
	cmpq	%r13, %r12
	movq	-72(%rbp), %r12
	movq	$1, %r12
	movq	%r12, -72(%rbp)
	popq	%r12
	movq	%r13, %rax
	movq	-64(%rbp), %r13
	movq	$1, %r13
	movq	%r13, -64(%rbp)
	popq	%r13
	jmp	l15
