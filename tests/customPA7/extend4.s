	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$5360, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
	movq	$5, %r14
	movq	%r14, -64(%rbp)
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -72(%rbp)
	movq	-72(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -72(%rbp)
	movq	-72(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	$8, %r14
	movq	%r14, -88(%rbp)
	movq	-80(%rbp), %r12
	movq	-88(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	-80(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -96(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-96(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -104(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-104(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -112(%rbp)
	movq	-64(%rbp), %r14
	movq	-112(%rbp), %r12
	movq	%r14, (%r12)
	movq	-112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -120(%rbp)
	movq	-120(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -120(%rbp)
	movq	-120(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -112(%rbp)
	movq	$0, %r14
	movq	%r14, -128(%rbp)
l16:
	movq	-128(%rbp), %r12
	movq	-64(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -128(%rbp)
	jl	l17
	movq	-112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	$0, %r14
	movq	%r14, -152(%rbp)
	movq	-144(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	-160(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -160(%rbp)
	movq	-160(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -168(%rbp)
	movq	-152(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -152(%rbp)
	jl	l20
	movq	-152(%rbp), %r12
	movq	-168(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -152(%rbp)
	jl	l21
l20:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	subq	$8, %rsp
	callq	_I_outOfBounds_p
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax

	.globl	_label0
	.align	4
_label0:
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l17:
	movq	-112(%rbp), %r12
	movq	-128(%rbp), %r13
	leaq	(%r12,%r13,8), %r14
	movq	%r14, -176(%rbp)
	movq	-176(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -184(%rbp)
	movq	$0, %r14
	movq	%r14, -192(%rbp)
	movq	-192(%rbp), %r14
	movq	-184(%rbp), %r12
	movq	%r14, (%r12)
	movq	-128(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -200(%rbp)
	movq	-200(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -200(%rbp)
	movq	-200(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -128(%rbp)
	jmp	l16
l21:
	movq	-144(%rbp), %r12
	movq	-152(%rbp), %r13
	leaq	(%r12,%r13,8), %r14
	movq	%r14, -208(%rbp)
	movq	-208(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -216(%rbp)
	movq	_I_size_Human(%rip), %r14
	movq	%r14, -224(%rbp)
	movq	$8, %r14
	movq	%r14, -232(%rbp)
	movq	-224(%rbp), %r12
	movq	-232(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -224(%rbp)
	movq	-224(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -240(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-240(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -248(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-248(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -256(%rbp)
	movq	_I_vt_Human(%rip), %r14
	movq	%r14, -264(%rbp)
	movq	-264(%rbp), %r14
	movq	-256(%rbp), %r12
	movq	%r14, (%r12)
	movq	-256(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -272(%rbp)
	movq	-272(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -280(%rbp)
	movq	-280(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -288(%rbp)
	movq	-288(%rbp), %r14
	addq	$56, %r14
	movq	%r14, -288(%rbp)
	movq	-288(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -296(%rbp)
	movq	-272(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -304(%rbp)
	movq	$30, %r14
	movq	%r14, -312(%rbp)
	movq	$100, %r14
	movq	%r14, -320(%rbp)
	movq	$50, %r14
	movq	%r14, -328(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-328(%rbp), %r14
	movq	%r14, %rcx
	movq	-320(%rbp), %r14
	movq	%r14, %rdx
	movq	-312(%rbp), %r14
	movq	%r14, %rsi
	movq	-304(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	movq	-296(%rbp), %r14
	callq	*%r14
	movq	%rax, %r12
	movq	%r12, -336(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-336(%rbp), %r14
	movq	-216(%rbp), %r12
	movq	%r14, (%r12)
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -344(%rbp)
	movq	$1, %r14
	movq	%r14, -352(%rbp)
	movq	-344(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -360(%rbp)
	movq	-360(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -360(%rbp)
	movq	-360(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -368(%rbp)
	movq	-352(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -352(%rbp)
	jl	l23
	movq	-352(%rbp), %r12
	movq	-368(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -352(%rbp)
	jl	l24
l23:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	subq	$8, %rsp
	callq	_I_outOfBounds_p
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	_label0
l24:
	movq	-344(%rbp), %r12
	movq	-352(%rbp), %r13
	leaq	(%r12,%r13,8), %r14
	movq	%r14, -376(%rbp)
	movq	-376(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -384(%rbp)
	movq	_I_size_Human(%rip), %r14
	movq	%r14, -392(%rbp)
	movq	$8, %r14
	movq	%r14, -400(%rbp)
	movq	-392(%rbp), %r12
	movq	-400(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -392(%rbp)
	movq	-392(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -408(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-408(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -416(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-416(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -424(%rbp)
	movq	_I_vt_Human(%rip), %r14
	movq	%r14, -432(%rbp)
	movq	-432(%rbp), %r14
	movq	-424(%rbp), %r12
	movq	%r14, (%r12)
	movq	-424(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -440(%rbp)
	movq	-440(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -448(%rbp)
	movq	-448(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -456(%rbp)
	movq	-456(%rbp), %r14
	addq	$56, %r14
	movq	%r14, -456(%rbp)
	movq	-456(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -464(%rbp)
	movq	-440(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -472(%rbp)
	movq	$20, %r14
	movq	%r14, -480(%rbp)
	movq	$130, %r14
	movq	%r14, -488(%rbp)
	movq	$23, %r14
	movq	%r14, -496(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-496(%rbp), %r14
	movq	%r14, %rcx
	movq	-488(%rbp), %r14
	movq	%r14, %rdx
	movq	-480(%rbp), %r14
	movq	%r14, %rsi
	movq	-472(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	movq	-464(%rbp), %r14
	callq	*%r14
	movq	%rax, %r12
	movq	%r12, -504(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-504(%rbp), %r14
	movq	-384(%rbp), %r12
	movq	%r14, (%r12)
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -512(%rbp)
	movq	$2, %r14
	movq	%r14, -520(%rbp)
	movq	-512(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -528(%rbp)
	movq	-528(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -528(%rbp)
	movq	-528(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -536(%rbp)
	movq	-520(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -520(%rbp)
	jl	l26
	movq	-520(%rbp), %r12
	movq	-536(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -520(%rbp)
	jl	l27
l26:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	subq	$8, %rsp
	callq	_I_outOfBounds_p
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	_label0
l27:
	movq	-512(%rbp), %r12
	movq	-520(%rbp), %r13
	leaq	(%r12,%r13,8), %r14
	movq	%r14, -544(%rbp)
	movq	-544(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -552(%rbp)
	movq	_I_size_Dog(%rip), %r14
	movq	%r14, -560(%rbp)
	movq	$8, %r14
	movq	%r14, -568(%rbp)
	movq	-560(%rbp), %r12
	movq	-568(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -560(%rbp)
	movq	-560(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -576(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-576(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -584(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-584(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -592(%rbp)
	movq	_I_vt_Dog(%rip), %r14
	movq	%r14, -600(%rbp)
	movq	-600(%rbp), %r14
	movq	-592(%rbp), %r12
	movq	%r14, (%r12)
	movq	-592(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -608(%rbp)
	movq	-608(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -616(%rbp)
	movq	-616(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -624(%rbp)
	movq	-624(%rbp), %r14
	addq	$56, %r14
	movq	%r14, -624(%rbp)
	movq	-624(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -632(%rbp)
	movq	-608(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -640(%rbp)
	movq	$40, %r14
	movq	%r14, -648(%rbp)
	movq	$150, %r14
	movq	%r14, -656(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-656(%rbp), %r14
	movq	%r14, %rdx
	movq	-648(%rbp), %r14
	movq	%r14, %rsi
	movq	-640(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	movq	-632(%rbp), %r14
	callq	*%r14
	movq	%rax, %r12
	movq	%r12, -664(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-664(%rbp), %r14
	movq	-552(%rbp), %r12
	movq	%r14, (%r12)
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -672(%rbp)
	movq	$3, %r14
	movq	%r14, -680(%rbp)
	movq	-672(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -688(%rbp)
	movq	-688(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -688(%rbp)
	movq	-688(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -696(%rbp)
	movq	-680(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -680(%rbp)
	jl	l29
	movq	-680(%rbp), %r12
	movq	-696(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -680(%rbp)
	jl	l30
l29:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	subq	$8, %rsp
	callq	_I_outOfBounds_p
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	_label0
l30:
	movq	-672(%rbp), %r12
	movq	-680(%rbp), %r13
	leaq	(%r12,%r13,8), %r14
	movq	%r14, -704(%rbp)
	movq	-704(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -712(%rbp)
	movq	_I_size_Dog(%rip), %r14
	movq	%r14, -720(%rbp)
	movq	$8, %r14
	movq	%r14, -728(%rbp)
	movq	-720(%rbp), %r12
	movq	-728(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -720(%rbp)
	movq	-720(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -736(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-736(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -744(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-744(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -752(%rbp)
	movq	_I_vt_Dog(%rip), %r14
	movq	%r14, -760(%rbp)
	movq	-760(%rbp), %r14
	movq	-752(%rbp), %r12
	movq	%r14, (%r12)
	movq	-752(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -768(%rbp)
	movq	-768(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -776(%rbp)
	movq	-776(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -784(%rbp)
	movq	-784(%rbp), %r14
	addq	$56, %r14
	movq	%r14, -784(%rbp)
	movq	-784(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -792(%rbp)
	movq	-768(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -800(%rbp)
	movq	$100, %r14
	movq	%r14, -808(%rbp)
	movq	$20, %r14
	movq	%r14, -816(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-816(%rbp), %r14
	movq	%r14, %rdx
	movq	-808(%rbp), %r14
	movq	%r14, %rsi
	movq	-800(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	movq	-792(%rbp), %r14
	callq	*%r14
	movq	%rax, %r12
	movq	%r12, -824(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-824(%rbp), %r14
	movq	-712(%rbp), %r12
	movq	%r14, (%r12)
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -832(%rbp)
	movq	$4, %r14
	movq	%r14, -840(%rbp)
	movq	-832(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -848(%rbp)
	movq	-848(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -848(%rbp)
	movq	-848(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -856(%rbp)
	movq	-840(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -840(%rbp)
	jl	l32
	movq	-840(%rbp), %r12
	movq	-856(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -840(%rbp)
	jl	l33
l32:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	subq	$8, %rsp
	callq	_I_outOfBounds_p
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	_label0
l33:
	movq	-832(%rbp), %r12
	movq	-840(%rbp), %r13
	leaq	(%r12,%r13,8), %r14
	movq	%r14, -864(%rbp)
	movq	-864(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -872(%rbp)
	movq	_I_size_Human(%rip), %r14
	movq	%r14, -880(%rbp)
	movq	$8, %r14
	movq	%r14, -888(%rbp)
	movq	-880(%rbp), %r12
	movq	-888(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -880(%rbp)
	movq	-880(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -896(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-896(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -904(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-904(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -912(%rbp)
	movq	_I_vt_Human(%rip), %r14
	movq	%r14, -920(%rbp)
	movq	-920(%rbp), %r14
	movq	-912(%rbp), %r12
	movq	%r14, (%r12)
	movq	-912(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -928(%rbp)
	movq	-928(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -936(%rbp)
	movq	-936(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -944(%rbp)
	movq	-944(%rbp), %r14
	addq	$56, %r14
	movq	%r14, -944(%rbp)
	movq	-944(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -952(%rbp)
	movq	-928(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -960(%rbp)
	movq	$60, %r14
	movq	%r14, -968(%rbp)
	movq	$115, %r14
	movq	%r14, -976(%rbp)
	movq	$10, %r14
	movq	%r14, -984(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-984(%rbp), %r14
	movq	%r14, %rcx
	movq	-976(%rbp), %r14
	movq	%r14, %rdx
	movq	-968(%rbp), %r14
	movq	%r14, %rsi
	movq	-960(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	movq	-952(%rbp), %r14
	callq	*%r14
	movq	%rax, %r12
	movq	%r12, -992(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-992(%rbp), %r14
	movq	-872(%rbp), %r12
	movq	%r14, (%r12)
	movq	$0, %r14
	movq	%r14, -1000(%rbp)
l34:
	movq	-1000(%rbp), %r14
	cmpq	$5, %r14
	movq	%r14, -1000(%rbp)
	jl	l35
l40:
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1008(%rbp)
	movq	$0, %r14
	movq	%r14, -1016(%rbp)
	movq	-1008(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1024(%rbp)
	movq	-1024(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -1024(%rbp)
	movq	-1024(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -1032(%rbp)
	movq	-1016(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -1016(%rbp)
	jl	l48
	movq	-1016(%rbp), %r12
	movq	-1032(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -1016(%rbp)
	jl	l49
l48:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	subq	$8, %rsp
	callq	_I_outOfBounds_p
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	_label0
l35:
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1040(%rbp)
	movq	-1000(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1048(%rbp)
	movq	-1040(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1056(%rbp)
	movq	-1056(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -1056(%rbp)
	movq	-1056(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -1064(%rbp)
	movq	-1048(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -1048(%rbp)
	jl	l38
	movq	-1048(%rbp), %r12
	movq	-1064(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -1048(%rbp)
	jl	l39
l38:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	subq	$8, %rsp
	callq	_I_outOfBounds_p
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	_label0
l39:
	movq	-1040(%rbp), %r12
	movq	-1048(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -1072(%rbp)
	movq	-1072(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -1080(%rbp)
	movq	-1080(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1088(%rbp)
	movq	-1088(%rbp), %r14
	addq	$16, %r14
	movq	%r14, -1088(%rbp)
	movq	-1088(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -1096(%rbp)
	movq	-1072(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1104(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-1104(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	movq	-1096(%rbp), %r14
	callq	*%r14
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-1000(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1112(%rbp)
	movq	-1112(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -1112(%rbp)
	movq	-1112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1000(%rbp)
	jmp	l34
l49:
	movq	-1008(%rbp), %r12
	movq	-1016(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -1120(%rbp)
	movq	-1120(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -1128(%rbp)
	movq	-1128(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1136(%rbp)
	movq	-1136(%rbp), %r14
	addq	$32, %r14
	movq	%r14, -1136(%rbp)
	movq	-1136(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -1144(%rbp)
	movq	-1120(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1152(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-1152(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	movq	-1144(%rbp), %r14
	callq	*%r14
	movq	%rax, %r12
	movq	%r12, -1160(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-1160(%rbp), %r12
	movq	-1160(%rbp), %r14
	testq	%r14, %r12
	movq	%r12, -1160(%rbp)
	jnz	l41
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1168(%rbp)
	movq	$1, %r14
	movq	%r14, -1176(%rbp)
	movq	-1168(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1184(%rbp)
	movq	-1184(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -1184(%rbp)
	movq	-1184(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -1192(%rbp)
	movq	-1176(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -1176(%rbp)
	jl	l51
	movq	-1176(%rbp), %r12
	movq	-1192(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -1176(%rbp)
	jl	l52
l51:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	subq	$8, %rsp
	callq	_I_outOfBounds_p
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	_label0
l52:
	movq	-1168(%rbp), %r12
	movq	-1176(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -1200(%rbp)
	movq	-1200(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -1208(%rbp)
	movq	-1208(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1216(%rbp)
	movq	-1216(%rbp), %r14
	addq	$32, %r14
	movq	%r14, -1216(%rbp)
	movq	-1216(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -1224(%rbp)
	movq	-1200(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1232(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-1232(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	movq	-1224(%rbp), %r14
	callq	*%r14
	movq	%rax, %r12
	movq	%r12, -1240(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-1240(%rbp), %r12
	movq	-1240(%rbp), %r14
	testq	%r14, %r12
	movq	%r12, -1240(%rbp)
	jnz	l41
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1248(%rbp)
	movq	$2, %r14
	movq	%r14, -1256(%rbp)
	movq	-1248(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1264(%rbp)
	movq	-1264(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -1264(%rbp)
	movq	-1264(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -1272(%rbp)
	movq	-1256(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -1256(%rbp)
	jl	l54
	movq	-1256(%rbp), %r12
	movq	-1272(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -1256(%rbp)
	jl	l55
l54:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	subq	$8, %rsp
	callq	_I_outOfBounds_p
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	_label0
l55:
	movq	-1248(%rbp), %r12
	movq	-1256(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -1280(%rbp)
	movq	-1280(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -1288(%rbp)
	movq	-1288(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1296(%rbp)
	movq	-1296(%rbp), %r14
	addq	$32, %r14
	movq	%r14, -1296(%rbp)
	movq	-1296(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -1304(%rbp)
	movq	-1280(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1312(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-1312(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	movq	-1304(%rbp), %r14
	callq	*%r14
	movq	%rax, %r12
	movq	%r12, -1320(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-1320(%rbp), %r12
	movq	-1320(%rbp), %r14
	testq	%r14, %r12
	movq	%r12, -1320(%rbp)
	jnz	l41
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1328(%rbp)
	movq	$3, %r14
	movq	%r14, -1336(%rbp)
	movq	-1328(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1344(%rbp)
	movq	-1344(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -1344(%rbp)
	movq	-1344(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -1352(%rbp)
	movq	-1336(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -1336(%rbp)
	jl	l57
	movq	-1336(%rbp), %r12
	movq	-1352(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -1336(%rbp)
	jl	l58
l57:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	subq	$8, %rsp
	callq	_I_outOfBounds_p
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	_label0
l58:
	movq	-1328(%rbp), %r12
	movq	-1336(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -1360(%rbp)
	movq	-1360(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -1368(%rbp)
	movq	-1368(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1376(%rbp)
	movq	-1376(%rbp), %r14
	addq	$32, %r14
	movq	%r14, -1376(%rbp)
	movq	-1376(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -1384(%rbp)
	movq	-1360(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1392(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-1392(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	movq	-1384(%rbp), %r14
	callq	*%r14
	movq	%rax, %r12
	movq	%r12, -1400(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-1400(%rbp), %r12
	movq	-1400(%rbp), %r14
	testq	%r14, %r12
	movq	%r12, -1400(%rbp)
	jnz	l41
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1408(%rbp)
	movq	$4, %r14
	movq	%r14, -1416(%rbp)
	movq	-1408(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1424(%rbp)
	movq	-1424(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -1424(%rbp)
	movq	-1424(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -1432(%rbp)
	movq	-1416(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -1416(%rbp)
	jl	l60
	movq	-1416(%rbp), %r12
	movq	-1432(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -1416(%rbp)
	jl	l61
l60:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	subq	$8, %rsp
	callq	_I_outOfBounds_p
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	_label0
l61:
	movq	-1408(%rbp), %r12
	movq	-1416(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -1440(%rbp)
	movq	-1440(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -1448(%rbp)
	movq	-1448(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1456(%rbp)
	movq	-1456(%rbp), %r14
	addq	$32, %r14
	movq	%r14, -1456(%rbp)
	movq	-1456(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -1464(%rbp)
	movq	-1440(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1472(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-1472(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	movq	-1464(%rbp), %r14
	callq	*%r14
	movq	%rax, %r12
	movq	%r12, -1480(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-1480(%rbp), %r12
	movq	-1480(%rbp), %r14
	testq	%r14, %r12
	movq	%r12, -1480(%rbp)
	jnz	l41
	jmp	_label0
l41:
	movq	$0, %r14
	movq	%r14, -1488(%rbp)
l62:
	movq	-1488(%rbp), %r14
	cmpq	$5, %r14
	movq	%r14, -1488(%rbp)
	jl	l63
	movq	$0, %r14
	movq	%r14, -1496(%rbp)
	movq	-1496(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1504(%rbp)
	movq	-1504(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -1504(%rbp)
	movq	-1504(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1512(%rbp)
	movq	$8, %r14
	movq	%r14, -1520(%rbp)
	movq	-1512(%rbp), %r12
	movq	-1520(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -1512(%rbp)
	movq	-1512(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1528(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-1528(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -1536(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-1536(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1544(%rbp)
	movq	-1496(%rbp), %r14
	movq	-1544(%rbp), %r12
	movq	%r14, (%r12)
	movq	-1544(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1552(%rbp)
	movq	-1552(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -1552(%rbp)
	movq	-1552(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1544(%rbp)
	movq	$0, %r14
	movq	%r14, -1560(%rbp)
l68:
	movq	-1560(%rbp), %r12
	movq	-1496(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -1560(%rbp)
	jl	l69
	movq	-1544(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1568(%rbp)
	movq	$48, %r14
	movq	%r14, -1576(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-1576(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -1584(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-1584(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1592(%rbp)
	movq	-1592(%rbp), %r12
	movq	$5, (%r12)
	movq	$8, %r14
	movq	%r14, -1600(%rbp)
	movq	-1600(%rbp), %r12
	movq	-1592(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -1600(%rbp)
	movq	-1600(%rbp), %r12
	movq	$48, (%r12)
	movq	$16, %r14
	movq	%r14, -1608(%rbp)
	movq	-1608(%rbp), %r12
	movq	-1592(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -1608(%rbp)
	movq	-1608(%rbp), %r12
	movq	$32, (%r12)
	movq	$24, %r14
	movq	%r14, -1616(%rbp)
	movq	-1616(%rbp), %r12
	movq	-1592(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -1616(%rbp)
	movq	-1616(%rbp), %r12
	movq	$105, (%r12)
	movq	$32, %r14
	movq	%r14, -1624(%rbp)
	movq	-1624(%rbp), %r12
	movq	-1592(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -1624(%rbp)
	movq	-1624(%rbp), %r12
	movq	$115, (%r12)
	movq	$40, %r14
	movq	%r14, -1632(%rbp)
	movq	-1632(%rbp), %r12
	movq	-1592(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -1632(%rbp)
	movq	-1632(%rbp), %r12
	movq	$32, (%r12)
	movq	-1592(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1640(%rbp)
	movq	-1640(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -1640(%rbp)
	movq	-1640(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1592(%rbp)
	movq	-1568(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1648(%rbp)
	movq	-1648(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -1648(%rbp)
	movq	-1592(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1656(%rbp)
	movq	-1656(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -1656(%rbp)
	movq	-1648(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -1664(%rbp)
	movq	-1664(%rbp), %r14
	movq	-1656(%rbp), %r12
	addq	(%r12), %r14
	movq	%r14, -1664(%rbp)
	movq	-1664(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1672(%rbp)
	movq	-1672(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1680(%rbp)
	movq	-1680(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -1680(%rbp)
	movq	-1680(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1688(%rbp)
	movq	$8, %r14
	movq	%r14, -1696(%rbp)
	movq	-1688(%rbp), %r12
	movq	-1696(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -1688(%rbp)
	movq	-1688(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1704(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-1704(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -1712(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-1712(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1720(%rbp)
	movq	-1672(%rbp), %r14
	movq	-1720(%rbp), %r12
	movq	%r14, (%r12)
	movq	-1720(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1728(%rbp)
	movq	-1728(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -1728(%rbp)
	movq	-1728(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1720(%rbp)
	movq	$0, %r14
	movq	%r14, -1736(%rbp)
l71:
	movq	-1568(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1744(%rbp)
	movq	-1744(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -1744(%rbp)
	movq	-1736(%rbp), %r14
	movq	-1744(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -1736(%rbp)
	jl	l72
	movq	$0, %r14
	movq	%r14, -1752(%rbp)
	movq	-1568(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1760(%rbp)
	movq	-1760(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -1760(%rbp)
	movq	-1760(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -1768(%rbp)
l74:
	movq	-1592(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1776(%rbp)
	movq	-1776(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -1776(%rbp)
	movq	-1752(%rbp), %r14
	movq	-1776(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -1752(%rbp)
	jl	l75
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1784(%rbp)
	movq	$0, %r14
	movq	%r14, -1792(%rbp)
	movq	-1784(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1800(%rbp)
	movq	-1800(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -1800(%rbp)
	movq	-1800(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -1808(%rbp)
	movq	-1792(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -1792(%rbp)
	jl	l78
	movq	-1792(%rbp), %r12
	movq	-1808(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -1792(%rbp)
	jl	l79
l78:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	subq	$8, %rsp
	callq	_I_outOfBounds_p
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	_label0
l63:
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1816(%rbp)
	movq	-1488(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1824(%rbp)
	movq	-1816(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1832(%rbp)
	movq	-1832(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -1832(%rbp)
	movq	-1832(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -1840(%rbp)
	movq	-1824(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -1824(%rbp)
	jl	l66
	movq	-1824(%rbp), %r12
	movq	-1840(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -1824(%rbp)
	jl	l67
l66:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	subq	$8, %rsp
	callq	_I_outOfBounds_p
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	_label0
l67:
	movq	-1816(%rbp), %r12
	movq	-1824(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -1848(%rbp)
	movq	-1848(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -1856(%rbp)
	movq	-1856(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1864(%rbp)
	movq	-1864(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -1864(%rbp)
	movq	-1864(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -1872(%rbp)
	movq	-1848(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1880(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-1880(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	movq	-1872(%rbp), %r14
	callq	*%r14
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-1488(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1888(%rbp)
	movq	-1888(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -1888(%rbp)
	movq	-1888(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1488(%rbp)
	jmp	l62
l69:
	movq	-1544(%rbp), %r12
	movq	-1560(%rbp), %r13
	leaq	(%r12,%r13,8), %r14
	movq	%r14, -1896(%rbp)
	movq	-1896(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1904(%rbp)
	movq	$0, %r14
	movq	%r14, -1912(%rbp)
	movq	-1912(%rbp), %r14
	movq	-1904(%rbp), %r12
	movq	%r14, (%r12)
	movq	-1560(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1920(%rbp)
	movq	-1920(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -1920(%rbp)
	movq	-1920(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1560(%rbp)
	jmp	l68
l72:
	movq	-1568(%rbp), %r12
	movq	-1736(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -1928(%rbp)
	movq	-1928(%rbp), %r14
	movq	-1720(%rbp), %r12
	movq	-1736(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-1736(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1936(%rbp)
	movq	-1936(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -1936(%rbp)
	movq	-1936(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1736(%rbp)
	jmp	l71
l75:
	movq	-1752(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1944(%rbp)
	movq	-1944(%rbp), %r12
	movq	-1768(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -1944(%rbp)
	movq	-1592(%rbp), %r12
	movq	-1752(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -1952(%rbp)
	movq	-1952(%rbp), %r14
	movq	-1720(%rbp), %r12
	movq	-1944(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-1752(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1960(%rbp)
	movq	-1960(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -1960(%rbp)
	movq	-1960(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1752(%rbp)
	jmp	l74
l79:
	movq	-1784(%rbp), %r12
	movq	-1792(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -1968(%rbp)
	movq	-1968(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -1976(%rbp)
	movq	-1976(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1984(%rbp)
	movq	-1984(%rbp), %r14
	addq	$40, %r14
	movq	%r14, -1984(%rbp)
	movq	-1984(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -1992(%rbp)
	movq	-1720(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2000(%rbp)
	movq	-2000(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -2000(%rbp)
	movq	-2000(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -2008(%rbp)
	movq	-1968(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2016(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-2016(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	movq	-1992(%rbp), %r14
	callq	*%r14
	movq	%rax, %r12
	movq	%r12, -2024(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-2024(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2032(%rbp)
	movq	-2032(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -2032(%rbp)
	movq	-2008(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2040(%rbp)
	movq	-2040(%rbp), %r14
	movq	-2032(%rbp), %r12
	addq	(%r12), %r14
	movq	%r14, -2040(%rbp)
	movq	-2040(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2048(%rbp)
	movq	-2048(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2056(%rbp)
	movq	-2056(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -2056(%rbp)
	movq	-2056(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2064(%rbp)
	movq	$8, %r14
	movq	%r14, -2072(%rbp)
	movq	-2064(%rbp), %r12
	movq	-2072(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -2064(%rbp)
	movq	-2064(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2080(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-2080(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -2088(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-2088(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2096(%rbp)
	movq	-2048(%rbp), %r14
	movq	-2096(%rbp), %r12
	movq	%r14, (%r12)
	movq	-2096(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2104(%rbp)
	movq	-2104(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -2104(%rbp)
	movq	-2104(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2096(%rbp)
	movq	$0, %r14
	movq	%r14, -2112(%rbp)
l80:
	movq	-1720(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2120(%rbp)
	movq	-2120(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -2120(%rbp)
	movq	-2112(%rbp), %r14
	movq	-2120(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -2112(%rbp)
	jl	l81
	movq	$0, %r14
	movq	%r14, -2128(%rbp)
	movq	-1720(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2136(%rbp)
	movq	-2136(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -2136(%rbp)
	movq	-2136(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -2144(%rbp)
l83:
	movq	-1968(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2152(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-2152(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	movq	-1992(%rbp), %r14
	callq	*%r14
	movq	%rax, %r12
	movq	%r12, -2160(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-2160(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2168(%rbp)
	movq	-2168(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -2168(%rbp)
	movq	-2128(%rbp), %r14
	movq	-2168(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -2128(%rbp)
	jl	l84
	movq	$16, %r14
	movq	%r14, -2176(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-2176(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -2184(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-2184(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2192(%rbp)
	movq	-2192(%rbp), %r12
	movq	$1, (%r12)
	movq	$8, %r14
	movq	%r14, -2200(%rbp)
	movq	-2200(%rbp), %r12
	movq	-2192(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -2200(%rbp)
	movq	-2200(%rbp), %r12
	movq	$10, (%r12)
	movq	-2192(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2208(%rbp)
	movq	-2208(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -2208(%rbp)
	movq	-2208(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2192(%rbp)
	movq	-2096(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2216(%rbp)
	movq	-2216(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -2216(%rbp)
	movq	-2192(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2224(%rbp)
	movq	-2224(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -2224(%rbp)
	movq	-2216(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -2232(%rbp)
	movq	-2232(%rbp), %r14
	movq	-2224(%rbp), %r12
	addq	(%r12), %r14
	movq	%r14, -2232(%rbp)
	movq	-2232(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2240(%rbp)
	movq	-2240(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2248(%rbp)
	movq	-2248(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -2248(%rbp)
	movq	-2248(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2256(%rbp)
	movq	$8, %r14
	movq	%r14, -2264(%rbp)
	movq	-2256(%rbp), %r12
	movq	-2264(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -2256(%rbp)
	movq	-2256(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2272(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-2272(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -2280(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-2280(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2288(%rbp)
	movq	-2240(%rbp), %r14
	movq	-2288(%rbp), %r12
	movq	%r14, (%r12)
	movq	-2288(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2296(%rbp)
	movq	-2296(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -2296(%rbp)
	movq	-2296(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2288(%rbp)
	movq	$0, %r14
	movq	%r14, -2304(%rbp)
l86:
	movq	-2096(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2312(%rbp)
	movq	-2312(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -2312(%rbp)
	movq	-2304(%rbp), %r14
	movq	-2312(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -2304(%rbp)
	jl	l87
	movq	$0, %r14
	movq	%r14, -2320(%rbp)
	movq	-2096(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2328(%rbp)
	movq	-2328(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -2328(%rbp)
	movq	-2328(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -2336(%rbp)
l89:
	movq	-2192(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2344(%rbp)
	movq	-2344(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -2344(%rbp)
	movq	-2320(%rbp), %r14
	movq	-2344(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -2320(%rbp)
	jl	l90
	movq	-2288(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1568(%rbp)
	movq	$48, %r14
	movq	%r14, -2352(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-2352(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -2360(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-2360(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2368(%rbp)
	movq	-2368(%rbp), %r12
	movq	$5, (%r12)
	movq	$8, %r14
	movq	%r14, -2376(%rbp)
	movq	-2376(%rbp), %r12
	movq	-2368(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -2376(%rbp)
	movq	-2376(%rbp), %r12
	movq	$49, (%r12)
	movq	$16, %r14
	movq	%r14, -2384(%rbp)
	movq	-2384(%rbp), %r12
	movq	-2368(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -2384(%rbp)
	movq	-2384(%rbp), %r12
	movq	$32, (%r12)
	movq	$24, %r14
	movq	%r14, -2392(%rbp)
	movq	-2392(%rbp), %r12
	movq	-2368(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -2392(%rbp)
	movq	-2392(%rbp), %r12
	movq	$105, (%r12)
	movq	$32, %r14
	movq	%r14, -2400(%rbp)
	movq	-2400(%rbp), %r12
	movq	-2368(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -2400(%rbp)
	movq	-2400(%rbp), %r12
	movq	$115, (%r12)
	movq	$40, %r14
	movq	%r14, -2408(%rbp)
	movq	-2408(%rbp), %r12
	movq	-2368(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -2408(%rbp)
	movq	-2408(%rbp), %r12
	movq	$32, (%r12)
	movq	-2368(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2416(%rbp)
	movq	-2416(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -2416(%rbp)
	movq	-2416(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2368(%rbp)
	movq	-1568(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2424(%rbp)
	movq	-2424(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -2424(%rbp)
	movq	-2368(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2432(%rbp)
	movq	-2432(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -2432(%rbp)
	movq	-2424(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -2440(%rbp)
	movq	-2440(%rbp), %r14
	movq	-2432(%rbp), %r12
	addq	(%r12), %r14
	movq	%r14, -2440(%rbp)
	movq	-2440(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2448(%rbp)
	movq	-2448(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2456(%rbp)
	movq	-2456(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -2456(%rbp)
	movq	-2456(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2464(%rbp)
	movq	$8, %r14
	movq	%r14, -2472(%rbp)
	movq	-2464(%rbp), %r12
	movq	-2472(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -2464(%rbp)
	movq	-2464(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2480(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-2480(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -2488(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-2488(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2496(%rbp)
	movq	-2448(%rbp), %r14
	movq	-2496(%rbp), %r12
	movq	%r14, (%r12)
	movq	-2496(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2504(%rbp)
	movq	-2504(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -2504(%rbp)
	movq	-2504(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2496(%rbp)
	movq	$0, %r14
	movq	%r14, -2512(%rbp)
l92:
	movq	-1568(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2520(%rbp)
	movq	-2520(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -2520(%rbp)
	movq	-2512(%rbp), %r14
	movq	-2520(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -2512(%rbp)
	jl	l93
	movq	$0, %r14
	movq	%r14, -2528(%rbp)
	movq	-1568(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2536(%rbp)
	movq	-2536(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -2536(%rbp)
	movq	-2536(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -2544(%rbp)
l95:
	movq	-2368(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2552(%rbp)
	movq	-2552(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -2552(%rbp)
	movq	-2528(%rbp), %r14
	movq	-2552(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -2528(%rbp)
	jl	l96
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2560(%rbp)
	movq	$1, %r14
	movq	%r14, -2568(%rbp)
	movq	-2560(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2576(%rbp)
	movq	-2576(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -2576(%rbp)
	movq	-2576(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -2584(%rbp)
	movq	-2568(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -2568(%rbp)
	jl	l99
	movq	-2568(%rbp), %r12
	movq	-2584(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -2568(%rbp)
	jl	l100
l99:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	subq	$8, %rsp
	callq	_I_outOfBounds_p
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	_label0
l81:
	movq	-1720(%rbp), %r12
	movq	-2112(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -2592(%rbp)
	movq	-2592(%rbp), %r14
	movq	-2096(%rbp), %r12
	movq	-2112(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-2112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2600(%rbp)
	movq	-2600(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -2600(%rbp)
	movq	-2600(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2112(%rbp)
	jmp	l80
l84:
	movq	-2128(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2608(%rbp)
	movq	-2608(%rbp), %r12
	movq	-2144(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -2608(%rbp)
	movq	-2096(%rbp), %r12
	movq	-2608(%rbp), %r13
	leaq	(%r12,%r13,8), %r14
	movq	%r14, -2616(%rbp)
	movq	-2616(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2624(%rbp)
	movq	-1968(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2632(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-2632(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	movq	-1992(%rbp), %r14
	callq	*%r14
	movq	%rax, %r12
	movq	%r12, -2640(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-2640(%rbp), %r12
	movq	-2128(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -2648(%rbp)
	movq	-2648(%rbp), %r14
	movq	-2624(%rbp), %r12
	movq	%r14, (%r12)
	movq	-2128(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2656(%rbp)
	movq	-2656(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -2656(%rbp)
	movq	-2656(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2128(%rbp)
	jmp	l83
l87:
	movq	-2096(%rbp), %r12
	movq	-2304(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -2664(%rbp)
	movq	-2664(%rbp), %r14
	movq	-2288(%rbp), %r12
	movq	-2304(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-2304(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2672(%rbp)
	movq	-2672(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -2672(%rbp)
	movq	-2672(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2304(%rbp)
	jmp	l86
l90:
	movq	-2320(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2680(%rbp)
	movq	-2680(%rbp), %r12
	movq	-2336(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -2680(%rbp)
	movq	-2192(%rbp), %r12
	movq	-2320(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -2688(%rbp)
	movq	-2688(%rbp), %r14
	movq	-2288(%rbp), %r12
	movq	-2680(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-2320(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2696(%rbp)
	movq	-2696(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -2696(%rbp)
	movq	-2696(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2320(%rbp)
	jmp	l89
l93:
	movq	-1568(%rbp), %r12
	movq	-2512(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -2704(%rbp)
	movq	-2704(%rbp), %r14
	movq	-2496(%rbp), %r12
	movq	-2512(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-2512(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2712(%rbp)
	movq	-2712(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -2712(%rbp)
	movq	-2712(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2512(%rbp)
	jmp	l92
l96:
	movq	-2528(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2720(%rbp)
	movq	-2720(%rbp), %r12
	movq	-2544(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -2720(%rbp)
	movq	-2368(%rbp), %r12
	movq	-2528(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -2728(%rbp)
	movq	-2728(%rbp), %r14
	movq	-2496(%rbp), %r12
	movq	-2720(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-2528(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2736(%rbp)
	movq	-2736(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -2736(%rbp)
	movq	-2736(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2528(%rbp)
	jmp	l95
l100:
	movq	-2560(%rbp), %r12
	movq	-2568(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -2744(%rbp)
	movq	-2744(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -2752(%rbp)
	movq	-2752(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2760(%rbp)
	movq	-2760(%rbp), %r14
	addq	$40, %r14
	movq	%r14, -2760(%rbp)
	movq	-2760(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -2768(%rbp)
	movq	-2496(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2776(%rbp)
	movq	-2776(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -2776(%rbp)
	movq	-2776(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -2784(%rbp)
	movq	-2744(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2792(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-2792(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	movq	-2768(%rbp), %r14
	callq	*%r14
	movq	%rax, %r12
	movq	%r12, -2800(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-2800(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2808(%rbp)
	movq	-2808(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -2808(%rbp)
	movq	-2784(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2816(%rbp)
	movq	-2816(%rbp), %r14
	movq	-2808(%rbp), %r12
	addq	(%r12), %r14
	movq	%r14, -2816(%rbp)
	movq	-2816(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2824(%rbp)
	movq	-2824(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2832(%rbp)
	movq	-2832(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -2832(%rbp)
	movq	-2832(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2840(%rbp)
	movq	$8, %r14
	movq	%r14, -2848(%rbp)
	movq	-2840(%rbp), %r12
	movq	-2848(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -2840(%rbp)
	movq	-2840(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2856(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-2856(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -2864(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-2864(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2872(%rbp)
	movq	-2824(%rbp), %r14
	movq	-2872(%rbp), %r12
	movq	%r14, (%r12)
	movq	-2872(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2880(%rbp)
	movq	-2880(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -2880(%rbp)
	movq	-2880(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2872(%rbp)
	movq	$0, %r14
	movq	%r14, -2888(%rbp)
l101:
	movq	-2496(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2896(%rbp)
	movq	-2896(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -2896(%rbp)
	movq	-2888(%rbp), %r14
	movq	-2896(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -2888(%rbp)
	jl	l102
	movq	$0, %r14
	movq	%r14, -2904(%rbp)
	movq	-2496(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2912(%rbp)
	movq	-2912(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -2912(%rbp)
	movq	-2912(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -2920(%rbp)
l104:
	movq	-2744(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2928(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-2928(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	movq	-2768(%rbp), %r14
	callq	*%r14
	movq	%rax, %r12
	movq	%r12, -2936(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-2936(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2944(%rbp)
	movq	-2944(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -2944(%rbp)
	movq	-2904(%rbp), %r14
	movq	-2944(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -2904(%rbp)
	jl	l105
	movq	$16, %r14
	movq	%r14, -2952(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-2952(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -2960(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-2960(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2968(%rbp)
	movq	-2968(%rbp), %r12
	movq	$1, (%r12)
	movq	$8, %r14
	movq	%r14, -2976(%rbp)
	movq	-2976(%rbp), %r12
	movq	-2968(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -2976(%rbp)
	movq	-2976(%rbp), %r12
	movq	$10, (%r12)
	movq	-2968(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2984(%rbp)
	movq	-2984(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -2984(%rbp)
	movq	-2984(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2968(%rbp)
	movq	-2872(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2992(%rbp)
	movq	-2992(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -2992(%rbp)
	movq	-2968(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3000(%rbp)
	movq	-3000(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -3000(%rbp)
	movq	-2992(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -3008(%rbp)
	movq	-3008(%rbp), %r14
	movq	-3000(%rbp), %r12
	addq	(%r12), %r14
	movq	%r14, -3008(%rbp)
	movq	-3008(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3016(%rbp)
	movq	-3016(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3024(%rbp)
	movq	-3024(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -3024(%rbp)
	movq	-3024(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3032(%rbp)
	movq	$8, %r14
	movq	%r14, -3040(%rbp)
	movq	-3032(%rbp), %r12
	movq	-3040(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -3032(%rbp)
	movq	-3032(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3048(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-3048(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -3056(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-3056(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3064(%rbp)
	movq	-3016(%rbp), %r14
	movq	-3064(%rbp), %r12
	movq	%r14, (%r12)
	movq	-3064(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3072(%rbp)
	movq	-3072(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -3072(%rbp)
	movq	-3072(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3064(%rbp)
	movq	$0, %r14
	movq	%r14, -3080(%rbp)
l107:
	movq	-2872(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3088(%rbp)
	movq	-3088(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -3088(%rbp)
	movq	-3080(%rbp), %r14
	movq	-3088(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -3080(%rbp)
	jl	l108
	movq	$0, %r14
	movq	%r14, -3096(%rbp)
	movq	-2872(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3104(%rbp)
	movq	-3104(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -3104(%rbp)
	movq	-3104(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -3112(%rbp)
l110:
	movq	-2968(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3120(%rbp)
	movq	-3120(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -3120(%rbp)
	movq	-3096(%rbp), %r14
	movq	-3120(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -3096(%rbp)
	jl	l111
	movq	-3064(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1568(%rbp)
	movq	$48, %r14
	movq	%r14, -3128(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-3128(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -3136(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-3136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3144(%rbp)
	movq	-3144(%rbp), %r12
	movq	$5, (%r12)
	movq	$8, %r14
	movq	%r14, -3152(%rbp)
	movq	-3152(%rbp), %r12
	movq	-3144(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -3152(%rbp)
	movq	-3152(%rbp), %r12
	movq	$50, (%r12)
	movq	$16, %r14
	movq	%r14, -3160(%rbp)
	movq	-3160(%rbp), %r12
	movq	-3144(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -3160(%rbp)
	movq	-3160(%rbp), %r12
	movq	$32, (%r12)
	movq	$24, %r14
	movq	%r14, -3168(%rbp)
	movq	-3168(%rbp), %r12
	movq	-3144(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -3168(%rbp)
	movq	-3168(%rbp), %r12
	movq	$105, (%r12)
	movq	$32, %r14
	movq	%r14, -3176(%rbp)
	movq	-3176(%rbp), %r12
	movq	-3144(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -3176(%rbp)
	movq	-3176(%rbp), %r12
	movq	$115, (%r12)
	movq	$40, %r14
	movq	%r14, -3184(%rbp)
	movq	-3184(%rbp), %r12
	movq	-3144(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -3184(%rbp)
	movq	-3184(%rbp), %r12
	movq	$32, (%r12)
	movq	-3144(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3192(%rbp)
	movq	-3192(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -3192(%rbp)
	movq	-3192(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3144(%rbp)
	movq	-1568(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3200(%rbp)
	movq	-3200(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -3200(%rbp)
	movq	-3144(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3208(%rbp)
	movq	-3208(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -3208(%rbp)
	movq	-3200(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -3216(%rbp)
	movq	-3216(%rbp), %r14
	movq	-3208(%rbp), %r12
	addq	(%r12), %r14
	movq	%r14, -3216(%rbp)
	movq	-3216(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3224(%rbp)
	movq	-3224(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3232(%rbp)
	movq	-3232(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -3232(%rbp)
	movq	-3232(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3240(%rbp)
	movq	$8, %r14
	movq	%r14, -3248(%rbp)
	movq	-3240(%rbp), %r12
	movq	-3248(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -3240(%rbp)
	movq	-3240(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3256(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-3256(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -3264(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-3264(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3272(%rbp)
	movq	-3224(%rbp), %r14
	movq	-3272(%rbp), %r12
	movq	%r14, (%r12)
	movq	-3272(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3280(%rbp)
	movq	-3280(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -3280(%rbp)
	movq	-3280(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3272(%rbp)
	movq	$0, %r14
	movq	%r14, -3288(%rbp)
l113:
	movq	-1568(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3296(%rbp)
	movq	-3296(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -3296(%rbp)
	movq	-3288(%rbp), %r14
	movq	-3296(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -3288(%rbp)
	jl	l114
	movq	$0, %r14
	movq	%r14, -3304(%rbp)
	movq	-1568(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3312(%rbp)
	movq	-3312(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -3312(%rbp)
	movq	-3312(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -3320(%rbp)
l116:
	movq	-3144(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3328(%rbp)
	movq	-3328(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -3328(%rbp)
	movq	-3304(%rbp), %r14
	movq	-3328(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -3304(%rbp)
	jl	l117
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3336(%rbp)
	movq	$2, %r14
	movq	%r14, -3344(%rbp)
	movq	-3336(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3352(%rbp)
	movq	-3352(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -3352(%rbp)
	movq	-3352(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -3360(%rbp)
	movq	-3344(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -3344(%rbp)
	jl	l120
	movq	-3344(%rbp), %r12
	movq	-3360(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -3344(%rbp)
	jl	l121
l120:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	subq	$8, %rsp
	callq	_I_outOfBounds_p
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	_label0
l102:
	movq	-2496(%rbp), %r12
	movq	-2888(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -3368(%rbp)
	movq	-3368(%rbp), %r14
	movq	-2872(%rbp), %r12
	movq	-2888(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-2888(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3376(%rbp)
	movq	-3376(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -3376(%rbp)
	movq	-3376(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2888(%rbp)
	jmp	l101
l105:
	movq	-2904(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3384(%rbp)
	movq	-3384(%rbp), %r12
	movq	-2920(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -3384(%rbp)
	movq	-2872(%rbp), %r12
	movq	-3384(%rbp), %r13
	leaq	(%r12,%r13,8), %r14
	movq	%r14, -3392(%rbp)
	movq	-3392(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3400(%rbp)
	movq	-2744(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3408(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-3408(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	movq	-2768(%rbp), %r14
	callq	*%r14
	movq	%rax, %r12
	movq	%r12, -3416(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-3416(%rbp), %r12
	movq	-2904(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -3424(%rbp)
	movq	-3424(%rbp), %r14
	movq	-3400(%rbp), %r12
	movq	%r14, (%r12)
	movq	-2904(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3432(%rbp)
	movq	-3432(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -3432(%rbp)
	movq	-3432(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -2904(%rbp)
	jmp	l104
l108:
	movq	-2872(%rbp), %r12
	movq	-3080(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -3440(%rbp)
	movq	-3440(%rbp), %r14
	movq	-3064(%rbp), %r12
	movq	-3080(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-3080(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3448(%rbp)
	movq	-3448(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -3448(%rbp)
	movq	-3448(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3080(%rbp)
	jmp	l107
l111:
	movq	-3096(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3456(%rbp)
	movq	-3456(%rbp), %r12
	movq	-3112(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -3456(%rbp)
	movq	-2968(%rbp), %r12
	movq	-3096(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -3464(%rbp)
	movq	-3464(%rbp), %r14
	movq	-3064(%rbp), %r12
	movq	-3456(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-3096(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3472(%rbp)
	movq	-3472(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -3472(%rbp)
	movq	-3472(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3096(%rbp)
	jmp	l110
l114:
	movq	-1568(%rbp), %r12
	movq	-3288(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -3480(%rbp)
	movq	-3480(%rbp), %r14
	movq	-3272(%rbp), %r12
	movq	-3288(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-3288(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3488(%rbp)
	movq	-3488(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -3488(%rbp)
	movq	-3488(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3288(%rbp)
	jmp	l113
l117:
	movq	-3304(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3496(%rbp)
	movq	-3496(%rbp), %r12
	movq	-3320(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -3496(%rbp)
	movq	-3144(%rbp), %r12
	movq	-3304(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -3504(%rbp)
	movq	-3504(%rbp), %r14
	movq	-3272(%rbp), %r12
	movq	-3496(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-3304(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3512(%rbp)
	movq	-3512(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -3512(%rbp)
	movq	-3512(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3304(%rbp)
	jmp	l116
l121:
	movq	-3336(%rbp), %r12
	movq	-3344(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -3520(%rbp)
	movq	-3520(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -3528(%rbp)
	movq	-3528(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3536(%rbp)
	movq	-3536(%rbp), %r14
	addq	$40, %r14
	movq	%r14, -3536(%rbp)
	movq	-3536(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -3544(%rbp)
	movq	-3272(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3552(%rbp)
	movq	-3552(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -3552(%rbp)
	movq	-3552(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -3560(%rbp)
	movq	-3520(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3568(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-3568(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	movq	-3544(%rbp), %r14
	callq	*%r14
	movq	%rax, %r12
	movq	%r12, -3576(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-3576(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3584(%rbp)
	movq	-3584(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -3584(%rbp)
	movq	-3560(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3592(%rbp)
	movq	-3592(%rbp), %r14
	movq	-3584(%rbp), %r12
	addq	(%r12), %r14
	movq	%r14, -3592(%rbp)
	movq	-3592(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3600(%rbp)
	movq	-3600(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3608(%rbp)
	movq	-3608(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -3608(%rbp)
	movq	-3608(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3616(%rbp)
	movq	$8, %r14
	movq	%r14, -3624(%rbp)
	movq	-3616(%rbp), %r12
	movq	-3624(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -3616(%rbp)
	movq	-3616(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3632(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-3632(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -3640(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-3640(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3648(%rbp)
	movq	-3600(%rbp), %r14
	movq	-3648(%rbp), %r12
	movq	%r14, (%r12)
	movq	-3648(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3656(%rbp)
	movq	-3656(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -3656(%rbp)
	movq	-3656(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3648(%rbp)
	movq	$0, %r14
	movq	%r14, -3664(%rbp)
l122:
	movq	-3272(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3672(%rbp)
	movq	-3672(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -3672(%rbp)
	movq	-3664(%rbp), %r14
	movq	-3672(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -3664(%rbp)
	jl	l123
	movq	$0, %r14
	movq	%r14, -3680(%rbp)
	movq	-3272(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3688(%rbp)
	movq	-3688(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -3688(%rbp)
	movq	-3688(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -3696(%rbp)
l125:
	movq	-3520(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3704(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-3704(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	movq	-3544(%rbp), %r14
	callq	*%r14
	movq	%rax, %r12
	movq	%r12, -3712(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-3712(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3720(%rbp)
	movq	-3720(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -3720(%rbp)
	movq	-3680(%rbp), %r14
	movq	-3720(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -3680(%rbp)
	jl	l126
	movq	$16, %r14
	movq	%r14, -3728(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-3728(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -3736(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-3736(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3744(%rbp)
	movq	-3744(%rbp), %r12
	movq	$1, (%r12)
	movq	$8, %r14
	movq	%r14, -3752(%rbp)
	movq	-3752(%rbp), %r12
	movq	-3744(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -3752(%rbp)
	movq	-3752(%rbp), %r12
	movq	$10, (%r12)
	movq	-3744(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3760(%rbp)
	movq	-3760(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -3760(%rbp)
	movq	-3760(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3744(%rbp)
	movq	-3648(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3768(%rbp)
	movq	-3768(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -3768(%rbp)
	movq	-3744(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3776(%rbp)
	movq	-3776(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -3776(%rbp)
	movq	-3768(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -3784(%rbp)
	movq	-3784(%rbp), %r14
	movq	-3776(%rbp), %r12
	addq	(%r12), %r14
	movq	%r14, -3784(%rbp)
	movq	-3784(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3792(%rbp)
	movq	-3792(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3800(%rbp)
	movq	-3800(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -3800(%rbp)
	movq	-3800(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3808(%rbp)
	movq	$8, %r14
	movq	%r14, -3816(%rbp)
	movq	-3808(%rbp), %r12
	movq	-3816(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -3808(%rbp)
	movq	-3808(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3824(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-3824(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -3832(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-3832(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3840(%rbp)
	movq	-3792(%rbp), %r14
	movq	-3840(%rbp), %r12
	movq	%r14, (%r12)
	movq	-3840(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3848(%rbp)
	movq	-3848(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -3848(%rbp)
	movq	-3848(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3840(%rbp)
	movq	$0, %r14
	movq	%r14, -3856(%rbp)
l128:
	movq	-3648(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3864(%rbp)
	movq	-3864(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -3864(%rbp)
	movq	-3856(%rbp), %r14
	movq	-3864(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -3856(%rbp)
	jl	l129
	movq	$0, %r14
	movq	%r14, -3872(%rbp)
	movq	-3648(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3880(%rbp)
	movq	-3880(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -3880(%rbp)
	movq	-3880(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -3888(%rbp)
l131:
	movq	-3744(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3896(%rbp)
	movq	-3896(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -3896(%rbp)
	movq	-3872(%rbp), %r14
	movq	-3896(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -3872(%rbp)
	jl	l132
	movq	-3840(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1568(%rbp)
	movq	$48, %r14
	movq	%r14, -3904(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-3904(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -3912(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-3912(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3920(%rbp)
	movq	-3920(%rbp), %r12
	movq	$5, (%r12)
	movq	$8, %r14
	movq	%r14, -3928(%rbp)
	movq	-3928(%rbp), %r12
	movq	-3920(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -3928(%rbp)
	movq	-3928(%rbp), %r12
	movq	$51, (%r12)
	movq	$16, %r14
	movq	%r14, -3936(%rbp)
	movq	-3936(%rbp), %r12
	movq	-3920(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -3936(%rbp)
	movq	-3936(%rbp), %r12
	movq	$32, (%r12)
	movq	$24, %r14
	movq	%r14, -3944(%rbp)
	movq	-3944(%rbp), %r12
	movq	-3920(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -3944(%rbp)
	movq	-3944(%rbp), %r12
	movq	$105, (%r12)
	movq	$32, %r14
	movq	%r14, -3952(%rbp)
	movq	-3952(%rbp), %r12
	movq	-3920(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -3952(%rbp)
	movq	-3952(%rbp), %r12
	movq	$115, (%r12)
	movq	$40, %r14
	movq	%r14, -3960(%rbp)
	movq	-3960(%rbp), %r12
	movq	-3920(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -3960(%rbp)
	movq	-3960(%rbp), %r12
	movq	$32, (%r12)
	movq	-3920(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3968(%rbp)
	movq	-3968(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -3968(%rbp)
	movq	-3968(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3920(%rbp)
	movq	-1568(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3976(%rbp)
	movq	-3976(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -3976(%rbp)
	movq	-3920(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3984(%rbp)
	movq	-3984(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -3984(%rbp)
	movq	-3976(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -3992(%rbp)
	movq	-3992(%rbp), %r14
	movq	-3984(%rbp), %r12
	addq	(%r12), %r14
	movq	%r14, -3992(%rbp)
	movq	-3992(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4000(%rbp)
	movq	-4000(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4008(%rbp)
	movq	-4008(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -4008(%rbp)
	movq	-4008(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4016(%rbp)
	movq	$8, %r14
	movq	%r14, -4024(%rbp)
	movq	-4016(%rbp), %r12
	movq	-4024(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -4016(%rbp)
	movq	-4016(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4032(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-4032(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -4040(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-4040(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4048(%rbp)
	movq	-4000(%rbp), %r14
	movq	-4048(%rbp), %r12
	movq	%r14, (%r12)
	movq	-4048(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4056(%rbp)
	movq	-4056(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -4056(%rbp)
	movq	-4056(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4048(%rbp)
	movq	$0, %r14
	movq	%r14, -4064(%rbp)
l134:
	movq	-1568(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4072(%rbp)
	movq	-4072(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -4072(%rbp)
	movq	-4064(%rbp), %r14
	movq	-4072(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -4064(%rbp)
	jl	l135
	movq	$0, %r14
	movq	%r14, -4080(%rbp)
	movq	-1568(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4088(%rbp)
	movq	-4088(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -4088(%rbp)
	movq	-4088(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -4096(%rbp)
l137:
	movq	-3920(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4104(%rbp)
	movq	-4104(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -4104(%rbp)
	movq	-4080(%rbp), %r14
	movq	-4104(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -4080(%rbp)
	jl	l138
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4112(%rbp)
	movq	$3, %r14
	movq	%r14, -4120(%rbp)
	movq	-4112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4128(%rbp)
	movq	-4128(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -4128(%rbp)
	movq	-4128(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -4136(%rbp)
	movq	-4120(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -4120(%rbp)
	jl	l141
	movq	-4120(%rbp), %r12
	movq	-4136(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -4120(%rbp)
	jl	l142
l141:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	subq	$8, %rsp
	callq	_I_outOfBounds_p
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	_label0
l123:
	movq	-3272(%rbp), %r12
	movq	-3664(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -4144(%rbp)
	movq	-4144(%rbp), %r14
	movq	-3648(%rbp), %r12
	movq	-3664(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-3664(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4152(%rbp)
	movq	-4152(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -4152(%rbp)
	movq	-4152(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3664(%rbp)
	jmp	l122
l126:
	movq	-3680(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4160(%rbp)
	movq	-4160(%rbp), %r12
	movq	-3696(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -4160(%rbp)
	movq	-3648(%rbp), %r12
	movq	-4160(%rbp), %r13
	leaq	(%r12,%r13,8), %r14
	movq	%r14, -4168(%rbp)
	movq	-4168(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4176(%rbp)
	movq	-3520(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4184(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-4184(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	movq	-3544(%rbp), %r14
	callq	*%r14
	movq	%rax, %r12
	movq	%r12, -4192(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-4192(%rbp), %r12
	movq	-3680(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -4200(%rbp)
	movq	-4200(%rbp), %r14
	movq	-4176(%rbp), %r12
	movq	%r14, (%r12)
	movq	-3680(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4208(%rbp)
	movq	-4208(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -4208(%rbp)
	movq	-4208(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3680(%rbp)
	jmp	l125
l129:
	movq	-3648(%rbp), %r12
	movq	-3856(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -4216(%rbp)
	movq	-4216(%rbp), %r14
	movq	-3840(%rbp), %r12
	movq	-3856(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-3856(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4224(%rbp)
	movq	-4224(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -4224(%rbp)
	movq	-4224(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3856(%rbp)
	jmp	l128
l132:
	movq	-3872(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4232(%rbp)
	movq	-4232(%rbp), %r12
	movq	-3888(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -4232(%rbp)
	movq	-3744(%rbp), %r12
	movq	-3872(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -4240(%rbp)
	movq	-4240(%rbp), %r14
	movq	-3840(%rbp), %r12
	movq	-4232(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-3872(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4248(%rbp)
	movq	-4248(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -4248(%rbp)
	movq	-4248(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -3872(%rbp)
	jmp	l131
l135:
	movq	-1568(%rbp), %r12
	movq	-4064(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -4256(%rbp)
	movq	-4256(%rbp), %r14
	movq	-4048(%rbp), %r12
	movq	-4064(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-4064(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4264(%rbp)
	movq	-4264(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -4264(%rbp)
	movq	-4264(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4064(%rbp)
	jmp	l134
l138:
	movq	-4080(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4272(%rbp)
	movq	-4272(%rbp), %r12
	movq	-4096(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -4272(%rbp)
	movq	-3920(%rbp), %r12
	movq	-4080(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -4280(%rbp)
	movq	-4280(%rbp), %r14
	movq	-4048(%rbp), %r12
	movq	-4272(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-4080(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4288(%rbp)
	movq	-4288(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -4288(%rbp)
	movq	-4288(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4080(%rbp)
	jmp	l137
l142:
	movq	-4112(%rbp), %r12
	movq	-4120(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -4296(%rbp)
	movq	-4296(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -4304(%rbp)
	movq	-4304(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4312(%rbp)
	movq	-4312(%rbp), %r14
	addq	$40, %r14
	movq	%r14, -4312(%rbp)
	movq	-4312(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -4320(%rbp)
	movq	-4048(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4328(%rbp)
	movq	-4328(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -4328(%rbp)
	movq	-4328(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -4336(%rbp)
	movq	-4296(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4344(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-4344(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	movq	-4320(%rbp), %r14
	callq	*%r14
	movq	%rax, %r12
	movq	%r12, -4352(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-4352(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4360(%rbp)
	movq	-4360(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -4360(%rbp)
	movq	-4336(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4368(%rbp)
	movq	-4368(%rbp), %r14
	movq	-4360(%rbp), %r12
	addq	(%r12), %r14
	movq	%r14, -4368(%rbp)
	movq	-4368(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4376(%rbp)
	movq	-4376(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4384(%rbp)
	movq	-4384(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -4384(%rbp)
	movq	-4384(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4392(%rbp)
	movq	$8, %r14
	movq	%r14, -4400(%rbp)
	movq	-4392(%rbp), %r12
	movq	-4400(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -4392(%rbp)
	movq	-4392(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4408(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-4408(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -4416(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-4416(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4424(%rbp)
	movq	-4376(%rbp), %r14
	movq	-4424(%rbp), %r12
	movq	%r14, (%r12)
	movq	-4424(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4432(%rbp)
	movq	-4432(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -4432(%rbp)
	movq	-4432(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4424(%rbp)
	movq	$0, %r14
	movq	%r14, -4440(%rbp)
l143:
	movq	-4048(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4448(%rbp)
	movq	-4448(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -4448(%rbp)
	movq	-4440(%rbp), %r14
	movq	-4448(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -4440(%rbp)
	jl	l144
	movq	$0, %r14
	movq	%r14, -4456(%rbp)
	movq	-4048(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4464(%rbp)
	movq	-4464(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -4464(%rbp)
	movq	-4464(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -4472(%rbp)
l146:
	movq	-4296(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4480(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-4480(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	movq	-4320(%rbp), %r14
	callq	*%r14
	movq	%rax, %r12
	movq	%r12, -4488(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-4488(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4496(%rbp)
	movq	-4496(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -4496(%rbp)
	movq	-4456(%rbp), %r14
	movq	-4496(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -4456(%rbp)
	jl	l147
	movq	$16, %r14
	movq	%r14, -4504(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-4504(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -4512(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-4512(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4520(%rbp)
	movq	-4520(%rbp), %r12
	movq	$1, (%r12)
	movq	$8, %r14
	movq	%r14, -4528(%rbp)
	movq	-4528(%rbp), %r12
	movq	-4520(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -4528(%rbp)
	movq	-4528(%rbp), %r12
	movq	$10, (%r12)
	movq	-4520(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4536(%rbp)
	movq	-4536(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -4536(%rbp)
	movq	-4536(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4520(%rbp)
	movq	-4424(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4544(%rbp)
	movq	-4544(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -4544(%rbp)
	movq	-4520(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4552(%rbp)
	movq	-4552(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -4552(%rbp)
	movq	-4544(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -4560(%rbp)
	movq	-4560(%rbp), %r14
	movq	-4552(%rbp), %r12
	addq	(%r12), %r14
	movq	%r14, -4560(%rbp)
	movq	-4560(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4568(%rbp)
	movq	-4568(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4576(%rbp)
	movq	-4576(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -4576(%rbp)
	movq	-4576(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4584(%rbp)
	movq	$8, %r14
	movq	%r14, -4592(%rbp)
	movq	-4584(%rbp), %r12
	movq	-4592(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -4584(%rbp)
	movq	-4584(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4600(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-4600(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -4608(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-4608(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4616(%rbp)
	movq	-4568(%rbp), %r14
	movq	-4616(%rbp), %r12
	movq	%r14, (%r12)
	movq	-4616(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4624(%rbp)
	movq	-4624(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -4624(%rbp)
	movq	-4624(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4616(%rbp)
	movq	$0, %r14
	movq	%r14, -4632(%rbp)
l149:
	movq	-4424(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4640(%rbp)
	movq	-4640(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -4640(%rbp)
	movq	-4632(%rbp), %r14
	movq	-4640(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -4632(%rbp)
	jl	l150
	movq	$0, %r14
	movq	%r14, -4648(%rbp)
	movq	-4424(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4656(%rbp)
	movq	-4656(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -4656(%rbp)
	movq	-4656(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -4664(%rbp)
l152:
	movq	-4520(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4672(%rbp)
	movq	-4672(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -4672(%rbp)
	movq	-4648(%rbp), %r14
	movq	-4672(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -4648(%rbp)
	jl	l153
	movq	-4616(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1568(%rbp)
	movq	$48, %r14
	movq	%r14, -4680(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-4680(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -4688(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-4688(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4696(%rbp)
	movq	-4696(%rbp), %r12
	movq	$5, (%r12)
	movq	$8, %r14
	movq	%r14, -4704(%rbp)
	movq	-4704(%rbp), %r12
	movq	-4696(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -4704(%rbp)
	movq	-4704(%rbp), %r12
	movq	$52, (%r12)
	movq	$16, %r14
	movq	%r14, -4712(%rbp)
	movq	-4712(%rbp), %r12
	movq	-4696(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -4712(%rbp)
	movq	-4712(%rbp), %r12
	movq	$32, (%r12)
	movq	$24, %r14
	movq	%r14, -4720(%rbp)
	movq	-4720(%rbp), %r12
	movq	-4696(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -4720(%rbp)
	movq	-4720(%rbp), %r12
	movq	$105, (%r12)
	movq	$32, %r14
	movq	%r14, -4728(%rbp)
	movq	-4728(%rbp), %r12
	movq	-4696(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -4728(%rbp)
	movq	-4728(%rbp), %r12
	movq	$115, (%r12)
	movq	$40, %r14
	movq	%r14, -4736(%rbp)
	movq	-4736(%rbp), %r12
	movq	-4696(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -4736(%rbp)
	movq	-4736(%rbp), %r12
	movq	$32, (%r12)
	movq	-4696(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4744(%rbp)
	movq	-4744(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -4744(%rbp)
	movq	-4744(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4696(%rbp)
	movq	-1568(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4752(%rbp)
	movq	-4752(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -4752(%rbp)
	movq	-4696(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4760(%rbp)
	movq	-4760(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -4760(%rbp)
	movq	-4752(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -4768(%rbp)
	movq	-4768(%rbp), %r14
	movq	-4760(%rbp), %r12
	addq	(%r12), %r14
	movq	%r14, -4768(%rbp)
	movq	-4768(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4776(%rbp)
	movq	-4776(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4784(%rbp)
	movq	-4784(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -4784(%rbp)
	movq	-4784(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4792(%rbp)
	movq	$8, %r14
	movq	%r14, -4800(%rbp)
	movq	-4792(%rbp), %r12
	movq	-4800(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -4792(%rbp)
	movq	-4792(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4808(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-4808(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -4816(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-4816(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4824(%rbp)
	movq	-4776(%rbp), %r14
	movq	-4824(%rbp), %r12
	movq	%r14, (%r12)
	movq	-4824(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4832(%rbp)
	movq	-4832(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -4832(%rbp)
	movq	-4832(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4824(%rbp)
	movq	$0, %r14
	movq	%r14, -4840(%rbp)
l155:
	movq	-1568(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4848(%rbp)
	movq	-4848(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -4848(%rbp)
	movq	-4840(%rbp), %r14
	movq	-4848(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -4840(%rbp)
	jl	l156
	movq	$0, %r14
	movq	%r14, -4856(%rbp)
	movq	-1568(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4864(%rbp)
	movq	-4864(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -4864(%rbp)
	movq	-4864(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -4872(%rbp)
l158:
	movq	-4696(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4880(%rbp)
	movq	-4880(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -4880(%rbp)
	movq	-4856(%rbp), %r14
	movq	-4880(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -4856(%rbp)
	jl	l159
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4888(%rbp)
	movq	$4, %r14
	movq	%r14, -4896(%rbp)
	movq	-4888(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4904(%rbp)
	movq	-4904(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -4904(%rbp)
	movq	-4904(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -4912(%rbp)
	movq	-4896(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -4896(%rbp)
	jl	l162
	movq	-4896(%rbp), %r12
	movq	-4912(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -4896(%rbp)
	jl	l163
l162:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	subq	$8, %rsp
	callq	_I_outOfBounds_p
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	_label0
l144:
	movq	-4048(%rbp), %r12
	movq	-4440(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -4920(%rbp)
	movq	-4920(%rbp), %r14
	movq	-4424(%rbp), %r12
	movq	-4440(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-4440(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4928(%rbp)
	movq	-4928(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -4928(%rbp)
	movq	-4928(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4440(%rbp)
	jmp	l143
l147:
	movq	-4456(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4936(%rbp)
	movq	-4936(%rbp), %r12
	movq	-4472(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -4936(%rbp)
	movq	-4424(%rbp), %r12
	movq	-4936(%rbp), %r13
	leaq	(%r12,%r13,8), %r14
	movq	%r14, -4944(%rbp)
	movq	-4944(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4952(%rbp)
	movq	-4296(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4960(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-4960(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	movq	-4320(%rbp), %r14
	callq	*%r14
	movq	%rax, %r12
	movq	%r12, -4968(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-4968(%rbp), %r12
	movq	-4456(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -4976(%rbp)
	movq	-4976(%rbp), %r14
	movq	-4952(%rbp), %r12
	movq	%r14, (%r12)
	movq	-4456(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4984(%rbp)
	movq	-4984(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -4984(%rbp)
	movq	-4984(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4456(%rbp)
	jmp	l146
l150:
	movq	-4424(%rbp), %r12
	movq	-4632(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -4992(%rbp)
	movq	-4992(%rbp), %r14
	movq	-4616(%rbp), %r12
	movq	-4632(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-4632(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -5000(%rbp)
	movq	-5000(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -5000(%rbp)
	movq	-5000(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4632(%rbp)
	jmp	l149
l153:
	movq	-4648(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -5008(%rbp)
	movq	-5008(%rbp), %r12
	movq	-4664(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -5008(%rbp)
	movq	-4520(%rbp), %r12
	movq	-4648(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -5016(%rbp)
	movq	-5016(%rbp), %r14
	movq	-4616(%rbp), %r12
	movq	-5008(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-4648(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -5024(%rbp)
	movq	-5024(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -5024(%rbp)
	movq	-5024(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4648(%rbp)
	jmp	l152
l156:
	movq	-1568(%rbp), %r12
	movq	-4840(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -5032(%rbp)
	movq	-5032(%rbp), %r14
	movq	-4824(%rbp), %r12
	movq	-4840(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-4840(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -5040(%rbp)
	movq	-5040(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -5040(%rbp)
	movq	-5040(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4840(%rbp)
	jmp	l155
l159:
	movq	-4856(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -5048(%rbp)
	movq	-5048(%rbp), %r12
	movq	-4872(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -5048(%rbp)
	movq	-4696(%rbp), %r12
	movq	-4856(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -5056(%rbp)
	movq	-5056(%rbp), %r14
	movq	-4824(%rbp), %r12
	movq	-5048(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-4856(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -5064(%rbp)
	movq	-5064(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -5064(%rbp)
	movq	-5064(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -4856(%rbp)
	jmp	l158
l163:
	movq	-4888(%rbp), %r12
	movq	-4896(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -5072(%rbp)
	movq	-5072(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -5080(%rbp)
	movq	-5080(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -5088(%rbp)
	movq	-5088(%rbp), %r14
	addq	$40, %r14
	movq	%r14, -5088(%rbp)
	movq	-5088(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -5096(%rbp)
	movq	-4824(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -5104(%rbp)
	movq	-5104(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -5104(%rbp)
	movq	-5104(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -5112(%rbp)
	movq	-5072(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -5120(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-5120(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	movq	-5096(%rbp), %r14
	callq	*%r14
	movq	%rax, %r12
	movq	%r12, -5128(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-5128(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -5136(%rbp)
	movq	-5136(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -5136(%rbp)
	movq	-5112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -5144(%rbp)
	movq	-5144(%rbp), %r14
	movq	-5136(%rbp), %r12
	addq	(%r12), %r14
	movq	%r14, -5144(%rbp)
	movq	-5144(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -5152(%rbp)
	movq	-5152(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -5160(%rbp)
	movq	-5160(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -5160(%rbp)
	movq	-5160(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -5168(%rbp)
	movq	$8, %r14
	movq	%r14, -5176(%rbp)
	movq	-5168(%rbp), %r12
	movq	-5176(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -5168(%rbp)
	movq	-5168(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -5184(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-5184(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -5192(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-5192(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -5200(%rbp)
	movq	-5152(%rbp), %r14
	movq	-5200(%rbp), %r12
	movq	%r14, (%r12)
	movq	-5200(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -5208(%rbp)
	movq	-5208(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -5208(%rbp)
	movq	-5208(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -5200(%rbp)
	movq	$0, %r14
	movq	%r14, -5216(%rbp)
l164:
	movq	-4824(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -5224(%rbp)
	movq	-5224(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -5224(%rbp)
	movq	-5216(%rbp), %r14
	movq	-5224(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -5216(%rbp)
	jl	l165
	movq	$0, %r14
	movq	%r14, -5232(%rbp)
	movq	-4824(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -5240(%rbp)
	movq	-5240(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -5240(%rbp)
	movq	-5240(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -5248(%rbp)
l167:
	movq	-5072(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -5256(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-5256(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	movq	-5096(%rbp), %r14
	callq	*%r14
	movq	%rax, %r12
	movq	%r12, -5264(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-5264(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -5272(%rbp)
	movq	-5272(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -5272(%rbp)
	movq	-5232(%rbp), %r14
	movq	-5272(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -5232(%rbp)
	jl	l168
	movq	-5200(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1568(%rbp)
	movq	-1568(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -5280(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-5280(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_Iprintln_pai
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	l40
l165:
	movq	-4824(%rbp), %r12
	movq	-5216(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -5288(%rbp)
	movq	-5288(%rbp), %r14
	movq	-5200(%rbp), %r12
	movq	-5216(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-5216(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -5296(%rbp)
	movq	-5296(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -5296(%rbp)
	movq	-5296(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -5216(%rbp)
	jmp	l164
l168:
	movq	-5232(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -5304(%rbp)
	movq	-5304(%rbp), %r12
	movq	-5248(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -5304(%rbp)
	movq	-5200(%rbp), %r12
	movq	-5304(%rbp), %r13
	leaq	(%r12,%r13,8), %r14
	movq	%r14, -5312(%rbp)
	movq	-5312(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -5320(%rbp)
	movq	-5072(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -5328(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-5328(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	movq	-5096(%rbp), %r14
	callq	*%r14
	movq	%rax, %r12
	movq	%r12, -5336(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-5336(%rbp), %r12
	movq	-5232(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -5344(%rbp)
	movq	-5344(%rbp), %r14
	movq	-5320(%rbp), %r12
	movq	%r14, (%r12)
	movq	-5232(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -5352(%rbp)
	movq	-5352(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -5352(%rbp)
	movq	-5352(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -5232(%rbp)
	jmp	l167

	.globl	_Animal_Ihibernate_p
	.align	4
_Animal_Ihibernate_p:
	enter	$64, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -64(%rbp)
	movq	-64(%rbp), %r14
	addq	$40, %r14
	movq	%r14, -64(%rbp)
	movq	-64(%rbp), %r12
	movq	$1, (%r12)
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret

	.globl	_Dog_Iinit_o3Dogii
	.align	4
_Dog_Iinit_o3Dogii:
	enter	$208, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
	movq	%rsi, %r12
	movq	%r12, -64(%rbp)
	movq	%rdx, %r12
	movq	%r12, -72(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	-80(%rbp), %r14
	addq	$48, %r14
	movq	%r14, -80(%rbp)
	movq	-64(%rbp), %r14
	movq	-80(%rbp), %r12
	movq	%r14, (%r12)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -88(%rbp)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -96(%rbp)
	movq	_I_size_Organism(%rip), %r14
	movq	%r14, -104(%rbp)
	movq	$8, %r14
	movq	%r14, -112(%rbp)
	movq	-104(%rbp), %r12
	movq	-112(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -104(%rbp)
	movq	-104(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -120(%rbp)
	movq	-72(%rbp), %r14
	movq	-96(%rbp), %r12
	movq	-120(%rbp), %r13
	movq	%r14, 16(%r12,%r13)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -128(%rbp)
	movq	_I_size_Organism(%rip), %r14
	movq	%r14, -136(%rbp)
	movq	$8, %r14
	movq	%r14, -144(%rbp)
	movq	-136(%rbp), %r12
	movq	-144(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -152(%rbp)
	movq	-128(%rbp), %r12
	movq	-152(%rbp), %r13
	movq	$1, 8(%r12,%r13)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	_I_size_Organism(%rip), %r14
	movq	%r14, -168(%rbp)
	movq	$8, %r14
	movq	%r14, -176(%rbp)
	movq	-168(%rbp), %r12
	movq	-176(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -168(%rbp)
	movq	-168(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -184(%rbp)
	movq	-72(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -192(%rbp)
	movq	$3, %r14
	movq	%r14, -200(%rbp)
	movq	-192(%rbp), %r12
	movq	-200(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -192(%rbp)
	movq	-192(%rbp), %r14
	movq	-160(%rbp), %r12
	movq	-184(%rbp), %r13
	movq	%r14, 24(%r12,%r13)
	movq	-56(%rbp), %r14
	movq	%r14, %rax
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret

	.globl	_Animal_IisSleep_b
	.align	4
_Animal_IisSleep_b:
	enter	$64, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -64(%rbp)
	movq	-64(%rbp), %r14
	addq	$40, %r14
	movq	%r14, -64(%rbp)
	movq	-64(%rbp), %r12
	movq	(%r12), %rax
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret

	.globl	_Dog_Ieat_p
	.align	4
_Dog_Ieat_p:
	enter	$80, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -64(%rbp)
	movq	-64(%rbp), %r14
	addq	$48, %r14
	movq	%r14, -64(%rbp)
	movq	-64(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -72(%rbp)
	movq	-72(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -72(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	-80(%rbp), %r14
	addq	$48, %r14
	movq	%r14, -80(%rbp)
	movq	-72(%rbp), %r14
	movq	-80(%rbp), %r12
	movq	%r14, (%r12)
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret

	.globl	_Human_Ilearn_pi
	.align	4
_Human_Ilearn_pi:
	enter	$80, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
	movq	%rsi, %r12
	movq	%r12, -64(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -72(%rbp)
	movq	-72(%rbp), %r14
	addq	$48, %r14
	movq	%r14, -72(%rbp)
	movq	-64(%rbp), %r14
	movq	-72(%rbp), %r12
	movq	%r14, (%r12)
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret

	.globl	_Human_Iinit_o5Humaniii
	.align	4
_Human_Iinit_o5Humaniii:
	enter	$320, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
	movq	%rsi, %r12
	movq	%r12, -64(%rbp)
	movq	%rdx, %r12
	movq	%r12, -72(%rbp)
	movq	%rcx, %r12
	movq	%r12, -80(%rbp)
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -88(%rbp)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -96(%rbp)
	movq	-96(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -96(%rbp)
	movq	-96(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -104(%rbp)
	movq	$8, %r14
	movq	%r14, -112(%rbp)
	movq	-104(%rbp), %r12
	movq	-112(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -104(%rbp)
	movq	-104(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -120(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-120(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -128(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-128(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	-88(%rbp), %r14
	movq	-136(%rbp), %r12
	movq	%r14, (%r12)
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	-144(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -144(%rbp)
	movq	-144(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	$0, %r14
	movq	%r14, -152(%rbp)
l11:
	movq	-152(%rbp), %r12
	movq	-88(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -152(%rbp)
	jl	l12
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -168(%rbp)
	movq	-168(%rbp), %r14
	addq	$56, %r14
	movq	%r14, -168(%rbp)
	movq	-160(%rbp), %r14
	movq	-168(%rbp), %r12
	movq	%r14, (%r12)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -176(%rbp)
	movq	-176(%rbp), %r14
	addq	$48, %r14
	movq	%r14, -176(%rbp)
	movq	-72(%rbp), %r14
	movq	-176(%rbp), %r12
	movq	%r14, (%r12)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -184(%rbp)
	movq	-184(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -192(%rbp)
	movq	_I_size_Organism(%rip), %r14
	movq	%r14, -200(%rbp)
	movq	$8, %r14
	movq	%r14, -208(%rbp)
	movq	-200(%rbp), %r12
	movq	-208(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -200(%rbp)
	movq	-200(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -216(%rbp)
	movq	-80(%rbp), %r14
	movq	-192(%rbp), %r12
	movq	-216(%rbp), %r13
	movq	%r14, 16(%r12,%r13)
	movq	-184(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -224(%rbp)
	movq	_I_size_Organism(%rip), %r14
	movq	%r14, -232(%rbp)
	movq	$8, %r14
	movq	%r14, -240(%rbp)
	movq	-232(%rbp), %r12
	movq	-240(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -232(%rbp)
	movq	-232(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -248(%rbp)
	movq	-224(%rbp), %r12
	movq	-248(%rbp), %r13
	movq	$0, 24(%r12,%r13)
	movq	-184(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -256(%rbp)
	movq	_I_size_Organism(%rip), %r14
	movq	%r14, -264(%rbp)
	movq	$8, %r14
	movq	%r14, -272(%rbp)
	movq	-264(%rbp), %r12
	movq	-272(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -264(%rbp)
	movq	-264(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -280(%rbp)
	movq	-256(%rbp), %r12
	movq	-280(%rbp), %r13
	movq	$0, 8(%r12,%r13)
	movq	-56(%rbp), %r14
	movq	%r14, %rax
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l12:
	movq	-136(%rbp), %r12
	movq	-152(%rbp), %r13
	leaq	(%r12,%r13,8), %r14
	movq	%r14, -288(%rbp)
	movq	-288(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -296(%rbp)
	movq	$0, %r14
	movq	%r14, -304(%rbp)
	movq	-304(%rbp), %r14
	movq	-296(%rbp), %r12
	movq	%r14, (%r12)
	movq	-152(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -312(%rbp)
	movq	-312(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -312(%rbp)
	movq	-312(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -152(%rbp)
	jmp	l11

	.globl	_I_init_Organism
	.align	4
_I_init_Organism:
	enter	$96, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	cmpq	$0, _I_size_Organism(%rip)
	je	l0
l1:
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l0:
	movq	$2, _I_size_Organism(%rip)
	movq	$8, %r14
	movq	%r14, -56(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-56(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -64(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -72(%rbp)
	movq	-72(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	-80(%rbp), %r14
	addq	$0, %r14
	movq	%r14, -80(%rbp)
	leaq	_Organism_Ievolve_p(%rip), %r14
	movq	%r14, -88(%rbp)
	movq	-88(%rbp), %r14
	movq	-80(%rbp), %r12
	movq	%r14, (%r12)
	movq	-72(%rbp), %r14
	movq	%r14, _I_vt_Organism(%rip)
	jmp	l1

	.globl	_Animal_Istatus_ai
	.align	4
_Animal_Istatus_ai:
	enter	$240, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -64(%rbp)
	movq	-64(%rbp), %r14
	addq	$40, %r14
	movq	%r14, -64(%rbp)
	movq	-64(%rbp), %r12
	testq	$1, (%r12)
	jnz	l4
	movq	$48, %r14
	movq	%r14, -72(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-72(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -80(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-80(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -88(%rbp)
	movq	-88(%rbp), %r12
	movq	$5, (%r12)
	movq	$8, %r14
	movq	%r14, -96(%rbp)
	movq	-96(%rbp), %r12
	movq	-88(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -96(%rbp)
	movq	-96(%rbp), %r12
	movq	$97, (%r12)
	movq	$16, %r14
	movq	%r14, -104(%rbp)
	movq	-104(%rbp), %r12
	movq	-88(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -104(%rbp)
	movq	-104(%rbp), %r12
	movq	$119, (%r12)
	movq	$24, %r14
	movq	%r14, -112(%rbp)
	movq	-112(%rbp), %r12
	movq	-88(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -112(%rbp)
	movq	-112(%rbp), %r12
	movq	$97, (%r12)
	movq	$32, %r14
	movq	%r14, -120(%rbp)
	movq	-120(%rbp), %r12
	movq	-88(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -120(%rbp)
	movq	-120(%rbp), %r12
	movq	$107, (%r12)
	movq	$40, %r14
	movq	%r14, -128(%rbp)
	movq	-128(%rbp), %r12
	movq	-88(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -128(%rbp)
	movq	-128(%rbp), %r12
	movq	$101, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	-136(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -136(%rbp)
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -88(%rbp)
	movq	-88(%rbp), %r14
	movq	%r14, %rax

	.globl	_label8
	.align	4
_label8:
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l4:
	movq	$72, %r14
	movq	%r14, -144(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-144(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -152(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-152(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	-160(%rbp), %r12
	movq	$8, (%r12)
	movq	$8, %r14
	movq	%r14, -168(%rbp)
	movq	-168(%rbp), %r12
	movq	-160(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -168(%rbp)
	movq	-168(%rbp), %r12
	movq	$115, (%r12)
	movq	$16, %r14
	movq	%r14, -176(%rbp)
	movq	-176(%rbp), %r12
	movq	-160(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -176(%rbp)
	movq	-176(%rbp), %r12
	movq	$108, (%r12)
	movq	$24, %r14
	movq	%r14, -184(%rbp)
	movq	-184(%rbp), %r12
	movq	-160(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -184(%rbp)
	movq	-184(%rbp), %r12
	movq	$101, (%r12)
	movq	$32, %r14
	movq	%r14, -192(%rbp)
	movq	-192(%rbp), %r12
	movq	-160(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -192(%rbp)
	movq	-192(%rbp), %r12
	movq	$101, (%r12)
	movq	$40, %r14
	movq	%r14, -200(%rbp)
	movq	-200(%rbp), %r12
	movq	-160(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -200(%rbp)
	movq	-200(%rbp), %r12
	movq	$112, (%r12)
	movq	$48, %r14
	movq	%r14, -208(%rbp)
	movq	-208(%rbp), %r12
	movq	-160(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -208(%rbp)
	movq	-208(%rbp), %r12
	movq	$105, (%r12)
	movq	$56, %r14
	movq	%r14, -216(%rbp)
	movq	-216(%rbp), %r12
	movq	-160(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -216(%rbp)
	movq	-216(%rbp), %r12
	movq	$110, (%r12)
	movq	$64, %r14
	movq	%r14, -224(%rbp)
	movq	-224(%rbp), %r12
	movq	-160(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -224(%rbp)
	movq	-224(%rbp), %r12
	movq	$103, (%r12)
	movq	-160(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -232(%rbp)
	movq	-232(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -232(%rbp)
	movq	-232(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	-160(%rbp), %r14
	movq	%r14, %rax
	jmp	_label8
	jmp	_label8

	.globl	_I_init_Animal
	.align	4
_I_init_Animal:
	enter	$192, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	cmpq	$0, _I_size_Animal(%rip)
	je	l7
l8:
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l7:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	subq	$8, %rsp
	callq	_I_init_Organism
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	_I_size_Organism(%rip), %r14
	movq	%r14, -56(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -64(%rbp)
	movq	-64(%rbp), %r14
	addq	$4, %r14
	movq	%r14, -64(%rbp)
	movq	-64(%rbp), %r14
	movq	%r14, _I_size_Animal(%rip)
	movq	$48, %r14
	movq	%r14, -72(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-72(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -80(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-80(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -88(%rbp)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -96(%rbp)
	movq	-96(%rbp), %r14
	addq	$0, %r14
	movq	%r14, -96(%rbp)
	leaq	_Organism_Ievolve_p(%rip), %r14
	movq	%r14, -104(%rbp)
	movq	-104(%rbp), %r14
	movq	-96(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -112(%rbp)
	movq	-112(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -112(%rbp)
	leaq	_Animal_Isleep_p(%rip), %r14
	movq	%r14, -120(%rbp)
	movq	-120(%rbp), %r14
	movq	-112(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -128(%rbp)
	movq	-128(%rbp), %r14
	addq	$16, %r14
	movq	%r14, -128(%rbp)
	leaq	_Animal_Ihibernate_p(%rip), %r14
	movq	%r14, -136(%rbp)
	movq	-136(%rbp), %r14
	movq	-128(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	-144(%rbp), %r14
	addq	$24, %r14
	movq	%r14, -144(%rbp)
	leaq	_Animal_Iwake_p(%rip), %r14
	movq	%r14, -152(%rbp)
	movq	-152(%rbp), %r14
	movq	-144(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	-160(%rbp), %r14
	addq	$32, %r14
	movq	%r14, -160(%rbp)
	leaq	_Animal_IisSleep_b(%rip), %r14
	movq	%r14, -168(%rbp)
	movq	-168(%rbp), %r14
	movq	-160(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -176(%rbp)
	movq	-176(%rbp), %r14
	addq	$40, %r14
	movq	%r14, -176(%rbp)
	leaq	_Animal_Istatus_ai(%rip), %r14
	movq	%r14, -184(%rbp)
	movq	-184(%rbp), %r14
	movq	-176(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, _I_vt_Animal(%rip)
	jmp	l8

	.globl	_Animal_Iwake_p
	.align	4
_Animal_Iwake_p:
	enter	$64, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -64(%rbp)
	movq	-64(%rbp), %r14
	addq	$40, %r14
	movq	%r14, -64(%rbp)
	movq	-64(%rbp), %r12
	movq	$0, (%r12)
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret

	.globl	_Organism_Ievolve_p
	.align	4
_Organism_Ievolve_p:
	enter	$80, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -64(%rbp)
	movq	-64(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -64(%rbp)
	movq	$2, %r14
	movq	%r14, -72(%rbp)
	movq	-72(%rbp), %r14
	movq	-64(%rbp), %r12
	imulq	(%r12), %r14
	movq	%r14, -72(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	-80(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -80(%rbp)
	movq	-72(%rbp), %r14
	movq	-80(%rbp), %r12
	movq	%r14, (%r12)
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret

	.globl	_I_init_Human
	.align	4
_I_init_Human:
	enter	$224, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	cmpq	$0, _I_size_Human(%rip)
	je	l14
l15:
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l14:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	subq	$8, %rsp
	callq	_I_init_Animal
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	_I_size_Animal(%rip), %r14
	movq	%r14, -56(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -64(%rbp)
	movq	-64(%rbp), %r14
	addq	$2, %r14
	movq	%r14, -64(%rbp)
	movq	-64(%rbp), %r14
	movq	%r14, _I_size_Human(%rip)
	movq	$64, %r14
	movq	%r14, -72(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-72(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -80(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-80(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -88(%rbp)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -96(%rbp)
	movq	-96(%rbp), %r14
	addq	$0, %r14
	movq	%r14, -96(%rbp)
	leaq	_Organism_Ievolve_p(%rip), %r14
	movq	%r14, -104(%rbp)
	movq	-104(%rbp), %r14
	movq	-96(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -112(%rbp)
	movq	-112(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -112(%rbp)
	leaq	_Animal_Isleep_p(%rip), %r14
	movq	%r14, -120(%rbp)
	movq	-120(%rbp), %r14
	movq	-112(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -128(%rbp)
	movq	-128(%rbp), %r14
	addq	$16, %r14
	movq	%r14, -128(%rbp)
	leaq	_Animal_Ihibernate_p(%rip), %r14
	movq	%r14, -136(%rbp)
	movq	-136(%rbp), %r14
	movq	-128(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	-144(%rbp), %r14
	addq	$24, %r14
	movq	%r14, -144(%rbp)
	leaq	_Animal_Iwake_p(%rip), %r14
	movq	%r14, -152(%rbp)
	movq	-152(%rbp), %r14
	movq	-144(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	-160(%rbp), %r14
	addq	$32, %r14
	movq	%r14, -160(%rbp)
	leaq	_Animal_IisSleep_b(%rip), %r14
	movq	%r14, -168(%rbp)
	movq	-168(%rbp), %r14
	movq	-160(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -176(%rbp)
	movq	-176(%rbp), %r14
	addq	$40, %r14
	movq	%r14, -176(%rbp)
	leaq	_Animal_Istatus_ai(%rip), %r14
	movq	%r14, -184(%rbp)
	movq	-184(%rbp), %r14
	movq	-176(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -192(%rbp)
	movq	-192(%rbp), %r14
	addq	$48, %r14
	movq	%r14, -192(%rbp)
	leaq	_Human_Ilearn_pi(%rip), %r14
	movq	%r14, -200(%rbp)
	movq	-200(%rbp), %r14
	movq	-192(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -208(%rbp)
	movq	-208(%rbp), %r14
	addq	$56, %r14
	movq	%r14, -208(%rbp)
	leaq	_Human_Iinit_o5Humaniii(%rip), %r14
	movq	%r14, -216(%rbp)
	movq	-216(%rbp), %r14
	movq	-208(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, _I_vt_Human(%rip)
	jmp	l15

	.globl	_Animal_Isleep_p
	.align	4
_Animal_Isleep_p:
	enter	$112, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -64(%rbp)
	movq	-64(%rbp), %r14
	addq	$32, %r14
	movq	%r14, -64(%rbp)
	movq	-64(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -72(%rbp)
	movq	-72(%rbp), %r14
	addq	$-1, %r14
	movq	%r14, -72(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	-80(%rbp), %r14
	addq	$32, %r14
	movq	%r14, -80(%rbp)
	movq	-72(%rbp), %r14
	movq	-80(%rbp), %r12
	movq	%r14, (%r12)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -88(%rbp)
	movq	-88(%rbp), %r14
	addq	$32, %r14
	movq	%r14, -88(%rbp)
	movq	-88(%rbp), %r12
	cmpq	$0, (%r12)
	je	l2
l3:
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l2:
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -96(%rbp)
	movq	-56(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -104(%rbp)
	movq	-104(%rbp), %r14
	addq	$24, %r14
	movq	%r14, -104(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-96(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	movq	-104(%rbp), %r12
	callq	*(%r12)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	l3

	.globl	_I_init_Dog
	.align	4
_I_init_Dog:
	enter	$224, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	cmpq	$0, _I_size_Dog(%rip)
	je	l9
l10:
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l9:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	subq	$8, %rsp
	callq	_I_init_Animal
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	_I_size_Animal(%rip), %r14
	movq	%r14, -56(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -64(%rbp)
	movq	-64(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -64(%rbp)
	movq	-64(%rbp), %r14
	movq	%r14, _I_size_Dog(%rip)
	movq	$64, %r14
	movq	%r14, -72(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-72(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -80(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-80(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -88(%rbp)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -96(%rbp)
	movq	-96(%rbp), %r14
	addq	$0, %r14
	movq	%r14, -96(%rbp)
	leaq	_Organism_Ievolve_p(%rip), %r14
	movq	%r14, -104(%rbp)
	movq	-104(%rbp), %r14
	movq	-96(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -112(%rbp)
	movq	-112(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -112(%rbp)
	leaq	_Animal_Isleep_p(%rip), %r14
	movq	%r14, -120(%rbp)
	movq	-120(%rbp), %r14
	movq	-112(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -128(%rbp)
	movq	-128(%rbp), %r14
	addq	$16, %r14
	movq	%r14, -128(%rbp)
	leaq	_Animal_Ihibernate_p(%rip), %r14
	movq	%r14, -136(%rbp)
	movq	-136(%rbp), %r14
	movq	-128(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	-144(%rbp), %r14
	addq	$24, %r14
	movq	%r14, -144(%rbp)
	leaq	_Animal_Iwake_p(%rip), %r14
	movq	%r14, -152(%rbp)
	movq	-152(%rbp), %r14
	movq	-144(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	-160(%rbp), %r14
	addq	$32, %r14
	movq	%r14, -160(%rbp)
	leaq	_Animal_IisSleep_b(%rip), %r14
	movq	%r14, -168(%rbp)
	movq	-168(%rbp), %r14
	movq	-160(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -176(%rbp)
	movq	-176(%rbp), %r14
	addq	$40, %r14
	movq	%r14, -176(%rbp)
	leaq	_Animal_Istatus_ai(%rip), %r14
	movq	%r14, -184(%rbp)
	movq	-184(%rbp), %r14
	movq	-176(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -192(%rbp)
	movq	-192(%rbp), %r14
	addq	$48, %r14
	movq	%r14, -192(%rbp)
	leaq	_Dog_Ieat_p(%rip), %r14
	movq	%r14, -200(%rbp)
	movq	-200(%rbp), %r14
	movq	-192(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -208(%rbp)
	movq	-208(%rbp), %r14
	addq	$56, %r14
	movq	%r14, -208(%rbp)
	leaq	_Dog_Iinit_o3Dogii(%rip), %r14
	movq	%r14, -216(%rbp)
	movq	-216(%rbp), %r14
	movq	-208(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, _I_vt_Dog(%rip)
	jmp	l10


	.bss
	.align 8
.globl _I_vt_Human
_I_vt_Human:
	.zero 64
	.text

.section .ctors
	.align 8
	.quad _I_init_Human
	.text

	.bss
	.align 8
.globl _I_size_Organism
_I_size_Organism:
	.zero 8
	.text

	.bss
	.align 8
.globl _I_size_Human
_I_size_Human:
	.zero 8
	.text

	.bss
	.align 8
.globl _I_size_Dog
_I_size_Dog:
	.zero 8
	.text

	.bss
	.align 8
.globl _I_vt_Animal
_I_vt_Animal:
	.zero 48
	.text

.section .ctors
	.align 8
	.quad _I_init_Animal
	.text

	.bss
	.align 8
.globl _I_vt_Dog
_I_vt_Dog:
	.zero 64
	.text

.section .ctors
	.align 8
	.quad _I_init_Dog
	.text

	.section .data
	.align 8
.globl _I_g_x_i
_I_g_x_i:
	.quad 0
	.text

	.bss
	.align 8
.globl _I_vt_Organism
_I_vt_Organism:
	.zero 8
	.text

.section .ctors
	.align 8
	.quad _I_init_Organism
	.text

	.section .data
	.align 8
.globl _I_g_z_i
_I_g_z_i:
	.quad 0
	.text

	.section .data
	.align 8
.globl _I_g_y_i
_I_g_y_i:
	.quad 0
	.text

	.bss
	.align 8
.globl _I_size_Animal
_I_size_Animal:
	.zero 8
	.text
