	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$640, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
	movq	$1, %r14
	movq	%r14, -64(%rbp)
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -72(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	subq	$24, %rsp
	movq	%rsp, %rdi
	addq	$16, %rdi
	movq	-72(%rbp), %r14
	movq	%r14, %rsi
	callq	_IreturnNext5Sequential_t5iiiiii
	movq	%rax, %r12
	movq	%r12, -80(%rbp)
	movq	(%rdi), %r14
	movq	%r14, -88(%rbp)
	movq	16(%rdi), %r14
	movq	%r14, -96(%rbp)
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-96(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -104(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-104(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %r12
	movq	%r12, -112(%rbp)
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
	movq	-112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -120(%rbp)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -128(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-128(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %r12
	movq	%r12, -136(%rbp)
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
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	-80(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -152(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-152(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %r12
	movq	%r12, -160(%rbp)
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
	movq	-160(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -168(%rbp)
	movq	$136, %r14
	movq	%r14, -176(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-176(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -184(%rbp)
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
	movq	-184(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -192(%rbp)
	movq	-192(%rbp), %r12
	movq	$16, (%r12)
	movq	-192(%rbp), %r12
	movq	$78, 8(%r12)
	movq	-192(%rbp), %r12
	movq	$101, 16(%r12)
	movq	-192(%rbp), %r12
	movq	$120, 24(%r12)
	movq	-192(%rbp), %r12
	movq	$116, 32(%r12)
	movq	-192(%rbp), %r12
	movq	$32, 40(%r12)
	movq	-192(%rbp), %r12
	movq	$51, 48(%r12)
	movq	-192(%rbp), %r12
	movq	$32, 56(%r12)
	movq	-192(%rbp), %r12
	movq	$101, 64(%r12)
	movq	-192(%rbp), %r12
	movq	$118, 72(%r12)
	movq	-192(%rbp), %r12
	movq	$101, 80(%r12)
	movq	-192(%rbp), %r12
	movq	$110, 88(%r12)
	movq	-192(%rbp), %r12
	movq	$32, 96(%r12)
	movq	-192(%rbp), %r12
	movq	$110, 104(%r12)
	movq	-192(%rbp), %r12
	movq	$117, 112(%r12)
	movq	-192(%rbp), %r12
	movq	$109, 120(%r12)
	movq	-192(%rbp), %r12
	movq	$32, 128(%r12)
	movq	-192(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -200(%rbp)
	movq	-200(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -200(%rbp)
	movq	-200(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -192(%rbp)
	movq	-192(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -208(%rbp)
	movq	-208(%rbp), %r14
	movq	-168(%rbp), %r12
	addq	-8(%r12), %r14
	movq	%r14, -208(%rbp)
	movq	-208(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -216(%rbp)
	movq	-216(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -224(%rbp)
	movq	-224(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -224(%rbp)
	movq	-224(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -232(%rbp)
	movq	-232(%rbp), %r14
	imulq	$8, %r14
	movq	%r14, -232(%rbp)
	movq	-232(%rbp), %r14
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
	movq	-216(%rbp), %r14
	movq	-256(%rbp), %r12
	movq	%r14, (%r12)
	movq	-256(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -264(%rbp)
	movq	-264(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -264(%rbp)
	movq	-264(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -256(%rbp)
	movq	$0, %r14
	movq	%r14, -272(%rbp)
l0:
	movq	-272(%rbp), %r14
	movq	-192(%rbp), %r12
	cmpq	-8(%r12), %r14
	movq	%r14, -272(%rbp)
	jl	l1
	movq	$0, %r14
	movq	%r14, -280(%rbp)
	movq	-192(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -288(%rbp)
l3:
	movq	-280(%rbp), %r14
	movq	-168(%rbp), %r12
	cmpq	-8(%r12), %r14
	movq	%r14, -280(%rbp)
	jl	l4
	movq	-256(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -296(%rbp)
	movq	-296(%rbp), %r14
	movq	-144(%rbp), %r12
	addq	-8(%r12), %r14
	movq	%r14, -296(%rbp)
	movq	-296(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -304(%rbp)
	movq	-304(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -312(%rbp)
	movq	-312(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -312(%rbp)
	movq	-312(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -320(%rbp)
	movq	-320(%rbp), %r14
	imulq	$8, %r14
	movq	%r14, -320(%rbp)
	movq	-320(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -328(%rbp)
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
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
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
	movq	%r14, %r12
	movq	%r12, -344(%rbp)
	movq	-304(%rbp), %r14
	movq	-344(%rbp), %r12
	movq	%r14, (%r12)
	movq	-344(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -352(%rbp)
	movq	-352(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -352(%rbp)
	movq	-352(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -344(%rbp)
	movq	$0, %r14
	movq	%r14, -360(%rbp)
l6:
	movq	-360(%rbp), %r14
	movq	-256(%rbp), %r12
	cmpq	-8(%r12), %r14
	movq	%r14, -360(%rbp)
	jl	l7
	movq	$0, %r14
	movq	%r14, -368(%rbp)
	movq	-256(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -376(%rbp)
l9:
	movq	-368(%rbp), %r14
	movq	-144(%rbp), %r12
	cmpq	-8(%r12), %r14
	movq	%r14, -368(%rbp)
	jl	l10
	movq	-344(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -384(%rbp)
	movq	-384(%rbp), %r14
	movq	-120(%rbp), %r12
	addq	-8(%r12), %r14
	movq	%r14, -384(%rbp)
	movq	-384(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -392(%rbp)
	movq	-392(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -400(%rbp)
	movq	-400(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -400(%rbp)
	movq	-400(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -408(%rbp)
	movq	-408(%rbp), %r14
	imulq	$8, %r14
	movq	%r14, -408(%rbp)
	movq	-408(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -416(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-416(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -424(%rbp)
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
	movq	-424(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -432(%rbp)
	movq	-392(%rbp), %r14
	movq	-432(%rbp), %r12
	movq	%r14, (%r12)
	movq	-432(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -440(%rbp)
	movq	-440(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -440(%rbp)
	movq	-440(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -432(%rbp)
	movq	$0, %r14
	movq	%r14, -448(%rbp)
l12:
	movq	-448(%rbp), %r14
	movq	-344(%rbp), %r12
	cmpq	-8(%r12), %r14
	movq	%r14, -448(%rbp)
	jl	l13
	movq	$0, %r14
	movq	%r14, -456(%rbp)
	movq	-344(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -464(%rbp)
l15:
	movq	-456(%rbp), %r14
	movq	-120(%rbp), %r12
	cmpq	-8(%r12), %r14
	movq	%r14, -456(%rbp)
	jl	l16
	movq	-432(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -472(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-472(%rbp), %r14
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
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l1:
	movq	-192(%rbp), %r12
	movq	-272(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -480(%rbp)
	movq	-480(%rbp), %r14
	movq	-256(%rbp), %r12
	movq	-272(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-272(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -488(%rbp)
	movq	-488(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -488(%rbp)
	movq	-488(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -272(%rbp)
	jmp	l0
l4:
	movq	-280(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -496(%rbp)
	movq	-496(%rbp), %r12
	movq	-288(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -496(%rbp)
	movq	-496(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -504(%rbp)
	movq	-504(%rbp), %r14
	imulq	$8, %r14
	movq	%r14, -504(%rbp)
	movq	-256(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -512(%rbp)
	movq	-512(%rbp), %r12
	movq	-504(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -512(%rbp)
	movq	-168(%rbp), %r12
	movq	-280(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -520(%rbp)
	movq	-520(%rbp), %r14
	movq	-512(%rbp), %r12
	movq	%r14, (%r12)
	movq	-280(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -528(%rbp)
	movq	-528(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -528(%rbp)
	movq	-528(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -280(%rbp)
	jmp	l3
l7:
	movq	-256(%rbp), %r12
	movq	-360(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -536(%rbp)
	movq	-536(%rbp), %r14
	movq	-344(%rbp), %r12
	movq	-360(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-360(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -544(%rbp)
	movq	-544(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -544(%rbp)
	movq	-544(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -360(%rbp)
	jmp	l6
l10:
	movq	-368(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -552(%rbp)
	movq	-552(%rbp), %r12
	movq	-376(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -552(%rbp)
	movq	-552(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -560(%rbp)
	movq	-560(%rbp), %r14
	imulq	$8, %r14
	movq	%r14, -560(%rbp)
	movq	-344(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -568(%rbp)
	movq	-568(%rbp), %r12
	movq	-560(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -568(%rbp)
	movq	-144(%rbp), %r12
	movq	-368(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -576(%rbp)
	movq	-576(%rbp), %r14
	movq	-568(%rbp), %r12
	movq	%r14, (%r12)
	movq	-368(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -584(%rbp)
	movq	-584(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -584(%rbp)
	movq	-584(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -368(%rbp)
	jmp	l9
l13:
	movq	-344(%rbp), %r12
	movq	-448(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -592(%rbp)
	movq	-592(%rbp), %r14
	movq	-432(%rbp), %r12
	movq	-448(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-448(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -600(%rbp)
	movq	-600(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -600(%rbp)
	movq	-600(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -448(%rbp)
	jmp	l12
l16:
	movq	-456(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -608(%rbp)
	movq	-608(%rbp), %r12
	movq	-464(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -608(%rbp)
	movq	-608(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -616(%rbp)
	movq	-616(%rbp), %r14
	imulq	$8, %r14
	movq	%r14, -616(%rbp)
	movq	-432(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -624(%rbp)
	movq	-624(%rbp), %r12
	movq	-616(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -624(%rbp)
	movq	-120(%rbp), %r12
	movq	-456(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -632(%rbp)
	movq	-632(%rbp), %r14
	movq	-624(%rbp), %r12
	movq	%r14, (%r12)
	movq	-456(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -640(%rbp)
	movq	-640(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -640(%rbp)
	movq	-640(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -456(%rbp)
	jmp	l15

	.globl	_IreturnNext5Sequential_t5iiiiii
	.align	4
_IreturnNext5Sequential_t5iiiiii:
	enter	$96, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rsi, %r12
	movq	%r12, -56(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -64(%rbp)
	movq	-64(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -64(%rbp)
	movq	-64(%rbp), %r14
	movq	%r14, %rax
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -72(%rbp)
	movq	-72(%rbp), %r14
	addq	$2, %r14
	movq	%r14, -72(%rbp)
	movq	-72(%rbp), %r14
	movq	%r14, %rdx
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	-80(%rbp), %r14
	addq	$3, %r14
	movq	%r14, -80(%rbp)
	movq	-80(%rbp), %r14
	movq	%r14, (%rdi)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -88(%rbp)
	movq	-88(%rbp), %r14
	addq	$4, %r14
	movq	%r14, -88(%rbp)
	movq	-88(%rbp), %r14
	movq	%r14, 8(%rdi)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -96(%rbp)
	movq	-96(%rbp), %r14
	addq	$5, %r14
	movq	%r14, -96(%rbp)
	movq	-96(%rbp), %r14
	movq	%r14, 16(%rdi)
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
