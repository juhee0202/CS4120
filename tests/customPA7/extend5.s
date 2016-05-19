	.text
	.globl	_I_init_Organism
	.align	4
_I_init_Organism:
	enter	$0, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	cmpq	$0, _I_size_Organism(%rip)
	je	l3
l4:
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l3:
	movq	$2, _I_size_Organism(%rip)
	movq	$8, %_temp0
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%_temp0, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %_temp1
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
	movq	%_temp1, %_t6
	movq	%_t6, %_tileRegister0
	addq	$0, %_tileRegister0
	leaq	_Organism_Ievolve_p(%rip), %_tileRegister1
	movq	%_tileRegister1, (%_tileRegister0)
	movq	%_t6, _I_vt_Organism(%rip)
	jmp	l4

	.globl	_I_init_Animal
	.align	4
_I_init_Animal:
	enter	$0, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	cmpq	$0, _I_size_Animal(%rip)
	je	l5
l6:
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l5:
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
	movq	_I_size_Organism(%rip), %_tileRegister2
	addq	$1, %_tileRegister2
	movq	%_tileRegister2, _I_size_Animal(%rip)
	movq	$24, %_temp3
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%_temp3, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %_temp4
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
	movq	%_temp4, %_t11
	movq	%_t11, %_tileRegister3
	addq	$0, %_tileRegister3
	leaq	_Organism_Ievolve_p(%rip), %_tileRegister4
	movq	%_tileRegister4, (%_tileRegister3)
	movq	%_t11, %_tileRegister5
	addq	$8, %_tileRegister5
	leaq	_Animal_Isleep_pi(%rip), %_tileRegister6
	movq	%_tileRegister6, (%_tileRegister5)
	movq	%_t11, %_tileRegister7
	addq	$16, %_tileRegister7
	leaq	_Animal_Iwake_p(%rip), %_tileRegister8
	movq	%_tileRegister8, (%_tileRegister7)
	movq	%_t11, _I_vt_Animal(%rip)
	jmp	l6

	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$0, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %args
	movq	_I_size_Dog(%rip), %_tileRegister9
	movq	$8, %_tileRegister10
	imulq	%_tileRegister10, %_tileRegister9
	movq	%_tileRegister9, %_temp5
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%_temp5, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %_temp6
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
	movq	%_temp6, %_t17
	movq	_I_vt_Dog(%rip), %_tileRegister11
	movq	%_tileRegister11, (%_t17)
	movq	%_t17, %doggie
	movq	_I_g_everyone_ai(%rip), %_temp10
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%_temp10, %rdi
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

	.globl	_Animal_Iwake_p
	.align	4
_Animal_Iwake_p:
	enter	$0, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %_t9
	movq	%_t9, %_tileRegister12
	addq	$16, %_tileRegister12
	movq	(%_tileRegister12), %_temp12
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%_temp12, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %_temp13
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
	movq	%_temp13, %_temp14
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%_temp14, %rdi
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
	movq	$48, %_temp16
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%_temp16, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %_temp17
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
	movq	%_temp17, %_t10
	movq	$5, (%_t10)
	movq	$8, %_tileRegister13
	addq	%_t10, %_tileRegister13
	movq	$87, (%_tileRegister13)
	movq	$16, %_tileRegister14
	addq	%_t10, %_tileRegister14
	movq	$79, (%_tileRegister14)
	movq	$24, %_tileRegister15
	addq	%_t10, %_tileRegister15
	movq	$82, (%_tileRegister15)
	movq	$32, %_tileRegister16
	addq	%_t10, %_tileRegister16
	movq	$76, (%_tileRegister16)
	movq	$40, %_tileRegister17
	addq	%_t10, %_tileRegister17
	movq	$68, (%_tileRegister17)
	movq	%_t10, %_tileRegister18
	addq	$8, %_tileRegister18
	movq	%_tileRegister18, %_t10
	movq	%_t10, %_temp18
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%_temp18, %rdi
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

	.globl	_Organism_Ievolve_p
	.align	4
