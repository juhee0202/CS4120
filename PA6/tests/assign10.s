	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$608, $0
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
	movq	$4, %r14
	movq	%r14, -72(%rbp)
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	-80(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -80(%rbp)
	movq	-80(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -88(%rbp)
	movq	-88(%rbp), %r14
	imulq	$8, %r14
	movq	%r14, -88(%rbp)
	movq	-88(%rbp), %r14
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
	pushq	%rsp
	movq	-96(%rbp), %r14
	movq	%r14, %rdi
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -104(%rbp)
	popq	%rsp
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
l0:
	movq	-128(%rbp), %r12
	movq	-64(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -128(%rbp)
	jl	l1
	movq	-112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	$0, %r14
	movq	%r14, -144(%rbp)
l6:
	movq	-144(%rbp), %r14
	movq	-136(%rbp), %r12
	cmpq	-8(%r12), %r14
	movq	%r14, -144(%rbp)
	jl	l7
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -152(%rbp)
	movq	$4, %r14
	movq	%r14, -160(%rbp)
	movq	$2, %r14
	movq	%r14, -168(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	-168(%rbp), %r14
	movq	%r14, %rdx
	movq	-160(%rbp), %r14
	movq	%r14, %rsi
	movq	-152(%rbp), %r14
	movq	%r14, %rdi
	callq	_If_iaaiii
	movq	%rax, %r12
	movq	%r12, -176(%rbp)
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-176(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -184(%rbp)
	movq	-184(%rbp), %r14
	addq	$-3, %r14
	movq	%r14, -184(%rbp)
	movq	-184(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -192(%rbp)
	movq	-136(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -200(%rbp)
	movq	-192(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -192(%rbp)
	jl	l22
	movq	-192(%rbp), %r12
	movq	-200(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -192(%rbp)
	jl	l23
l22:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	callq	_I_outOfBounds_p
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
label0:
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l1:
	movq	-128(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -208(%rbp)
	movq	-208(%rbp), %r14
	imulq	$8, %r14
	movq	%r14, -208(%rbp)
	movq	-112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -216(%rbp)
	movq	-216(%rbp), %r12
	movq	-208(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -216(%rbp)
	movq	-216(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -224(%rbp)
	movq	-72(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -232(%rbp)
	movq	-232(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -232(%rbp)
	movq	-232(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -240(%rbp)
	movq	-240(%rbp), %r14
	imulq	$8, %r14
	movq	%r14, -240(%rbp)
	movq	-240(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -248(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	-248(%rbp), %r14
	movq	%r14, %rdi
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -256(%rbp)
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-256(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -264(%rbp)
	movq	-72(%rbp), %r14
	movq	-264(%rbp), %r12
	movq	%r14, (%r12)
	movq	-264(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -272(%rbp)
	movq	-272(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -272(%rbp)
	movq	-272(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -264(%rbp)
	movq	$0, %r14
	movq	%r14, -280(%rbp)
l3:
	movq	-280(%rbp), %r12
	movq	-72(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -280(%rbp)
	jl	l4
	movq	-264(%rbp), %r14
	movq	-224(%rbp), %r12
	movq	%r14, (%r12)
	movq	-128(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -288(%rbp)
	movq	-288(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -288(%rbp)
	movq	-288(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -128(%rbp)
	jmp	l0
l4:
	movq	-264(%rbp), %r12
	movq	-280(%rbp), %r13
	movq	$97, (%r12,%r13,8)
	movq	-280(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -296(%rbp)
	movq	-296(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -296(%rbp)
	movq	-296(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -280(%rbp)
	jmp	l3
l7:
	movq	$0, %r14
	movq	%r14, -304(%rbp)
l9:
	movq	-144(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -312(%rbp)
	movq	-136(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -320(%rbp)
	movq	-312(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -312(%rbp)
	jl	l13
	movq	-312(%rbp), %r12
	movq	-320(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -312(%rbp)
	jl	l14
l13:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	callq	_I_outOfBounds_p
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	label0
l14:
	movq	-136(%rbp), %r12
	movq	-312(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -328(%rbp)
	movq	-328(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -328(%rbp)
	movq	-304(%rbp), %r14
	movq	-328(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -304(%rbp)
	jl	l10
	movq	-144(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -336(%rbp)
	movq	-336(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -336(%rbp)
	movq	-336(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -144(%rbp)
	jmp	l6
l10:
	movq	-144(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -344(%rbp)
	movq	-136(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -352(%rbp)
	movq	-344(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -344(%rbp)
	jl	l16
	movq	-344(%rbp), %r12
	movq	-352(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -344(%rbp)
	jl	l17
l16:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	callq	_I_outOfBounds_p
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	label0
l17:
	movq	-304(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -360(%rbp)
	movq	-136(%rbp), %r12
	movq	-344(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -368(%rbp)
	movq	-368(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -368(%rbp)
	movq	-368(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -376(%rbp)
	movq	-360(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -360(%rbp)
	jl	l19
	movq	-360(%rbp), %r12
	movq	-376(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -360(%rbp)
	jl	l20
l19:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	callq	_I_outOfBounds_p
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	label0
l20:
	movq	-144(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -384(%rbp)
	movq	-384(%rbp), %r12
	movq	-304(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -384(%rbp)
	movq	$8, %r14
	movq	%r14, -392(%rbp)
	movq	-392(%rbp), %r12
	movq	-360(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -392(%rbp)
	movq	-136(%rbp), %r12
	movq	-344(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -400(%rbp)
	movq	-400(%rbp), %r12
	movq	-392(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -400(%rbp)
	movq	-384(%rbp), %r14
	movq	-400(%rbp), %r12
	movq	%r14, (%r12)
	movq	-304(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -408(%rbp)
	movq	-408(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -408(%rbp)
	movq	-408(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -304(%rbp)
	jmp	l9
l23:
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -416(%rbp)
	movq	$4, %r14
	movq	%r14, -424(%rbp)
	movq	$2, %r14
	movq	%r14, -432(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	-432(%rbp), %r14
	movq	%r14, %rdx
	movq	-424(%rbp), %r14
	movq	%r14, %rsi
	movq	-416(%rbp), %r14
	movq	%r14, %rdi
	callq	_If_iaaiii
	movq	%rax, %r12
	movq	%r12, -440(%rbp)
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-440(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -448(%rbp)
	movq	-448(%rbp), %r14
	addq	$-6, %r14
	movq	%r14, -448(%rbp)
	movq	-448(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -456(%rbp)
	movq	-136(%rbp), %r12
	movq	-192(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -464(%rbp)
	movq	-464(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -464(%rbp)
	movq	-464(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -472(%rbp)
	movq	-456(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -456(%rbp)
	jl	l25
	movq	-456(%rbp), %r12
	movq	-472(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -456(%rbp)
	jl	l26
l25:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	callq	_I_outOfBounds_p
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	label0
l26:
	movq	$8, %r14
	movq	%r14, -480(%rbp)
	movq	-480(%rbp), %r12
	movq	-456(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -480(%rbp)
	movq	-136(%rbp), %r12
	movq	-192(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -488(%rbp)
	movq	-488(%rbp), %r12
	movq	-480(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -488(%rbp)
	movq	-488(%rbp), %r12
	movq	$47, (%r12)
	movq	$0, %r14
	movq	%r14, -144(%rbp)
l27:
	movq	-144(%rbp), %r14
	movq	-136(%rbp), %r12
	cmpq	-8(%r12), %r14
	movq	%r14, -144(%rbp)
	jl	l28
	jmp	label0
l28:
	movq	$0, %r14
	movq	%r14, -304(%rbp)
l30:
	movq	-144(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -496(%rbp)
	movq	-136(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -504(%rbp)
	movq	-496(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -496(%rbp)
	jl	l34
	movq	-496(%rbp), %r12
	movq	-504(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -496(%rbp)
	jl	l35
l34:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	callq	_I_outOfBounds_p
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	label0
l35:
	movq	-136(%rbp), %r12
	movq	-496(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -512(%rbp)
	movq	-512(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -512(%rbp)
	movq	-304(%rbp), %r14
	movq	-512(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -304(%rbp)
	jl	l31
	movq	-144(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -520(%rbp)
	movq	-520(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -520(%rbp)
	movq	-520(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -144(%rbp)
	jmp	l27
l31:
	movq	-144(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -528(%rbp)
	movq	-136(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -536(%rbp)
	movq	-528(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -528(%rbp)
	jl	l37
	movq	-528(%rbp), %r12
	movq	-536(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -528(%rbp)
	jl	l38
l37:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	callq	_I_outOfBounds_p
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	label0
l38:
	movq	-304(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -544(%rbp)
	movq	-136(%rbp), %r12
	movq	-528(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -552(%rbp)
	movq	-552(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -552(%rbp)
	movq	-552(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -560(%rbp)
	movq	-544(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -544(%rbp)
	jl	l40
	movq	-544(%rbp), %r12
	movq	-560(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -544(%rbp)
	jl	l41
l40:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	callq	_I_outOfBounds_p
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	label0
l41:
	movq	$8, %r14
	movq	%r14, -568(%rbp)
	movq	-568(%rbp), %r12
	movq	-544(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -568(%rbp)
	movq	-136(%rbp), %r12
	movq	-528(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -576(%rbp)
	movq	-576(%rbp), %r12
	movq	-568(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -576(%rbp)
	movq	-576(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -584(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	-584(%rbp), %r14
	movq	%r14, %rdi
	callq	_IunparseInt_aii
	movq	%rax, %r12
	movq	%r12, -592(%rbp)
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-592(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -600(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	-600(%rbp), %r14
	movq	%r14, %rdi
	callq	_Iprintln_pai
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-304(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -608(%rbp)
	movq	-608(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -608(%rbp)
	movq	-608(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -304(%rbp)
	jmp	l30

	.globl	_If_iaaiii
	.align	4
_If_iaaiii:
	enter	$384, $0
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
	movq	$64, %r14
	movq	%r14, -80(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	-80(%rbp), %r14
	movq	%r14, %rdi
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -88(%rbp)
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -96(%rbp)
	movq	-96(%rbp), %r12
	movq	$7, (%r12)
	movq	-96(%rbp), %r12
	movq	$73, 8(%r12)
	movq	-96(%rbp), %r12
	movq	$110, 16(%r12)
	movq	-96(%rbp), %r12
	movq	$100, 24(%r12)
	movq	-96(%rbp), %r12
	movq	$101, 32(%r12)
	movq	-96(%rbp), %r12
	movq	$120, 40(%r12)
	movq	-96(%rbp), %r12
	movq	$58, 48(%r12)
	movq	-96(%rbp), %r12
	movq	$32, 56(%r12)
	movq	-96(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -104(%rbp)
	movq	-104(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -104(%rbp)
	movq	-104(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -96(%rbp)
	movq	-96(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -112(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	-112(%rbp), %r14
	movq	%r14, %rdi
	callq	_Iprint_pai
	popq	%rsp
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
	pushq	%rsp
	movq	-120(%rbp), %r14
	movq	%r14, %rdi
	callq	_IunparseInt_aii
	movq	%rax, %r12
	movq	%r12, -128(%rbp)
	popq	%rsp
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
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	-136(%rbp), %r14
	movq	%r14, %rdi
	callq	_Iprint_pai
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	$16, %r14
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
	pushq	%rsp
	movq	-144(%rbp), %r14
	movq	%r14, %rdi
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -152(%rbp)
	popq	%rsp
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
	movq	$1, (%r12)
	movq	-160(%rbp), %r12
	movq	$32, 8(%r12)
	movq	-160(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -168(%rbp)
	movq	-168(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -168(%rbp)
	movq	-168(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	-160(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -176(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	-176(%rbp), %r14
	movq	%r14, %rdi
	callq	_Iprint_pai
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-72(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -184(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	-184(%rbp), %r14
	movq	%r14, %rdi
	callq	_IunparseInt_aii
	movq	%rax, %r12
	movq	%r12, -192(%rbp)
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-192(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -200(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	-200(%rbp), %r14
	movq	%r14, %rdi
	callq	_Iprintln_pai
	popq	%rsp
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
	movq	%r12, -208(%rbp)
	movq	-56(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -216(%rbp)
	movq	-208(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -208(%rbp)
	jl	l43
	movq	-208(%rbp), %r12
	movq	-216(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -208(%rbp)
	jl	l44
l43:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	callq	_I_outOfBounds_p
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
label1:
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l44:
	movq	-72(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -224(%rbp)
	movq	-56(%rbp), %r12
	movq	-208(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -232(%rbp)
	movq	-232(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -232(%rbp)
	movq	-232(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -240(%rbp)
	movq	-224(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -224(%rbp)
	jl	l46
	movq	-224(%rbp), %r12
	movq	-240(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -224(%rbp)
	jl	l47
l46:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	callq	_I_outOfBounds_p
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	label1
l47:
	movq	$8, %r14
	movq	%r14, -248(%rbp)
	movq	-248(%rbp), %r12
	movq	-224(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -248(%rbp)
	movq	-56(%rbp), %r12
	movq	-208(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -256(%rbp)
	movq	-256(%rbp), %r12
	movq	-248(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -256(%rbp)
	movq	-256(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -264(%rbp)
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -272(%rbp)
	movq	-56(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -280(%rbp)
	movq	-272(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -272(%rbp)
	jl	l49
	movq	-272(%rbp), %r12
	movq	-280(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -272(%rbp)
	jl	l50
l49:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	callq	_I_outOfBounds_p
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	label1
l50:
	movq	-72(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -288(%rbp)
	movq	-56(%rbp), %r12
	movq	-272(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -296(%rbp)
	movq	-296(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -296(%rbp)
	movq	-296(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -304(%rbp)
	movq	-288(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -288(%rbp)
	jl	l52
	movq	-288(%rbp), %r12
	movq	-304(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -288(%rbp)
	jl	l53
l52:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	callq	_I_outOfBounds_p
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	label1
l53:
	movq	$8, %r14
	movq	%r14, -312(%rbp)
	movq	-312(%rbp), %r12
	movq	-288(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -312(%rbp)
	movq	-56(%rbp), %r12
	movq	-272(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -320(%rbp)
	movq	-320(%rbp), %r12
	movq	-312(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -320(%rbp)
	movq	-320(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -328(%rbp)
	movq	-328(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -328(%rbp)
	movq	-328(%rbp), %r14
	movq	-264(%rbp), %r12
	movq	%r14, (%r12)
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -336(%rbp)
	movq	-56(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -344(%rbp)
	movq	-336(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -336(%rbp)
	jl	l55
	movq	-336(%rbp), %r12
	movq	-344(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -336(%rbp)
	jl	l56
l55:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	callq	_I_outOfBounds_p
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	label1
l56:
	movq	-72(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -352(%rbp)
	movq	-56(%rbp), %r12
	movq	-336(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -360(%rbp)
	movq	-360(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -360(%rbp)
	movq	-360(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -368(%rbp)
	movq	-352(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -352(%rbp)
	jl	l58
	movq	-352(%rbp), %r12
	movq	-368(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -352(%rbp)
	jl	l59
l58:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	callq	_I_outOfBounds_p
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	label1
l59:
	movq	$8, %r14
	movq	%r14, -376(%rbp)
	movq	-376(%rbp), %r12
	movq	-352(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -376(%rbp)
	movq	-56(%rbp), %r12
	movq	-336(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -384(%rbp)
	movq	-384(%rbp), %r12
	movq	-376(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -384(%rbp)
	movq	-384(%rbp), %r12
	movq	(%r12), %rax
	jmp	label1
