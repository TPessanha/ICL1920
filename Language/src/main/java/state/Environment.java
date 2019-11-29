package state;

import exceptions.DuplicatedIdentifierException;
import exceptions.UndeclaredException;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description:
 */
public class Environment<T> {
	private Environment<T> parent;
	protected Map<String, T> declarations;
	private boolean compiler;

	public Environment(){
		this(false);
	}

	public Environment(boolean compiler) {
		parent = null;
		this.compiler = compiler;
		if (compiler)
			declarations = new LinkedHashMap<>();
		else
			declarations = new HashMap<>();
	}

	protected Environment(Environment<T> parent, boolean compiler) {
		this(compiler);
		this.parent = parent;
	}

	public T find(String identifier) throws UndeclaredException {
		T value = declarations.get(identifier);
		if (value != null)
			return value;

		Environment<T> scope = parent;
		while (scope != null) {
			if (scope.declarations.containsKey(identifier))
				return scope.declarations.get(identifier);
			scope = scope.parent;
		}

		throw new UndeclaredException("Variable '" + identifier + "' is not declared");
	}

	public Environment<T> beginScope() throws IOException {
		return new Environment<>(this,compiler);
	}

	public Environment<T> endScope() {
		return parent;
	}

	public void associate(String identifier, T value) throws DuplicatedIdentifierException {
		if (declarations.containsKey(identifier))
			throw new DuplicatedIdentifierException("Variable '" + identifier + "' is already declared in the scope");

		declarations.put(identifier, value);
	}

//	public T assign(String identifier, T value) throws UndeclaredException {
//		Environment<T> scope = this;
//		do {
//			if (scope.declarations.containsKey(identifier)) {
//				scope.declarations.put(identifier, value);
//				return value;
//			}
//			scope = scope.parent;
//		} while (scope != null);
//
//		throw new UndeclaredException("Variable '" + identifier + "' is not declared");
//	}

	public Environment<T> getParent() {
		return parent;
	}

}
