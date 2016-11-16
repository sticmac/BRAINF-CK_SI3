package brainfuck;

import java.util.Map;
import java.util.HashMap;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.List;

import brainfuck.exceptions.SyntaxMacroException;

public class MacroParser {
	private enum State {
		NO_MACRO, // Not defining a macro
		MACRO_NAME, // Next field is macro name
		MACRO_ARGS, // Next field is macro arguments
		MACRO_BODY // Next field is macro body
	}

	private class Macro {
		//private List<String> args;
		private List<String> body;

		Macro() {
			body = new ArrayList<>();
		}

		void addToBody(String line) {
			body.add(line);
		}

		List<String> getBody() {
			return body;
		}
	}

	private Map<String, Macro> macros;
	private Stream<String> prog;
	private State state = State.NO_MACRO;

	String macroName;
	Macro macro;

	public MacroParser(Stream<String> stream) {
		this.macros = new HashMap<>();
		this.prog = stream;
	}

	public Stream<String> parse() {
		return prog.flatMap(line -> {
			State prev_state = state;

			// MACRO_ARGS not implemented for now
			if ("DEFINE".equals(line)) {
				if (state == State.NO_MACRO) {
					state = State.MACRO_NAME;
					macro = new Macro();
					return Stream.empty();
				} else {
					throw new SyntaxMacroException("Defining a Macro while not finishing defining a previous one.");
				}
			} else if ("AS".equals(line)) {
				if (state == State.MACRO_NAME) {
					state = State.MACRO_BODY;
					return Stream.empty();
				} else {
					throw new SyntaxMacroException("AS without a previous DEFINE.");
				}
			} else if ("END".equals(line)) {
				if (state == State.MACRO_NAME) {
					state = State.NO_MACRO;
					macros.put(macroName, macro);
					return Stream.empty();
				} else {
					throw new SyntaxMacroException("END without a previous AS.");
				}
			}

			if (state == State.MACRO_NAME) macroName = line;
			if (state == State.MACRO_BODY) macro.addToBody(line);

			if (state == State.NO_MACRO /*|| state == MACRO_BODY // uncomment that for recursive expansion*/) {
				if (macros.containsKey(line)) { // We've got a macro right there, try to replace it. Naive check so you can be evil and declare a "+" macro.
					return macros.get(line).getBody().stream();
				} else {
					return Stream.of(line);
				}
			}
			return Stream.empty();
		});
	}
}
