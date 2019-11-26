package values;

import nodes.ASTNode;
import state.Environment;

public class Closure {
	String id;
	Environment environment;
	ASTNode body;

	public Closure(String id, Environment environment, ASTNode body) {
		this.id = id;
		this.environment = environment;
		this.body = body;
	}

	public String getId() {
		return id;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public ASTNode getBody() {
		return body;
	}
}
