package compiler;

import exceptions.UndeclaredException;
import state.Environment;

import java.io.IOException;
import java.util.Collection;

import static utils.PropertiesUtils.getCompiledPath;

public class CompilerEnvironment extends Environment<IdentifierDetails> {
	private int level;
	private int SL;

	public CompilerEnvironment() throws IOException {
		super(true);
		level = 0;
		SL = 4;
		StackFrameFile stackFrameFile = new StackFrameFile(this);
		stackFrameFile.dump(getCompiledPath());
	}

	public CompilerEnvironment(CompilerEnvironment parent) throws IOException {
		super(parent, true);
		SL = 4;
		level = ((CompilerEnvironment) this.getParent()).level + 1;
	}

	public Collection<IdentifierDetails> getValues() {
		return declarations.values();
	}

	public IdentifierDetails find(String identifier) throws UndeclaredException {
		return super.find(identifier);
	}

	@Override
	public CompilerEnvironment beginScope() throws IOException {
		return new CompilerEnvironment(this);
	}

	public int getLevel() {
		return level;
	}

	public int getSL() {
		return SL;
	}
}
