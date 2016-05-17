	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$720, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
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
	callq	_Ipoison_ai
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
	callq	_Ipoison_ai
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
	movq	$0, %r14
	movq	%r14, -96(%rbp)
	movq	-88(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -104(%rbp)
	movq	-96(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -96(%rbp)
	jl	l1
	movq	-96(%rbp), %r12
	movq	-104(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -96(%rbp)
	jl	l2
l1:
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
label0:
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l2:
	movq	-88(%rbp), %r12
	movq	-96(%rbp), %r13
	movq	$97, (%r12,%r13,8)
	movq	$1, %r14
	movq	%r14, -112(%rbp)
	movq	-88(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -120(%rbp)
	movq	-112(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -112(%rbp)
	jl	l4
	movq	-112(%rbp), %r12
	movq	-120(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -112(%rbp)
	jl	l5
l4:
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
	jmp	label0
l5:
	movq	-88(%rbp), %r12
	movq	-112(%rbp), %r13
	movq	$110, (%r12,%r13,8)
	movq	$2, %r14
	movq	%r14, -128(%rbp)
	movq	-88(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -136(%rbp)
	movq	-128(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -128(%rbp)
	jl	l7
	movq	-128(%rbp), %r12
	movq	-136(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -128(%rbp)
	jl	l8
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
	jmp	label0
l8:
	movq	-88(%rbp), %r12
	movq	-128(%rbp), %r13
	movq	$116, (%r12,%r13,8)
	movq	$3, %r14
	movq	%r14, -144(%rbp)
	movq	-88(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -152(%rbp)
	movq	-144(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -144(%rbp)
	jl	l10
	movq	-144(%rbp), %r12
	movq	-152(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -144(%rbp)
	jl	l11
l10:
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
	jmp	label0
l11:
	movq	-88(%rbp), %r12
	movq	-144(%rbp), %r13
	movq	$105, (%r12,%r13,8)
	movq	$4, %r14
	movq	%r14, -160(%rbp)
	movq	-88(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -168(%rbp)
	movq	-160(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -160(%rbp)
	jl	l13
	movq	-160(%rbp), %r12
	movq	-168(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -160(%rbp)
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
	jmp	label0
l14:
	movq	-88(%rbp), %r12
	movq	-160(%rbp), %r13
	movq	$100, (%r12,%r13,8)
	movq	$5, %r14
	movq	%r14, -176(%rbp)
	movq	-88(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -184(%rbp)
	movq	-176(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -176(%rbp)
	jl	l16
	movq	-176(%rbp), %r12
	movq	-184(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -176(%rbp)
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
	jmp	label0
l17:
	movq	-88(%rbp), %r12
	movq	-176(%rbp), %r13
	movq	$111, (%r12,%r13,8)
	movq	$6, %r14
	movq	%r14, -192(%rbp)
	movq	-88(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -200(%rbp)
	movq	-192(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -192(%rbp)
	jl	l19
	movq	-192(%rbp), %r12
	movq	-200(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -192(%rbp)
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
	jmp	label0
l20:
	movq	-88(%rbp), %r12
	movq	-192(%rbp), %r13
	movq	$116, (%r12,%r13,8)
	movq	$7, %r14
	movq	%r14, -208(%rbp)
	movq	-88(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -216(%rbp)
	movq	-208(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -208(%rbp)
	jl	l22
	movq	-208(%rbp), %r12
	movq	-216(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -208(%rbp)
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
	jmp	label0
l23:
	movq	-88(%rbp), %r12
	movq	-208(%rbp), %r13
	movq	$101, (%r12,%r13,8)
	movq	$72, %r14
	movq	%r14, -224(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-224(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -232(%rbp)
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
	movq	-232(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -240(%rbp)
	movq	-240(%rbp), %r12
	movq	$8, (%r12)
	movq	-240(%rbp), %r12
	movq	$84, 8(%r12)
	movq	-240(%rbp), %r12
	movq	$111, 16(%r12)
	movq	-240(%rbp), %r12
	movq	$32, 24(%r12)
	movq	-240(%rbp), %r12
	movq	$99, 32(%r12)
	movq	-240(%rbp), %r12
	movq	$117, 40(%r12)
	movq	-240(%rbp), %r12
	movq	$114, 48(%r12)
	movq	-240(%rbp), %r12
	movq	$101, 56(%r12)
	movq	-240(%rbp), %r12
	movq	$32, 64(%r12)
	movq	-240(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -248(%rbp)
	movq	-248(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -248(%rbp)
	movq	-248(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -240(%rbp)
	movq	-240(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -256(%rbp)
	movq	-256(%rbp), %r14
	movq	-72(%rbp), %r12
	addq	-8(%r12), %r14
	movq	%r14, -256(%rbp)
	movq	-256(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -264(%rbp)
	movq	-264(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -272(%rbp)
	movq	-272(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -272(%rbp)
	movq	-272(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -280(%rbp)
	movq	-280(%rbp), %r14
	imulq	$8, %r14
	movq	%r14, -280(%rbp)
	movq	-280(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -288(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-288(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -296(%rbp)
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
	movq	-296(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -304(%rbp)
	movq	-264(%rbp), %r14
	movq	-304(%rbp), %r12
	movq	%r14, (%r12)
	movq	-304(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -312(%rbp)
	movq	-312(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -312(%rbp)
	movq	-312(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -304(%rbp)
	movq	$0, %r14
	movq	%r14, -320(%rbp)
l24:
	movq	-320(%rbp), %r14
	movq	-240(%rbp), %r12
	cmpq	-8(%r12), %r14
	movq	%r14, -320(%rbp)
	jl	l25
	movq	$0, %r14
	movq	%r14, -328(%rbp)
	movq	-240(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -336(%rbp)
l27:
	movq	-328(%rbp), %r14
	movq	-72(%rbp), %r12
	cmpq	-8(%r12), %r14
	movq	%r14, -328(%rbp)
	jl	l28
	movq	$104, %r14
	movq	%r14, -344(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-344(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -352(%rbp)
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
	movq	-352(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -360(%rbp)
	movq	-360(%rbp), %r12
	movq	$12, (%r12)
	movq	-360(%rbp), %r12
	movq	$121, 8(%r12)
	movq	-360(%rbp), %r12
	movq	$111, 16(%r12)
	movq	-360(%rbp), %r12
	movq	$117, 24(%r12)
	movq	-360(%rbp), %r12
	movq	$32, 32(%r12)
	movq	-360(%rbp), %r12
	movq	$110, 40(%r12)
	movq	-360(%rbp), %r12
	movq	$101, 48(%r12)
	movq	-360(%rbp), %r12
	movq	$101, 56(%r12)
	movq	-360(%rbp), %r12
	movq	$100, 64(%r12)
	movq	-360(%rbp), %r12
	movq	$32, 72(%r12)
	movq	-360(%rbp), %r12
	movq	$97, 80(%r12)
	movq	-360(%rbp), %r12
	movq	$110, 88(%r12)
	movq	-360(%rbp), %r12
	movq	$32, 96(%r12)
	movq	-360(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -368(%rbp)
	movq	-368(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -368(%rbp)
	movq	-368(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -360(%rbp)
	movq	-304(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -376(%rbp)
	movq	-376(%rbp), %r14
	movq	-360(%rbp), %r12
	addq	-8(%r12), %r14
	movq	%r14, -376(%rbp)
	movq	-376(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -384(%rbp)
	movq	-384(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -392(%rbp)
	movq	-392(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -392(%rbp)
	movq	-392(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -400(%rbp)
	movq	-400(%rbp), %r14
	imulq	$8, %r14
	movq	%r14, -400(%rbp)
	movq	-400(%rbp), %r14
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
	movq	-384(%rbp), %r14
	movq	-424(%rbp), %r12
	movq	%r14, (%r12)
	movq	-424(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -432(%rbp)
	movq	-432(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -432(%rbp)
	movq	-432(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -424(%rbp)
	movq	$0, %r14
	movq	%r14, -440(%rbp)
l30:
	movq	-440(%rbp), %r14
	movq	-304(%rbp), %r12
	cmpq	-8(%r12), %r14
	movq	%r14, -440(%rbp)
	jl	l31
	movq	$0, %r14
	movq	%r14, -448(%rbp)
	movq	-304(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -456(%rbp)
l33:
	movq	-448(%rbp), %r14
	movq	-360(%rbp), %r12
	cmpq	-8(%r12), %r14
	movq	%r14, -448(%rbp)
	jl	l34
	movq	-424(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -464(%rbp)
	movq	-464(%rbp), %r14
	movq	-88(%rbp), %r12
	addq	-8(%r12), %r14
	movq	%r14, -464(%rbp)
	movq	-464(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -472(%rbp)
	movq	-472(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -480(%rbp)
	movq	-480(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -480(%rbp)
	movq	-480(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -488(%rbp)
	movq	-488(%rbp), %r14
	imulq	$8, %r14
	movq	%r14, -488(%rbp)
	movq	-488(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -496(%rbp)
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
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
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
	movq	%r14, %r12
	movq	%r12, -512(%rbp)
	movq	-472(%rbp), %r14
	movq	-512(%rbp), %r12
	movq	%r14, (%r12)
	movq	-512(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -520(%rbp)
	movq	-520(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -520(%rbp)
	movq	-520(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -512(%rbp)
	movq	$0, %r14
	movq	%r14, -528(%rbp)
l36:
	movq	-528(%rbp), %r14
	movq	-424(%rbp), %r12
	cmpq	-8(%r12), %r14
	movq	%r14, -528(%rbp)
	jl	l37
	movq	$0, %r14
	movq	%r14, -536(%rbp)
	movq	-424(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -544(%rbp)
l39:
	movq	-536(%rbp), %r14
	movq	-88(%rbp), %r12
	cmpq	-8(%r12), %r14
	movq	%r14, -536(%rbp)
	jl	l40
	movq	-512(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -552(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-552(%rbp), %r14
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
	jmp	label0
l25:
	movq	-240(%rbp), %r12
	movq	-320(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -560(%rbp)
	movq	-560(%rbp), %r14
	movq	-304(%rbp), %r12
	movq	-320(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-320(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -568(%rbp)
	movq	-568(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -568(%rbp)
	movq	-568(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -320(%rbp)
	jmp	l24
l28:
	movq	-328(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -576(%rbp)
	movq	-576(%rbp), %r12
	movq	-336(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -576(%rbp)
	movq	-576(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -584(%rbp)
	movq	-584(%rbp), %r14
	imulq	$8, %r14
	movq	%r14, -584(%rbp)
	movq	-304(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -592(%rbp)
	movq	-592(%rbp), %r12
	movq	-584(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -592(%rbp)
	movq	-72(%rbp), %r12
	movq	-328(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -600(%rbp)
	movq	-600(%rbp), %r14
	movq	-592(%rbp), %r12
	movq	%r14, (%r12)
	movq	-328(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -608(%rbp)
	movq	-608(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -608(%rbp)
	movq	-608(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -328(%rbp)
	jmp	l27
l31:
	movq	-304(%rbp), %r12
	movq	-440(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -616(%rbp)
	movq	-616(%rbp), %r14
	movq	-424(%rbp), %r12
	movq	-440(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-440(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -624(%rbp)
	movq	-624(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -624(%rbp)
	movq	-624(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -440(%rbp)
	jmp	l30
l34:
	movq	-448(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -632(%rbp)
	movq	-632(%rbp), %r12
	movq	-456(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -632(%rbp)
	movq	-632(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -640(%rbp)
	movq	-640(%rbp), %r14
	imulq	$8, %r14
	movq	%r14, -640(%rbp)
	movq	-424(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -648(%rbp)
	movq	-648(%rbp), %r12
	movq	-640(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -648(%rbp)
	movq	-360(%rbp), %r12
	movq	-448(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -656(%rbp)
	movq	-656(%rbp), %r14
	movq	-648(%rbp), %r12
	movq	%r14, (%r12)
	movq	-448(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -664(%rbp)
	movq	-664(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -664(%rbp)
	movq	-664(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -448(%rbp)
	jmp	l33
l37:
	movq	-424(%rbp), %r12
	movq	-528(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -672(%rbp)
	movq	-672(%rbp), %r14
	movq	-512(%rbp), %r12
	movq	-528(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-528(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -680(%rbp)
	movq	-680(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -680(%rbp)
	movq	-680(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -528(%rbp)
	jmp	l36
l40:
	movq	-536(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -688(%rbp)
	movq	-688(%rbp), %r12
	movq	-544(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -688(%rbp)
	movq	-688(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -696(%rbp)
	movq	-696(%rbp), %r14
	imulq	$8, %r14
	movq	%r14, -696(%rbp)
	movq	-512(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -704(%rbp)
	movq	-704(%rbp), %r12
	movq	-696(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -704(%rbp)
	movq	-88(%rbp), %r12
	movq	-536(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -712(%rbp)
	movq	-712(%rbp), %r14
	movq	-704(%rbp), %r12
	movq	%r14, (%r12)
	movq	-536(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -720(%rbp)
	movq	-720(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -720(%rbp)
	movq	-720(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -536(%rbp)
	jmp	l39

	.globl	_Ipoison_ai
	.align	4
_Ipoison_ai:
	enter	$80, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	$72, %r14
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
	movq	-72(%rbp), %r12
	movq	$8, (%r12)
	movq	-72(%rbp), %r12
	movq	$112, 8(%r12)
	movq	-72(%rbp), %r12
	movq	$111, 16(%r12)
	movq	-72(%rbp), %r12
	movq	$105, 24(%r12)
	movq	-72(%rbp), %r12
	movq	$115, 32(%r12)
	movq	-72(%rbp), %r12
	movq	$111, 40(%r12)
	movq	-72(%rbp), %r12
	movq	$110, 48(%r12)
	movq	-72(%rbp), %r12
	movq	$32, 56(%r12)
	movq	-72(%rbp), %r12
	movq	$32, 64(%r12)
	movq	-72(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	-80(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -80(%rbp)
	movq	-80(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -72(%rbp)
	movq	-72(%rbp), %r14
	movq	%r14, %rax
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
