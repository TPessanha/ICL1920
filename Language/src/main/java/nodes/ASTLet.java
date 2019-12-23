package nodes;

import compiler.CodeBlock;
import compiler.Compiler;
import compiler.CompilerEnvironment;
import compiler.IdentifierDetails;
import compiler.classes.StackFrame;
import exceptions.IncompatibleTypeException;
import state.Binding;
import state.Environment;
import types.IType;
import values.IValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 */
public class ASTLet extends ASTExpression {

	private final List<Binding> bindings;
	private final ASTNode body;

	public ASTLet(List<Binding> bindings, ASTNode body) {
		this.bindings = bindings;
		this.body = body;
	}

	@Override
	public IValue<?> eval(Environment<IValue<?>> env) throws Exception {
		Environment<IValue<?>> localScope = env.beginScope();

		for (Binding d : this.bindings)
			localScope.associate(d.getIdentifier(), d.getExpression().eval(localScope));

		IValue result = body.eval(localScope);
		localScope.endScope();

		return result;
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		CompilerEnvironment localScope = environment.beginScope();

		for (int i = 0; i < this.bindings.size(); i++) {
			Binding d = this.bindings.get(i);

			IdentifierDetails details =
				new IdentifierDetails("x_" + i, ((ASTNode) d.getExpression()).getType(), localScope.getLevel());
			localScope.associate(this.bindings.get(i).getIdentifier(), details);
			localScope.getFrame().addField(details);
		}

		Compiler.addClassFile(localScope.getFrame());

		CodeBlock code = new CodeBlock();
		int SL = environment.getSL();
		StackFrame frame = localScope.getFrame();
		String className = frame.getClassName();
		String parentName = environment.getFrame().getClassName();

		code.emit_comment("LET CODE START --------------------------------");
		code.emit_blank();

		//new Frame
		code.emit_comment("new Frame");
		code.emit_new(className);
		code.emit_duplicate();
		code.emit_invokeSpecial(className + "/<init>()V");
		code.emit_duplicate();
		code.emit_comment("store SL in new frame");
		code.emit_aload(SL);
		code.emit_putField(className + "/SL", "L" + parentName + ";");
		code.emit_comment("update SL");
		code.emit_astore(SL);
		code.emit_blank();

		//store vars
		code.tabify();

		List<IdentifierDetails> vars = new ArrayList<>(localScope.getValues());
		for (int i = 0; i < vars.size(); i++) {
			Binding binding = this.bindings.get(i);
			IdentifierDetails details = vars.get(i);

			code.emit_comment("Value of " + binding.getIdentifier());

			code.emit_aload(SL);
			code.append(binding.getExpression().compile(localScope));
			code.emit_putField(className + "/x_" + i, details.getType());

			code.emit_blank();
		}

		code.detabify();

		//Body

		code.emit_comment("Body code START --------------------------------");
		code.emit_blank();
		code.tabify();
		code.append(body.compile(localScope));
		code.detabify();
		code.emit_blank();
		code.emit_comment("Body code END --------------------------------");
		code.emit_blank();

		//finish
		code.emit_comment("Update the SL");
		code.emit_aload(SL);
		code.emit_getField(className + "/SL", "L" + parentName + ";");
		code.emit_astore(SL);
		code.emit_blank();

		code.emit_comment("LET CODE END --------------------------------");

		localScope.endScope();
		return code;
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		Environment<IType> localScope = environment.beginScope();

		for (Binding bind : this.bindings) {
//			if (((ASTNode) d.getExpression()).getType() == AnyType.value)
//				d.setType(d.getExpression().typecheck(localScope));
			localScope.associate(bind.getIdentifier(), bind.getType());
			IType bindType = bind.getExpression().typecheck(localScope);

			if (!bindType.equals(bindType.getType()))
				throw new IncompatibleTypeException(bind.getType(),bindType);
		}
		IType type = setType(body.typecheck(localScope));

		localScope.endScope();
		return type;
	}
}
