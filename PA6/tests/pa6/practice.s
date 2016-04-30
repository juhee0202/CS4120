	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$144, $0
	pushq	%rdi
	pushq	%rsi
	pushq	%rbx
	pushq	%r12
	pushq	%r13
	pushq	%r14
	pushq	%r15
	movq	%rdi, %rcx
	movq	%rcx, -64(%rbp)
	movq	$1, %rcx
	movq	%rcx, -72(%rbp)
	movq	$2, %rcx
	movq	%rcx, -80(%rbp)
	movq	$3, %rcx
	movq	%rcx, -88(%rbp)
	movq	-72(%rbp), %rcx
	cmpq	$3, %rcx
	movq	%rcx, -72(%rbp)
	jl	l0
l1:
	movq	-72(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -96(%rbp)
	movq	-96(%rbp), %rcx
	movq	-80(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -96(%rbp)
	movq	-96(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -104(%rbp)
	movq	-104(%rbp), %rcx
	movq	-88(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -104(%rbp)
	movq	-104(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -112(%rbp)
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
	movq	-72(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -120(%rbp)
	movq	-120(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -120(%rbp)
	movq	-72(%rbp), %rcx
	movq	-120(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -72(%rbp)
	movq	-72(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -128(%rbp)
	movq	-128(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -128(%rbp)
	movq	-80(%rbp), %rcx
	movq	-128(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	movq	-80(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -136(%rbp)
	movq	-136(%rbp), %rcx
	movq	-72(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -136(%rbp)
	movq	-88(%rbp), %rcx
	movq	-136(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -88(%rbp)
	jmp	l1
