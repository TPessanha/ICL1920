package values;

import nodes.ASTNode;
import state.Environment;
import state.Parameter;

import java.util.List;

public class Closure {
	List<Parameter> parameters;
	Environment environment;
	ASTNode body;

	public Closure(List<Parameter> parameters, Environment environment, ASTNode body) {
		this.parameters = parameters;
		this.environment = environment;
		this.body = body;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public ASTNode getBody() {
		return body;
	}
}