_Organism_Ievolve_p:
	enter	$0, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %_t4
	movq	%_t4, %_tileRegister19
	addq	$8, %_tileRegister19
	movq	$10, (%_tileRegister19)
	movq	$64, %_temp20
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%_temp20, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %_temp21
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
	movq	%_temp21, %_t5
	movq	$7, (%_t5)
	movq	$8, %_tileRegister20
	addq	%_t5, %_tileRegister20
	movq	$87, (%_tileRegister20)
	movq	$16, %_tileRegister21
	addq	%_t5, %_tileRegister21
	movq	$79, (%_tileRegister21)
	movq	$24, %_tileRegister22
	addq	%_t5, %_tileRegister22
	movq	$80, (%_tileRegister22)
	movq	$32, %_tileRegister23
	addq	%_t5, %_tileRegister23
	movq	$32, (%_tileRegister23)
	movq	$40, %_tileRegister24
	addq	%_t5, %_tileRegister24
	movq	$87, (%_tileRegister24)
	movq	$48, %_tileRegister25
	addq	%_t5, %_tileRegister25
	movq	$79, (%_tileRegister25)
	movq	$56, %_tileRegister26
	addq	%_t5, %_tileRegister26
	movq	$80, (%_tileRegister26)
	movq	%_t5, %_tileRegister27
	addq	$8, %_tileRegister27
	movq	%_tileRegister27, %_t5
	movq	%_t5, %_temp22
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%_temp22, %rdi
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

	.globl	_Animal_Isleep_pi
	.align	4
_Animal_Isleep_pi:
	enter	$0, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %_t7
	movq	%rsi, %c
	movq	$48, %_temp24
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%_temp24, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %_temp25
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
	movq	%_temp25, %_t8
	movq	$5, (%_t8)
	movq	$8, %_tileRegister28
	addq	%_t8, %_tileRegister28
	movq	$72, (%_tileRegister28)
	movq	$16, %_tileRegister29
	addq	%_t8, %_tileRegister29
	movq	$69, (%_tileRegister29)
	movq	$24, %_tileRegister30
	addq	%_t8, %_tileRegister30
	movq	$76, (%_tileRegister30)
	movq	$32, %_tileRegister31
	addq	%_t8, %_tileRegister31
	movq	$76, (%_tileRegister31)
	movq	$40, %_tileRegister32
	addq	%_t8, %_tileRegister32
	movq	$79, (%_tileRegister32)
	movq	%_t8, %_tileRegister33
	addq	$8, %_tileRegister33
	movq	%_tileRegister33, %_t8
	movq	%_t8, %_temp26
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%_temp26, %rdi
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

	.globl	_Dog_Ieat_p
	.align	4
_Dog_Ieat_p:
	enter	$0, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %_t12
	movq	$88, %_temp28
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%_temp28, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %_temp29
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
	movq	%_temp29, %_t13
	movq	$10, (%_t13)
	movq	$8, %_tileRegister34
	addq	%_t13, %_tileRegister34
	movq	$68, (%_tileRegister34)
	movq	$16, %_tileRegister35
	addq	%_t13, %_tileRegister35
	movq	$79, (%_tileRegister35)
	movq	$24, %_tileRegister36
	addq	%_t13, %_tileRegister36
	movq	$71, (%_tileRegister36)
	movq	$32, %_tileRegister37
	addq	%_t13, %_tileRegister37
	movq	$32, (%_tileRegister37)
	movq	$40, %_tileRegister38
	addq	%_t13, %_tileRegister38
	movq	$73, (%_tileRegister38)
	movq	$48, %_tileRegister39
	addq	%_t13, %_tileRegister39
	movq	$83, (%_tileRegister39)
	movq	$56, %_tileRegister40
	addq	%_t13, %_tileRegister40
	movq	$32, (%_tileRegister40)
	movq	$64, %_tileRegister41
	addq	%_t13, %_tileRegister41
	movq	$69, (%_tileRegister41)
	movq	$72, %_tileRegister42
	addq	%_t13, %_tileRegister42
	movq	$65, (%_tileRegister42)
	movq	$80, %_tileRegister43
	addq	%_t13, %_tileRegister43
	movq	$84, (%_tileRegister43)
	movq	%_t13, %_tileRegister44
	addq	$8, %_tileRegister44
	movq	%_tileRegister44, %_t13
	movq	%_t13, %_temp30
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%_temp30, %rdi
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

	.globl	_Dog_Isleep_pi
	.align	4
