	.text
	.globl	_If_t3ibi
	.align	4
_If_t3ibi:
	enter	$120, $0
	movq	$88, %rcx
	movq	%rcx, -8(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-8(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -16(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-16(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -24(%rbp)
	movq	-24(%rbp), %rcx
	movq	$10, (%rcx)
	movq	$8, %rcx
	movq	%rcx, -32(%rbp)
	movq	-32(%rbp), %rcx
	movq	-24(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -32(%rbp)
	movq	-32(%rbp), %rcx
	movq	$102, (%rcx)
	movq	$16, %rcx
	movq	%rcx, -40(%rbp)
	movq	-40(%rbp), %rcx
	movq	-24(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -40(%rbp)
	movq	-40(%rbp), %rcx
	movq	$40, (%rcx)
	movq	$24, %rcx
	movq	%rcx, -48(%rbp)
	movq	-48(%rbp), %rcx
	movq	-24(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -48(%rbp)
	movq	-48(%rbp), %rcx
	movq	$41, (%rcx)
	movq	$32, %rcx
	movq	%rcx, -56(%rbp)
	movq	-56(%rbp), %rcx
	movq	-24(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -56(%rbp)
	movq	-56(%rbp), %rcx
	movq	$32, (%rcx)
	movq	$40, %rcx
	movq	%rcx, -64(%rbp)
	movq	-64(%rbp), %rcx
	movq	-24(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -64(%rbp)
	movq	-64(%rbp), %rcx
	movq	$99, (%rcx)
	movq	$48, %rcx
	movq	%rcx, -72(%rbp)
	movq	-72(%rbp), %rcx
	movq	-24(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -72(%rbp)
	movq	-72(%rbp), %rcx
	movq	$97, (%rcx)
	movq	$56, %rcx
	movq	%rcx, -80(%rbp)
	movq	-80(%rbp), %rcx
	movq	-24(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	movq	-80(%rbp), %rcx
	movq	$108, (%rcx)
	movq	$64, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rcx
	movq	-24(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rcx
	movq	$108, (%rcx)
	movq	$72, %rcx
	movq	%rcx, -96(%rbp)
	movq	-96(%rbp), %rcx
	movq	-24(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -96(%rbp)
	movq	-96(%rbp), %rcx
	movq	$101, (%rcx)
	movq	$80, %rcx
	movq	%rcx, -104(%rbp)
	movq	-104(%rbp), %rcx
	movq	-24(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -104(%rbp)
	movq	-104(%rbp), %rcx
	movq	$100, (%rcx)
	movq	-24(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -112(%rbp)
	movq	-112(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -112(%rbp)
	movq	-24(%rbp), %rcx
	movq	-112(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -24(%rbp)
	movq	-24(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -120(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-120(%rbp), %rdx
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
	movq	$17, %rax
	movq	$1, %rdx
	movq	$42, 0(%rdi)
	leave
	ret

	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$304, $0
	movq	%rdi, %rcx
	movq	%rcx, -8(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-8(%rsp), %rdi
	subq	$8, %rsp
	callq	_If_t3ibi
	movq	%rax, %rcx
	movq	%rcx, -16(%rbp)
	movq	%rdx, %rcx
	movq	%rcx, -24(%rbp)
	movq	0(%rdi), %r11
	movq	%r11, -32(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-16(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -40(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-40(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %rcx
	movq	%rcx, -48(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-48(%rbp), %rdx
	movq	%rdx, %rcx
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
	callq	_Iprintln_pai
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-24(%rbp), %rcx
	movq	-24(%rbp), %rdx
	testq	%rdx, %rcx
	movq	%rcx, -24(%rbp)
	jnz	l0
l1:
	movq	$48, %rcx
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
	subq	$8, %rsp
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
	addq	$64, %rsp
	movq	-72(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	movq	-80(%rbp), %rcx
	movq	$5, (%rcx)
	movq	$8, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rcx
	movq	-80(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rcx
	movq	$102, (%rcx)
	movq	$16, %rcx
	movq	%rcx, -96(%rbp)
	movq	-96(%rbp), %rcx
	movq	-80(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -96(%rbp)
	movq	-96(%rbp), %rcx
	movq	$97, (%rcx)
	movq	$24, %rcx
	movq	%rcx, -104(%rbp)
	movq	-104(%rbp), %rcx
	movq	-80(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -104(%rbp)
	movq	-104(%rbp), %rcx
	movq	$108, (%rcx)
	movq	$32, %rcx
	movq	%rcx, -112(%rbp)
	movq	-112(%rbp), %rcx
	movq	-80(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -112(%rbp)
	movq	-112(%rbp), %rcx
	movq	$115, (%rcx)
	movq	$40, %rcx
	movq	%rcx, -120(%rbp)
	movq	-120(%rbp), %rcx
	movq	-80(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -120(%rbp)
	movq	-120(%rbp), %rcx
	movq	$101, (%rcx)
	movq	-80(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -128(%rbp)
	movq	-128(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -128(%rbp)
	movq	-80(%rbp), %rcx
	movq	-128(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	movq	-80(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -136(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-136(%rbp), %rdx
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
l2:
	movq	-32(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -144(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-144(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %rcx
	movq	%rcx, -152(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-152(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -160(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-160(%rbp), %rdx
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
	movq	$40, %rcx
	movq	%rcx, -168(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-168(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -176(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-176(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -184(%rbp)
	movq	-184(%rbp), %rcx
	movq	$4, (%rcx)
	movq	$8, %rcx
	movq	%rcx, -192(%rbp)
	movq	-192(%rbp), %rcx
	movq	-184(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -192(%rbp)
	movq	-192(%rbp), %rcx
	movq	$100, (%rcx)
	movq	$16, %rcx
	movq	%rcx, -200(%rbp)
	movq	-200(%rbp), %rcx
	movq	-184(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -200(%rbp)
	movq	-200(%rbp), %rcx
	movq	$111, (%rcx)
	movq	$24, %rcx
	movq	%rcx, -208(%rbp)
	movq	-208(%rbp), %rcx
	movq	-184(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -208(%rbp)
	movq	-208(%rbp), %rcx
	movq	$110, (%rcx)
	movq	$32, %rcx
	movq	%rcx, -216(%rbp)
	movq	-216(%rbp), %rcx
	movq	-184(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -216(%rbp)
	movq	-216(%rbp), %rcx
	movq	$101, (%rcx)
	movq	-184(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -224(%rbp)
	movq	-224(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -224(%rbp)
	movq	-184(%rbp), %rcx
	movq	-224(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -184(%rbp)
	movq	-184(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -232(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-232(%rbp), %rdx
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
l0:
	movq	$40, %rcx
	movq	%rcx, -240(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-240(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -248(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-248(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -256(%rbp)
	movq	-256(%rbp), %rcx
	movq	$4, (%rcx)
	movq	$8, %rcx
	movq	%rcx, -264(%rbp)
	movq	-264(%rbp), %rcx
	movq	-256(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -264(%rbp)
	movq	-264(%rbp), %rcx
	movq	$116, (%rcx)
	movq	$16, %rcx
	movq	%rcx, -272(%rbp)
	movq	-272(%rbp), %rcx
	movq	-256(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -272(%rbp)
	movq	-272(%rbp), %rcx
	movq	$114, (%rcx)
	movq	$24, %rcx
	movq	%rcx, -280(%rbp)
	movq	-280(%rbp), %rcx
	movq	-256(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -280(%rbp)
	movq	-280(%rbp), %rcx
	movq	$117, (%rcx)
	movq	$32, %rcx
	movq	%rcx, -288(%rbp)
	movq	-288(%rbp), %rcx
	movq	-256(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -288(%rbp)
	movq	-288(%rbp), %rcx
	movq	$101, (%rcx)
	movq	-256(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -296(%rbp)
	movq	-296(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -296(%rbp)
	movq	-256(%rbp), %rcx
	movq	-296(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -256(%rbp)
	movq	-256(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -304(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-304(%rbp), %rdx
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
	jmp	l2
