	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$512, $0
	movq	%rdi, %rcx
	movq	%rcx, -8(%rbp)
	movq	$1, %rcx
	movq	%rcx, -16(%rbp)
	movq	$24, %rcx
	movq	%rcx, -24(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-24(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -32(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-32(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -40(%rbp)
	movq	-40(%rbp), %rcx
	movq	$2, 0(%rcx)
	movq	$24, %rcx
	movq	%rcx, -48(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-48(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -56(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-56(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -64(%rbp)
	movq	-64(%rbp), %rcx
	movq	$2, 0(%rcx)
	movq	$8, %rcx
	movq	%rcx, -72(%rbp)
	movq	-72(%rbp), %rcx
	movq	-64(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -72(%rbp)
	movq	-72(%rbp), %rcx
	movq	$1, 0(%rcx)
	movq	$16, %rcx
	movq	%rcx, -80(%rbp)
	movq	-80(%rbp), %rcx
	movq	-64(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	movq	-80(%rbp), %rcx
	movq	$2, 0(%rcx)
	movq	-64(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -88(%rbp)
	movq	-64(%rbp), %rcx
	movq	-88(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -64(%rbp)
	movq	$8, %rcx
	movq	%rcx, -96(%rbp)
	movq	-96(%rbp), %rcx
	movq	-40(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -96(%rbp)
	movq	-64(%rbp), %r11
	movq	-96(%rbp), %rcx
	movq	%r11, 0(%rcx)
	movq	$24, %rcx
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
	movq	$2, 0(%rcx)
	movq	$8, %rcx
	movq	%rcx, -128(%rbp)
	movq	-128(%rbp), %rcx
	movq	-120(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -128(%rbp)
	movq	-128(%rbp), %rcx
	movq	$3, 0(%rcx)
	movq	$16, %rcx
	movq	%rcx, -136(%rbp)
	movq	-136(%rbp), %rcx
	movq	-120(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -136(%rbp)
	movq	-136(%rbp), %rcx
	movq	$4, 0(%rcx)
	movq	-120(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -144(%rbp)
	movq	-144(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -144(%rbp)
	movq	-120(%rbp), %rcx
	movq	-144(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -120(%rbp)
	movq	$16, %rcx
	movq	%rcx, -152(%rbp)
	movq	-152(%rbp), %rcx
	movq	-40(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -152(%rbp)
	movq	-120(%rbp), %r11
	movq	-152(%rbp), %rcx
	movq	%r11, 0(%rcx)
	movq	-40(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -160(%rbp)
	movq	-160(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -160(%rbp)
	movq	-40(%rbp), %rcx
	movq	-160(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -40(%rbp)
	movq	-40(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -168(%rbp)
	movq	-168(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -168(%rbp)
	movq	-168(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -176(%rbp)
	movq	-16(%rbp), %rcx
	cmpq	$0, %rcx
	movq	%rcx, -16(%rbp)
	jl	l1
l0:
	movq	-16(%rbp), %rcx
	movq	-176(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -16(%rbp)
	jl	l2
l1:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	subq	$8, %rsp
	callq	_I_outOfBounds_p
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
l2:
	movq	$0, %rcx
	movq	%rcx, -184(%rbp)
	movq	$24, %rcx
	movq	%rcx, -192(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-192(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -200(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-40(%rbp), %rcx
	movq	-200(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -40(%rbp)
	movq	-40(%rbp), %rcx
	movq	$2, 0(%rcx)
	movq	$24, %rcx
	movq	%rcx, -208(%rbp)
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
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -216(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-64(%rbp), %rcx
	movq	-216(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -64(%rbp)
	movq	-64(%rbp), %rcx
	movq	$2, 0(%rcx)
	movq	$8, %rcx
	movq	%rcx, -224(%rbp)
	movq	-224(%rbp), %rcx
	movq	-64(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -224(%rbp)
	movq	-224(%rbp), %rcx
	movq	$1, 0(%rcx)
	movq	$16, %rcx
	movq	%rcx, -232(%rbp)
	movq	-232(%rbp), %rcx
	movq	-64(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -232(%rbp)
	movq	-232(%rbp), %rcx
	movq	$2, 0(%rcx)
	movq	-64(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -240(%rbp)
	movq	-240(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -240(%rbp)
	movq	-64(%rbp), %rcx
	movq	-240(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -64(%rbp)
	movq	$8, %rcx
	movq	%rcx, -248(%rbp)
	movq	-248(%rbp), %rcx
	movq	-40(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -248(%rbp)
	movq	-64(%rbp), %r11
	movq	-248(%rbp), %rcx
	movq	%r11, 0(%rcx)
	movq	$24, %rcx
	movq	%rcx, -256(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-256(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -264(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-120(%rbp), %rcx
	movq	-264(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -120(%rbp)
	movq	-120(%rbp), %rcx
	movq	$2, 0(%rcx)
	movq	$8, %rcx
	movq	%rcx, -272(%rbp)
	movq	-272(%rbp), %rcx
	movq	-120(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -272(%rbp)
	movq	-272(%rbp), %rcx
	movq	$3, 0(%rcx)
	movq	$16, %rcx
	movq	%rcx, -280(%rbp)
	movq	-280(%rbp), %rcx
	movq	-120(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -280(%rbp)
	movq	-280(%rbp), %rcx
	movq	$4, 0(%rcx)
	movq	-120(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -288(%rbp)
	movq	-288(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -288(%rbp)
	movq	-120(%rbp), %rcx
	movq	-288(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -120(%rbp)
	movq	$16, %rcx
	movq	%rcx, -296(%rbp)
	movq	-296(%rbp), %rcx
	movq	-40(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -296(%rbp)
	movq	-120(%rbp), %r11
	movq	-296(%rbp), %rcx
	movq	%r11, 0(%rcx)
	movq	-40(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -304(%rbp)
	movq	-304(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -304(%rbp)
	movq	-40(%rbp), %rcx
	movq	-304(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -40(%rbp)
	movq	$8, %rcx
	movq	%rcx, -312(%rbp)
	movq	-312(%rbp), %rcx
	movq	-16(%rbp), %rdx
	imulq	%rdx, %rcx
	movq	%rcx, -312(%rbp)
	movq	-40(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -320(%rbp)
	movq	-320(%rbp), %rcx
	movq	-312(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -320(%rbp)
	movq	-320(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -328(%rbp)
	movq	-328(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -328(%rbp)
	movq	-328(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -336(%rbp)
	movq	-184(%rbp), %rcx
	cmpq	$0, %rcx
	movq	%rcx, -184(%rbp)
	jl	l4
l3:
	movq	-184(%rbp), %rcx
	movq	-336(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -184(%rbp)
	jl	l5
l4:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	subq	$8, %rsp
	callq	_I_outOfBounds_p
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
l5:
	movq	$24, %rcx
	movq	%rcx, -344(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-344(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -352(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-40(%rbp), %rcx
	movq	-352(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -40(%rbp)
	movq	-40(%rbp), %rcx
	movq	$2, 0(%rcx)
	movq	$24, %rcx
	movq	%rcx, -360(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-360(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -368(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-64(%rbp), %rcx
	movq	-368(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -64(%rbp)
	movq	-64(%rbp), %rcx
	movq	$2, 0(%rcx)
	movq	$8, %rcx
	movq	%rcx, -376(%rbp)
	movq	-376(%rbp), %rcx
	movq	-64(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -376(%rbp)
	movq	-376(%rbp), %rcx
	movq	$1, 0(%rcx)
	movq	$16, %rcx
	movq	%rcx, -384(%rbp)
	movq	-384(%rbp), %rcx
	movq	-64(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -384(%rbp)
	movq	-384(%rbp), %rcx
	movq	$2, 0(%rcx)
	movq	-64(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -392(%rbp)
	movq	-392(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -392(%rbp)
	movq	-64(%rbp), %rcx
	movq	-392(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -64(%rbp)
	movq	$8, %rcx
	movq	%rcx, -400(%rbp)
	movq	-400(%rbp), %rcx
	movq	-40(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -400(%rbp)
	movq	-64(%rbp), %r11
	movq	-400(%rbp), %rcx
	movq	%r11, 0(%rcx)
	movq	$24, %rcx
	movq	%rcx, -408(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-408(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -416(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-120(%rbp), %rcx
	movq	-416(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -120(%rbp)
	movq	-120(%rbp), %rcx
	movq	$2, 0(%rcx)
	movq	$8, %rcx
	movq	%rcx, -424(%rbp)
	movq	-424(%rbp), %rcx
	movq	-120(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -424(%rbp)
	movq	-424(%rbp), %rcx
	movq	$3, 0(%rcx)
	movq	$16, %rcx
	movq	%rcx, -432(%rbp)
	movq	-432(%rbp), %rcx
	movq	-120(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -432(%rbp)
	movq	-432(%rbp), %rcx
	movq	$4, 0(%rcx)
	movq	-120(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -440(%rbp)
	movq	-440(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -440(%rbp)
	movq	-120(%rbp), %rcx
	movq	-440(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -120(%rbp)
	movq	$16, %rcx
	movq	%rcx, -448(%rbp)
	movq	-448(%rbp), %rcx
	movq	-40(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -448(%rbp)
	movq	-120(%rbp), %r11
	movq	-448(%rbp), %rcx
	movq	%r11, 0(%rcx)
	movq	-40(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -456(%rbp)
	movq	-456(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -456(%rbp)
	movq	-40(%rbp), %rcx
	movq	-456(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -40(%rbp)
	movq	$8, %rcx
	movq	%rcx, -464(%rbp)
	movq	-464(%rbp), %rcx
	movq	-16(%rbp), %rdx
	imulq	%rdx, %rcx
	movq	%rcx, -464(%rbp)
	movq	-40(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -472(%rbp)
	movq	-472(%rbp), %rcx
	movq	-464(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -472(%rbp)
	movq	$8, %rcx
	movq	%rcx, -480(%rbp)
	movq	-480(%rbp), %rcx
	movq	-184(%rbp), %rdx
	imulq	%rdx, %rcx
	movq	%rcx, -480(%rbp)
	movq	-472(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -488(%rbp)
	movq	-488(%rbp), %rcx
	movq	-480(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -488(%rbp)
	movq	-488(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -496(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-496(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %rcx
	movq	%rcx, -504(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-504(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -512(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-512(%rbp), %rdx
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

	.globl	_IcreateArray_aai
	.align	4
_IcreateArray_aai:
	enter	$152, $0
	movq	$24, %rcx
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
	movq	$2, 0(%rcx)
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
	movq	$8, %rcx
	movq	%rcx, -56(%rbp)
	movq	-56(%rbp), %rcx
	movq	-48(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -56(%rbp)
	movq	-56(%rbp), %rcx
	movq	$1, 0(%rcx)
	movq	$16, %rcx
	movq	%rcx, -64(%rbp)
	movq	-64(%rbp), %rcx
	movq	-48(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -64(%rbp)
	movq	-64(%rbp), %rcx
	movq	$2, 0(%rcx)
	movq	-48(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -72(%rbp)
	movq	-72(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -72(%rbp)
	movq	-48(%rbp), %rcx
	movq	-72(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -48(%rbp)
	movq	$8, %rcx
	movq	%rcx, -80(%rbp)
	movq	-80(%rbp), %rcx
	movq	-24(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	movq	-48(%rbp), %r11
	movq	-80(%rbp), %rcx
	movq	%r11, 0(%rcx)
	movq	$24, %rcx
	movq	%rcx, -88(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-88(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -96(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-96(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -104(%rbp)
	movq	-104(%rbp), %rcx
	movq	$2, 0(%rcx)
	movq	$8, %rcx
	movq	%rcx, -112(%rbp)
	movq	-112(%rbp), %rcx
	movq	-104(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -112(%rbp)
	movq	-112(%rbp), %rcx
	movq	$3, 0(%rcx)
	movq	$16, %rcx
	movq	%rcx, -120(%rbp)
	movq	-120(%rbp), %rcx
	movq	-104(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -120(%rbp)
	movq	-120(%rbp), %rcx
	movq	$4, 0(%rcx)
	movq	-104(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -128(%rbp)
	movq	-128(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -128(%rbp)
	movq	-104(%rbp), %rcx
	movq	-128(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -104(%rbp)
	movq	$16, %rcx
	movq	%rcx, -136(%rbp)
	movq	-136(%rbp), %rcx
	movq	-24(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -136(%rbp)
	movq	-104(%rbp), %r11
	movq	-136(%rbp), %rcx
	movq	%r11, 0(%rcx)
	movq	-24(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -144(%rbp)
	movq	-144(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -144(%rbp)
	movq	-24(%rbp), %rcx
	movq	-144(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -24(%rbp)
	movq	-24(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -152(%rbp)
	movq	-152(%rbp), %rdx
	movq	%rdx, %rax
	leave
	ret
