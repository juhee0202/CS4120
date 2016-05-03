	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$1008, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %rcx
	movq	%rcx, -56(%rbp)
	movq	$0, %rcx
	movq	%rcx, -64(%rbp)
	movq	$0, %rcx
	movq	%rcx, -72(%rbp)
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
	movq	-72(%rbp), %rdx
	movq	%rdx, %rsi
	movq	-64(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_Ilt_bii
	movq	%rax, %rcx
	movq	%rcx, -80(%rbp)
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
	movq	-80(%rbp), %rcx
	movq	-80(%rbp), %rdx
	testq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	jnz	l2
l1:
	movq	$0, %rcx
	movq	%rcx, -88(%rbp)
	movq	$0, %rcx
	movq	%rcx, -96(%rbp)
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
	movq	-96(%rbp), %rdx
	movq	%rdx, %rsi
	movq	-88(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_Ilt_bii
	movq	%rax, %rcx
	movq	%rcx, -104(%rbp)
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
	movq	-104(%rbp), %rcx
	movq	-104(%rbp), %rdx
	testq	%rdx, %rcx
	movq	%rcx, -104(%rbp)
	jnz	l5
l4:
	movq	$0, %rcx
	movq	%rcx, -112(%rbp)
	movq	$0, %rcx
	movq	%rcx, -120(%rbp)
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
	movq	-120(%rbp), %rdx
	movq	%rdx, %rsi
	movq	-112(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_Ilt_bii
	movq	%rax, %rcx
	movq	%rcx, -128(%rbp)
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
	movq	-128(%rbp), %rcx
	movq	-128(%rbp), %rdx
	testq	%rdx, %rcx
	movq	%rcx, -128(%rbp)
	jnz	l8
l7:
	movq	$0, %rcx
	movq	%rcx, -136(%rbp)
	movq	$0, %rcx
	movq	%rcx, -144(%rbp)
	movq	-144(%rbp), %rcx
	subq	$1, %rcx
	movq	%rcx, -144(%rbp)
	movq	-144(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -152(%rbp)
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
	movq	-152(%rbp), %rdx
	movq	%rdx, %rsi
	movq	-136(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_Ilt_bii
	movq	%rax, %rcx
	movq	%rcx, -160(%rbp)
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
	movq	-160(%rbp), %rcx
	movq	-160(%rbp), %rdx
	testq	%rdx, %rcx
	movq	%rcx, -160(%rbp)
	jnz	l11
l10:
	movq	$0, %rcx
	movq	%rcx, -168(%rbp)
	movq	$0, %rcx
	movq	%rcx, -176(%rbp)
	movq	-176(%rbp), %rcx
	subq	$1, %rcx
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
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	-184(%rbp), %rdx
	movq	%rdx, %rsi
	movq	-168(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_Ilt_bii
	movq	%rax, %rcx
	movq	%rcx, -192(%rbp)
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
	movq	-192(%rbp), %rcx
	movq	-192(%rbp), %rdx
	testq	%rdx, %rcx
	movq	%rcx, -192(%rbp)
	jnz	l14
l13:
	movq	$0, %rcx
	movq	%rcx, -200(%rbp)
	movq	$0, %rcx
	movq	%rcx, -208(%rbp)
	movq	-208(%rbp), %rcx
	subq	$1, %rcx
	movq	%rcx, -208(%rbp)
	movq	-208(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -216(%rbp)
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
	movq	-216(%rbp), %rdx
	movq	%rdx, %rsi
	movq	-200(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_Ilt_bii
	movq	%rax, %rcx
	movq	%rcx, -224(%rbp)
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
	movq	-224(%rbp), %rcx
	movq	-224(%rbp), %rdx
	testq	%rdx, %rcx
	movq	%rcx, -224(%rbp)
	jnz	l17
l16:
	movq	$0, %rcx
	movq	%rcx, -232(%rbp)
	movq	$1, %rcx
	movq	%rcx, -240(%rbp)
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
	movq	-240(%rbp), %rdx
	movq	%rdx, %rsi
	movq	-232(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_Ilt_bii
	movq	%rax, %rcx
	movq	%rcx, -248(%rbp)
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
	movq	-248(%rbp), %rcx
	movq	-248(%rbp), %rdx
	testq	%rdx, %rcx
	movq	%rcx, -248(%rbp)
	jnz	l20
l19:
	movq	$0, %rcx
	movq	%rcx, -256(%rbp)
	movq	$1, %rcx
	movq	%rcx, -264(%rbp)
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
	movq	-264(%rbp), %rdx
	movq	%rdx, %rsi
	movq	-256(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_Ilt_bii
	movq	%rax, %rcx
	movq	%rcx, -272(%rbp)
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
	movq	-272(%rbp), %rcx
	movq	-272(%rbp), %rdx
	testq	%rdx, %rcx
	movq	%rcx, -272(%rbp)
	jnz	l23
l22:
	movq	$0, %rcx
	movq	%rcx, -280(%rbp)
	movq	$1, %rcx
	movq	%rcx, -288(%rbp)
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
	movq	-288(%rbp), %rdx
	movq	%rdx, %rsi
	movq	-280(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_Ilt_bii
	movq	%rax, %rcx
	movq	%rcx, -296(%rbp)
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
	movq	-296(%rbp), %rcx
	movq	-296(%rbp), %rdx
	testq	%rdx, %rcx
	movq	%rcx, -296(%rbp)
	jnz	l26
l25:
	movq	$64, %rcx
	movq	%rcx, -304(%rbp)
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
	movq	-304(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -312(%rbp)
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
	movq	-312(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -320(%rbp)
	movq	-320(%rbp), %rcx
	movq	$7, (%rcx)
	movq	-320(%rbp), %rcx
	movq	$112, 8(%rcx)
	movq	-320(%rbp), %rcx
	movq	$114, 16(%rcx)
	movq	-320(%rbp), %rcx
	movq	$105, 24(%rcx)
	movq	-320(%rbp), %rcx
	movq	$110, 32(%rcx)
	movq	-320(%rbp), %rcx
	movq	$116, 40(%rcx)
	movq	-320(%rbp), %rcx
	movq	$101, 48(%rcx)
	movq	-320(%rbp), %rcx
	movq	$100, 56(%rcx)
	movq	-320(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -328(%rbp)
	movq	-328(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -328(%rbp)
	movq	-328(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -320(%rbp)
	movq	-320(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -336(%rbp)
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
	movq	-336(%rbp), %rdx
	movq	%rdx, %rdi
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
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l2:
	movq	$0, %rcx
	movq	%rcx, -344(%rbp)
	movq	$0, %rcx
	movq	%rcx, -352(%rbp)
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
	movq	-352(%rbp), %rdx
	movq	%rdx, %rsi
	movq	-344(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_Ilt_bii
	movq	%rax, %rcx
	movq	%rcx, -360(%rbp)
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
	movq	$1, %rcx
	movq	%rcx, -368(%rbp)
	movq	-368(%rbp), %rcx
	movq	-360(%rbp), %rdx
	xorq	%rdx, %rcx
	movq	%rcx, -368(%rbp)
	movq	-368(%rbp), %rcx
	movq	-368(%rbp), %rdx
	testq	%rdx, %rcx
	movq	%rcx, -368(%rbp)
	jnz	l1
	movq	$40, %rcx
	movq	%rcx, -376(%rbp)
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
	movq	-376(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -384(%rbp)
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
	movq	-384(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -392(%rbp)
	movq	-392(%rbp), %rcx
	movq	$4, (%rcx)
	movq	-392(%rbp), %rcx
	movq	$116, 8(%rcx)
	movq	-392(%rbp), %rcx
	movq	$114, 16(%rcx)
	movq	-392(%rbp), %rcx
	movq	$117, 24(%rcx)
	movq	-392(%rbp), %rcx
	movq	$101, 32(%rcx)
	movq	-392(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -400(%rbp)
	movq	-400(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -400(%rbp)
	movq	-400(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -392(%rbp)
	movq	-392(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -408(%rbp)
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
	movq	-408(%rbp), %rdx
	movq	%rdx, %rdi
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
	jmp	l1
l5:
	movq	$0, %rcx
	movq	%rcx, -416(%rbp)
	movq	$0, %rcx
	movq	%rcx, -424(%rbp)
	movq	-424(%rbp), %rcx
	subq	$1, %rcx
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
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	-432(%rbp), %rdx
	movq	%rdx, %rsi
	movq	-416(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_Ilt_bii
	movq	%rax, %rcx
	movq	%rcx, -440(%rbp)
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
	movq	$1, %rcx
	movq	%rcx, -448(%rbp)
	movq	-448(%rbp), %rcx
	movq	-440(%rbp), %rdx
	xorq	%rdx, %rcx
	movq	%rcx, -448(%rbp)
	movq	-448(%rbp), %rcx
	movq	-448(%rbp), %rdx
	testq	%rdx, %rcx
	movq	%rcx, -448(%rbp)
	jnz	l4
	movq	$40, %rcx
	movq	%rcx, -456(%rbp)
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
	movq	-456(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -464(%rbp)
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
	movq	-464(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -472(%rbp)
	movq	-472(%rbp), %rcx
	movq	$4, (%rcx)
	movq	-472(%rbp), %rcx
	movq	$116, 8(%rcx)
	movq	-472(%rbp), %rcx
	movq	$114, 16(%rcx)
	movq	-472(%rbp), %rcx
	movq	$117, 24(%rcx)
	movq	-472(%rbp), %rcx
	movq	$101, 32(%rcx)
	movq	-472(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -480(%rbp)
	movq	-480(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -480(%rbp)
	movq	-480(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -472(%rbp)
	movq	-472(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -488(%rbp)
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
	movq	-488(%rbp), %rdx
	movq	%rdx, %rdi
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
	jmp	l4
l8:
	movq	$0, %rcx
	movq	%rcx, -496(%rbp)
	movq	$1, %rcx
	movq	%rcx, -504(%rbp)
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
	movq	-504(%rbp), %rdx
	movq	%rdx, %rsi
	movq	-496(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_Ilt_bii
	movq	%rax, %rcx
	movq	%rcx, -512(%rbp)
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
	movq	$1, %rcx
	movq	%rcx, -520(%rbp)
	movq	-520(%rbp), %rcx
	movq	-512(%rbp), %rdx
	xorq	%rdx, %rcx
	movq	%rcx, -520(%rbp)
	movq	-520(%rbp), %rcx
	movq	-520(%rbp), %rdx
	testq	%rdx, %rcx
	movq	%rcx, -520(%rbp)
	jnz	l7
	movq	$40, %rcx
	movq	%rcx, -528(%rbp)
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
	movq	-528(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -536(%rbp)
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
	movq	-536(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -544(%rbp)
	movq	-544(%rbp), %rcx
	movq	$4, (%rcx)
	movq	-544(%rbp), %rcx
	movq	$116, 8(%rcx)
	movq	-544(%rbp), %rcx
	movq	$114, 16(%rcx)
	movq	-544(%rbp), %rcx
	movq	$117, 24(%rcx)
	movq	-544(%rbp), %rcx
	movq	$101, 32(%rcx)
	movq	-544(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -552(%rbp)
	movq	-552(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -552(%rbp)
	movq	-552(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -544(%rbp)
	movq	-544(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -560(%rbp)
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
	movq	-560(%rbp), %rdx
	movq	%rdx, %rdi
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
	jmp	l7
l11:
	movq	$0, %rcx
	movq	%rcx, -568(%rbp)
	movq	$0, %rcx
	movq	%rcx, -576(%rbp)
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
	movq	-576(%rbp), %rdx
	movq	%rdx, %rsi
	movq	-568(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_Ilt_bii
	movq	%rax, %rcx
	movq	%rcx, -584(%rbp)
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
	movq	$1, %rcx
	movq	%rcx, -592(%rbp)
	movq	-592(%rbp), %rcx
	movq	-584(%rbp), %rdx
	xorq	%rdx, %rcx
	movq	%rcx, -592(%rbp)
	movq	-592(%rbp), %rcx
	movq	-592(%rbp), %rdx
	testq	%rdx, %rcx
	movq	%rcx, -592(%rbp)
	jnz	l10
	movq	$40, %rcx
	movq	%rcx, -600(%rbp)
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
	movq	-600(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -608(%rbp)
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
	movq	-608(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -616(%rbp)
	movq	-616(%rbp), %rcx
	movq	$4, (%rcx)
	movq	-616(%rbp), %rcx
	movq	$116, 8(%rcx)
	movq	-616(%rbp), %rcx
	movq	$114, 16(%rcx)
	movq	-616(%rbp), %rcx
	movq	$117, 24(%rcx)
	movq	-616(%rbp), %rcx
	movq	$101, 32(%rcx)
	movq	-616(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -624(%rbp)
	movq	-624(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -624(%rbp)
	movq	-624(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -616(%rbp)
	movq	-616(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -632(%rbp)
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
	movq	-632(%rbp), %rdx
	movq	%rdx, %rdi
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
	jmp	l10
l14:
	movq	$0, %rcx
	movq	%rcx, -640(%rbp)
	movq	$0, %rcx
	movq	%rcx, -648(%rbp)
	movq	-648(%rbp), %rcx
	subq	$1, %rcx
	movq	%rcx, -648(%rbp)
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
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	-656(%rbp), %rdx
	movq	%rdx, %rsi
	movq	-640(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_Ilt_bii
	movq	%rax, %rcx
	movq	%rcx, -664(%rbp)
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
	movq	$1, %rcx
	movq	%rcx, -672(%rbp)
	movq	-672(%rbp), %rcx
	movq	-664(%rbp), %rdx
	xorq	%rdx, %rcx
	movq	%rcx, -672(%rbp)
	movq	-672(%rbp), %rcx
	movq	-672(%rbp), %rdx
	testq	%rdx, %rcx
	movq	%rcx, -672(%rbp)
	jnz	l13
	movq	$40, %rcx
	movq	%rcx, -680(%rbp)
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
	movq	-680(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -688(%rbp)
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
	movq	-688(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -696(%rbp)
	movq	-696(%rbp), %rcx
	movq	$4, (%rcx)
	movq	-696(%rbp), %rcx
	movq	$116, 8(%rcx)
	movq	-696(%rbp), %rcx
	movq	$114, 16(%rcx)
	movq	-696(%rbp), %rcx
	movq	$117, 24(%rcx)
	movq	-696(%rbp), %rcx
	movq	$101, 32(%rcx)
	movq	-696(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -704(%rbp)
	movq	-704(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -704(%rbp)
	movq	-704(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -696(%rbp)
	movq	-696(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -712(%rbp)
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
	movq	-712(%rbp), %rdx
	movq	%rdx, %rdi
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
	jmp	l13
l17:
	movq	$0, %rcx
	movq	%rcx, -720(%rbp)
	movq	$1, %rcx
	movq	%rcx, -728(%rbp)
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
	movq	-728(%rbp), %rdx
	movq	%rdx, %rsi
	movq	-720(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_Ilt_bii
	movq	%rax, %rcx
	movq	%rcx, -736(%rbp)
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
	movq	$1, %rcx
	movq	%rcx, -744(%rbp)
	movq	-744(%rbp), %rcx
	movq	-736(%rbp), %rdx
	xorq	%rdx, %rcx
	movq	%rcx, -744(%rbp)
	movq	-744(%rbp), %rcx
	movq	-744(%rbp), %rdx
	testq	%rdx, %rcx
	movq	%rcx, -744(%rbp)
	jnz	l16
	movq	$40, %rcx
	movq	%rcx, -752(%rbp)
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
	movq	-752(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -760(%rbp)
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
	movq	-760(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -768(%rbp)
	movq	-768(%rbp), %rcx
	movq	$4, (%rcx)
	movq	-768(%rbp), %rcx
	movq	$116, 8(%rcx)
	movq	-768(%rbp), %rcx
	movq	$114, 16(%rcx)
	movq	-768(%rbp), %rcx
	movq	$117, 24(%rcx)
	movq	-768(%rbp), %rcx
	movq	$101, 32(%rcx)
	movq	-768(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -776(%rbp)
	movq	-776(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -776(%rbp)
	movq	-776(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -768(%rbp)
	movq	-768(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -784(%rbp)
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
	movq	-784(%rbp), %rdx
	movq	%rdx, %rdi
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
	jmp	l16
l20:
	movq	$0, %rcx
	movq	%rcx, -792(%rbp)
	movq	$0, %rcx
	movq	%rcx, -800(%rbp)
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
	movq	-800(%rbp), %rdx
	movq	%rdx, %rsi
	movq	-792(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_Ilt_bii
	movq	%rax, %rcx
	movq	%rcx, -808(%rbp)
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
	movq	$1, %rcx
	movq	%rcx, -816(%rbp)
	movq	-816(%rbp), %rcx
	movq	-808(%rbp), %rdx
	xorq	%rdx, %rcx
	movq	%rcx, -816(%rbp)
	movq	-816(%rbp), %rcx
	movq	-816(%rbp), %rdx
	testq	%rdx, %rcx
	movq	%rcx, -816(%rbp)
	jnz	l19
	movq	$40, %rcx
	movq	%rcx, -824(%rbp)
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
	movq	-824(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -832(%rbp)
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
	movq	-832(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -840(%rbp)
	movq	-840(%rbp), %rcx
	movq	$4, (%rcx)
	movq	-840(%rbp), %rcx
	movq	$116, 8(%rcx)
	movq	-840(%rbp), %rcx
	movq	$114, 16(%rcx)
	movq	-840(%rbp), %rcx
	movq	$117, 24(%rcx)
	movq	-840(%rbp), %rcx
	movq	$101, 32(%rcx)
	movq	-840(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -848(%rbp)
	movq	-848(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -848(%rbp)
	movq	-848(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -840(%rbp)
	movq	-840(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -856(%rbp)
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
	movq	-856(%rbp), %rdx
	movq	%rdx, %rdi
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
	jmp	l19
l23:
	movq	$0, %rcx
	movq	%rcx, -864(%rbp)
	movq	$0, %rcx
	movq	%rcx, -872(%rbp)
	movq	-872(%rbp), %rcx
	subq	$1, %rcx
	movq	%rcx, -872(%rbp)
	movq	-872(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -880(%rbp)
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
	movq	-880(%rbp), %rdx
	movq	%rdx, %rsi
	movq	-864(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_Ilt_bii
	movq	%rax, %rcx
	movq	%rcx, -888(%rbp)
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
	movq	$1, %rcx
	movq	%rcx, -896(%rbp)
	movq	-896(%rbp), %rcx
	movq	-888(%rbp), %rdx
	xorq	%rdx, %rcx
	movq	%rcx, -896(%rbp)
	movq	-896(%rbp), %rcx
	movq	-896(%rbp), %rdx
	testq	%rdx, %rcx
	movq	%rcx, -896(%rbp)
	jnz	l22
	movq	$40, %rcx
	movq	%rcx, -904(%rbp)
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
	movq	-904(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -912(%rbp)
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
	movq	-912(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -920(%rbp)
	movq	-920(%rbp), %rcx
	movq	$4, (%rcx)
	movq	-920(%rbp), %rcx
	movq	$116, 8(%rcx)
	movq	-920(%rbp), %rcx
	movq	$114, 16(%rcx)
	movq	-920(%rbp), %rcx
	movq	$117, 24(%rcx)
	movq	-920(%rbp), %rcx
	movq	$101, 32(%rcx)
	movq	-920(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -928(%rbp)
	movq	-928(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -928(%rbp)
	movq	-928(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -920(%rbp)
	movq	-920(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -936(%rbp)
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
	movq	-936(%rbp), %rdx
	movq	%rdx, %rdi
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
	jmp	l22
l26:
	movq	$0, %rcx
	movq	%rcx, -944(%rbp)
	movq	$1, %rcx
	movq	%rcx, -952(%rbp)
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
	movq	-952(%rbp), %rdx
	movq	%rdx, %rsi
	movq	-944(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_Ilt_bii
	movq	%rax, %rcx
	movq	%rcx, -960(%rbp)
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
	movq	$1, %rcx
	movq	%rcx, -968(%rbp)
	movq	-968(%rbp), %rcx
	movq	-960(%rbp), %rdx
	xorq	%rdx, %rcx
	movq	%rcx, -968(%rbp)
	movq	-968(%rbp), %rcx
	movq	-968(%rbp), %rdx
	testq	%rdx, %rcx
	movq	%rcx, -968(%rbp)
	jnz	l25
	movq	$40, %rcx
	movq	%rcx, -976(%rbp)
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
	movq	-976(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -984(%rbp)
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
	movq	-984(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -992(%rbp)
	movq	-992(%rbp), %rcx
	movq	$4, (%rcx)
	movq	-992(%rbp), %rcx
	movq	$116, 8(%rcx)
	movq	-992(%rbp), %rcx
	movq	$114, 16(%rcx)
	movq	-992(%rbp), %rcx
	movq	$117, 24(%rcx)
	movq	-992(%rbp), %rcx
	movq	$101, 32(%rcx)
	movq	-992(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1000(%rbp)
	movq	-1000(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -1000(%rbp)
	movq	-1000(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -992(%rbp)
	movq	-992(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -1008(%rbp)
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
	movq	-1008(%rbp), %rdx
	movq	%rdx, %rdi
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
	jmp	l25

	.globl	_Ilt_bii
	.align	4
_Ilt_bii:
	enter	$224, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %rcx
	movq	%rcx, -56(%rbp)
	movq	%rsi, %rcx
	movq	%rcx, -64(%rbp)
	movq	$112, %rcx
	movq	%rcx, -72(%rbp)
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
	movq	-72(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -80(%rbp)
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
	movq	-80(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rcx
	movq	$13, (%rcx)
	movq	-88(%rbp), %rcx
	movq	$108, 8(%rcx)
	movq	-88(%rbp), %rcx
	movq	$116, 16(%rcx)
	movq	-88(%rbp), %rcx
	movq	$40, 24(%rcx)
	movq	-88(%rbp), %rcx
	movq	$41, 32(%rcx)
	movq	-88(%rbp), %rcx
	movq	$32, 40(%rcx)
	movq	-88(%rbp), %rcx
	movq	$99, 48(%rcx)
	movq	-88(%rbp), %rcx
	movq	$97, 56(%rcx)
	movq	-88(%rbp), %rcx
	movq	$108, 64(%rcx)
	movq	-88(%rbp), %rcx
	movq	$108, 72(%rcx)
	movq	-88(%rbp), %rcx
	movq	$101, 80(%rcx)
	movq	-88(%rbp), %rcx
	movq	$100, 88(%rcx)
	movq	-88(%rbp), %rcx
	movq	$58, 96(%rcx)
	movq	-88(%rbp), %rcx
	movq	$32, 104(%rcx)
	movq	-88(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -96(%rbp)
	movq	-96(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -96(%rbp)
	movq	-96(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -104(%rbp)
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
	movq	-104(%rbp), %rdx
	movq	%rdx, %rdi
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
	movq	-56(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -112(%rbp)
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
	movq	-112(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_IunparseInt_aii
	movq	%rax, %rcx
	movq	%rcx, -120(%rbp)
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
	movq	-120(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -128(%rbp)
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
	movq	-128(%rbp), %rdx
	movq	%rdx, %rdi
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
	movq	$32, %rcx
	movq	%rcx, -136(%rbp)
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
	movq	-136(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -144(%rbp)
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
	movq	-144(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -152(%rbp)
	movq	-152(%rbp), %rcx
	movq	$3, (%rcx)
	movq	-152(%rbp), %rcx
	movq	$32, 8(%rcx)
	movq	-152(%rbp), %rcx
	movq	$60, 16(%rcx)
	movq	-152(%rbp), %rcx
	movq	$32, 24(%rcx)
	movq	-152(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -160(%rbp)
	movq	-160(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -160(%rbp)
	movq	-160(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -152(%rbp)
	movq	-152(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -168(%rbp)
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
	movq	-168(%rbp), %rdx
	movq	%rdx, %rdi
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
	movq	-64(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -176(%rbp)
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
	movq	-176(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_IunparseInt_aii
	movq	%rax, %rcx
	movq	%rcx, -184(%rbp)
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
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	-192(%rbp), %rdx
	movq	%rdx, %rdi
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
	movq	-56(%rbp), %rcx
	movq	-64(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -56(%rbp)
	movq	$1, %rcx
	movq	%rcx, -200(%rbp)
	movq	$0, %rcx
	movq	%rcx, -208(%rbp)
	movq	-200(%rbp), %rdx
	cmovl	%rdx, %rcx
	movq	%rcx, -216(%rbp)
	movq	-216(%rbp), %rcx
	movq	-208(%rbp), %rdx
	cmovge	%rdx, %rcx
	movq	%rcx, -216(%rbp)
	movq	-216(%rbp), %rdx
	movq	%rdx, %rax
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
