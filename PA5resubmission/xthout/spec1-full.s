	.text
	.globl	_If_t2bii
	.align	4
_If_t2bii:
	enter	$24, $0
	movq	%rdi, %rcx
	movq	%rcx, -8(%rbp)
	movq	$2, %r9
	movq	-8(%rbp), %rdx
	cmpq	%rdx, %r9
	cmovl	$1, %rcx
	movq	%rcx, -16(%rbp)
	movq	-16(%rbp), %rcx
	cmovge	$0, %rcx
	movq	%rcx, -16(%rbp)
	movq	-16(%rbp), %rdx
	movq	%rdx, %rax
	movq	-8(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -24(%rbp)
	movq	-24(%rbp), %rcx
	addq	$2, %rcx
	movq	%rcx, -24(%rbp)
	movq	-24(%rbp), %rdx
	movq	%rdx, %rdx
	leave
	ret

	.globl	_Ifoo_p
	.align	4
_Ifoo_p:
	enter	$104, $0
	movq	$2, %rcx
	movq	%rcx, -8(%rbp)
	movq	-8(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -16(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-16(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_If_t2bii
	movq	%rax, %rcx
	movq	%rcx, -24(%rbp)
	movq	-8(%rbp), %rcx
	movq	%rdx, %rcx
	movq	%rcx, -8(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	$48, %rcx
	movq	%rcx, -32(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-32(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -40(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-40(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -48(%rbp)
	movq	-48(%rbp), %rcx
	movq	$5, 0(%rcx)
	movq	$8, %rcx
	movq	%rcx, -56(%rbp)
	movq	-56(%rbp), %rcx
	movq	-48(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -56(%rbp)
	movq	-56(%rbp), %rcx
	movq	$72, 0(%rcx)
	movq	$16, %rcx
	movq	%rcx, -64(%rbp)
	movq	-64(%rbp), %rcx
	movq	-48(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -64(%rbp)
	movq	-64(%rbp), %rcx
	movq	$101, 0(%rcx)
	movq	$24, %rcx
	movq	%rcx, -72(%rbp)
	movq	-72(%rbp), %rcx
	movq	-48(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -72(%rbp)
	movq	-72(%rbp), %rcx
	movq	$108, 0(%rcx)
	movq	$32, %rcx
	movq	%rcx, -80(%rbp)
	movq	-80(%rbp), %rcx
	movq	-48(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	movq	-80(%rbp), %rcx
	movq	$108, 0(%rcx)
	movq	$40, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rcx
	movq	-48(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rcx
	movq	$111, 0(%rcx)
	movq	-48(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -96(%rbp)
	movq	-96(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -96(%rbp)
	movq	-48(%rbp), %rcx
	movq	-96(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -48(%rbp)
	movq	-48(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -104(%rbp)
	leave
	ret
