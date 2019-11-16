package compiler;

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

	public void appendCodeLine(String codeLine) {

		code.add(codeLine);
	}

	public void tabify() {
		emit_comment("tabify");
	}

	public void detabify() {
		emit_comment("detabify");
	}

	public void appendAllCodeLines(List<String> codeLines) {
		for (String line : codeLines)
			appendCodeLine(line);
	}

	List<String> getCode() {
		return code;
	}

	public void appendCodeBlock(CodeBlock instructions) {
		appendAllCodeLines(instructions.getCode());
	}

	public void emit_sint(int i) {
		appendCodeLine("sipush " + i);
	}

	public void emit_bint(int i) {
		appendCodeLine("bipush " + i);
	}

	public void emit_iconst(int i) {
		appendCodeLine("iconst_" + i);
	}

	public void emit_boolean(boolean b) {
		emit_iconst(b ? 1 : 0);
	}

	public void emit_iadd() {
		appendCodeLine("iadd");
	}

	public void emit_add(String type) {
		appendCodeLine(type + "add");
	}

	public void emit_sub(String type) {
		appendCodeLine(type + "sub");
	}

	public void emit_optimize_int(int value) {
		if (value >= 0 && value <= 5)
			emit_iconst(value);
		else if (value == -1)
			appendCodeLine("iconst_m1");
		else if (value >= -128 && value <= 127)
			emit_bint(value);
		else if (value >= -32768 && value <= 32767)
			emit_sint(value);
		else
			appendCodeLine("ldc " + value);
	}

	public void emit_mul(String type) {
		appendCodeLine(type + "mul");
	}

	public void emit_div(String type) {
		appendCodeLine(type + "div");
	}

	public void emit_int_compare_not_equal(String label) {
		appendCodeLine("if_icmpne " + label);
	}

	public void emit_int_compare_less_or_equal(String label) {
		appendCodeLine("if_icmple " + label);
	}

	public void emit_int_compare_equal(String label) {
		appendCodeLine("if_icmpeq " + label);
	}

	public void emit_goto(String label) {
		appendCodeLine("goto " + label);
	}

	public void emit_isub() {
		appendCodeLine("isub");
	}

	public void emit_imul() {
		appendCodeLine("imul");
	}

	public void emit_idiv() {
		appendCodeLine("idiv");
	}

	public void emit_fadd() {
		appendCodeLine("fadd");
	}

	public void emit_fsub() {
		appendCodeLine("fsub");
	}

	public void emit_fmul() {
		appendCodeLine("fmul");
	}

	public void emit_fdiv() {
		appendCodeLine("fdiv");
	}

	public void emit_new(String className) {
		appendCodeLine("new " + className);
	}

	public void emit_duplicate() {
		appendCodeLine("dup");
	}

	public void emit_invokeSpecial(String params) {
		appendCodeLine("invokespecial " + params);
	}

	public void emit_aload(int n) {
		appendCodeLine("aload " + n);
	}

	public void emit_astore(int n) {
		appendCodeLine("astore " + n);
	}

	public void emit_putField(String field, String descriptor) {
		appendCodeLine("putfield " + field + " " + descriptor);
	}

	public void emit_comment(String msg) {
		appendCodeLine("; " + msg);
	}

	public void emit_blank() {
		appendCodeLine("");
	}

	public void emit_getField(String field, String descriptor) {
		appendCodeLine("getfield " + field + " " + descriptor);
	}

	public void emit_ineg() {
		appendCodeLine("ineg");
	}

	public void emit_float(float value) {
		appendCodeLine("ldc " + value);
	}

	public void emit_optimize_float(float value) {
		if (value == 0 || value == 1 || value == 2) {
			appendCodeLine("fconst_" + (int) value);
		} else
			appendCodeLine("ldc " + value);
	}

	public void emit_ixor() {
		appendCodeLine("ixor");
	}

	public void emit_label(String name) {
		appendCodeLine(name + ":");
	}

	public void emit_int_to_float() {
		emit_convert("i", "f");
	}

	public void emit_float_to_int() {
		emit_convert("f", "i");
	}

	public void emit_float_compare() {
		appendCodeLine("fcmpl");
	}

	public void emit_if_not_equal(String label) {
		appendCodeLine("ifne " + label);
	}

	public void emit_if_less_equal(String label) {
		appendCodeLine("ifle " + label);
	}

	public void emit_if_equal(String label) {
		appendCodeLine("ifeq " + label);
	}

	public void emit_convert(String type1, String type2) {
		appendCodeLine(type1 + "2" + type2);
	}

	public void emit_println(String jvmType) {
		appendCodeLine("; convert to String;");
		appendCodeLine("invokestatic java/lang/String/valueOf(" + jvmType + ")Ljava/lang/String;");
		appendCodeLine("; call println");
		appendCodeLine("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
	}

	public void emit_if_greater_equal(String label) {
		appendCodeLine("ifge " + label);
	}

	public void emit_int_compare_greater_or_equal(String label) {
		appendCodeLine("if_icmpge " + label);
	}

	public void emit_if_less(String label) {
		appendCodeLine("ifle " + label);
	}

	public void emit_int_compare_less(String label) {
		appendCodeLine("if_icmplt " + label);
	}

	public void emit_if_greater(String label) {
		appendCodeLine("ifgt " + label);
	}

	public void emit_int_compare_greater(String label) {
		appendCodeLine("if_icmpgt " + label);
	}

	public void emit_iand() {
		appendCodeLine("iand");
	}

	public void emit_ior() {
		appendCodeLine("ior");
	}
}
