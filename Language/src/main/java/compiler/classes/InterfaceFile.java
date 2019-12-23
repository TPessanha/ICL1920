package compiler.classes;

import compiler.CodeBlock;
import compiler.IdentifierDetails;

import java.util.List;

public class InterfaceFile {
	protected CodeBlock code;
	protected String className;
	protected String superName;
	protected List<IdentifierDetails> fields;
}
