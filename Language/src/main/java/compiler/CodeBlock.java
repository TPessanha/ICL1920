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

	public void emit_isub() {
		appendCodeLine("isub");
	}

	public void emit_imul() {
		appendCodeLine("imul");
	}

	public void emit_idiv() {
		appendCodeLine("idiv");
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

	public void emit_ixor() {
		appendCodeLine("ixor");
	}

	public void emit_icomp(int mem) {
		appendCodeLine("if_icmpne");
	}

	public void emit_println(String jvmType) {
		appendCodeLine("; convert to String;");
		appendCodeLine("invokestatic java/lang/String/valueOf(" + jvmType + ")Ljava/lang/String;");
		appendCodeLine("; call println");
		appendCodeLine("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
	}
}
