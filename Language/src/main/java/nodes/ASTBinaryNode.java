package nodes;

public abstract class ASTBinaryNode extends ASTNode {
	protected ASTNode lNode, rNode;

	public ASTBinaryNode(ASTNode lNode, ASTNode rNode) {
		this.lNode = lNode;
		this.rNode = rNode;
	}
}
