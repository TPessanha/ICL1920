package nodes;

public abstract class ASTOperation extends ASTExpression {
	protected final String operator;

	public ASTOperation(String operator) {
		this.operator=operator;
	}
}
