	.text
	.globl	_Igcd_iii
	.align	4
_Igcd_iii:
	enter	$16, $0
	movq	%rdi, %rcx
	movq	%rcx, -8(%rbp)
	movq	%rsi, %rcx
	movq	%rcx, -16(%rbp)
	movq	-16(%rbp), %rdx
	movq	%rdx, %rax
	leave
	ret

	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$712, $0
	movq	%rdi, %rcx
	movq	%rcx, -8(%rbp)
	movq	$272, %rcx
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
	movq	$33, 0(%rcx)
	movq	$8, %rcx
	movq	%rcx, -40(%rbp)
	movq	-40(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -40(%rbp)
	movq	-40(%rbp), %rcx
	movq	$76, 0(%rcx)
	movq	$16, %rcx
	movq	%rcx, -48(%rbp)
	movq	-48(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -48(%rbp)
	movq	-48(%rbp), %rcx
	movq	$97, 0(%rcx)
	movq	$24, %rcx
	movq	%rcx, -56(%rbp)
	movq	-56(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -56(%rbp)
	movq	-56(%rbp), %rcx
	movq	$114, 0(%rcx)
	movq	$32, %rcx
	movq	%rcx, -64(%rbp)
	movq	-64(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -64(%rbp)
	movq	-64(%rbp), %rcx
	movq	$103, 0(%rcx)
	movq	$40, %rcx
	movq	%rcx, -72(%rbp)
	movq	-72(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -72(%rbp)
	movq	-72(%rbp), %rcx
	movq	$101, 0(%rcx)
	movq	$48, %rcx
	movq	%rcx, -80(%rbp)
	movq	-80(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	movq	-80(%rbp), %rcx
	movq	$115, 0(%rcx)
	movq	$56, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rcx
	movq	$116, 0(%rcx)
	movq	$64, %rcx
	movq	%rcx, -96(%rbp)
	movq	-96(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -96(%rbp)
	movq	-96(%rbp), %rcx
	movq	$32, 0(%rcx)
	movq	$72, %rcx
	movq	%rcx, -104(%rbp)
	movq	-104(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -104(%rbp)
	movq	-104(%rbp), %rcx
	movq	$112, 0(%rcx)
	movq	$80, %rcx
	movq	%rcx, -112(%rbp)
	movq	-112(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -112(%rbp)
	movq	-112(%rbp), %rcx
	movq	$114, 0(%rcx)
	movq	$88, %rcx
	movq	%rcx, -120(%rbp)
	movq	-120(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -120(%rbp)
	movq	-120(%rbp), %rcx
	movq	$105, 0(%rcx)
	movq	$96, %rcx
	movq	%rcx, -128(%rbp)
	movq	-128(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -128(%rbp)
	movq	-128(%rbp), %rcx
	movq	$109, 0(%rcx)
	movq	$104, %rcx
	movq	%rcx, -136(%rbp)
	movq	-136(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -136(%rbp)
	movq	-136(%rbp), %rcx
	movq	$101, 0(%rcx)
	movq	$112, %rcx
	movq	%rcx, -144(%rbp)
	movq	-144(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -144(%rbp)
	movq	-144(%rbp), %rcx
	movq	$32, 0(%rcx)
	movq	$120, %rcx
	movq	%rcx, -152(%rbp)
	movq	-152(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -152(%rbp)
	movq	-152(%rbp), %rcx
	movq	$108, 0(%rcx)
	movq	$128, %rcx
	movq	%rcx, -160(%rbp)
	movq	-160(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -160(%rbp)
	movq	-160(%rbp), %rcx
	movq	$101, 0(%rcx)
	movq	$136, %rcx
	movq	%rcx, -168(%rbp)
	movq	-168(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -168(%rbp)
	movq	-168(%rbp), %rcx
	movq	$115, 0(%rcx)
	movq	$144, %rcx
	movq	%rcx, -176(%rbp)
	movq	-176(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -176(%rbp)
	movq	-176(%rbp), %rcx
	movq	$115, 0(%rcx)
	movq	$152, %rcx
	movq	%rcx, -184(%rbp)
	movq	-184(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -184(%rbp)
	movq	-184(%rbp), %rcx
	movq	$32, 0(%rcx)
	movq	$160, %rcx
	movq	%rcx, -192(%rbp)
	movq	-192(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -192(%rbp)
	movq	-192(%rbp), %rcx
	movq	$116, 0(%rcx)
	movq	$168, %rcx
	movq	%rcx, -200(%rbp)
	movq	-200(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -200(%rbp)
	movq	-200(%rbp), %rcx
	movq	$104, 0(%rcx)
	movq	$176, %rcx
	movq	%rcx, -208(%rbp)
	movq	-208(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -208(%rbp)
	movq	-208(%rbp), %rcx
	movq	$97, 0(%rcx)
	movq	$184, %rcx
	movq	%rcx, -216(%rbp)
	movq	-216(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -216(%rbp)
	movq	-216(%rbp), %rcx
	movq	$110, 0(%rcx)
	movq	$192, %rcx
	movq	%rcx, -224(%rbp)
	movq	-224(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -224(%rbp)
	movq	-224(%rbp), %rcx
	movq	$32, 0(%rcx)
	movq	$200, %rcx
	movq	%rcx, -232(%rbp)
	movq	-232(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -232(%rbp)
	movq	-232(%rbp), %rcx
	movq	$49, 0(%rcx)
	movq	$208, %rcx
	movq	%rcx, -240(%rbp)
	movq	-240(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -240(%rbp)
	movq	-240(%rbp), %rcx
	movq	$44, 0(%rcx)
	movq	$216, %rcx
	movq	%rcx, -248(%rbp)
	movq	-248(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -248(%rbp)
	movq	-248(%rbp), %rcx
	movq	$48, 0(%rcx)
	movq	$224, %rcx
	movq	%rcx, -256(%rbp)
	movq	-256(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -256(%rbp)
	movq	-256(%rbp), %rcx
	movq	$48, 0(%rcx)
	movq	$232, %rcx
	movq	%rcx, -264(%rbp)
	movq	-264(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -264(%rbp)
	movq	-264(%rbp), %rcx
	movq	$48, 0(%rcx)
	movq	$240, %rcx
	movq	%rcx, -272(%rbp)
	movq	-272(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -272(%rbp)
	movq	-272(%rbp), %rcx
	movq	$32, 0(%rcx)
	movq	$248, %rcx
	movq	%rcx, -280(%rbp)
	movq	-280(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -280(%rbp)
	movq	-280(%rbp), %rcx
	movq	$105, 0(%rcx)
	movq	$256, %rcx
	movq	%rcx, -288(%rbp)
	movq	-288(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -288(%rbp)
	movq	-288(%rbp), %rcx
	movq	$115, 0(%rcx)
	movq	$264, %rcx
	movq	%rcx, -296(%rbp)
	movq	-296(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -296(%rbp)
	movq	-296(%rbp), %rcx
	movq	$32, 0(%rcx)
	movq	-32(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -304(%rbp)
	movq	-304(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -304(%rbp)
	movq	-32(%rbp), %rcx
	movq	-304(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -32(%rbp)
	movq	-32(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -312(%rbp)
	movq	-312(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -312(%rbp)
	movq	-312(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -320(%rbp)
	movq	$1000, %rcx
	movq	%rcx, -328(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-328(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_Ilargestprime_ii
	movq	%rax, %rcx
	movq	%rcx, -336(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-336(%rbp), %rdx
	movq	%rdx, %rcx
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
	callq	_IunparseInt_aii
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
	subq	$8, %rcx
	movq	%rcx, -360(%rbp)
	movq	-320(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -368(%rbp)
	movq	-368(%rbp), %r11
	movq	-360(%rbp), %rcx
	addq	0(%rcx), %r11
	movq	%r11, -368(%rbp)
	movq	-368(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -376(%rbp)
	movq	-376(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -376(%rbp)
	movq	-376(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -384(%rbp)
	movq	-384(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -384(%rbp)
	movq	-384(%rbp), %rdx
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
	callq	_I_alloc_i
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
	movq	-32(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -416(%rbp)
	movq	-416(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -416(%rbp)
	movq	-416(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -424(%rbp)
	movq	$1000, %rcx
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
	callq	_Ilargestprime_ii
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
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-448(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %rcx
	movq	%rcx, -456(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-456(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -464(%rbp)
	movq	-464(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -464(%rbp)
	movq	-424(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -472(%rbp)
	movq	-472(%rbp), %r11
	movq	-464(%rbp), %rcx
	addq	0(%rcx), %r11
	movq	%r11, -472(%rbp)
	movq	-472(%rbp), %r11
	movq	-408(%rbp), %rcx
	movq	%r11, 0(%rcx)
	movq	-408(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -480(%rbp)
	movq	-480(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -480(%rbp)
	movq	-408(%rbp), %rcx
	movq	-480(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -408(%rbp)
	movq	$0, %rcx
	movq	%rcx, -488(%rbp)
l0:
	movq	-32(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -496(%rbp)
	movq	-496(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -496(%rbp)
	movq	-496(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -504(%rbp)
	movq	-488(%rbp), %rcx
	movq	-504(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -488(%rbp)
	jl	l1
l2:
	movq	$0, %rcx
	movq	%rcx, -512(%rbp)
	movq	-32(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -520(%rbp)
	movq	-520(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -520(%rbp)
	movq	-520(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -528(%rbp)
l3:
	movq	-512(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -536(%rbp)
	movq	$1000, %rcx
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
	callq	_Ilargestprime_ii
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
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-560(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %rcx
	movq	%rcx, -568(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-568(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -576(%rbp)
	movq	-576(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -576(%rbp)
	movq	-576(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -584(%rbp)
	movq	-536(%rbp), %rcx
	movq	-584(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -536(%rbp)
	jl	l4
l5:
	movq	-408(%rbp), %rdx
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
	callq	_Iprint_pai
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
	movq	-488(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -600(%rbp)
	movq	-600(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -600(%rbp)
	movq	-32(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -608(%rbp)
	movq	-608(%rbp), %rcx
	movq	-600(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -608(%rbp)
	movq	-488(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -616(%rbp)
	movq	-616(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -616(%rbp)
	movq	-408(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -624(%rbp)
	movq	-624(%rbp), %rcx
	movq	-616(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -624(%rbp)
	movq	-608(%rbp), %rcx
	movq	0(%rcx), %rcx
	movq	-624(%rbp), %rcx
	movq	%rcx, 0(%rcx)
	movq	-488(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -632(%rbp)
	movq	-632(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -632(%rbp)
	movq	-488(%rbp), %rcx
	movq	-632(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -488(%rbp)
	jmp	l0
l4:
	movq	$1000, %rcx
	movq	%rcx, -640(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-640(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_Ilargestprime_ii
	movq	%rax, %rcx
	movq	%rcx, -648(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-648(%rbp), %rdx
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
	callq	_IunparseInt_aii
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
	movq	-512(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -672(%rbp)
	movq	-672(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -672(%rbp)
	movq	-664(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -680(%rbp)
	movq	-680(%rbp), %rcx
	movq	-672(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -680(%rbp)
	movq	-512(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -688(%rbp)
	movq	-688(%rbp), %rcx
	movq	-528(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -688(%rbp)
	movq	-688(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -696(%rbp)
	movq	-696(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -696(%rbp)
	movq	-408(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -704(%rbp)
	movq	-704(%rbp), %rcx
	movq	-696(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -704(%rbp)
	movq	-680(%rbp), %rcx
	movq	0(%rcx), %rcx
	movq	-704(%rbp), %rcx
	movq	%rcx, 0(%rcx)
	movq	-512(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -712(%rbp)
	movq	-712(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -712(%rbp)
	movq	-512(%rbp), %rcx
	movq	-712(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -512(%rbp)
	jmp	l3

	.globl	_Iisprime_bi
	.align	4
_Iisprime_bi:
	enter	$16, $0
	movq	%rdi, %rcx
	movq	%rcx, -8(%rbp)
	movq	$2, %rcx
	movq	%rcx, -16(%rbp)
	movq	$1, %rax
	leave
	ret

	.globl	_Ilargestprime_ii
	.align	4
_Ilargestprime_ii:
	enter	$16, $0
	movq	%rdi, %rcx
	movq	%rcx, -8(%rbp)
	movq	-8(%rbp), %rcx
	movq	$1, %rcx
	movq	%rcx, -8(%rbp)
	movq	$1, %rcx
	movq	%rcx, -16(%rbp)
	movq	-16(%rbp), %rdx
	movq	%rdx, %rax
	leave
	ret
