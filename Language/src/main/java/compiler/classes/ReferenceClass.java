package compiler.classes;

import compiler.IdentifierDetails;
import types.ReferenceType;

import java.util.Collections;

public class ReferenceClass extends ClassFile {
	public ReferenceClass(ReferenceType type) {
		this(type, new IdentifierDetails("value", type.getReferenceType()));
	}

	public ReferenceClass(ReferenceType type, IdentifierDetails value) {
		super(
			type.getClassName(),
			Collections.singletonList(value)
		);
	}
}
