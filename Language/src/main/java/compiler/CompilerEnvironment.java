package compiler;

import state.Environment;

import java.io.IOException;
import java.util.Collection;

import static utils.PropertiesUtils.getCompiledPath;

public class CompilerEnvironment extends Environment<IdentifierDetails> {
	private int level;
	private int SL = 4;

	public CompilerEnvironment() throws IOException {
		super(true);
		level = 0;
		StackFrameFile stackFrameFile = new StackFrameFile(this);
		stackFrameFile.dump(getCompiledPath());
	}

	public CompilerEnvironment(CompilerEnvironment parent) {
		super(parent, true);
		level = ((CompilerEnvironment) this.getParent()).level + 1;
	}

	public Collection<IdentifierDetails> getValues() {
		return declarations.values();
	}

	@Override
	public CompilerEnvironment beginScope() {
		return new CompilerEnvironment(this);
	}

	public int getLevel() {
		return level;
	}

	public int getSL() {
		return SL;
	}
}
