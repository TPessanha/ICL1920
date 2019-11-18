package compiler;

import compiler.classes.StackFrame;
import state.Environment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompilerEnvironment extends Environment<IdentifierDetails> {
	private int level;
	private int SL;
	private StackFrame frame;

	public CompilerEnvironment(int SL) throws IOException {
		super(true);
		level = 0;
		this.SL = SL;
		frame = new StackFrame(this);
		Compiler.addClassFile(frame);
	}

	public CompilerEnvironment(CompilerEnvironment parent) throws IOException {
		super(parent, true);
		this.SL = parent.getSL();
		level = ((CompilerEnvironment) this.getParent()).level + 1;
		frame = new StackFrame(this,parent.frame);
	}

	public List<IdentifierDetails> getValues() {
		return new ArrayList<>(declarations.values());
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

	public StackFrame getFrame() {
		return frame;
	}
}
