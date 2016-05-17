# Compiled using "xic" by Owen Arden, Catalin Dumitru, Wenzel Jakob, and Danfeng Zhang
# Command line: java -jar xic.jar -I runtime/include/ QtXi/examples/layouts.xi

.file "QtXi/examples/layouts.xi"
.att_syntax prefix
.text

.globl _Imain_paai
_Imain_paai:
	pushq %rbx
	pushq %rbp
	pushq %r12
	pushq %r13
	pushq %r14
	pushq %r15
	subq $200, %rsp
	movq %rdi, %rsi
	movq %rsp, %rdi
	call _Iqapplication_t2o12QApplicationaaiaai
	movq (%rsp), %r12
	call _Iqdialog_o7QDialog
	movq %rax, 16(%rsp)
	movq 16(%rsp), %rcx
	movq %rcx, 16(%rsp)
	call _IqvboxLayout_o11QVBoxLayout
	movq %rax, 192(%rsp)
	movq 192(%rsp), %rcx
	movq %rcx, 192(%rsp)
	movq 192(%rsp), %rsi
	movq 16(%rsp), %rdi
	movq 16(%rsp), %rax
	movq (%rax), %rax
	callq *88(%rax)
	leaq .L0(%rip), %rdi
	call strdup
	movq %rax, %rdi
	call _Iqs_o7QStringai
	movq %rax, %rdi
	call _Iqlabel_o6QLabelo7QString
	movq %rax, %r14
	call _Iqslider_o7QSlider
	movq %rax, %r13
	movq %r13, %rsi
	movq %r14, %rdi
	movq (%r14), %rax
	callq *664(%rax)
	call _IHorizontal_o11Orientation
	movq (%r13), %rbx
	movq %rax, %rsi
	movq %r13, %rdi
	callq *640(%rbx)
	movq $100, %rdx
	xorq %rsi, %rsi
	movq %r13, %rdi
	callq *664(%rbx)
	movq $10, %rsi
	movq %r13, %rdi
	callq *752(%rbx)
	call _ITicksAbove_o18SliderTickPosition
	movq %rax, %rsi
	movq %r13, %rdi
	callq *760(%rbx)
	call _IqhboxLayout_o11QHBoxLayout
	movq %rax, %r15
	movq (%r15), %rax
	movq 56(%rax), %rbx
	movq %r14, %rsi
	movq %r15, %rdi
	callq *%rbx
	movq %r13, %rsi
	movq %r15, %rdi
	callq *%rbx
	movq 192(%rsp), %rax
	movq (%rax), %rcx
	movq 40(%rcx), %rax
	movq %r15, %rsi
	movq 192(%rsp), %rdi
	callq *%rax
	leaq .L1(%rip), %rdi
	call strdup
	movq %rax, %rdi
	call _Iqs_o7QStringai
	movq %rax, %rdi
	call _Iqcheckbox_o9QCheckBoxo7QString
	movq %rax, 120(%rsp)
	movq 120(%rsp), %rax
	movq %rax, 120(%rsp)
	movq 192(%rsp), %rcx
	movq (%rcx), %rax
	addq $56, %rax
	movq (%rax), %rbp
	movq 120(%rsp), %rsi
	movq 192(%rsp), %rdi
	callq *%rbp
	leaq .L2(%rip), %rax
	movq %rax, 88(%rsp)
	movq 88(%rsp), %rdi
	call strdup
	movq %rax, %rdi
	call _Iqs_o7QStringai
	movq %rax, %r14
	call _Iqbuttongroup_o12QButtonGroup
	movq %rax, 128(%rsp)
	movq 128(%rsp), %rax
	movq %rax, 128(%rsp)
	movq 128(%rsp), %rcx
	movq %rcx, _I_g_bg_o12QButtonGroup(%rip)
	movq $1, %rdi
	call _IqsNum_o7QStringi
	movq %rax, 184(%rsp)
	movq 184(%rsp), %rax
	movq %rax, 184(%rsp)
	movq (%r14), %rax
	movq %rax, 112(%rsp)
	movq 112(%rsp), %rcx
	movq 264(%rcx), %r15
	movq 184(%rsp), %rsi
	movq %r14, %rdi
	callq *%r15
	movq %rax, 144(%rsp)
	movq 144(%rsp), %rax
	movq %rax, 144(%rsp)
	movq 144(%rsp), %rdi
	call _Iqradio_o12QRadioButtono7QString
	movq %rax, %rbx
	movq %rbx, %rsi
	movq 192(%rsp), %rdi
	callq *%rbp
	movq _I_g_bg_o12QButtonGroup(%rip), %rax
	movq %rax, 24(%rsp)
	movq %rbx, %rsi
	movq 24(%rsp), %rdi
	movq 24(%rsp), %rcx
	movq (%rcx), %rcx
	movq %rcx, 168(%rsp)
	movq 168(%rsp), %rax
	callq *8(%rax)
	movq $1, %r13
