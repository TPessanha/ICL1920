package nodes;

import compiler.CodeBlock;
import compiler.CompilerEnvironment;
import compiler.IdentifierDetails;
import exceptions.UndeclaredException;
import state.Environment;
import types.IType;
import values.Value;

public interface ASTNode {
    Value<?> eval(Environment<Value<?>> environment) throws Exception;

    CodeBlock compile(CompilerEnvironment environment) throws Exception;

    IType typecheck() throws Exception;
}
