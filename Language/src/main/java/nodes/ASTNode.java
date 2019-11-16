package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import state.Environment;
import types.IType;
import values.IValue;

public interface ASTNode {
    IValue<?> eval(Environment<IValue<?>> environment) throws Exception;

    CodeBlock compile(CompilerEnvironment environment) throws Exception;

    IType typecheck(Environment<IType> environment) throws Exception;
}