.L3:
	cmpq $5, %r13
	jge .L5
	movq %r13, 72(%rsp)
	movq 72(%rsp), %rax
	addq $1, 72(%rsp)
	movq 72(%rsp), %rdi
	call _IqsNum_o7QStringi
	movq %rax, 32(%rsp)
	movq 32(%rsp), %rax
	movq %rax, 32(%rsp)
	movq 32(%rsp), %rsi
	movq %r14, %rdi
	callq *%r15
	movq %rax, 56(%rsp)
	movq 56(%rsp), %rax
	movq %rax, 56(%rsp)
	movq 56(%rsp), %rdi
	call _Iqradio_o12QRadioButtono7QString
	movq %rax, %rbx
	movq %rbx, %rsi
	movq 192(%rsp), %rdi
	callq *%rbp
	movq _I_g_bg_o12QButtonGroup(%rip), %rax
	movq %rax, 160(%rsp)
	movq %rbx, %rsi
	movq 160(%rsp), %rdi
	movq 160(%rsp), %rcx
	movq (%rcx), %rax
	movq %rax, 96(%rsp)
	movq 96(%rsp), %rcx
	callq *8(%rcx)
	incq %r13
	jmp .L3
.L5:
	leaq .L7(%rip), %rax
	movq %rax, 104(%rsp)
	movq 104(%rsp), %rdi
	call strdup
	movq %rax, %rdi
	call _Iqs_o7QStringai
	movq %rax, %rdi
	call _Iqgroupbox_o9QGroupBoxo7QString
	movq %rax, %rbx
	movq %rbx, %rsi
	movq 192(%rsp), %rdi
	callq *%rbp
	call _IqvboxLayout_o11QVBoxLayout
	movq %rax, %r13
	movq (%rbx), %r14
	movq %r13, %rsi
	movq %rbx, %rdi
	callq *88(%r14)
	movq $1, %rsi
	movq %rbx, %rdi
	callq *648(%r14)
	leaq .L8(%rip), %rax
	movq %rax, 152(%rsp)
	movq 152(%rsp), %rdi
	call strdup
	movq %rax, %rdi
	call _Iqs_o7QStringai
	movq %rax, %rbp
	movq $1, %rdi
	call _IqsNum_o7QStringi
	movq %rax, 48(%rsp)
	movq 48(%rsp), %rax
	movq %rax, 48(%rsp)
	movq (%rbp), %rcx
	movq %rcx, 80(%rsp)
	movq 80(%rsp), %rax
	movq 264(%rax), %r15
	movq 48(%rsp), %rsi
	movq %rbp, %rdi
	callq *%r15
	movq %rax, 64(%rsp)
	movq 64(%rsp), %rax
	movq %rax, 64(%rsp)
	movq 64(%rsp), %rdi
	call _Iqradio_o12QRadioButtono7QString
	movq %rax, 176(%rsp)
	movq 176(%rsp), %rax
	movq %rax, 176(%rsp)
	movq (%r13), %rax
	movq 56(%rax), %r14
	movq 176(%rsp), %rsi
	movq %r13, %rdi
	callq *%r14
	movq $1, %rbx
.L9:
	cmpq $5, %rbx
	jge .L11
	movq %rbx, 136(%rsp)
	movq 136(%rsp), %rax
	addq $1, 136(%rsp)
	movq 136(%rsp), %rdi
	call _IqsNum_o7QStringi
	movq %rax, %rsi
	movq %rbp, %rdi
	callq *%r15
	movq %rax, %rdi
	call _Iqradio_o12QRadioButtono7QString
	movq %rax, %rsi
	movq %r13, %rdi
	callq *%r14
	incq %rbx
	jmp .L9
