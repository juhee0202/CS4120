	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
<<<<<<< HEAD
	enter	$392, $0
	movq	%rdi, %rcx
	movq	%rcx, -8(%rbp)
	movq	$24, %rcx
=======
	enter	$88, $0
	movq	%rdi, %rcx
	movq	%rcx, -8(%rbp)
	movq	$48, %rcx
>>>>>>> 0729dbfeaca890761220f04139058b81193a9525
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
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -24(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-24(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -32(%rbp)
	movq	-32(%rbp), %rcx
<<<<<<< HEAD
	movq	$2, 0(%rcx)
=======
	movq	$5, 0(%rcx)
>>>>>>> 0729dbfeaca890761220f04139058b81193a9525
	movq	$8, %rcx
	movq	%rcx, -40(%rbp)
	movq	-40(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -40(%rbp)
	movq	-40(%rbp), %rcx
	movq	$111, 0(%rcx)
	movq	$16, %rcx
	movq	%rcx, -48(%rbp)
	movq	-48(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -48(%rbp)
	movq	-48(%rbp), %rcx
	movq	$107, 0(%rcx)
	movq	-32(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -56(%rbp)
	movq	-56(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -56(%rbp)
	movq	-32(%rbp), %rcx
	movq	-56(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -32(%rbp)
	movq	-32(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -64(%rbp)
<<<<<<< HEAD
	movq	$48, %rcx
	movq	%rcx, -72(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-72(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -80(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-80(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rcx
	movq	$5, 0(%rcx)
	movq	$8, %rcx
	movq	%rcx, -96(%rbp)
	movq	-96(%rbp), %rcx
	movq	-88(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -96(%rbp)
	movq	-96(%rbp), %rcx
	movq	$104, 0(%rcx)
	movq	$16, %rcx
	movq	%rcx, -104(%rbp)
	movq	-104(%rbp), %rcx
	movq	-88(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -104(%rbp)
	movq	-104(%rbp), %rcx
	movq	$101, 0(%rcx)
	movq	$24, %rcx
	movq	%rcx, -112(%rbp)
	movq	-112(%rbp), %rcx
	movq	-88(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -112(%rbp)
	movq	-112(%rbp), %rcx
	movq	$108, 0(%rcx)
	movq	$32, %rcx
	movq	%rcx, -120(%rbp)
	movq	-120(%rbp), %rcx
	movq	-88(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -120(%rbp)
	movq	-120(%rbp), %rcx
	movq	$108, 0(%rcx)
	movq	$40, %rcx
	movq	%rcx, -128(%rbp)
	movq	-128(%rbp), %rcx
	movq	-88(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -128(%rbp)
	movq	-128(%rbp), %rcx
	movq	$111, 0(%rcx)
	movq	-88(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -136(%rbp)
	movq	-136(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -136(%rbp)
	movq	-88(%rbp), %rcx
	movq	-136(%rbp), %rdx
=======
	movq	-64(%rbp), %rcx
	movq	$108, 0(%rcx)
	movq	$40, %rcx
	movq	%rcx, -72(%rbp)
	movq	-72(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -72(%rbp)
	movq	-72(%rbp), %rcx
	movq	$111, 0(%rcx)
	movq	-32(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	movq	-80(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -80(%rbp)
	movq	-32(%rbp), %rcx
	movq	-80(%rbp), %rdx
>>>>>>> 0729dbfeaca890761220f04139058b81193a9525
	movq	%rdx, %rcx
	movq	%rcx, -88(%rbp)
	movq	-64(%rbp), %rdx
	movq	%rdx, %rcx
<<<<<<< HEAD
	movq	%rcx, -144(%rbp)
	movq	-144(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -144(%rbp)
	movq	-88(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -152(%rbp)
	movq	-152(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -152(%rbp)
	movq	-144(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -160(%rbp)
	movq	-160(%rbp), %r11
	movq	-152(%rbp), %rcx
	addq	0(%rcx), %r11
	movq	%r11, -160(%rbp)
	movq	-160(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -168(%rbp)
	movq	-168(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -168(%rbp)
	movq	-168(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -176(%rbp)
	movq	-176(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -176(%rbp)
	movq	-176(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -184(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-184(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -192(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-192(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -200(%rbp)
	movq	-64(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -208(%rbp)
	movq	-208(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -208(%rbp)
	movq	-88(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -216(%rbp)
	movq	-216(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -216(%rbp)
	movq	-208(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -224(%rbp)
	movq	-224(%rbp), %r11
	movq	-216(%rbp), %rcx
	addq	0(%rcx), %r11
	movq	%r11, -224(%rbp)
	movq	-224(%rbp), %r11
	movq	-200(%rbp), %rcx
	movq	%r11, 0(%rcx)
	movq	-200(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -232(%rbp)
	movq	-232(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -232(%rbp)
	movq	-200(%rbp), %rcx
	movq	-232(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -200(%rbp)
	movq	$0, %rcx
	movq	%rcx, -240(%rbp)
l0:
	movq	-64(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -248(%rbp)
	movq	-248(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -248(%rbp)
	movq	-248(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -256(%rbp)
	movq	-240(%rbp), %rcx
	movq	-256(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -240(%rbp)
	jl	l1
l2:
	movq	$0, %rcx
	movq	%rcx, -264(%rbp)
	movq	-64(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -272(%rbp)
	movq	-272(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -272(%rbp)
	movq	-272(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -280(%rbp)
l3:
	movq	-88(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -288(%rbp)
	movq	-288(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -288(%rbp)
	movq	-288(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -296(%rbp)
	movq	-264(%rbp), %rcx
	movq	-296(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -264(%rbp)
	jl	l4
l5:
	movq	-200(%rbp), %rdx
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
	callq	_Iprint_pai
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
=======
	movq	%rcx, -88(%rbp)
>>>>>>> 0729dbfeaca890761220f04139058b81193a9525
	leave
	ret
l1:
	movq	-240(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -312(%rbp)
	movq	-312(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -312(%rbp)
	movq	-64(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -320(%rbp)
	movq	-320(%rbp), %rcx
	movq	-312(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -320(%rbp)
	movq	-240(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -328(%rbp)
	movq	-328(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -328(%rbp)
	movq	-200(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -336(%rbp)
	movq	-336(%rbp), %rcx
	movq	-328(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -336(%rbp)
	movq	-320(%rbp), %rcx
	movq	0(%rcx), %rcx
	movq	-336(%rbp), %rcx
	movq	%rcx, 0(%rcx)
	movq	-240(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -344(%rbp)
	movq	-344(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -344(%rbp)
	movq	-240(%rbp), %rcx
	movq	-344(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -240(%rbp)
	jmp	l0
l4:
	movq	-264(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -352(%rbp)
	movq	-352(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -352(%rbp)
	movq	-88(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -360(%rbp)
	movq	-360(%rbp), %rcx
	movq	-352(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -360(%rbp)
	movq	-264(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -368(%rbp)
	movq	-368(%rbp), %rcx
	movq	-280(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -368(%rbp)
	movq	-368(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -376(%rbp)
	movq	-376(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -376(%rbp)
	movq	-200(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -384(%rbp)
	movq	-384(%rbp), %rcx
	movq	-376(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -384(%rbp)
	movq	-360(%rbp), %rcx
	movq	0(%rcx), %rcx
	movq	-384(%rbp), %rcx
	movq	%rcx, 0(%rcx)
	movq	-264(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -392(%rbp)
	movq	-392(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -392(%rbp)
	movq	-264(%rbp), %rcx
	movq	-392(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -264(%rbp)
	jmp	l3

<<<<<<< HEAD
	.globl	_Ifoo_ai
	.align	4
_Ifoo_ai:
	enter	$48, $0
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
	movq	$8, %rcx
	movq	%rcx, -32(%rbp)
	movq	-32(%rbp), %rcx
	movq	-24(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -32(%rbp)
	movq	-32(%rbp), %rcx
	movq	$111, 0(%rcx)
	movq	$16, %rcx
	movq	%rcx, -40(%rbp)
	movq	-40(%rbp), %rcx
	movq	-24(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -40(%rbp)
	movq	-40(%rbp), %rcx
	movq	$107, 0(%rcx)
	movq	-24(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -48(%rbp)
	movq	-48(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -48(%rbp)
	movq	-24(%rbp), %rcx
	movq	-48(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -24(%rbp)
	movq	-24(%rbp), %rdx
	movq	%rdx, %rax
=======
	.globl	_Ifoo_p
	.align	4
_Ifoo_p:
	enter	$8, $0
	movq	$3, %rcx
	movq	%rcx, -8(%rbp)
>>>>>>> 0729dbfeaca890761220f04139058b81193a9525
	leave
	ret
