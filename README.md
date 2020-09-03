# ICL1920_41774_44292
This is a basic programming language with an interpreter and a compiler.
Made for the ICL (Interpretação e Compilação de Linguagens) course at FCT 1920.

Tomas Pessanha - 41774

Gonçalo Feliciano - 44292

## Main

### Interpreter:
Runs the interpreter, and needs no arguments

### Compiler:
Runs the compiler that takes code in 'ICL Language' and produces jasmin files (*.j)

**Args:**
> usage: \[-a\] \[-f \<filePath\>\]

**-a** : Also assembles the jasmin files into .class bytecode.  
**-f \<filePath\>** : Path to the file containing the code .
		
If run with no **-f** argument it runs in the console like the interpreter and complies the writen code.

### Assembler:

It has no arguments, just run it and it will assemble the jasmin files into bytecode files writing them into the default directory.

## Config
In the *config.propreties* file located in the resource folder you can configure the ouptput directories of the compiler and assembler

## In the IDE
This is a Maven project and Maven is used to facilitate the work of the programmer.

### Editing the grammar
You can edit the grammar of the language by editing the Grammer.jj file in the Grammar folder.  
After editing this file you can rebuild the parser with `mvn clean clean:clean process-resources -f pom.xml`  we suggest adding a run configuration for easy access.

### Included
Both jasmin and JavaCC are included in the project so you do not need to add them yourself.
