	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$112, $0
	pushq	%rdi
	pushq	%rsi
	pushq	%rbx
	pushq	%r12
	pushq	%r13
	pushq	%r14
	pushq	%r15
	movq	$0, %r14
	movq	$1, %r15
	movq	$2, %r12
	pushq	%r8
	movq	-64(%rbp), %r8
	movq	$3, %r8
	movq	%r8, -64(%rbp)
	popq	%r8
	pushq	%r13
	movq	-72(%rbp), %r13
	movq	$4, %r13
	movq	%r13, -72(%rbp)
	popq	%r13
	pushq	%rbx
	movq	-80(%rbp), %rbx
	movq	$5, %rbx
	movq	%rbx, -80(%rbp)
	popq	%rbx
	pushq	%r8
	movq	-88(%rbp), %r8
	movq	$6, %r8
	movq	%r8, -88(%rbp)
	popq	%r8
	pushq	%rbx
	movq	-96(%rbp), %rbx
	movq	$7, %rbx
	movq	%rbx, -96(%rbp)
	popq	%rbx
	movq	$8, %r13
	movq	$9, %rbx
	pushq	%r11
	movq	-104(%rbp), %r11
	movq	$10, %r11
	movq	%r11, -104(%rbp)
	popq	%r11
	addq	%r15, %r14
	movq	%r14, %r14
	addq	%r12, %r14
	movq	%r14, %r14
	pushq	%r8
	movq	-64(%rbp), %r8
	addq	%r8, %r14
	movq	-64(%rbp), %r8
	movq	$3, %r8
	movq	%r8, -64(%rbp)
	popq	%r8
	movq	%r14, %r14
	pushq	%r13
	movq	-72(%rbp), %r13
	addq	%r13, %r14
	movq	-72(%rbp), %r13
	movq	$4, %r13
	movq	%r13, -72(%rbp)
	popq	%r13
	movq	%r14, %r14
	pushq	%rbx
	movq	-80(%rbp), %rbx
	addq	%rbx, %r14
	movq	-80(%rbp), %rbx
	movq	$5, %rbx
	movq	%rbx, -80(%rbp)
	popq	%rbx
	movq	%r14, %r14
	pushq	%r8
	movq	-88(%rbp), %r8
	addq	%r8, %r14
	movq	-88(%rbp), %r8
	movq	$6, %r8
	movq	%r8, -88(%rbp)
	popq	%r8
	movq	%r14, %r14
	pushq	%rbx
	movq	-96(%rbp), %rbx
	addq	%rbx, %r14
	movq	-96(%rbp), %rbx
	movq	$7, %rbx
	movq	%rbx, -96(%rbp)
	popq	%rbx
	movq	%r14, %r14
	addq	%r13, %r14
	movq	%r14, %r13
	addq	%rbx, %r13
	pushq	%r11
	movq	-104(%rbp), %r11
	addq	%r11, %r13
	movq	-104(%rbp), %r11
	movq	$10, %r11
	movq	%r11, -104(%rbp)
	popq	%r11
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%r13, %rdi
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
