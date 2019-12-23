package compiler;

import types.IType;
import types.NumberType;

import java.util.ArrayList;
import java.util.List;

public class CodeBlock {

	private List<String> code;

	public CodeBlock(int initialSize) {
		code = new ArrayList<>(initialSize);
	}

	public CodeBlock() {
		code = new ArrayList<>();
	}

	public void append(String codeLine) {

		code.add(codeLine);
	}

	public void tabify() {
		emit_comment("tabify");
	}

	public void detabify() {
		emit_comment("detabify");
	}

	public void append(List<String> codeLines) {
		for (String line : codeLines)
			append(line);
	}

	public List<String> getCode() {
		return code;
	}

	public void append(CodeBlock instructions) {
		append(instructions.getCode());
	}

	public void emit_sint(int i) {
		append("sipush " + i);
	}

	public void emit_bint(int i) {
		append("bipush " + i);
	}

	public void emit_iconst(int i) {
		append("iconst_" + i);
	}

	public void emit_boolean(boolean b) {
		emit_iconst(b ? 1 : 0);
	}

	public void emit_iadd() {
		append("iadd");
	}

	public void emit_add(String type) {
		append(type + "add");
	}

	public void emit_sub(String type) {
		append(type + "sub");
	}

	public void emit_optimize_int(int value) {
		if (value >= 0 && value <= 5)
			emit_iconst(value);
		else if (value == -1)
			append("iconst_m1");
		else if (value >= -128 && value <= 127)
			emit_bint(value);
		else if (value >= -32768 && value <= 32767)
			emit_sint(value);
		else
			append("ldc " + value);
	}

	public void emit_mul(String type) {
		append(type + "mul");
	}

	public void emit_div(String type) {
		append(type + "div");
	}

	public void emit_int_compare_not_equal(String label) {
		append("if_icmpne " + label);
	}

	public void emit_int_compare_less_or_equal(String label) {
		append("if_icmple " + label);
	}

	public void emit_int_compare_equal(String label) {
		append("if_icmpeq " + label);
	}

	public void emit_goto(String label) {
		append("goto " + label);
	}

	public void emit_isub() {
		append("isub");
	}

	public void emit_imul() {
		append("imul");
	}

	public void emit_idiv() {
		append("idiv");
	}

	public void emit_fadd() {
		append("fadd");
	}

	public void emit_fsub() {
		append("fsub");
	}

	public void emit_fmul() {
		append("fmul");
	}

	public void emit_fdiv() {
		append("fdiv");
	}

	public void emit_new(String className) {
		append("new " + className);
	}

	public void emit_duplicate() {
		append("dup");
	}

	public void emit_invokeSpecial(String params) {
		append("invokespecial " + params);
	}

	public void emit_aload(int n) {
		append("aload " + n);
	}

	public void emit_astore(int n) {
		append("astore " + n);
	}

	public void emit_putField(String field, String descriptor) {
		append("putfield " + field + " " + descriptor);
	}

	public void emit_comment(String msg) {
		append("; " + msg);
	}

	public void emit_blank() {
		append("");
	}

	public void emit_getField(String field, String descriptor) {
		append("getfield " + field + " " + descriptor);
	}

	public void emit_ineg() {
		append("ineg");
	}

	public void emit_float(float value) {
		append("ldc " + value);
	}

	public void emit_optimize_float(float value) {
		if (value == 0 || value == 1 || value == 2) {
			append("fconst_" + (int) value);
		} else
			append("ldc " + value);
	}

	public void emit_ixor() {
		append("ixor");
	}

	public void emit_label(String name) {
		append(name + ":");
	}

	public void emit_int_to_float() {
		emit_convert("i", "f");
	}

	public void emit_float_to_int() {
		emit_convert("f", "i");
	}

	public void emit_float_compare() {
		append("fcmpl");
	}

	public void emit_if_not_equal(String label) {
		append("ifne " + label);
	}

	public void emit_if_less_equal(String label) {
		append("ifle " + label);
	}

	public void emit_if_equal(String label) {
		append("ifeq " + label);
	}

	public void emit_convert(String type1, String type2) {
		append(type1 + "2" + type2);
	}

	public void emit_convert(IType type1, IType type2) {
		emit_convert(((NumberType) type1).getConversionLiteral(), ((NumberType) type2).getConversionLiteral());
	}

//	public void emit_println(String jvmType) {
//		appendCodeLine("; convert to String;");
//		appendCodeLine("invokestatic java/lang/String/valueOf(" + jvmType + ")Ljava/lang/String;");
//		appendCodeLine("; call println");
//		appendCodeLine("getstatic java/lang/System/out Ljava/io/PrintStream;");
//		appendCodeLine("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
//	}

	public void emit_if_greater_equal(String label) {
		append("ifge " + label);
	}

	public void emit_int_compare_greater_or_equal(String label) {
		append("if_icmpge " + label);
	}

	public void emit_if_less(String label) {
		append("ifle " + label);
	}

	public void emit_int_compare_less(String label) {
		append("if_icmplt " + label);
	}

	public void emit_if_greater(String label) {
		append("ifgt " + label);
	}

	public void emit_int_compare_greater(String label) {
		append("if_icmpgt " + label);
	}

	public void emit_iand() {
		append("iand");
	}

	public void emit_ior() {
		append("ior");
	}

	public void emit_checkcast(String className) {
		append("checkcast " + className);
	}

	public void emit_getstatic(String field, String descriptor) {
		append("getstatic " + field + " " + descriptor);
	}

	public void emit_invokevirtual(String method) {
		append("invokevirtual " + method);
	}

	public void emit_invokeStatic(String method) {
		append("invokestatic " + method);
	}

	public void emit_valueOf(IType type) {
		emit_invokeStatic(type.getJavaClass() + "/valueOf(" + type.getJVMType() + ")" + "L" + type
			.getJavaClass() + ";");
	}

	public void emit_invoke_println(IType type) {
		emit_invokevirtual("java/io/PrintStream/println(" + type.getJVMType() + ")V");
	}

	public void emit_invoke_println(String type) {
		emit_invokevirtual("java/io/PrintStream/println(" + type + ")V");
	}

	public void emit_invokeinterface(String method) {
		append("invokeinterface " + method);
	}
}
