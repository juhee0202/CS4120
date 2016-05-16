# Compiled using "xic" by Owen Arden, Catalin Dumitru, Wenzel Jakob, and Danfeng Zhang
# Command line: java -jar xic.jar -I runtime/include/ -I xth/tests/pa5 QtXi/examples/widgetevents.xi

.file "QtXi/examples/widgetevents.xi"
.att_syntax prefix
.text

.globl _I_MyButton_clicked_p
_I_MyButton_clicked_p:
	pushq %rbx
	pushq %r14
	pushq %r15
	leaq .L1(%rip), %rdi
	call strdup
	movq %rax, %rdi
	call _Iqs_o7QStringai
	movq %rax, %r15
	leaq .L2(%rip), %rdi
	call strdup
	movq %rax, %rdi
	call _Iqs_o7QStringai
	movq %rax, %r14
	call _IButtonYes_i
	movq %rax, %rbx
	call _IButtonNo_i
	addq %rax, %rbx
	movq %rbx, %rcx
	movq %r14, %rdx
	movq %r15, %rsi
	xorq %rdi, %rdi
	call _IqmessageBoxQuestion_io7QWidgeto7QStringo7QStringi
	movq %rax, %rbx
	call _IButtonYes_i
	cmpq %rax, %rbx
	jne .L4
	movq _I_g_qapp_o12QApplication(%rip), %rax
	movq %rax, %rdi
	movq (%rax), %rax
	callq *16(%rax)
.L4:
	popq %r15
	popq %r14
	popq %rbx
	ret

.globl _I_MyButton_keyPressEvent_po9QKeyEvent
_I_MyButton_keyPressEvent_po9QKeyEvent:
	pushq %rbx
	pushq %r14
	subq $8, %rsp
	movq %rdi, %r14
	movq %rsi, %rbx
	movq %rbx, %rdi
	movq (%rbx), %rax
	callq *96(%rax)
	movq %rax, %rdi
	movq (%rax), %rax
	callq *8(%rax)
	movq %rax, %rdi
	call _Iprintln_pai
	movq %rbx, %rsi
	movq %r14, %rdi
	movq (%r14), %rax
	callq *616(%rax)
	addq $8, %rsp
	popq %r14
	popq %rbx
	ret

.globl _I_MyButton_mouseMoveEvent_po11QMouseEvent
_I_MyButton_mouseMoveEvent_po11QMouseEvent:
	pushq %rbx
	pushq %r14
	subq $8, %rsp
	movq %rsi, %rdi
	movq (%rsi), %rax
	callq *88(%rax)
	movq %rax, %r14
	movq (%r14), %rbx
	movq %r14, %rdi
	callq *8(%rbx)
	movq %rax, %rdi
	call _IunparseInt_aii
	movq %rax, %rdi
	call _Iprint_pai
	leaq .L0(%rip), %rdi
	call strdup
	movq %rax, %rdi
	call _Iprint_pai
	movq %r14, %rdi
	callq *16(%rbx)
	movq %rax, %rdi
	call _IunparseInt_aii
	movq %rax, %rdi
	call _Iprintln_pai
	addq $8, %rsp
	popq %r14
	popq %rbx
	ret

.globl _I_init_MyButton
_I_init_MyButton:
	subq $8, %rsp
	cmpq $0, _I_size_MyButton(%rip)
	jne .L5
	call _I_init_QPushButton
	movq _I_size_QPushButton(%rip), %rax
	movq %rax, _I_size_MyButton(%rip)
	xorq %rcx, %rcx
.L6:
	cmpq $94, %rcx
	jge .L7
	leaq _I_vt_QPushButton(%rip), %rax
	movq (%rax,%rcx,8), %rdx
	leaq _I_vt_MyButton(%rip), %rax
	movq %rdx, (%rax,%rcx,8)
	incq %rcx
	jmp .L6
.L7:
	leaq _I_MyButton_mouseMoveEvent_po11QMouseEvent(%rip), %rax
	movq %rax, _I_vt_MyButton+528(%rip)
	leaq _I_MyButton_keyPressEvent_po9QKeyEvent(%rip), %rax
	movq %rax, _I_vt_MyButton+544(%rip)
	leaq _I_MyButton_clicked_p(%rip), %rax
	movq %rax, _I_vt_MyButton+744(%rip)
.L5:
	addq $8, %rsp
	ret

.globl _Imain_paai
_Imain_paai:
	pushq %rbx
	pushq %r14
	subq $24, %rsp
	movq %rdi, %rsi
	movq %rsp, %rdi
	call _Iqapplication_t2o12QApplicationaaiaai
	movq (%rsp), %rax
	movq %rax, _I_g_qapp_o12QApplication(%rip)
	movq _I_size_MyButton(%rip), %rdi
	call _I_alloc_i
	movq %rax, %rbx
	leaq _I_vt_MyButton(%rip), %rax
	movq %rax, (%rbx)
	call _IDialogOkButton_o12StandardIcon
	movq %rax, %rdi
	call _IqiconStandard_o5QIcono12StandardIcon
	movq (%rbx), %r14
	movq %rax, %rsi
	movq %rbx, %rdi
	callq *640(%r14)
	call _IClickFocus_o11FocusPolicy
	movq %rax, %rsi
	movq %rbx, %rdi
	callq *408(%r14)
	movq $1, %rsi
	movq %rbx, %rdi
	callq *480(%r14)
	movq %rbx, %rdi
	callq *32(%r14)
	movq _I_g_qapp_o12QApplication(%rip), %rax
	movq %rax, %rdi
	movq (%rax), %rax
	callq *8(%rax)
	addq $24, %rsp
	popq %r14
	popq %rbx
	ret

strdup:
	pushq %rbx
	pushq %r14
	subq $8, %rsp
	movq %rdi, %r14
	movq (%r14), %rbx
	leaq 8(,%rbx,8), %rdi
	call _I_alloc_i
.L8:
	movq (%r14,%rbx,8), %rcx
	movq %rcx, (%rax,%rbx,8)
	decq %rbx
	cmpq $0, %rbx
	jge .L8
	addq $8, %rax
	addq $8, %rsp
	popq %r14
	popq %rbx
	ret

	.bss
	.align 8
.globl _I_size_MyButton
_I_size_MyButton:
	.zero 8
	.text

	.bss
	.align 8
.globl _I_vt_MyButton
_I_vt_MyButton:
	.zero 760
	.text

.section .ctors
	.align 8
	.quad _I_init_MyButton
	.text

	.section .rodata
	.align 8
.L0:
	.quad 1
	.quad 44
	.text

	.section .rodata
	.align 8
.L1:
	.quad 5
	.quad 81
	.quad 117
	.quad 105
	.quad 116
	.quad 63
	.text

	.section .rodata
	.align 8
.L2:
	.quad 15
	.quad 83
	.quad 104
	.quad 111
	.quad 117
	.quad 108
	.quad 100
	.quad 32
	.quad 119
	.quad 101
	.quad 32
	.quad 113
	.quad 117
	.quad 105
	.quad 116
	.quad 63
	.text

	.bss
	.align 8
.globl _I_g_qapp_o12QApplication
_I_g_qapp_o12QApplication:
	.zero 8
	.text

