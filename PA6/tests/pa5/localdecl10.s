	.text
	.globl	_If_t3ibi
	.align	4
_If_t3ibi:
	enter	$160, $0
	pushq	%rdi
	pushq	%rsi
	pushq	%rbx
	pushq	%r12
	pushq	%r13
	pushq	%r14
	pushq	%r15
	movq	$16, %rcx
	movq	%rcx, -64(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-64(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -72(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$56, %rsp
	movq	-72(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	movq	-80(%rbp), %rcx
	movq	$1, (%rcx)
	movq	$8, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rcx
	movq	-80(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rcx
	movq	$8, (%rcx)
	movq	-80(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -96(%rbp)
	movq	-96(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -96(%rbp)
	movq	-80(%rbp), %rcx
	movq	-96(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	movq	-80(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -104(%rbp)
	movq	$17, %rax
	movq	$1, %rdx
	movq	$42, 0(%rdi)
	movq	-8(%rbp), %rdi
	movq	-16(%rbp), %rsi
	movq	-24(%rbp), %rbx
	movq	-32(%rbp), %r12
	movq	-40(%rbp), %r13
	movq	-48(%rbp), %r14
	movq	-56(%rbp), %r15
	leave
	ret

	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$136, $0
	pushq	%rdi
	pushq	%rsi
	pushq	%rbx
	pushq	%r12
	pushq	%r13
	pushq	%r14
	pushq	%r15
	movq	%rdi, %rcx
	movq	%rcx, -64(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	%rsp, %r11
	subq	$8, %r11
	movq	%r11, %rdi
	subq	$8, %rsp
	subq	$8, %rsp
	callq	_If_t3ibi
	movq	%rax, %rcx
	movq	%rcx, -72(%rbp)
	movq	0(%rdi), %r11
	movq	%r11, -80(%rbp)
	movq	64(%rsp), %rax
	movq	56(%rsp), %rcx
	movq	48(%rsp), %rdx
	movq	40(%rsp), %r8
	movq	32(%rsp), %r9
	movq	24(%rsp), %r10
	movq	16(%rsp), %r11
	addq	$72, %rsp
	movq	-8(%rbp), %rdi
	movq	-16(%rbp), %rsi
	movq	-24(%rbp), %rbx
	movq	-32(%rbp), %r12
	movq	-40(%rbp), %r13
	movq	-48(%rbp), %r14
	movq	-56(%rbp), %r15
	leave
	ret
