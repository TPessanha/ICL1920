package nodes.functions;

import compiler.CodeBlock;
import compiler.Compiler;
import compiler.CompilerEnvironment;
import compiler.IdentifierDetails;
import compiler.classes.ClosureClass;
import compiler.classes.ClosureInterface;
import compiler.classes.StackFrame;
import nodes.ASTExpression;
import nodes.ASTNode;
import state.Environment;
import state.Parameter;
import types.FunctionType;
import types.IType;
import values.FunctionValue;
import values.IValue;

import java.util.ArrayList;
import java.util.List;

public class ASTFunction extends ASTExpression {
	private final List<Parameter> parameters;
	private final ASTNode body;

	public ASTFunction(List<Parameter> parameters, ASTNode body) {
		this.parameters = parameters;
		this.body = body;
	}

	@Override
	public IValue<?> eval(Environment<IValue<?>> environment) throws Exception {
		return new FunctionValue(parameters, environment.beginScope(), body);
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		CompilerEnvironment localScope = environment.beginScope();

		localScope.setSL(parameters.size() + 2);

		for (int i = 0; i < this.parameters.size(); i++) {
			Parameter p = this.parameters.get(i);

			IdentifierDetails details = new IdentifierDetails("x_" + i, p.getType(),localScope.getLevel());
			localScope.associate(p.getIdentifier(), details);
			localScope.getFrame().addField(details);
		}

		StackFrame functionFrame = localScope.getFrame();
		Compiler.addClassFile(functionFrame);

		String name = "closure_" + functionFrame.getClassName();

		FunctionType type = (FunctionType) getType();
		ClosureInterface inter = new ClosureInterface(type);
		ClosureClass closure =
			new ClosureClass(name, inter, functionFrame, environment.getFrame(), type, body.compile(localScope));

		Compiler.addClassFile(inter);
		Compiler.addClassFile(closure);

		int SL = environment.getSL();
		String parentName = environment.getFrame().getClassName();

		CodeBlock code = new CodeBlock();
		code.emit_new(closure.getClassName());
		code.emit_duplicate();
		code.emit_invokeSpecial(closure.getClassName() + "/<init>()V");
		code.emit_duplicate();
		code.emit_aload(SL);
		code.emit_putField(closure.getClassName() + "/SL", "L" + parentName + ";");

		return code;
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		Environment<IType> local = environment.beginScope();

		List<IType> paramsType = new ArrayList<>();
		for (Parameter p : parameters) {
			paramsType.add(p.getType());
			local.associate(p.getIdentifier(), p.getType());
		}

		return setType(new FunctionType(body.typecheck(local), paramsType));
	}
}
