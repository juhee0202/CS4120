	.text
	.globl	_Iusage_p
	.align	4
_Iusage_p:
	enter	$320, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	$240, %r14
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
	movq	$29, (%r12)
	movq	$8, %r14
	movq	%r14, -80(%rbp)
	movq	-80(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	-80(%rbp), %r12
	movq	$80, (%r12)
	movq	$16, %r14
	movq	%r14, -88(%rbp)
	movq	-88(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -88(%rbp)
	movq	-88(%rbp), %r12
	movq	$108, (%r12)
	movq	$24, %r14
	movq	%r14, -96(%rbp)
	movq	-96(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -96(%rbp)
	movq	-96(%rbp), %r12
	movq	$101, (%r12)
	movq	$32, %r14
	movq	%r14, -104(%rbp)
	movq	-104(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -104(%rbp)
	movq	-104(%rbp), %r12
	movq	$97, (%r12)
	movq	$40, %r14
	movq	%r14, -112(%rbp)
	movq	-112(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -112(%rbp)
	movq	-112(%rbp), %r12
	movq	$115, (%r12)
	movq	$48, %r14
	movq	%r14, -120(%rbp)
	movq	-120(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -120(%rbp)
	movq	-120(%rbp), %r12
	movq	$101, (%r12)
	movq	$56, %r14
	movq	%r14, -128(%rbp)
	movq	-128(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -128(%rbp)
	movq	-128(%rbp), %r12
	movq	$32, (%r12)
	movq	$64, %r14
	movq	%r14, -136(%rbp)
	movq	-136(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	-136(%rbp), %r12
	movq	$115, (%r12)
	movq	$72, %r14
	movq	%r14, -144(%rbp)
	movq	-144(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	-144(%rbp), %r12
	movq	$112, (%r12)
	movq	$80, %r14
	movq	%r14, -152(%rbp)
	movq	-152(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -152(%rbp)
	movq	-152(%rbp), %r12
	movq	$101, (%r12)
	movq	$88, %r14
	movq	%r14, -160(%rbp)
	movq	-160(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	-160(%rbp), %r12
	movq	$99, (%r12)
	movq	$96, %r14
	movq	%r14, -168(%rbp)
	movq	-168(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -168(%rbp)
	movq	-168(%rbp), %r12
	movq	$105, (%r12)
	movq	$104, %r14
	movq	%r14, -176(%rbp)
	movq	-176(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -176(%rbp)
	movq	-176(%rbp), %r12
	movq	$102, (%r12)
	movq	$112, %r14
	movq	%r14, -184(%rbp)
	movq	-184(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -184(%rbp)
	movq	-184(%rbp), %r12
	movq	$121, (%r12)
	movq	$120, %r14
	movq	%r14, -192(%rbp)
	movq	-192(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -192(%rbp)
	movq	-192(%rbp), %r12
	movq	$32, (%r12)
	movq	$128, %r14
	movq	%r14, -200(%rbp)
	movq	-200(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -200(%rbp)
	movq	-200(%rbp), %r12
	movq	$116, (%r12)
	movq	$136, %r14
	movq	%r14, -208(%rbp)
	movq	-208(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -208(%rbp)
	movq	-208(%rbp), %r12
	movq	$104, (%r12)
	movq	$144, %r14
	movq	%r14, -216(%rbp)
	movq	-216(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -216(%rbp)
	movq	-216(%rbp), %r12
	movq	$101, (%r12)
	movq	$152, %r14
	movq	%r14, -224(%rbp)
	movq	-224(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -224(%rbp)
	movq	-224(%rbp), %r12
	movq	$32, (%r12)
	movq	$160, %r14
	movq	%r14, -232(%rbp)
	movq	-232(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -232(%rbp)
	movq	-232(%rbp), %r12
	movq	$105, (%r12)
	movq	$168, %r14
	movq	%r14, -240(%rbp)
	movq	-240(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -240(%rbp)
	movq	-240(%rbp), %r12
	movq	$110, (%r12)
	movq	$176, %r14
	movq	%r14, -248(%rbp)
	movq	-248(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -248(%rbp)
	movq	-248(%rbp), %r12
	movq	$112, (%r12)
	movq	$184, %r14
	movq	%r14, -256(%rbp)
	movq	-256(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -256(%rbp)
	movq	-256(%rbp), %r12
	movq	$117, (%r12)
	movq	$192, %r14
	movq	%r14, -264(%rbp)
	movq	-264(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -264(%rbp)
	movq	-264(%rbp), %r12
	movq	$116, (%r12)
	movq	$200, %r14
	movq	%r14, -272(%rbp)
	movq	-272(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -272(%rbp)
	movq	-272(%rbp), %r12
	movq	$32, (%r12)
	movq	$208, %r14
	movq	%r14, -280(%rbp)
	movq	-280(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -280(%rbp)
	movq	-280(%rbp), %r12
	movq	$115, (%r12)
	movq	$216, %r14
	movq	%r14, -288(%rbp)
	movq	-288(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -288(%rbp)
	movq	-288(%rbp), %r12
	movq	$105, (%r12)
	movq	$224, %r14
	movq	%r14, -296(%rbp)
	movq	-296(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -296(%rbp)
	movq	-296(%rbp), %r12
	movq	$122, (%r12)
	movq	$232, %r14
	movq	%r14, -304(%rbp)
	movq	-304(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -304(%rbp)
	movq	-304(%rbp), %r12
	movq	$101, (%r12)
	movq	-72(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -312(%rbp)
	movq	-312(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -312(%rbp)
	movq	-312(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -72(%rbp)
	movq	-72(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -320(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-320(%rbp), %r14
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

	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$336, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
	movq	$11, %r14
	movq	%r14, -64(%rbp)
	movq	$2, %r14
	movq	%r14, -72(%rbp)
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-80(%rbp), %r14
	movq	%r14, %rsi
	movq	-72(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_IAck_iii
	movq	%rax, %r12
	movq	%r12, -88(%rbp)
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
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -96(%rbp)
	movq	$56, %r14
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
	movq	-104(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
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
	movq	-120(%rbp), %r12
	movq	$6, (%r12)
	movq	$8, %r14
	movq	%r14, -128(%rbp)
	movq	-128(%rbp), %r12
	movq	-120(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -128(%rbp)
	movq	-128(%rbp), %r12
	movq	$65, (%r12)
	movq	$16, %r14
	movq	%r14, -136(%rbp)
	movq	-136(%rbp), %r12
	movq	-120(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	-136(%rbp), %r12
	movq	$99, (%r12)
	movq	$24, %r14
	movq	%r14, -144(%rbp)
	movq	-144(%rbp), %r12
	movq	-120(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	-144(%rbp), %r12
	movq	$107, (%r12)
	movq	$32, %r14
	movq	%r14, -152(%rbp)
	movq	-152(%rbp), %r12
	movq	-120(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -152(%rbp)
	movq	-152(%rbp), %r12
	movq	$40, (%r12)
	movq	$40, %r14
	movq	%r14, -160(%rbp)
	movq	-160(%rbp), %r12
	movq	-120(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	-160(%rbp), %r12
	movq	$50, (%r12)
	movq	$48, %r14
	movq	%r14, -168(%rbp)
	movq	-168(%rbp), %r12
	movq	-120(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -168(%rbp)
	movq	-168(%rbp), %r12
	movq	$44, (%r12)
	movq	-120(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -176(%rbp)
	movq	-176(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -176(%rbp)
	movq	-176(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -120(%rbp)
	movq	-120(%rbp), %r14
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
	movq	-184(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_Iprint_pai
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
	movq	%r12, -192(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-192(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %r12
	movq	%r12, -200(%rbp)
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
	movq	-200(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -208(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-208(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_Iprint_pai
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
	movq	$32, %r14
	movq	%r14, -216(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-216(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -224(%rbp)
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
	movq	-224(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -232(%rbp)
	movq	-232(%rbp), %r12
	movq	$3, (%r12)
	movq	$8, %r14
	movq	%r14, -240(%rbp)
	movq	-240(%rbp), %r12
	movq	-232(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -240(%rbp)
	movq	-240(%rbp), %r12
	movq	$41, (%r12)
	movq	$16, %r14
	movq	%r14, -248(%rbp)
	movq	-248(%rbp), %r12
	movq	-232(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -248(%rbp)
	movq	-248(%rbp), %r12
	movq	$58, (%r12)
	movq	$24, %r14
	movq	%r14, -256(%rbp)
	movq	-256(%rbp), %r12
	movq	-232(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -256(%rbp)
	movq	-256(%rbp), %r12
	movq	$32, (%r12)
	movq	-232(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -264(%rbp)
	movq	-264(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -264(%rbp)
	movq	-264(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -232(%rbp)
	movq	-232(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -272(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-272(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_Iprint_pai
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
	movq	-96(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -280(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-280(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %r12
	movq	%r12, -288(%rbp)
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
	movq	-288(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -296(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-296(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_Iprint_pai
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
	movq	$8, %r14
	movq	%r14, -304(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-304(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -312(%rbp)
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
	movq	-312(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -320(%rbp)
	movq	-320(%rbp), %r12
	movq	$0, (%r12)
	movq	-320(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -328(%rbp)
	movq	-328(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -328(%rbp)
	movq	-328(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -320(%rbp)
	movq	-320(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -336(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-336(%rbp), %r14
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

	.globl	_IAck_iii
	.align	4
_IAck_iii:
	enter	$176, $0
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
	cmpq	$0, %r14
	movq	%r14, -56(%rbp)
	je	l0
	movq	-64(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -64(%rbp)
	je	l3
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -72(%rbp)
	movq	-72(%rbp), %r14
	addq	$-1, %r14
	movq	%r14, -72(%rbp)
	movq	-72(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -88(%rbp)
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -96(%rbp)
	movq	-96(%rbp), %r14
	addq	$-1, %r14
	movq	%r14, -96(%rbp)
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
	movq	%r14, %rsi
	movq	-88(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_IAck_iii
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
	movq	%r14, %rsi
	movq	-80(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_IAck_iii
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
	movq	%r14, %rax
label4:
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l0:
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	-136(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -136(%rbp)
	movq	-136(%rbp), %r14
	movq	%r14, %rax
	jmp	label4
l2:
	jmp	label4
l3:
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	-144(%rbp), %r14
	addq	$-1, %r14
	movq	%r14, -144(%rbp)
	movq	-144(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -152(%rbp)
	movq	$1, %r14
	movq	%r14, -160(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-160(%rbp), %r14
	movq	%r14, %rsi
	movq	-152(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_IAck_iii
	movq	%rax, %r12
	movq	%r12, -168(%rbp)
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
	movq	-168(%rbp), %r14
	movq	%r14, %rax
	jmp	label4
	jmp	l2
