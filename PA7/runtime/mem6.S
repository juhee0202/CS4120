	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$208, $0
	movq	%rdi, %rcx
	movq	%rcx, -8(%rbp)
	movq	$0, %rcx
	movq	%rcx, -16(%rbp)
	movq	$0, %rcx
	movq	%rcx, -24(%rbp)
	movq	$24, %rcx
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
	movq	$2, 0(%rcx)
	movq	$16, %rcx
	movq	%rcx, -56(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-56(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -64(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-64(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -72(%rbp)
	movq	-72(%rbp), %rcx
	movq	$1, 0(%rcx)
	movq	$8, %rcx
	movq	%rcx, -80(%rbp)
	movq	-80(%rbp), %rcx
	movq	-72(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	movq	-80(%rbp), %rcx
	movq	$97, 0(%rcx)
	movq	-72(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -88(%rbp)
	movq	-72(%rbp), %rcx
	movq	-88(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -72(%rbp)
	movq	$8, %rcx
	movq	%rcx, -96(%rbp)
	movq	-96(%rbp), %rcx
	movq	-48(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -96(%rbp)
	movq	-72(%rbp), %r11
	movq	-96(%rbp), %rcx
	movq	%r11, 0(%rcx)
	movq	$16, %rcx
	movq	%rcx, -104(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-104(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -112(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-112(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -120(%rbp)
	movq	-120(%rbp), %rcx
	movq	$1, 0(%rcx)
	movq	$8, %rcx
	movq	%rcx, -128(%rbp)
	movq	-128(%rbp), %rcx
	movq	-120(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -128(%rbp)
	movq	-128(%rbp), %rcx
	movq	$98, 0(%rcx)
	movq	-120(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -136(%rbp)
	movq	-136(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -136(%rbp)
	movq	-120(%rbp), %rcx
	movq	-136(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -120(%rbp)
	movq	$16, %rcx
	movq	%rcx, -144(%rbp)
	movq	-144(%rbp), %rcx
	movq	-48(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -144(%rbp)
	movq	-120(%rbp), %r11
	movq	-144(%rbp), %rcx
	movq	%r11, 0(%rcx)
	movq	-48(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -152(%rbp)
	movq	-152(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -152(%rbp)
	movq	-48(%rbp), %rcx
	movq	-152(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -48(%rbp)
	movq	-48(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -160(%rbp)
	movq	-24(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -168(%rbp)
	movq	-168(%rbp), %rcx
	imulq	$2, %rcx
	movq	%rcx, -168(%rbp)
	movq	-16(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -176(%rbp)
	movq	-176(%rbp), %rcx
	movq	-168(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -176(%rbp)
	movq	-176(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -184(%rbp)
	movq	-184(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -184(%rbp)
	movq	$8, %rcx
	movq	%rcx, -192(%rbp)
	movq	-192(%rbp), %rcx
	movq	-184(%rbp), %rdx
	imulq	%rdx, %rcx
	movq	%rcx, -192(%rbp)
	movq	-160(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -200(%rbp)
	movq	-200(%rbp), %rcx
	movq	-192(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -200(%rbp)
	movq	-200(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -208(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-208(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_Iprintln_pai
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	leave
	ret