.L11:
	leaq .L0(%rip), %rdi
	call strdup
	movq %rax, %rdi
	call _Iqs_o7QStringai
	movq %rax, %rdi
	call _Iqlabel_o6QLabelo7QString
	movq %rax, %r13
	call _Iqlineedit_o9QLineEdit
	movq %rax, %r15
	movq %r15, %rsi
	movq %r13, %rdi
	movq (%r13), %rax
	callq *664(%rax)
	call _IqhboxLayout_o11QHBoxLayout
	movq %rax, %rbx
	movq (%rbx), %rax
	movq 56(%rax), %r14
	movq %r13, %rsi
	movq %rbx, %rdi
	callq *%r14
	movq %r15, %rsi
	movq %rbx, %rdi
	callq *%r14
	movq 192(%rsp), %rax
	movq (%rax), %rax
	movq 40(%rax), %r13
	movq %rbx, %rsi
	movq 192(%rsp), %rdi
	callq *%r13
	call _Iqtextedit_o9QTextEdit
	movq 192(%rsp), %rcx
	movq (%rcx), %rcx
	addq $56, %rcx
	movq (%rcx), %rcx
	movq %rcx, 40(%rsp)
	movq %rax, %rsi
	movq 192(%rsp), %rdi
	callq *40(%rsp)
	call _IqhboxLayout_o11QHBoxLayout
	movq %rax, %r15
	movq (%r15), %rax
	movq %r15, %rdi
	callq *48(%rax)
	leaq .L13(%rip), %rdi
	call strdup
	movq %rax, %rdi
	call _Iqs_o7QStringai
	movq %rax, %rdi
	call _Iqpushbutton_o11QPushButtono7QString
	movq %rax, %r14
	call _IDialogOkButton_o12StandardIcon
	movq %rax, %rdi
	call _IqiconStandard_o5QIcono12StandardIcon
	movq %rax, %rsi
	movq %r14, %rdi
	movq (%r14), %rax
	callq *640(%rax)
	movq (%r15), %rax
	addq $56, %rax
	movq (%rax), %rbx
	movq %r14, %rsi
	movq %r15, %rdi
	callq *%rbx
	leaq .L14(%rip), %rdi
	call strdup
	movq %rax, %rdi
	call _Iqs_o7QStringai
	movq %rax, %rdi
	call _Iqpushbutton_o11QPushButtono7QString
	movq %rax, %r14
	call _IDialogCancelButton_o12StandardIcon
	movq %rax, %rdi
	call _IqiconStandard_o5QIcono12StandardIcon
	movq %rax, %rsi
	movq %r14, %rdi
	movq (%r14), %rax
	callq *640(%rax)
	movq %r14, %rsi
	movq %r15, %rdi
	callq *%rbx
	movq %r15, %rsi
	movq 192(%rsp), %rdi
	callq *%r13
	movq 16(%rsp), %rdi
	movq 16(%rsp), %rcx
	movq (%rcx), %rax
	callq *32(%rax)
	movq %r12, %rdi
	movq (%r12), %rax
	callq *8(%rax)
	addq $200, %rsp
	popq %r15
	popq %r14
	popq %r13
	popq %r12
	popq %rbp
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
.L15:
	movq (%r14,%rbx,8), %rcx
	movq %rcx, (%rax,%rbx,8)
	decq %rbx
	cmpq $0, %rbx
	jge .L15
	addq $8, %rax
	addq $8, %rsp
	popq %r14
	popq %rbx
	ret

	.section .rodata
	.align 8
.L2:
	.quad 1
	.quad 65
	.text

	.section .rodata
	.align 8
.L8:
	.quad 1
	.quad 66
	.text

	.section .rodata
	.align 8
.L14:
	.quad 6
	.quad 67
	.quad 97
	.quad 110
	.quad 99
	.quad 101
	.quad 108
	.text

	.section .rodata
	.align 8
.L1:
	.quad 8
	.quad 67
	.quad 104
	.quad 101
	.quad 99
	.quad 107
	.quad 98
	.quad 111
	.quad 120
	.text

	.section .rodata
	.align 8
.L7:
	.quad 5
	.quad 71
	.quad 114
	.quad 111
	.quad 117
	.quad 112
	.text

	.section .rodata
	.align 8
.L13:
	.quad 2
	.quad 79
	.quad 107
	.text

	.section .rodata
	.align 8
.L0:
	.quad 5
	.quad 86
	.quad 97
	.quad 108
	.quad 117
	.quad 101
	.text

	.bss
	.align 8
.globl _I_g_bg_o12QButtonGroup
_I_g_bg_o12QButtonGroup:
	.zero 8
	.text

