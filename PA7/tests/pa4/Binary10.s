	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$1048, $0
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
	movq	-200(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -208(%rbp)
	movq	-144(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -216(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-216(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_If_aiai
	movq	%rax, %rcx
	movq	%rcx, -224(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-224(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -232(%rbp)
	movq	-208(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -240(%rbp)
	movq	-240(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -240(%rbp)
	movq	-232(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -248(%rbp)
	movq	-248(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -248(%rbp)
	movq	-240(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -256(%rbp)
	movq	-256(%rbp), %r11
	movq	-248(%rbp), %rcx
	addq	0(%rcx), %r11
	movq	%r11, -256(%rbp)
	movq	-256(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -264(%rbp)
	movq	-264(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -272(%rbp)
	movq	-272(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -272(%rbp)
	movq	-272(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -280(%rbp)
	movq	-280(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -280(%rbp)
	movq	-280(%rbp), %rdx
	movq	%rdx, %rcx
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
	movq	-264(%rbp), %r11
	movq	-304(%rbp), %rcx
	movq	%r11, 0(%rcx)
	movq	-304(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -312(%rbp)
	movq	-312(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -312(%rbp)
	movq	-304(%rbp), %rcx
	movq	-312(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -304(%rbp)
	movq	$0, %rcx
	movq	%rcx, -320(%rbp)
l0:
	movq	-208(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -328(%rbp)
	movq	-328(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -328(%rbp)
	movq	-328(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -336(%rbp)
	movq	-320(%rbp), %rcx
	movq	-336(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -320(%rbp)
	jl	l1
l2:
	movq	$0, %rcx
	movq	%rcx, -344(%rbp)
	movq	-208(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -352(%rbp)
	movq	-352(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -352(%rbp)
	movq	-352(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -360(%rbp)
l3:
	movq	-232(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -368(%rbp)
	movq	-368(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -368(%rbp)
	movq	-368(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -376(%rbp)
	movq	-344(%rbp), %rcx
	movq	-376(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -344(%rbp)
	jl	l4
l5:
	movq	-304(%rbp), %rdx
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
	movq	%rcx, -392(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-392(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_If_aiai
	movq	%rax, %rcx
	movq	%rcx, -400(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-400(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -408(%rbp)
	movq	-184(%rbp), %rdx
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
	callq	_If_aiai
	movq	%rax, %rcx
	movq	%rcx, -424(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-424(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -432(%rbp)
	movq	-408(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -440(%rbp)
	movq	-440(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -440(%rbp)
	movq	-432(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -448(%rbp)
	movq	-448(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -448(%rbp)
	movq	-440(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -456(%rbp)
	movq	-456(%rbp), %r11
	movq	-448(%rbp), %rcx
	addq	0(%rcx), %r11
	movq	%r11, -456(%rbp)
	movq	-456(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -464(%rbp)
	movq	-464(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -472(%rbp)
	movq	-472(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -472(%rbp)
	movq	-472(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -480(%rbp)
	movq	-480(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -480(%rbp)
	movq	-480(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -488(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-488(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -496(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-496(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -504(%rbp)
	movq	-464(%rbp), %r11
	movq	-504(%rbp), %rcx
	movq	%r11, 0(%rcx)
	movq	-504(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -512(%rbp)
	movq	-512(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -512(%rbp)
	movq	-504(%rbp), %rcx
	movq	-512(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -504(%rbp)
	movq	$0, %rcx
	movq	%rcx, -520(%rbp)
l6:
	movq	-408(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -528(%rbp)
	movq	-528(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -528(%rbp)
	movq	-528(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -536(%rbp)
	movq	-520(%rbp), %rcx
	movq	-536(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -520(%rbp)
	jl	l7
l8:
	movq	$0, %rcx
	movq	%rcx, -544(%rbp)
	movq	-408(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -552(%rbp)
	movq	-552(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -552(%rbp)
	movq	-552(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -560(%rbp)
l9:
	movq	-432(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -568(%rbp)
	movq	-568(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -568(%rbp)
	movq	-568(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -576(%rbp)
	movq	-544(%rbp), %rcx
	movq	-576(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -544(%rbp)
	jl	l10
l11:
	movq	-504(%rbp), %rdx
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
	movq	%rcx, -592(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-592(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_If_aiai
	movq	%rax, %rcx
	movq	%rcx, -600(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-600(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -608(%rbp)
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
	movq	-608(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -640(%rbp)
	movq	-640(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -640(%rbp)
	movq	-632(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -648(%rbp)
	movq	-648(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -648(%rbp)
	movq	-640(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -656(%rbp)
	movq	-656(%rbp), %r11
	movq	-648(%rbp), %rcx
	addq	0(%rcx), %r11
	movq	%r11, -656(%rbp)
	movq	-656(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -664(%rbp)
	movq	-664(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -672(%rbp)
	movq	-672(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -672(%rbp)
	movq	-672(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -680(%rbp)
	movq	-680(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -680(%rbp)
	movq	-680(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -688(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-688(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -696(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-696(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -704(%rbp)
	movq	-664(%rbp), %r11
	movq	-704(%rbp), %rcx
	movq	%r11, 0(%rcx)
	movq	-704(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -712(%rbp)
	movq	-712(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -712(%rbp)
	movq	-704(%rbp), %rcx
	movq	-712(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -704(%rbp)
	movq	$0, %rcx
	movq	%rcx, -720(%rbp)
l12:
	movq	-608(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -728(%rbp)
	movq	-728(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -728(%rbp)
	movq	-728(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -736(%rbp)
	movq	-720(%rbp), %rcx
	movq	-736(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -720(%rbp)
	jl	l13
l14:
	movq	$0, %rcx
	movq	%rcx, -744(%rbp)
	movq	-608(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -752(%rbp)
	movq	-752(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -752(%rbp)
	movq	-752(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -760(%rbp)
l15:
	movq	-632(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -768(%rbp)
	movq	-768(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -768(%rbp)
	movq	-768(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -776(%rbp)
	movq	-744(%rbp), %rcx
	movq	-776(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -744(%rbp)
	jl	l16
l17:
	movq	-704(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -784(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-784(%rbp), %rdx
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
	movq	-320(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -792(%rbp)
	movq	-792(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -792(%rbp)
	movq	-208(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -800(%rbp)
	movq	-800(%rbp), %rcx
	movq	-792(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -800(%rbp)
	movq	-320(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -808(%rbp)
	movq	-808(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -808(%rbp)
	movq	-304(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -816(%rbp)
	movq	-816(%rbp), %rcx
	movq	-808(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -816(%rbp)
	movq	-800(%rbp), %rcx
	movq	0(%rcx), %r10
	movq	-816(%rbp), %rcx
	movq	%r10, 0(%rcx)
	movq	-320(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -824(%rbp)
	movq	-824(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -824(%rbp)
	movq	-320(%rbp), %rcx
	movq	-824(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -320(%rbp)
	jmp	l0
l4:
	movq	-344(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -832(%rbp)
	movq	-832(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -832(%rbp)
	movq	-232(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -840(%rbp)
	movq	-840(%rbp), %rcx
	movq	-832(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -840(%rbp)
	movq	-344(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -848(%rbp)
	movq	-848(%rbp), %rcx
	movq	-360(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -848(%rbp)
	movq	-848(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -856(%rbp)
	movq	-856(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -856(%rbp)
	movq	-304(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -864(%rbp)
	movq	-864(%rbp), %rcx
	movq	-856(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -864(%rbp)
	movq	-840(%rbp), %rcx
	movq	0(%rcx), %r10
	movq	-864(%rbp), %rcx
	movq	%r10, 0(%rcx)
	movq	-344(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -872(%rbp)
	movq	-872(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -872(%rbp)
	movq	-344(%rbp), %rcx
	movq	-872(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -344(%rbp)
	jmp	l3
l7:
	movq	-520(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -880(%rbp)
	movq	-880(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -880(%rbp)
	movq	-408(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -888(%rbp)
	movq	-888(%rbp), %rcx
	movq	-880(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -888(%rbp)
	movq	-520(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -896(%rbp)
	movq	-896(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -896(%rbp)
	movq	-504(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -904(%rbp)
	movq	-904(%rbp), %rcx
	movq	-896(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -904(%rbp)
	movq	-888(%rbp), %rcx
	movq	0(%rcx), %r10
	movq	-904(%rbp), %rcx
	movq	%r10, 0(%rcx)
	movq	-520(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -912(%rbp)
	movq	-912(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -912(%rbp)
	movq	-520(%rbp), %rcx
	movq	-912(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -520(%rbp)
	jmp	l6
l10:
	movq	-544(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -920(%rbp)
	movq	-920(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -920(%rbp)
	movq	-432(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -928(%rbp)
	movq	-928(%rbp), %rcx
	movq	-920(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -928(%rbp)
	movq	-544(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -936(%rbp)
	movq	-936(%rbp), %rcx
	movq	-560(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -936(%rbp)
	movq	-936(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -944(%rbp)
	movq	-944(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -944(%rbp)
	movq	-504(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -952(%rbp)
	movq	-952(%rbp), %rcx
	movq	-944(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -952(%rbp)
	movq	-928(%rbp), %rcx
	movq	0(%rcx), %r10
	movq	-952(%rbp), %rcx
	movq	%r10, 0(%rcx)
	movq	-544(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -960(%rbp)
	movq	-960(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -960(%rbp)
	movq	-544(%rbp), %rcx
	movq	-960(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -544(%rbp)
	jmp	l9
l13:
	movq	-720(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -968(%rbp)
	movq	-968(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -968(%rbp)
	movq	-608(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -976(%rbp)
	movq	-976(%rbp), %rcx
	movq	-968(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -976(%rbp)
	movq	-720(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -984(%rbp)
	movq	-984(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -984(%rbp)
	movq	-704(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -992(%rbp)
	movq	-992(%rbp), %rcx
	movq	-984(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -992(%rbp)
	movq	-976(%rbp), %rcx
	movq	0(%rcx), %r10
	movq	-992(%rbp), %rcx
	movq	%r10, 0(%rcx)
	movq	-720(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1000(%rbp)
	movq	-1000(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -1000(%rbp)
	movq	-720(%rbp), %rcx
	movq	-1000(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -720(%rbp)
	jmp	l12
l16:
	movq	-744(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1008(%rbp)
	movq	-1008(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -1008(%rbp)
	movq	-632(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1016(%rbp)
	movq	-1016(%rbp), %rcx
	movq	-1008(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -1016(%rbp)
	movq	-744(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1024(%rbp)
	movq	-1024(%rbp), %rcx
	movq	-760(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -1024(%rbp)
	movq	-1024(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1032(%rbp)
	movq	-1032(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -1032(%rbp)
	movq	-704(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1040(%rbp)
	movq	-1040(%rbp), %rcx
	movq	-1032(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -1040(%rbp)
	movq	-1016(%rbp), %rcx
	movq	0(%rcx), %r10
	movq	-1040(%rbp), %rcx
	movq	%r10, 0(%rcx)
	movq	-744(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1048(%rbp)
	movq	-1048(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -1048(%rbp)
	movq	-744(%rbp), %rcx
	movq	-1048(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -744(%rbp)
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
