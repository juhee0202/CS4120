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
	movq	$1000, %rbx
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
	callq	_Ilargestprime_ii
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
	movq	$1, %r8
	movq	$1, %rbx
l0:
	cmpq	%rdi, %r8
	jl	l1
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
l1:
	movq	%r8, %rax
	movq	$2, %rsi
	movq	$1, %r14
l3:
	movq	%rsi, %r15
	imulq	%rsi, %r15
	cmpq	%rax, %r15
	jle	l4
	testq	%r14, %r14
	jnz	l14
l15:
	addq	$1, %r8
	jmp	l0
l4:
	movq	%rsi, %r11
	movq	%rax, %r15
l6:
	movq	$0, %r10
	cmpq	%r11, %r10
	jne	l7
	movq	$1, %r11
	cmpq	%r15, %r11
	jne	l12
l13:
	addq	$1, %rsi
	jmp	l3
l7:
	cmpq	%r15, %r11
	jl	l9
	subq	%r15, %r11
l11:
	jmp	l6
l9:
	subq	%r11, %r15
	jmp	l11
l12:
	movq	$0, %r14
	jmp	l13
l14:
	movq	%r8, %rbx
	jmp	l15
