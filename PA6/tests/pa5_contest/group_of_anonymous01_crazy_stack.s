	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$1328, $0
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
	subq	$296, %rsp
	movq	%rsp, %rdi
	addq	$288, %rdi
	callq	_Ibn_t39iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii
	movq	%rax, %r12
	movq	%r12, -64(%rbp)
	movq	%rdx, %r12
	movq	%r12, -72(%rbp)
	movq	(%rdi), %r14
	movq	%r14, -80(%rbp)
	movq	8(%rdi), %r14
	movq	%r14, -88(%rbp)
	movq	16(%rdi), %r14
	movq	%r14, -96(%rbp)
	movq	24(%rdi), %r14
	movq	%r14, -104(%rbp)
	movq	32(%rdi), %r14
	movq	%r14, -112(%rbp)
	movq	40(%rdi), %r14
	movq	%r14, -120(%rbp)
	movq	48(%rdi), %r14
	movq	%r14, -128(%rbp)
	movq	56(%rdi), %r14
	movq	%r14, -136(%rbp)
	movq	64(%rdi), %r14
	movq	%r14, -144(%rbp)
	movq	72(%rdi), %r14
	movq	%r14, -152(%rbp)
	movq	80(%rdi), %r14
	movq	%r14, -160(%rbp)
	movq	88(%rdi), %r14
	movq	%r14, -168(%rbp)
	movq	96(%rdi), %r14
	movq	%r14, -176(%rbp)
	movq	104(%rdi), %r14
	movq	%r14, -184(%rbp)
	movq	112(%rdi), %r14
	movq	%r14, -192(%rbp)
	movq	120(%rdi), %r14
	movq	%r14, -200(%rbp)
	movq	128(%rdi), %r14
	movq	%r14, -208(%rbp)
	movq	136(%rdi), %r14
	movq	%r14, -216(%rbp)
	movq	144(%rdi), %r14
	movq	%r14, -224(%rbp)
	movq	152(%rdi), %r14
	movq	%r14, -232(%rbp)
	movq	160(%rdi), %r14
	movq	%r14, -240(%rbp)
	movq	168(%rdi), %r14
	movq	%r14, -248(%rbp)
	movq	176(%rdi), %r14
	movq	%r14, -256(%rbp)
	movq	184(%rdi), %r14
	movq	%r14, -264(%rbp)
	movq	192(%rdi), %r14
	movq	%r14, -272(%rbp)
	movq	200(%rdi), %r14
	movq	%r14, -280(%rbp)
	movq	208(%rdi), %r14
	movq	%r14, -288(%rbp)
	movq	216(%rdi), %r14
	movq	%r14, -296(%rbp)
	movq	224(%rdi), %r14
	movq	%r14, -304(%rbp)
	movq	232(%rdi), %r14
	movq	%r14, -312(%rbp)
	movq	240(%rdi), %r14
	movq	%r14, -320(%rbp)
	movq	248(%rdi), %r14
	movq	%r14, -328(%rbp)
	movq	256(%rdi), %r14
	movq	%r14, -336(%rbp)
	movq	264(%rdi), %r14
	movq	%r14, -344(%rbp)
	movq	272(%rdi), %r14
	movq	%r14, -352(%rbp)
	movq	280(%rdi), %r14
	movq	%r14, -360(%rbp)
	movq	288(%rdi), %r14
	movq	%r14, -368(%rbp)
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
	movq	%r14, -376(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-376(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -384(%rbp)
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
	movq	-384(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -392(%rbp)
	movq	-392(%rbp), %r12
	movq	$1, (%r12)
	movq	-392(%rbp), %r12
	movq	$0, 8(%r12)
	movq	-392(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -400(%rbp)
	movq	-400(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -400(%rbp)
	movq	-400(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -392(%rbp)
	movq	-392(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -408(%rbp)
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -416(%rbp)
	movq	-416(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -416(%rbp)
	movq	-416(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -424(%rbp)
	movq	-424(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -424(%rbp)
	movq	-424(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -432(%rbp)
	movq	-432(%rbp), %r12
	movq	-88(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -432(%rbp)
	movq	-432(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -440(%rbp)
	movq	-440(%rbp), %r12
	movq	-96(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -440(%rbp)
	movq	-440(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -448(%rbp)
	movq	-448(%rbp), %r12
	movq	-104(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -448(%rbp)
	movq	-448(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -456(%rbp)
	movq	-456(%rbp), %r12
	movq	-112(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -456(%rbp)
	movq	-456(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -464(%rbp)
	movq	-464(%rbp), %r12
	movq	-120(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -464(%rbp)
	movq	-464(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -472(%rbp)
	movq	-472(%rbp), %r12
	movq	-128(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -472(%rbp)
	movq	-472(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -480(%rbp)
	movq	-480(%rbp), %r12
	movq	-136(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -480(%rbp)
	movq	-480(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -488(%rbp)
	movq	-488(%rbp), %r12
	movq	-144(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -488(%rbp)
	movq	-488(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -496(%rbp)
	movq	-496(%rbp), %r12
	movq	-152(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -496(%rbp)
	movq	-496(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -504(%rbp)
	movq	-504(%rbp), %r12
	movq	-160(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -504(%rbp)
	movq	-504(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -512(%rbp)
	movq	-512(%rbp), %r12
	movq	-168(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -512(%rbp)
	movq	-512(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -520(%rbp)
	movq	-520(%rbp), %r12
	movq	-176(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -520(%rbp)
	movq	-520(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -528(%rbp)
	movq	-528(%rbp), %r12
	movq	-184(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -528(%rbp)
	movq	-528(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -536(%rbp)
	movq	-536(%rbp), %r12
	movq	-192(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -536(%rbp)
	movq	-536(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -544(%rbp)
	movq	-544(%rbp), %r12
	movq	-200(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -544(%rbp)
	movq	-544(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -552(%rbp)
	movq	-552(%rbp), %r12
	movq	-208(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -552(%rbp)
	movq	-552(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -560(%rbp)
	movq	-560(%rbp), %r12
	movq	-216(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -560(%rbp)
	movq	-560(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -568(%rbp)
	movq	-568(%rbp), %r12
	movq	-224(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -568(%rbp)
	movq	-568(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -576(%rbp)
	movq	-576(%rbp), %r12
	movq	-232(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -576(%rbp)
	movq	-576(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -584(%rbp)
	movq	-584(%rbp), %r12
	movq	-240(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -584(%rbp)
	movq	-584(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -592(%rbp)
	movq	-592(%rbp), %r12
	movq	-248(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -592(%rbp)
	movq	-592(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -600(%rbp)
	movq	-600(%rbp), %r12
	movq	-256(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -600(%rbp)
	movq	-600(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -608(%rbp)
	movq	-608(%rbp), %r12
	movq	-264(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -608(%rbp)
	movq	-608(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -616(%rbp)
	movq	-616(%rbp), %r12
	movq	-272(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -616(%rbp)
	movq	-616(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -624(%rbp)
	movq	-624(%rbp), %r12
	movq	-280(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -624(%rbp)
	movq	-624(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -632(%rbp)
	movq	-632(%rbp), %r12
	movq	-288(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -632(%rbp)
	movq	-632(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -640(%rbp)
	movq	-640(%rbp), %r12
	movq	-296(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -640(%rbp)
	movq	-640(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -648(%rbp)
	movq	-648(%rbp), %r12
	movq	-304(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -648(%rbp)
	movq	-648(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -656(%rbp)
	movq	-656(%rbp), %r12
	movq	-312(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -656(%rbp)
	movq	-656(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -664(%rbp)
	movq	-664(%rbp), %r12
	movq	-320(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -664(%rbp)
	movq	-664(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -672(%rbp)
	movq	-672(%rbp), %r12
	movq	-328(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -672(%rbp)
	movq	-672(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -680(%rbp)
	movq	-680(%rbp), %r12
	movq	-336(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -680(%rbp)
	movq	-680(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -688(%rbp)
	movq	-688(%rbp), %r12
	movq	-344(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -688(%rbp)
	movq	-688(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -696(%rbp)
	movq	-696(%rbp), %r12
	movq	-352(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -696(%rbp)
	movq	-696(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -704(%rbp)
	movq	-704(%rbp), %r12
	movq	-360(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -704(%rbp)
	movq	-704(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -712(%rbp)
	movq	-712(%rbp), %r12
	movq	-368(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -712(%rbp)
	movq	-712(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -720(%rbp)
	movq	-720(%rbp), %r14
	addq	$-171, %r14
	movq	%r14, -720(%rbp)
	movq	-720(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -728(%rbp)
	movq	-408(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -736(%rbp)
	movq	-728(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -728(%rbp)
	jl	l1
	movq	-728(%rbp), %r12
	movq	-736(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -728(%rbp)
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
	movq	-408(%rbp), %r12
	movq	-728(%rbp), %r13
	movq	$0, (%r12,%r13,8)
	movq	$0, %r14
	movq	%r14, -744(%rbp)
	movq	$1, %r14
	movq	%r14, -752(%rbp)
	movq	$2, %r14
	movq	%r14, -760(%rbp)
	movq	$3, %r14
	movq	%r14, -768(%rbp)
	movq	$4, %r14
	movq	%r14, -776(%rbp)
	movq	$5, %r14
	movq	%r14, -784(%rbp)
	movq	$6, %r14
	movq	%r14, -792(%rbp)
	movq	$7, %r14
	movq	%r14, -800(%rbp)
	movq	$8, %r14
	movq	%r14, -808(%rbp)
	movq	$9, %r14
	movq	%r14, -816(%rbp)
	movq	$0, %r14
	movq	%r14, -824(%rbp)
	movq	$1, %r14
	movq	%r14, -832(%rbp)
	movq	$2, %r14
	movq	%r14, -840(%rbp)
	movq	$3, %r14
	movq	%r14, -848(%rbp)
	movq	$4, %r14
	movq	%r14, -856(%rbp)
	movq	$5, %r14
	movq	%r14, -864(%rbp)
	movq	$6, %r14
	movq	%r14, -872(%rbp)
	movq	$7, %r14
	movq	%r14, -880(%rbp)
	movq	$8, %r14
	movq	%r14, -888(%rbp)
	movq	$9, %r14
	movq	%r14, -896(%rbp)
	movq	$0, %r14
	movq	%r14, -904(%rbp)
	movq	$1, %r14
	movq	%r14, -912(%rbp)
	movq	$2, %r14
	movq	%r14, -920(%rbp)
	movq	$3, %r14
	movq	%r14, -928(%rbp)
	movq	$4, %r14
	movq	%r14, -936(%rbp)
	movq	$5, %r14
	movq	%r14, -944(%rbp)
	movq	$6, %r14
	movq	%r14, -952(%rbp)
	movq	$7, %r14
	movq	%r14, -960(%rbp)
	movq	$8, %r14
	movq	%r14, -968(%rbp)
	movq	$9, %r14
	movq	%r14, -976(%rbp)
	movq	$0, %r14
	movq	%r14, -984(%rbp)
	movq	$1, %r14
	movq	%r14, -992(%rbp)
	movq	$2, %r14
	movq	%r14, -1000(%rbp)
	movq	$3, %r14
	movq	%r14, -1008(%rbp)
	movq	$4, %r14
	movq	%r14, -1016(%rbp)
	movq	$5, %r14
	movq	%r14, -1024(%rbp)
	movq	$6, %r14
	movq	%r14, -1032(%rbp)
	movq	$7, %r14
	movq	%r14, -1040(%rbp)
	movq	$8, %r14
	movq	%r14, -1048(%rbp)
	movq	$9, %r14
	movq	%r14, -1056(%rbp)
	movq	$0, %r14
	movq	%r14, -1064(%rbp)
	movq	$1, %r14
	movq	%r14, -1072(%rbp)
	movq	$2, %r14
	movq	%r14, -1080(%rbp)
	movq	$3, %r14
	movq	%r14, -1088(%rbp)
	movq	$4, %r14
	movq	%r14, -1096(%rbp)
	movq	$5, %r14
	movq	%r14, -1104(%rbp)
	movq	$6, %r14
	movq	%r14, -1112(%rbp)
	movq	$7, %r14
	movq	%r14, -1120(%rbp)
	movq	$8, %r14
	movq	%r14, -1128(%rbp)
	movq	$9, %r14
	movq	%r14, -1136(%rbp)
	movq	$0, %r14
	movq	%r14, -1144(%rbp)
	movq	$1, %r14
	movq	%r14, -1152(%rbp)
	movq	$2, %r14
	movq	%r14, -1160(%rbp)
	movq	$3, %r14
	movq	%r14, -1168(%rbp)
	movq	$4, %r14
	movq	%r14, -1176(%rbp)
	movq	$5, %r14
	movq	%r14, -1184(%rbp)
	movq	$6, %r14
	movq	%r14, -1192(%rbp)
	movq	$7, %r14
	movq	%r14, -1200(%rbp)
	movq	$8, %r14
	movq	%r14, -1208(%rbp)
	movq	$9, %r14
	movq	%r14, -1216(%rbp)
	movq	$0, %r14
	movq	%r14, -1224(%rbp)
	movq	$1, %r14
	movq	%r14, -1232(%rbp)
	movq	$2, %r14
	movq	%r14, -1240(%rbp)
	movq	$3, %r14
	movq	%r14, -1248(%rbp)
	movq	$4, %r14
	movq	%r14, -1256(%rbp)
	movq	$5, %r14
	movq	%r14, -1264(%rbp)
	movq	$6, %r14
	movq	%r14, -1272(%rbp)
	movq	$7, %r14
	movq	%r14, -1280(%rbp)
	movq	$8, %r14
	movq	%r14, -1288(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-1288(%rbp), %r14
	pushq	%r14
	movq	-1280(%rbp), %r14
	pushq	%r14
	movq	-1272(%rbp), %r14
	pushq	%r14
	movq	-1264(%rbp), %r14
	pushq	%r14
	movq	-1256(%rbp), %r14
	pushq	%r14
	movq	-1248(%rbp), %r14
	pushq	%r14
	movq	-1240(%rbp), %r14
	pushq	%r14
	movq	-1232(%rbp), %r14
	pushq	%r14
	movq	-1224(%rbp), %r14
	pushq	%r14
	movq	-1216(%rbp), %r14
	pushq	%r14
	movq	-1208(%rbp), %r14
	pushq	%r14
	movq	-1200(%rbp), %r14
	pushq	%r14
	movq	-1192(%rbp), %r14
	pushq	%r14
	movq	-1184(%rbp), %r14
	pushq	%r14
	movq	-1176(%rbp), %r14
	pushq	%r14
	movq	-1168(%rbp), %r14
	pushq	%r14
	movq	-1160(%rbp), %r14
	pushq	%r14
	movq	-1152(%rbp), %r14
	pushq	%r14
	movq	-1144(%rbp), %r14
	pushq	%r14
	movq	-1136(%rbp), %r14
	pushq	%r14
	movq	-1128(%rbp), %r14
	pushq	%r14
	movq	-1120(%rbp), %r14
	pushq	%r14
	movq	-1112(%rbp), %r14
	pushq	%r14
	movq	-1104(%rbp), %r14
	pushq	%r14
	movq	-1096(%rbp), %r14
	pushq	%r14
	movq	-1088(%rbp), %r14
	pushq	%r14
	movq	-1080(%rbp), %r14
	pushq	%r14
	movq	-1072(%rbp), %r14
	pushq	%r14
	movq	-1064(%rbp), %r14
	pushq	%r14
	movq	-1056(%rbp), %r14
	pushq	%r14
	movq	-1048(%rbp), %r14
	pushq	%r14
	movq	-1040(%rbp), %r14
	pushq	%r14
	movq	-1032(%rbp), %r14
	pushq	%r14
	movq	-1024(%rbp), %r14
	pushq	%r14
	movq	-1016(%rbp), %r14
	pushq	%r14
	movq	-1008(%rbp), %r14
	pushq	%r14
	movq	-1000(%rbp), %r14
	pushq	%r14
	movq	-992(%rbp), %r14
	pushq	%r14
	movq	-984(%rbp), %r14
	pushq	%r14
	movq	-976(%rbp), %r14
	pushq	%r14
	movq	-968(%rbp), %r14
	pushq	%r14
	movq	-960(%rbp), %r14
	pushq	%r14
	movq	-952(%rbp), %r14
	pushq	%r14
	movq	-944(%rbp), %r14
	pushq	%r14
	movq	-936(%rbp), %r14
	pushq	%r14
	movq	-928(%rbp), %r14
	pushq	%r14
	movq	-920(%rbp), %r14
	pushq	%r14
	movq	-912(%rbp), %r14
	pushq	%r14
	movq	-904(%rbp), %r14
	pushq	%r14
	movq	-896(%rbp), %r14
	pushq	%r14
	movq	-888(%rbp), %r14
	pushq	%r14
	movq	-880(%rbp), %r14
	pushq	%r14
	movq	-872(%rbp), %r14
	pushq	%r14
	movq	-864(%rbp), %r14
	pushq	%r14
	movq	-856(%rbp), %r14
	pushq	%r14
	movq	-848(%rbp), %r14
	pushq	%r14
	movq	-840(%rbp), %r14
	pushq	%r14
	movq	-832(%rbp), %r14
	pushq	%r14
	movq	-824(%rbp), %r14
	pushq	%r14
	movq	-816(%rbp), %r14
	pushq	%r14
	movq	-808(%rbp), %r14
	pushq	%r14
	movq	-800(%rbp), %r14
	pushq	%r14
	movq	-792(%rbp), %r14
	pushq	%r14
	movq	-784(%rbp), %r14
	movq	%r14, %r9
	movq	-776(%rbp), %r14
	movq	%r14, %r8
	movq	-768(%rbp), %r14
	movq	%r14, %rcx
	movq	-760(%rbp), %r14
	movq	%r14, %rdx
	movq	-752(%rbp), %r14
	movq	%r14, %rsi
	movq	-744(%rbp), %r14
	movq	%r14, %rdi
	callq	_Ifn_t2iaiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii
	movq	%rax, %r12
	movq	%r12, -1296(%rbp)
	movq	%rdx, %r12
	movq	%r12, -1304(%rbp)
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-1296(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1312(%rbp)
	movq	-1312(%rbp), %r14
	addq	$-306, %r14
	movq	%r14, -1312(%rbp)
	movq	-1312(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1320(%rbp)
	movq	-1304(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -1328(%rbp)
	movq	-1320(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -1320(%rbp)
	jl	l4
	movq	-1320(%rbp), %r12
	movq	-1328(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -1320(%rbp)
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
	movq	-1296(%rbp), %r14
	movq	-1304(%rbp), %r12
	movq	-1320(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	jmp	label0

	.globl	_Ibn_t39iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii
	.align	4
_Ibn_t39iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii:
	enter	$48, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	$0, %rax
	movq	$1, %rdx
	movq	$2, (%rdi)
	movq	$3, 8(%rdi)
	movq	$4, 16(%rdi)
	movq	$5, 24(%rdi)
	movq	$6, 32(%rdi)
	movq	$7, 40(%rdi)
	movq	$8, 48(%rdi)
	movq	$9, 56(%rdi)
	movq	$0, 64(%rdi)
	movq	$1, 72(%rdi)
	movq	$2, 80(%rdi)
	movq	$3, 88(%rdi)
	movq	$4, 96(%rdi)
	movq	$5, 104(%rdi)
	movq	$6, 112(%rdi)
	movq	$7, 120(%rdi)
	movq	$8, 128(%rdi)
	movq	$9, 136(%rdi)
	movq	$0, 144(%rdi)
	movq	$1, 152(%rdi)
	movq	$2, 160(%rdi)
	movq	$3, 168(%rdi)
	movq	$4, 176(%rdi)
	movq	$5, 184(%rdi)
	movq	$6, 192(%rdi)
	movq	$7, 200(%rdi)
	movq	$8, 208(%rdi)
	movq	$9, 216(%rdi)
	movq	$0, 224(%rdi)
	movq	$1, 232(%rdi)
	movq	$2, 240(%rdi)
	movq	$3, 248(%rdi)
	movq	$4, 256(%rdi)
	movq	$5, 264(%rdi)
	movq	$6, 272(%rdi)
	movq	$7, 280(%rdi)
	movq	$8, 288(%rdi)
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret

	.globl	_Ifn_t2iaiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii
	.align	4
_Ifn_t2iaiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii:
	enter	$1184, $0
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
	movq	%r8, %r12
	movq	%r12, -88(%rbp)
	movq	%r9, %r12
	movq	%r12, -96(%rbp)
	movq	16(%rbp), %r14
	movq	%r14, -104(%rbp)
	movq	24(%rbp), %r14
	movq	%r14, -112(%rbp)
	movq	32(%rbp), %r14
	movq	%r14, -120(%rbp)
	movq	40(%rbp), %r14
	movq	%r14, -128(%rbp)
	movq	48(%rbp), %r14
	movq	%r14, -136(%rbp)
	movq	56(%rbp), %r14
	movq	%r14, -144(%rbp)
	movq	64(%rbp), %r14
	movq	%r14, -152(%rbp)
	movq	72(%rbp), %r14
	movq	%r14, -160(%rbp)
	movq	80(%rbp), %r14
	movq	%r14, -168(%rbp)
	movq	88(%rbp), %r14
	movq	%r14, -176(%rbp)
	movq	96(%rbp), %r14
	movq	%r14, -184(%rbp)
	movq	104(%rbp), %r14
	movq	%r14, -192(%rbp)
	movq	112(%rbp), %r14
	movq	%r14, -200(%rbp)
	movq	120(%rbp), %r14
	movq	%r14, -208(%rbp)
	movq	128(%rbp), %r14
	movq	%r14, -216(%rbp)
	movq	136(%rbp), %r14
	movq	%r14, -224(%rbp)
	movq	144(%rbp), %r14
	movq	%r14, -232(%rbp)
	movq	152(%rbp), %r14
	movq	%r14, -240(%rbp)
	movq	160(%rbp), %r14
	movq	%r14, -248(%rbp)
	movq	168(%rbp), %r14
	movq	%r14, -256(%rbp)
	movq	176(%rbp), %r14
	movq	%r14, -264(%rbp)
	movq	184(%rbp), %r14
	movq	%r14, -272(%rbp)
	movq	192(%rbp), %r14
	movq	%r14, -280(%rbp)
	movq	200(%rbp), %r14
	movq	%r14, -288(%rbp)
	movq	208(%rbp), %r14
	movq	%r14, -296(%rbp)
	movq	216(%rbp), %r14
	movq	%r14, -304(%rbp)
	movq	224(%rbp), %r14
	movq	%r14, -312(%rbp)
	movq	232(%rbp), %r14
	movq	%r14, -320(%rbp)
	movq	240(%rbp), %r14
	movq	%r14, -328(%rbp)
	movq	248(%rbp), %r14
	movq	%r14, -336(%rbp)
	movq	256(%rbp), %r14
	movq	%r14, -344(%rbp)
	movq	264(%rbp), %r14
	movq	%r14, -352(%rbp)
	movq	272(%rbp), %r14
	movq	%r14, -360(%rbp)
	movq	280(%rbp), %r14
	movq	%r14, -368(%rbp)
	movq	288(%rbp), %r14
	movq	%r14, -376(%rbp)
	movq	296(%rbp), %r14
	movq	%r14, -384(%rbp)
	movq	304(%rbp), %r14
	movq	%r14, -392(%rbp)
	movq	312(%rbp), %r14
	movq	%r14, -400(%rbp)
	movq	320(%rbp), %r14
	movq	%r14, -408(%rbp)
	movq	328(%rbp), %r14
	movq	%r14, -416(%rbp)
	movq	336(%rbp), %r14
	movq	%r14, -424(%rbp)
	movq	344(%rbp), %r14
	movq	%r14, -432(%rbp)
	movq	352(%rbp), %r14
	movq	%r14, -440(%rbp)
	movq	360(%rbp), %r14
	movq	%r14, -448(%rbp)
	movq	368(%rbp), %r14
	movq	%r14, -456(%rbp)
	movq	376(%rbp), %r14
	movq	%r14, -464(%rbp)
	movq	384(%rbp), %r14
	movq	%r14, -472(%rbp)
	movq	392(%rbp), %r14
	movq	%r14, -480(%rbp)
	movq	400(%rbp), %r14
	movq	%r14, -488(%rbp)
	movq	408(%rbp), %r14
	movq	%r14, -496(%rbp)
	movq	416(%rbp), %r14
	movq	%r14, -504(%rbp)
	movq	424(%rbp), %r14
	movq	%r14, -512(%rbp)
	movq	432(%rbp), %r14
	movq	%r14, -520(%rbp)
	movq	440(%rbp), %r14
	movq	%r14, -528(%rbp)
	movq	448(%rbp), %r14
	movq	%r14, -536(%rbp)
	movq	456(%rbp), %r14
	movq	%r14, -544(%rbp)
	movq	464(%rbp), %r14
	movq	%r14, -552(%rbp)
	movq	472(%rbp), %r14
	movq	%r14, -560(%rbp)
	movq	480(%rbp), %r14
	movq	%r14, -568(%rbp)
	movq	488(%rbp), %r14
	movq	%r14, -576(%rbp)
	movq	496(%rbp), %r14
	movq	%r14, -584(%rbp)
	movq	504(%rbp), %r14
	movq	%r14, -592(%rbp)
	movq	512(%rbp), %r14
	movq	%r14, -600(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -608(%rbp)
	movq	-608(%rbp), %r12
	movq	-64(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -608(%rbp)
	movq	-608(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -616(%rbp)
	movq	-616(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -616(%rbp)
	movq	-616(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -624(%rbp)
	movq	-624(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -624(%rbp)
	movq	-624(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -632(%rbp)
	movq	-632(%rbp), %r12
	movq	-88(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -632(%rbp)
	movq	-632(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -640(%rbp)
	movq	-640(%rbp), %r12
	movq	-96(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -640(%rbp)
	movq	-640(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -648(%rbp)
	movq	-648(%rbp), %r12
	movq	-104(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -648(%rbp)
	movq	-648(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -656(%rbp)
	movq	-656(%rbp), %r12
	movq	-112(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -656(%rbp)
	movq	-656(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -664(%rbp)
	movq	-664(%rbp), %r12
	movq	-120(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -664(%rbp)
	movq	-664(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -672(%rbp)
	movq	-672(%rbp), %r12
	movq	-128(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -672(%rbp)
	movq	-672(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -680(%rbp)
	movq	-680(%rbp), %r12
	movq	-136(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -680(%rbp)
	movq	-680(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -688(%rbp)
	movq	-688(%rbp), %r12
	movq	-144(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -688(%rbp)
	movq	-688(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -696(%rbp)
	movq	-696(%rbp), %r12
	movq	-152(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -696(%rbp)
	movq	-696(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -704(%rbp)
	movq	-704(%rbp), %r12
	movq	-160(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -704(%rbp)
	movq	-704(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -712(%rbp)
	movq	-712(%rbp), %r12
	movq	-168(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -712(%rbp)
	movq	-712(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -720(%rbp)
	movq	-720(%rbp), %r12
	movq	-176(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -720(%rbp)
	movq	-720(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -728(%rbp)
	movq	-728(%rbp), %r12
	movq	-184(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -728(%rbp)
	movq	-728(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -736(%rbp)
	movq	-736(%rbp), %r12
	movq	-192(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -736(%rbp)
	movq	-736(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -744(%rbp)
	movq	-744(%rbp), %r12
	movq	-200(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -744(%rbp)
	movq	-744(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -752(%rbp)
	movq	-752(%rbp), %r12
	movq	-208(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -752(%rbp)
	movq	-752(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -760(%rbp)
	movq	-760(%rbp), %r12
	movq	-216(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -760(%rbp)
	movq	-760(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -768(%rbp)
	movq	-768(%rbp), %r12
	movq	-224(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -768(%rbp)
	movq	-768(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -776(%rbp)
	movq	-776(%rbp), %r12
	movq	-232(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -776(%rbp)
	movq	-776(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -784(%rbp)
	movq	-784(%rbp), %r12
	movq	-240(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -784(%rbp)
	movq	-784(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -792(%rbp)
	movq	-792(%rbp), %r12
	movq	-248(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -792(%rbp)
	movq	-792(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -800(%rbp)
	movq	-800(%rbp), %r12
	movq	-256(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -800(%rbp)
	movq	-800(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -808(%rbp)
	movq	-808(%rbp), %r12
	movq	-264(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -808(%rbp)
	movq	-808(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -816(%rbp)
	movq	-816(%rbp), %r12
	movq	-272(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -816(%rbp)
	movq	-816(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -824(%rbp)
	movq	-824(%rbp), %r12
	movq	-280(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -824(%rbp)
	movq	-824(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -832(%rbp)
	movq	-832(%rbp), %r12
	movq	-288(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -832(%rbp)
	movq	-832(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -840(%rbp)
	movq	-840(%rbp), %r12
	movq	-296(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -840(%rbp)
	movq	-840(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -848(%rbp)
	movq	-848(%rbp), %r12
	movq	-304(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -848(%rbp)
	movq	-848(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -856(%rbp)
	movq	-856(%rbp), %r12
	movq	-312(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -856(%rbp)
	movq	-856(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -864(%rbp)
	movq	-864(%rbp), %r12
	movq	-320(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -864(%rbp)
	movq	-864(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -872(%rbp)
	movq	-872(%rbp), %r12
	movq	-328(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -872(%rbp)
	movq	-872(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -880(%rbp)
	movq	-880(%rbp), %r12
	movq	-336(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -880(%rbp)
	movq	-880(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -888(%rbp)
	movq	-888(%rbp), %r12
	movq	-344(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -888(%rbp)
	movq	-888(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -896(%rbp)
	movq	-896(%rbp), %r12
	movq	-352(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -896(%rbp)
	movq	-896(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -904(%rbp)
	movq	-904(%rbp), %r12
	movq	-360(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -904(%rbp)
	movq	-904(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -912(%rbp)
	movq	-912(%rbp), %r12
	movq	-368(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -912(%rbp)
	movq	-912(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -920(%rbp)
	movq	-920(%rbp), %r12
	movq	-376(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -920(%rbp)
	movq	-920(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -928(%rbp)
	movq	-928(%rbp), %r12
	movq	-384(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -928(%rbp)
	movq	-928(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -936(%rbp)
	movq	-936(%rbp), %r12
	movq	-392(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -936(%rbp)
	movq	-936(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -944(%rbp)
	movq	-944(%rbp), %r12
	movq	-400(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -944(%rbp)
	movq	-944(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -952(%rbp)
	movq	-952(%rbp), %r12
	movq	-408(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -952(%rbp)
	movq	-952(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -960(%rbp)
	movq	-960(%rbp), %r12
	movq	-416(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -960(%rbp)
	movq	-960(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -968(%rbp)
	movq	-968(%rbp), %r12
	movq	-424(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -968(%rbp)
	movq	-968(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -976(%rbp)
	movq	-976(%rbp), %r12
	movq	-432(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -976(%rbp)
	movq	-976(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -984(%rbp)
	movq	-984(%rbp), %r12
	movq	-440(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -984(%rbp)
	movq	-984(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -992(%rbp)
	movq	-992(%rbp), %r12
	movq	-448(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -992(%rbp)
	movq	-992(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1000(%rbp)
	movq	-1000(%rbp), %r12
	movq	-456(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -1000(%rbp)
	movq	-1000(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1008(%rbp)
	movq	-1008(%rbp), %r12
	movq	-464(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -1008(%rbp)
	movq	-1008(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1016(%rbp)
	movq	-1016(%rbp), %r12
	movq	-472(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -1016(%rbp)
	movq	-1016(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1024(%rbp)
	movq	-1024(%rbp), %r12
	movq	-480(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -1024(%rbp)
	movq	-1024(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1032(%rbp)
	movq	-1032(%rbp), %r12
	movq	-488(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -1032(%rbp)
	movq	-1032(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1040(%rbp)
	movq	-1040(%rbp), %r12
	movq	-496(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -1040(%rbp)
	movq	-1040(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1048(%rbp)
	movq	-1048(%rbp), %r12
	movq	-504(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -1048(%rbp)
	movq	-1048(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1056(%rbp)
	movq	-1056(%rbp), %r12
	movq	-512(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -1056(%rbp)
	movq	-1056(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1064(%rbp)
	movq	-1064(%rbp), %r12
	movq	-520(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -1064(%rbp)
	movq	-1064(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1072(%rbp)
	movq	-1072(%rbp), %r12
	movq	-528(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -1072(%rbp)
	movq	-1072(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1080(%rbp)
	movq	-1080(%rbp), %r12
	movq	-536(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -1080(%rbp)
	movq	-1080(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1088(%rbp)
	movq	-1088(%rbp), %r12
	movq	-544(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -1088(%rbp)
	movq	-1088(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1096(%rbp)
	movq	-1096(%rbp), %r12
	movq	-552(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -1096(%rbp)
	movq	-1096(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1104(%rbp)
	movq	-1104(%rbp), %r12
	movq	-560(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -1104(%rbp)
	movq	-1104(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1112(%rbp)
	movq	-1112(%rbp), %r12
	movq	-568(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -1112(%rbp)
	movq	-1112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1120(%rbp)
	movq	-1120(%rbp), %r12
	movq	-576(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -1120(%rbp)
	movq	-1120(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1128(%rbp)
	movq	-1128(%rbp), %r12
	movq	-584(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -1128(%rbp)
	movq	-1128(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1136(%rbp)
	movq	-1136(%rbp), %r12
	movq	-592(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -1136(%rbp)
	movq	-1136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1144(%rbp)
	movq	-1144(%rbp), %r12
	movq	-600(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -1144(%rbp)
	movq	-1144(%rbp), %r14
	movq	%r14, %rax
	movq	$16, %r14
	movq	%r14, -1152(%rbp)
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
	callq	_I_alloc_i
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
	movq	-1160(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1168(%rbp)
	movq	-1168(%rbp), %r12
	movq	$1, (%r12)
	movq	-1168(%rbp), %r12
	movq	$0, 8(%r12)
	movq	-1168(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1176(%rbp)
	movq	-1176(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -1176(%rbp)
	movq	-1176(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -1168(%rbp)
	movq	-1168(%rbp), %r14
	movq	%r14, %rdx
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
