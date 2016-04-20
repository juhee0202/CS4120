	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$1240, $0
	movq	%rdi, %rcx
	movq	%rcx, -8(%rbp)
	movq	$40, %rcx
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
	movq	$4, 0(%rcx)
	movq	$8, %rcx
	movq	%rcx, -40(%rbp)
	movq	-40(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -40(%rbp)
	movq	-40(%rbp), %rcx
	movq	$1, 0(%rcx)
	movq	$16, %rcx
	movq	%rcx, -48(%rbp)
	movq	-48(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -48(%rbp)
	movq	-48(%rbp), %rcx
	movq	$2, 0(%rcx)
	movq	$24, %rcx
	movq	%rcx, -56(%rbp)
	movq	-56(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -56(%rbp)
	movq	-56(%rbp), %rcx
	movq	$3, 0(%rcx)
	movq	$32, %rcx
	movq	%rcx, -64(%rbp)
	movq	-64(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -64(%rbp)
	movq	-64(%rbp), %rcx
	movq	$4, 0(%rcx)
	movq	-32(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -72(%rbp)
	movq	-72(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -72(%rbp)
	movq	-32(%rbp), %rcx
	movq	-72(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -32(%rbp)
	movq	-32(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	movq	$32, %rcx
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
	movq	$3, 0(%rcx)
	movq	$8, %rcx
	movq	%rcx, -112(%rbp)
	movq	-112(%rbp), %rcx
	movq	-104(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -112(%rbp)
	movq	-112(%rbp), %rcx
	movq	$5, 0(%rcx)
	movq	$16, %rcx
	movq	%rcx, -120(%rbp)
	movq	-120(%rbp), %rcx
	movq	-104(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -120(%rbp)
	movq	-120(%rbp), %rcx
	movq	$6, 0(%rcx)
	movq	$24, %rcx
	movq	%rcx, -128(%rbp)
	movq	-128(%rbp), %rcx
	movq	-104(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -128(%rbp)
	movq	-128(%rbp), %rcx
	movq	$7, 0(%rcx)
	movq	-104(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -136(%rbp)
	movq	-136(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -136(%rbp)
	movq	-104(%rbp), %rcx
	movq	-136(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -104(%rbp)
	movq	-104(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -144(%rbp)
	movq	$8, %rcx
	movq	%rcx, -152(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-152(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -160(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-160(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -168(%rbp)
	movq	-168(%rbp), %rcx
	movq	$0, 0(%rcx)
	movq	-168(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -176(%rbp)
	movq	-176(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -176(%rbp)
	movq	-168(%rbp), %rcx
	movq	-176(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -168(%rbp)
	movq	-168(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -184(%rbp)
	movq	-80(%rbp), %rdx
	movq	%rdx, %rcx
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
	callq	_If_aiai
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
	movq	-144(%rbp), %rdx
	movq	%rdx, %rcx
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
	callq	_If_aiai
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
	movq	-200(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -224(%rbp)
	movq	-224(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -224(%rbp)
	movq	-216(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -232(%rbp)
	movq	-232(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -232(%rbp)
	movq	-224(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -240(%rbp)
	movq	-240(%rbp), %r11
	movq	-232(%rbp), %rcx
	addq	0(%rcx), %r11
	movq	%r11, -240(%rbp)
	movq	-240(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -248(%rbp)
	movq	-248(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -256(%rbp)
	movq	-256(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -256(%rbp)
	movq	-256(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -264(%rbp)
	movq	-264(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -264(%rbp)
	movq	-264(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -272(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-272(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -280(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-280(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -288(%rbp)
	movq	-248(%rbp), %r11
	movq	-288(%rbp), %rcx
	movq	%r11, 0(%rcx)
	movq	-288(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -296(%rbp)
	movq	-296(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -296(%rbp)
	movq	-288(%rbp), %rcx
	movq	-296(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -288(%rbp)
	movq	$0, %rcx
	movq	%rcx, -304(%rbp)
l0:
	movq	-80(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -312(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-312(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_If_aiai
	movq	%rax, %rcx
	movq	%rcx, -320(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-320(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -328(%rbp)
	movq	-328(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -328(%rbp)
	movq	-328(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -336(%rbp)
	movq	-304(%rbp), %rcx
	movq	-336(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -304(%rbp)
	jl	l1
l2:
	movq	$0, %rcx
	movq	%rcx, -344(%rbp)
	movq	-80(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -352(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-352(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_If_aiai
	movq	%rax, %rcx
	movq	%rcx, -360(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-360(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -368(%rbp)
	movq	-368(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -368(%rbp)
	movq	-368(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -376(%rbp)
l3:
	movq	-144(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -384(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-384(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_If_aiai
	movq	%rax, %rcx
	movq	%rcx, -392(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-392(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -400(%rbp)
	movq	-400(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -400(%rbp)
	movq	-400(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -408(%rbp)
	movq	-344(%rbp), %rcx
	movq	-408(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -344(%rbp)
	jl	l4
l5:
	movq	-288(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -416(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-416(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_Iprintai_pai
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
	movq	%rcx, -424(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-424(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_If_aiai
	movq	%rax, %rcx
	movq	%rcx, -432(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-184(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -440(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-440(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_If_aiai
	movq	%rax, %rcx
	movq	%rcx, -448(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-432(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -456(%rbp)
	movq	-456(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -456(%rbp)
	movq	-448(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -464(%rbp)
	movq	-464(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -464(%rbp)
	movq	-456(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -472(%rbp)
	movq	-472(%rbp), %r11
	movq	-464(%rbp), %rcx
	addq	0(%rcx), %r11
	movq	%r11, -472(%rbp)
	movq	-472(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -480(%rbp)
	movq	-480(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -488(%rbp)
	movq	-488(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -488(%rbp)
	movq	-488(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -496(%rbp)
	movq	-496(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -496(%rbp)
	movq	-496(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -504(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-504(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -512(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-512(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -520(%rbp)
	movq	-480(%rbp), %r11
	movq	-520(%rbp), %rcx
	movq	%r11, 0(%rcx)
	movq	-520(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -528(%rbp)
	movq	-528(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -528(%rbp)
	movq	-520(%rbp), %rcx
	movq	-528(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -520(%rbp)
	movq	$0, %rcx
	movq	%rcx, -536(%rbp)
l6:
	movq	-80(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -544(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-544(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_If_aiai
	movq	%rax, %rcx
	movq	%rcx, -552(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-552(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -560(%rbp)
	movq	-560(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -560(%rbp)
	movq	-560(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -568(%rbp)
	movq	-536(%rbp), %rcx
	movq	-568(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -536(%rbp)
	jl	l7
l8:
	movq	$0, %rcx
	movq	%rcx, -576(%rbp)
	movq	-80(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -584(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-584(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_If_aiai
	movq	%rax, %rcx
	movq	%rcx, -592(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-592(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -600(%rbp)
	movq	-600(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -600(%rbp)
	movq	-600(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -608(%rbp)
l9:
	movq	-184(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -616(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-616(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_If_aiai
	movq	%rax, %rcx
	movq	%rcx, -624(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-624(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -632(%rbp)
	movq	-632(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -632(%rbp)
	movq	-632(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -640(%rbp)
	movq	-576(%rbp), %rcx
	movq	-640(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -576(%rbp)
	jl	l10
l11:
	movq	-520(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -648(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-648(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_Iprintai_pai
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-144(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -656(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-656(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_If_aiai
	movq	%rax, %rcx
	movq	%rcx, -664(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-184(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -672(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-672(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_If_aiai
	movq	%rax, %rcx
	movq	%rcx, -680(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-664(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -688(%rbp)
	movq	-688(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -688(%rbp)
	movq	-680(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -696(%rbp)
	movq	-696(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -696(%rbp)
	movq	-688(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -704(%rbp)
	movq	-704(%rbp), %r11
	movq	-696(%rbp), %rcx
	addq	0(%rcx), %r11
	movq	%r11, -704(%rbp)
	movq	-704(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -712(%rbp)
	movq	-712(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -720(%rbp)
	movq	-720(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -720(%rbp)
	movq	-720(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -728(%rbp)
	movq	-728(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -728(%rbp)
	movq	-728(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -736(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-736(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -744(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-744(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -752(%rbp)
	movq	-712(%rbp), %r11
	movq	-752(%rbp), %rcx
	movq	%r11, 0(%rcx)
	movq	-752(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -760(%rbp)
	movq	-760(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -760(%rbp)
	movq	-752(%rbp), %rcx
	movq	-760(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -752(%rbp)
	movq	$0, %rcx
	movq	%rcx, -768(%rbp)
l12:
	movq	-144(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -776(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-776(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_If_aiai
	movq	%rax, %rcx
	movq	%rcx, -784(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-784(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -792(%rbp)
	movq	-792(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -792(%rbp)
	movq	-792(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -800(%rbp)
	movq	-768(%rbp), %rcx
	movq	-800(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -768(%rbp)
	jl	l13
l14:
	movq	$0, %rcx
	movq	%rcx, -808(%rbp)
	movq	-144(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -816(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-816(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_If_aiai
	movq	%rax, %rcx
	movq	%rcx, -824(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-824(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -832(%rbp)
	movq	-832(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -832(%rbp)
	movq	-832(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -840(%rbp)
l15:
	movq	-184(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -848(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-848(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_If_aiai
	movq	%rax, %rcx
	movq	%rcx, -856(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-856(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -864(%rbp)
	movq	-864(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -864(%rbp)
	movq	-864(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -872(%rbp)
	movq	-808(%rbp), %rcx
	movq	-872(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -808(%rbp)
	jl	l16
l17:
	movq	-752(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -880(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-880(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_Iprintai_pai
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
l1:
	movq	-80(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -888(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-888(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_If_aiai
	movq	%rax, %rcx
	movq	%rcx, -896(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-304(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -904(%rbp)
	movq	-904(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -904(%rbp)
	movq	-896(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -912(%rbp)
	movq	-912(%rbp), %rcx
	movq	-904(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -912(%rbp)
	movq	-304(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -920(%rbp)
	movq	-920(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -920(%rbp)
	movq	-288(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -928(%rbp)
	movq	-928(%rbp), %rcx
	movq	-920(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -928(%rbp)
	movq	-912(%rbp), %rcx
	movq	0(%rcx), %r10
	movq	-928(%rbp), %rcx
	movq	%r10, 0(%rcx)
	movq	-304(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -936(%rbp)
	movq	-936(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -936(%rbp)
	movq	-304(%rbp), %rcx
	movq	-936(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -304(%rbp)
	jmp	l0
l4:
	movq	-144(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -944(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-944(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_If_aiai
	movq	%rax, %rcx
	movq	%rcx, -952(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-344(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -960(%rbp)
	movq	-960(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -960(%rbp)
	movq	-952(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -968(%rbp)
	movq	-968(%rbp), %rcx
	movq	-960(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -968(%rbp)
	movq	-344(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -976(%rbp)
	movq	-976(%rbp), %rcx
	movq	-376(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -976(%rbp)
	movq	-976(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -984(%rbp)
	movq	-984(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -984(%rbp)
	movq	-288(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -992(%rbp)
	movq	-992(%rbp), %rcx
	movq	-984(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -992(%rbp)
	movq	-968(%rbp), %rcx
	movq	0(%rcx), %r10
	movq	-992(%rbp), %rcx
	movq	%r10, 0(%rcx)
	movq	-344(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1000(%rbp)
	movq	-1000(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -1000(%rbp)
	movq	-344(%rbp), %rcx
	movq	-1000(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -344(%rbp)
	jmp	l3
l7:
	movq	-80(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1008(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-1008(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_If_aiai
	movq	%rax, %rcx
	movq	%rcx, -1016(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-536(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1024(%rbp)
	movq	-1024(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -1024(%rbp)
	movq	-1016(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1032(%rbp)
	movq	-1032(%rbp), %rcx
	movq	-1024(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -1032(%rbp)
	movq	-536(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1040(%rbp)
	movq	-1040(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -1040(%rbp)
	movq	-520(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1048(%rbp)
	movq	-1048(%rbp), %rcx
	movq	-1040(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -1048(%rbp)
	movq	-1032(%rbp), %rcx
	movq	0(%rcx), %r10
	movq	-1048(%rbp), %rcx
	movq	%r10, 0(%rcx)
	movq	-536(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1056(%rbp)
	movq	-1056(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -1056(%rbp)
	movq	-536(%rbp), %rcx
	movq	-1056(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -536(%rbp)
	jmp	l6
l10:
	movq	-184(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1064(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-1064(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_If_aiai
	movq	%rax, %rcx
	movq	%rcx, -1072(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-576(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1080(%rbp)
	movq	-1080(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -1080(%rbp)
	movq	-1072(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1088(%rbp)
	movq	-1088(%rbp), %rcx
	movq	-1080(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -1088(%rbp)
	movq	-576(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1096(%rbp)
	movq	-1096(%rbp), %rcx
	movq	-608(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -1096(%rbp)
	movq	-1096(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1104(%rbp)
	movq	-1104(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -1104(%rbp)
	movq	-520(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1112(%rbp)
	movq	-1112(%rbp), %rcx
	movq	-1104(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -1112(%rbp)
	movq	-1088(%rbp), %rcx
	movq	0(%rcx), %r10
	movq	-1112(%rbp), %rcx
	movq	%r10, 0(%rcx)
	movq	-576(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1120(%rbp)
	movq	-1120(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -1120(%rbp)
	movq	-576(%rbp), %rcx
	movq	-1120(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -576(%rbp)
	jmp	l9
l13:
	movq	-144(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1128(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-1128(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_If_aiai
	movq	%rax, %rcx
	movq	%rcx, -1136(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-768(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1144(%rbp)
	movq	-1144(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -1144(%rbp)
	movq	-1136(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1152(%rbp)
	movq	-1152(%rbp), %rcx
	movq	-1144(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -1152(%rbp)
	movq	-768(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1160(%rbp)
	movq	-1160(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -1160(%rbp)
	movq	-752(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1168(%rbp)
	movq	-1168(%rbp), %rcx
	movq	-1160(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -1168(%rbp)
	movq	-1152(%rbp), %rcx
	movq	0(%rcx), %r10
	movq	-1168(%rbp), %rcx
	movq	%r10, 0(%rcx)
	movq	-768(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1176(%rbp)
	movq	-1176(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -1176(%rbp)
	movq	-768(%rbp), %rcx
	movq	-1176(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -768(%rbp)
	jmp	l12
l16:
	movq	-184(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1184(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-1184(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_If_aiai
	movq	%rax, %rcx
	movq	%rcx, -1192(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-808(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1200(%rbp)
	movq	-1200(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -1200(%rbp)
	movq	-1192(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1208(%rbp)
	movq	-1208(%rbp), %rcx
	movq	-1200(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -1208(%rbp)
	movq	-808(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1216(%rbp)
	movq	-1216(%rbp), %rcx
	movq	-840(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -1216(%rbp)
	movq	-1216(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1224(%rbp)
	movq	-1224(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -1224(%rbp)
	movq	-752(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1232(%rbp)
	movq	-1232(%rbp), %rcx
	movq	-1224(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -1232(%rbp)
	movq	-1208(%rbp), %rcx
	movq	0(%rcx), %r10
	movq	-1232(%rbp), %rcx
	movq	%r10, 0(%rcx)
	movq	-808(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1240(%rbp)
	movq	-1240(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -1240(%rbp)
	movq	-808(%rbp), %rcx
	movq	-1240(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -808(%rbp)
	jmp	l15

	.globl	_If_aiai
	.align	4
_If_aiai:
	enter	$152, $0
	movq	%rdi, %rcx
	movq	%rcx, -8(%rbp)
	movq	$104, %rcx
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
	movq	$12, 0(%rcx)
	movq	$8, %rcx
	movq	%rcx, -40(%rbp)
	movq	-40(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -40(%rbp)
	movq	-40(%rbp), %rcx
	movq	$102, 0(%rcx)
	movq	$16, %rcx
	movq	%rcx, -48(%rbp)
	movq	-48(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -48(%rbp)
	movq	-48(%rbp), %rcx
	movq	$40, 0(%rcx)
	movq	$24, %rcx
	movq	%rcx, -56(%rbp)
	movq	-56(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -56(%rbp)
	movq	-56(%rbp), %rcx
	movq	$41, 0(%rcx)
	movq	$32, %rcx
	movq	%rcx, -64(%rbp)
	movq	-64(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -64(%rbp)
	movq	-64(%rbp), %rcx
	movq	$32, 0(%rcx)
	movq	$40, %rcx
	movq	%rcx, -72(%rbp)
	movq	-72(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -72(%rbp)
	movq	-72(%rbp), %rcx
	movq	$99, 0(%rcx)
	movq	$48, %rcx
	movq	%rcx, -80(%rbp)
	movq	-80(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	movq	-80(%rbp), %rcx
	movq	$97, 0(%rcx)
	movq	$56, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rcx
	movq	$108, 0(%rcx)
	movq	$64, %rcx
	movq	%rcx, -96(%rbp)
	movq	-96(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -96(%rbp)
	movq	-96(%rbp), %rcx
	movq	$108, 0(%rcx)
	movq	$72, %rcx
	movq	%rcx, -104(%rbp)
	movq	-104(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -104(%rbp)
	movq	-104(%rbp), %rcx
	movq	$101, 0(%rcx)
	movq	$80, %rcx
	movq	%rcx, -112(%rbp)
	movq	-112(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -112(%rbp)
	movq	-112(%rbp), %rcx
	movq	$100, 0(%rcx)
	movq	$88, %rcx
	movq	%rcx, -120(%rbp)
	movq	-120(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -120(%rbp)
	movq	-120(%rbp), %rcx
	movq	$58, 0(%rcx)
	movq	$96, %rcx
	movq	%rcx, -128(%rbp)
	movq	-128(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -128(%rbp)
	movq	-128(%rbp), %rcx
	movq	$32, 0(%rcx)
	movq	-32(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -136(%rbp)
	movq	-136(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -136(%rbp)
	movq	-32(%rbp), %rcx
	movq	-136(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -32(%rbp)
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
	callq	_Iprint_pai
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-8(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -152(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-152(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_Iprintai_pai
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-8(%rbp), %rdx
	movq	%rdx, %rax
	leave
	ret

	.globl	_Iprintai_pai
	.align	4
_Iprintai_pai:
	enter	$256, $0
	movq	%rdi, %rcx
	movq	%rcx, -8(%rbp)
	movq	$0, %rcx
	movq	%rcx, -16(%rbp)
	movq	$16, %rcx
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
	movq	$1, 0(%rcx)
	movq	$8, %rcx
	movq	%rcx, -48(%rbp)
	movq	-48(%rbp), %rcx
	movq	-40(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -48(%rbp)
	movq	-48(%rbp), %rcx
	movq	$123, 0(%rcx)
	movq	-40(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -56(%rbp)
	movq	-56(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -56(%rbp)
	movq	-40(%rbp), %rcx
	movq	-56(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -40(%rbp)
	movq	-40(%rbp), %rdx
	movq	%rdx, %rcx
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
	callq	_Iprint_pai
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
l18:
	movq	-8(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -72(%rbp)
	movq	-72(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -72(%rbp)
	movq	-72(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -80(%rbp)
	movq	-16(%rbp), %rcx
	movq	-80(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -16(%rbp)
	jl	l19
l20:
	movq	$16, %rcx
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
	movq	$1, 0(%rcx)
	movq	$8, %rcx
	movq	%rcx, -112(%rbp)
	movq	-112(%rbp), %rcx
	movq	-104(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -112(%rbp)
	movq	-112(%rbp), %rcx
	movq	$125, 0(%rcx)
	movq	-104(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -120(%rbp)
	movq	-120(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -120(%rbp)
	movq	-104(%rbp), %rcx
	movq	-120(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -104(%rbp)
	movq	-104(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -128(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-128(%rbp), %rdx
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
l19:
	movq	-16(%rbp), %rcx
	cmpq	$0, %rcx
	movq	%rcx, -16(%rbp)
	jg	l21
l22:
	movq	-16(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -136(%rbp)
	movq	-8(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -144(%rbp)
	movq	-144(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -144(%rbp)
	movq	-144(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -152(%rbp)
	movq	-136(%rbp), %rcx
	cmpq	$0, %rcx
	movq	%rcx, -136(%rbp)
	jl	l24
l23:
	movq	-136(%rbp), %rcx
	movq	-152(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -136(%rbp)
	jl	l25
l24:
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
l25:
	movq	$8, %rcx
	movq	%rcx, -160(%rbp)
	movq	-160(%rbp), %rcx
	movq	-136(%rbp), %rdx
	imulq	%rdx, %rcx
	movq	%rcx, -160(%rbp)
	movq	-8(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -168(%rbp)
	movq	-168(%rbp), %rcx
	movq	-160(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -168(%rbp)
	movq	-168(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -176(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-176(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %rcx
	movq	%rcx, -184(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-184(%rbp), %rdx
	movq	%rdx, %rcx
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
	callq	_Iprint_pai
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
	movq	%rcx, -200(%rbp)
	movq	-200(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -200(%rbp)
	movq	-16(%rbp), %rcx
	movq	-200(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -16(%rbp)
	jmp	l18
l21:
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
	movq	-216(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -224(%rbp)
	movq	-224(%rbp), %rcx
	movq	$2, 0(%rcx)
	movq	$8, %rcx
	movq	%rcx, -232(%rbp)
	movq	-232(%rbp), %rcx
	movq	-224(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -232(%rbp)
	movq	-232(%rbp), %rcx
	movq	$44, 0(%rcx)
	movq	$16, %rcx
	movq	%rcx, -240(%rbp)
	movq	-240(%rbp), %rcx
	movq	-224(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -240(%rbp)
	movq	-240(%rbp), %rcx
	movq	$32, 0(%rcx)
	movq	-224(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -248(%rbp)
	movq	-248(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -248(%rbp)
	movq	-224(%rbp), %rcx
	movq	-248(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -224(%rbp)
	movq	-224(%rbp), %rdx
	movq	%rdx, %rcx
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
	callq	_Iprint_pai
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	jmp	l22
