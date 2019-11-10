package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import compiler.IdentifierDetails;
import compiler.StackFrameFile;
import exceptions.TypeMismatchException;
import state.Declaration;
import state.Environment;
import types.IType;
import values.IValue;

import java.util.ArrayList;
import java.util.List;

import static utils.PropertiesUtils.getCompiledPath;

/**
 * Description:
 */
public class ASTLet implements ASTNode {

	private final List<Declaration> declarations;
	private final ASTNode body;

	public ASTLet(List<Declaration> declarations, ASTNode body) {
		this.declarations = declarations;
		this.body = body;
	}

	@Override
	public IValue<?> eval(Environment<IValue<?>> env) throws Exception {
		Environment<IValue<?>> localScope = env.beginScope();

		for (Declaration d : this.declarations)
			localScope.declareVariable(d.getIdentifier(), d.getExpression().eval(localScope));

		return body.eval(localScope);
	}

	@Override
	public CodeBlock compile(CompilerEnvironment environment) throws Exception {
		CompilerEnvironment newEnv = environment.beginScope();

		for (int i = 0; i < this.declarations.size(); i++) {
			Declaration d = this.declarations.get(i);

			IdentifierDetails details =
				new IdentifierDetails(d.getExpression().typecheck(new Environment<>()).getJVMType(), newEnv.getLevel(), "x_" + i);
			newEnv.declareVariable(this.declarations.get(i).getIdentifier(), details);
		}

		StackFrameFile stackFrameFile = new StackFrameFile(newEnv);
		stackFrameFile.dump(getCompiledPath());

		CodeBlock code = new CodeBlock();
		int SL = environment.getSL();
		int level = newEnv.getLevel();
		String className = "frame_" + level;
		String parentName = "frame_" + (level - 1);

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
		code.emit_putField(className + "/sl", "L" + parentName + ";");
		code.emit_comment("update SL");
		code.emit_astore(SL);
		code.emit_blank();

		//store vars
		List<IdentifierDetails> vars = new ArrayList<>(newEnv.getValues());
		for (int i = 0; i < vars.size(); i++) {
			Declaration declaration = this.declarations.get(i);
			IdentifierDetails details = vars.get(i);

			code.emit_comment("Value of " + declaration.getIdentifier());

			code.emit_aload(SL);
			code.appendCodeBlock(declaration.getExpression().compile(newEnv));
			code.emit_putField(className + "/x_" + i, details.getType());

			code.emit_blank();
		}

		//Body

		code.emit_comment("Body code START --------------------------------");
		code.emit_blank();
		code.tabify();
		code.appendCodeBlock(body.compile(newEnv));
		code.detabify();
		code.emit_blank();
		code.emit_comment("Body code END --------------------------------");
		code.emit_blank();

		//finish
		code.emit_comment("Update the SL");
		code.emit_aload(SL);
		code.emit_getField(className + "/sl", "L" + parentName + ";");
		code.emit_astore(SL);
		code.emit_blank();

		code.emit_comment("LET CODE END --------------------------------");

		return code;
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws Exception {
		Environment<IType> localScope = environment.beginScope();

		for (Declaration d : this.declarations)
			localScope.declareVariable(d.getIdentifier(),d.getExpression().typecheck(localScope));

		return body.typecheck(localScope);
	}
}
