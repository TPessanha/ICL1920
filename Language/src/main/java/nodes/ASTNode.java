package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import exceptions.TypeMismatchException;
import state.Environment;
import types.IType;
import values.IValue;

public interface ASTNode {
    IValue<?> eval(Environment<IValue<?>> environment) throws Exception;

    CodeBlock compile(CompilerEnvironment environment) throws Exception;

    IType typecheck() throws TypeMismatchException;
}
