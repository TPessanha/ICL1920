package values;

import nodes.ASTNode;
import state.Environment;

import java.util.List;

public class Closure {
	List<String> parameters;
	Environment environment;
	ASTNode body;

	public Closure(List<String> parameters, Environment environment, ASTNode body) {
		this.parameters = parameters;
		this.environment = environment;
		this.body = body;
	}

	public List<String> getParameters() {
		return parameters;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public ASTNode getBody() {
		return body;
	}
}