_Dog_Isleep_pi:
	enter	$0, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %_t14
	movq	%rsi, %c
	movq	$72, %_temp32
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%_temp32, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %_temp33
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
	movq	%_temp33, %_t15
	movq	$8, (%_t15)
	movq	$8, %_tileRegister45
	addq	%_t15, %_tileRegister45
	movq	$78, (%_tileRegister45)
	movq	$16, %_tileRegister46
	addq	%_t15, %_tileRegister46
	movq	$79, (%_tileRegister46)
	movq	$24, %_tileRegister47
	addq	%_t15, %_tileRegister47
	movq	$32, (%_tileRegister47)
	movq	$32, %_tileRegister48
	addq	%_t15, %_tileRegister48
	movq	$72, (%_tileRegister48)
	movq	$40, %_tileRegister49
	addq	%_t15, %_tileRegister49
	movq	$69, (%_tileRegister49)
	movq	$48, %_tileRegister50
	addq	%_t15, %_tileRegister50
	movq	$76, (%_tileRegister50)
	movq	$56, %_tileRegister51
	addq	%_t15, %_tileRegister51
	movq	$76, (%_tileRegister51)
	movq	$64, %_tileRegister52
	addq	%_t15, %_tileRegister52
	movq	$79, (%_tileRegister52)
	movq	%_t15, %_tileRegister53
	addq	$8, %_tileRegister53
	movq	%_tileRegister53, %_t15
	movq	%_t15, %_temp34
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%_temp34, %rdi
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

	.globl	_I_init_Dog
	.align	4
_I_init_Dog:
	enter	$0, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	cmpq	$0, _I_size_Dog(%rip)
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
	movq	_I_size_Animal(%rip), %_tileRegister54
	addq	$1, %_tileRegister54
	movq	%_tileRegister54, _I_size_Dog(%rip)
	movq	$32, %_temp37
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%_temp37, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %_temp38
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
	movq	%_temp38, %_t16
	movq	%_t16, %_tileRegister55
	addq	$0, %_tileRegister55
	leaq	_Organism_Ievolve_p(%rip), %_tileRegister56
	movq	%_tileRegister56, (%_tileRegister55)
	movq	%_t16, %_tileRegister57
	addq	$8, %_tileRegister57
	leaq	_Dog_Isleep_pi(%rip), %_tileRegister58
	movq	%_tileRegister58, (%_tileRegister57)
	movq	%_t16, %_tileRegister59
	addq	$16, %_tileRegister59
	leaq	_Animal_Iwake_p(%rip), %_tileRegister60
	movq	%_tileRegister60, (%_tileRegister59)
	movq	%_t16, %_tileRegister61
	addq	$24, %_tileRegister61
	leaq	_Dog_Ieat_p(%rip), %_tileRegister62
	movq	%_tileRegister62, (%_tileRegister61)
	movq	%_t16, _I_vt_Dog(%rip)
	jmp	l8

	.globl	_I_init_everyone_ai
	.align	4
_I_init_everyone_ai:
	enter	$0, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	$6, %_t0
	movq	%_t0, %_tileRegister63
	addq	$1, %_tileRegister63
	movq	%_tileRegister63, %_tileRegister64
	movq	$8, %_tileRegister65
	imulq	%_tileRegister65, %_tileRegister64
	movq	%_tileRegister64, %_temp39
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%_temp39, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %_temp40
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
	movq	%_temp40, %_t1
	movq	%_t0, (%_t1)
	movq	%_t1, %_tileRegister66
	addq	$8, %_tileRegister66
	movq	%_tileRegister66, %_t1
	movq	$0, %_t2
l0:
	cmpq	%_t0, %_t2
	jl	l1
	movq	%_t1, _I_g_everyone_ai(%rip)
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l1:
	leaq	(%_t1,%_t2,8), %_tileRegister67
	movq	%_tileRegister67, %_temp41
	movq	$0, %_t3
	movq	%_t3, (%_temp41)
	movq	%_t2, %_tileRegister70
	addq	$1, %_tileRegister70
	movq	%_tileRegister70, %_t2
	jmp	l0


	.section .data
	.align 8
.globl _I_g_x_i
_I_g_x_i:
	.quad 0
	.text

	.bss
	.align 8
.globl _I_vt_Dog
_I_vt_Dog:
	.zero 32
	.text

.section .ctors
	.align 8
	.quad _I_init_Dog
	.text

	.section .data
	.align 8
.globl _I_g_z_i
_I_g_z_i:
	.quad 0
	.text

	.bss
	.align 8
.globl _I_size_Dog
_I_size_Dog:
	.zero 8
	.text

	.bss
	.align 8
.globl _I_size_Organism
_I_size_Organism:
	.zero 8
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
.globl _I_g_y_i
_I_g_y_i:
	.quad 0
	.text

	.bss
	.align 8
.globl _I_vt_Animal
_I_vt_Animal:
	.zero 24
	.text

.section .ctors
	.align 8
	.quad _I_init_Animal
	.text

	.section .data
	.align 8
.globl _I_g_everyone_ai
_I_g_everyone_ai:
	.zero 8
	.text

.section .ctors
	.align 8
	.quad _I_init_everyone_ai
	.text

	.bss
	.align 8
.globl _I_size_Animal
_I_size_Animal:
	.zero 8
	.text
