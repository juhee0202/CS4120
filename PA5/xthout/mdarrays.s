	.text
	.globl	_Ifoo_p
	.align	4
_Ifoo_p:
	enter	$536, $0
	movq	$3, %rcx
	movq	%rcx, -8(%rbp)
	movq	$4, %rcx
	movq	%rcx, -16(%rbp)
	movq	-8(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -24(%rbp)
	movq	-24(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -24(%rbp)
	movq	-24(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -32(%rbp)
	movq	-32(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -32(%rbp)
	movq	-32(%rbp), %rdx
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
	callq	_I_alloc_i
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
	movq	-8(%rbp), %r11
	movq	-56(%rbp), %rcx
	movq	%r11, 0(%rcx)
	movq	-56(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -64(%rbp)
	movq	-64(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -64(%rbp)
	movq	-56(%rbp), %rcx
	movq	-64(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -56(%rbp)
	movq	$0, %rcx
	movq	%rcx, -72(%rbp)
l0:
	movq	-72(%rbp), %rcx
	movq	-8(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -72(%rbp)
	jl	l1
l2:
	movq	-56(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	movq	-80(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -88(%rbp)
	movq	$3, %rcx
	movq	%rcx, -96(%rbp)
	movq	-96(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -104(%rbp)
	movq	-104(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -104(%rbp)
	movq	-104(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -112(%rbp)
	movq	-112(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -112(%rbp)
	movq	-112(%rbp), %rdx
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
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -128(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-128(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -136(%rbp)
	movq	-96(%rbp), %r11
	movq	-136(%rbp), %rcx
	movq	%r11, 0(%rcx)
	movq	-136(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -144(%rbp)
	movq	-144(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -144(%rbp)
	movq	-136(%rbp), %rcx
	movq	-144(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -136(%rbp)
	movq	$0, %rcx
	movq	%rcx, -152(%rbp)
l6:
	movq	-152(%rbp), %rcx
	movq	-96(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -152(%rbp)
	jl	l7
l8:
	movq	-136(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -160(%rbp)
	movq	$8, %rcx
	movq	%rcx, -168(%rbp)
	movq	-168(%rbp), %rcx
	imulq	$0, %rcx
	movq	%rcx, -168(%rbp)
	movq	-80(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -176(%rbp)
	movq	-176(%rbp), %rcx
	movq	-168(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -176(%rbp)
	movq	$8, %rcx
	movq	%rcx, -184(%rbp)
	movq	-184(%rbp), %rcx
	imulq	$0, %rcx
	movq	%rcx, -184(%rbp)
	movq	-160(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -192(%rbp)
	movq	-192(%rbp), %rcx
	movq	-184(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -192(%rbp)
	movq	-176(%rbp), %rcx
	movq	0(%rcx), %rcx
	movq	-192(%rbp), %rcx
	movq	%rcx, 0(%rcx)
	movq	$8, %rcx
	movq	%rcx, -200(%rbp)
	movq	-200(%rbp), %rcx
	imulq	$1, %rcx
	movq	%rcx, -200(%rbp)
	movq	-80(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -208(%rbp)
	movq	-208(%rbp), %rcx
	movq	-200(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -208(%rbp)
	movq	$8, %rcx
	movq	%rcx, -216(%rbp)
	movq	-216(%rbp), %rcx
	imulq	$1, %rcx
	movq	%rcx, -216(%rbp)
	movq	-160(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -224(%rbp)
	movq	-224(%rbp), %rcx
	movq	-216(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -224(%rbp)
	movq	-208(%rbp), %rcx
	movq	0(%rcx), %rcx
	movq	-224(%rbp), %rcx
	movq	%rcx, 0(%rcx)
	movq	$8, %rcx
	movq	%rcx, -232(%rbp)
	movq	-232(%rbp), %rcx
	imulq	$2, %rcx
	movq	%rcx, -232(%rbp)
	movq	-80(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -240(%rbp)
	movq	-240(%rbp), %rcx
	movq	-232(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -240(%rbp)
	movq	$8, %rcx
	movq	%rcx, -248(%rbp)
	movq	-248(%rbp), %rcx
	imulq	$2, %rcx
	movq	%rcx, -248(%rbp)
	movq	-160(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -256(%rbp)
	movq	-256(%rbp), %rcx
	movq	-248(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -256(%rbp)
	movq	-240(%rbp), %rcx
	movq	0(%rcx), %rcx
	movq	-256(%rbp), %rcx
	movq	%rcx, 0(%rcx)
	movq	$24, %rcx
	movq	%rcx, -264(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-264(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -272(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-272(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -280(%rbp)
	movq	-280(%rbp), %rcx
	movq	$2, 0(%rcx)
	movq	$24, %rcx
	movq	%rcx, -288(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-288(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -296(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-296(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -304(%rbp)
	movq	-304(%rbp), %rcx
	movq	$2, 0(%rcx)
	movq	$8, %rcx
	movq	%rcx, -312(%rbp)
	movq	-312(%rbp), %rcx
	movq	-304(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -312(%rbp)
	movq	-312(%rbp), %rcx
	movq	$1, 0(%rcx)
	movq	$16, %rcx
	movq	%rcx, -320(%rbp)
	movq	-320(%rbp), %rcx
	movq	-304(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -320(%rbp)
	movq	-320(%rbp), %rcx
	movq	$0, 0(%rcx)
	movq	-304(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -328(%rbp)
	movq	-328(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -328(%rbp)
	movq	-304(%rbp), %rcx
	movq	-328(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -304(%rbp)
	movq	$8, %rcx
	movq	%rcx, -336(%rbp)
	movq	-336(%rbp), %rcx
	movq	-280(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -336(%rbp)
	movq	-304(%rbp), %r11
	movq	-336(%rbp), %rcx
	movq	%r11, 0(%rcx)
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
	movq	-352(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -360(%rbp)
	movq	-360(%rbp), %rcx
	movq	$2, 0(%rcx)
	movq	$8, %rcx
	movq	%rcx, -368(%rbp)
	movq	-368(%rbp), %rcx
	movq	-360(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -368(%rbp)
	movq	-368(%rbp), %rcx
	movq	$0, 0(%rcx)
	movq	$16, %rcx
	movq	%rcx, -376(%rbp)
	movq	-376(%rbp), %rcx
	movq	-360(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -376(%rbp)
	movq	-376(%rbp), %rcx
	movq	$1, 0(%rcx)
	movq	-360(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -384(%rbp)
	movq	-384(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -384(%rbp)
	movq	-360(%rbp), %rcx
	movq	-384(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -360(%rbp)
	movq	$16, %rcx
	movq	%rcx, -392(%rbp)
	movq	-392(%rbp), %rcx
	movq	-280(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -392(%rbp)
	movq	-360(%rbp), %r11
	movq	-392(%rbp), %rcx
	movq	%r11, 0(%rcx)
	movq	-280(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -400(%rbp)
	movq	-400(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -400(%rbp)
	movq	-280(%rbp), %rcx
	movq	-400(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -280(%rbp)
	movq	-280(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -408(%rbp)
	leave
	ret
l1:
	movq	-16(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -416(%rbp)
	movq	-416(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -416(%rbp)
	movq	-416(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -424(%rbp)
	movq	-424(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -424(%rbp)
	movq	-424(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -432(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-432(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -440(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-440(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -448(%rbp)
	movq	-16(%rbp), %r11
	movq	-448(%rbp), %rcx
	movq	%r11, 0(%rcx)
	movq	-448(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -456(%rbp)
	movq	-456(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -456(%rbp)
	movq	-448(%rbp), %rcx
	movq	-456(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -448(%rbp)
	movq	$0, %rcx
	movq	%rcx, -464(%rbp)
l3:
	movq	-464(%rbp), %rcx
	movq	-16(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -464(%rbp)
	jl	l4
l5:
	movq	-72(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -472(%rbp)
	movq	-472(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -472(%rbp)
	movq	-56(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -480(%rbp)
	movq	-480(%rbp), %rcx
	movq	-472(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -480(%rbp)
	movq	-448(%rbp), %r11
	movq	-480(%rbp), %rcx
	movq	%r11, 0(%rcx)
	movq	-72(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -488(%rbp)
	movq	-488(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -488(%rbp)
	movq	-72(%rbp), %rcx
	movq	-488(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -72(%rbp)
	jmp	l0
l4:
	movq	-464(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -496(%rbp)
	movq	-496(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -496(%rbp)
	movq	-448(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -504(%rbp)
	movq	-504(%rbp), %rcx
	movq	-496(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -504(%rbp)
	movq	-504(%rbp), %rcx
	movq	$97, 0(%rcx)
	movq	-464(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -512(%rbp)
	movq	-512(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -512(%rbp)
	movq	-464(%rbp), %rcx
	movq	-512(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -464(%rbp)
	jmp	l3
l7:
	movq	-152(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -520(%rbp)
	movq	-520(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -520(%rbp)
	movq	-136(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -528(%rbp)
	movq	-528(%rbp), %rcx
	movq	-520(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -528(%rbp)
	movq	-528(%rbp), %rcx
	movq	$97, 0(%rcx)
	movq	-152(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -536(%rbp)
	movq	-536(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -536(%rbp)
	movq	-152(%rbp), %rcx
	movq	-536(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -152(%rbp)
	jmp	l6
