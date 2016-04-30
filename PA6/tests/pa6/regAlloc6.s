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
	movq	$1000, %r12
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%r12, %rdi
	subq	$8, %rsp
	callq	_Ilargestprime_ii
	movq	%rax, %r12
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
	movq	%r12, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %r12
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
	movq	%r12, %rdi
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
	movq	$2, %r11
l0:
	movq	%r11, %rax
	imulq	%r11, %rax
	cmpq	%rdi, %rax
	jle	l1
	movq	$1, %rax
label1:
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
	movq	%r11, %rax
	movq	%rdi, %rdx
l3:
	movq	$0, %r12
	cmpq	%rax, %r12
	jne	l4
	movq	$1, %rax
	cmpq	%rdx, %rax
	jne	l9
	addq	$1, %r11
	jmp	l0
l4:
	cmpq	%rdx, %rax
	jl	l6
	subq	%rdx, %rax
l8:
	jmp	l3
l6:
	subq	%rax, %rdx
	jmp	l8
l9:
	movq	$0, %rax
	jmp	label1

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
	movq	$1, %r13
	movq	$1, %rbx
l11:
	cmpq	%r12, %r13
	jl	l12
	movq	%rbx, %rax
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
	movq	%r13, %r14
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
	movq	%r13, %r14
	addq	$1, %r14
	movq	%r14, %r13
	jmp	l11
l14:
	movq	%r13, %rbx
	jmp	l15
