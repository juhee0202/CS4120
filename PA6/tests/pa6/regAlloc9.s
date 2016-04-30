	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$80, $0
	pushq	%rdi
	pushq	%rsi
	pushq	%rbx
	pushq	%r12
	pushq	%r13
	pushq	%r14
	pushq	%r15
	movq	$0, %r12
	movq	$1, %r15
	movq	$2, %r13
	movq	$3, %r14
	movq	$4, %rbx
	pushq	%r13
	movq	-64(%rbp), %r13
	movq	$5, %r13
	movq	%r13, -64(%rbp)
	popq	%r13
	addq	%r15, %r12
	movq	%r12, %r15
	addq	%r13, %r15
	movq	%r15, %r15
	addq	%r14, %r15
	movq	%r15, %r15
	addq	%rbx, %r15
	pushq	%r13
	movq	-64(%rbp), %r13
	addq	%r13, %r15
	movq	-64(%rbp), %r13
	movq	$5, %r13
	movq	%r13, -64(%rbp)
	popq	%r13
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
