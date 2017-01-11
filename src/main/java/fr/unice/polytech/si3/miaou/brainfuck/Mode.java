package fr.unice.polytech.si3.miaou.brainfuck;

import java.util.Optional;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

import fr.unice.polytech.si3.miaou.brainfuck.exceptions.ArgumentsException;

/**
 * The mode of execution of the current instance of the program.
 * @author Julien Lemaire
 * @author Pierre-Emmanuel Novac
 */
public enum Mode {
	RUN,
	REWRITE,
	TRANSLATE,
	CHECK,
	TRACE,
	GENERATE;

	/**
	 * EnumSet of conflicting modes (Run, Rewrite, Translate)
	 */
	private static final Set<Mode> conflicts = EnumSet.of(RUN, REWRITE, TRANSLATE, GENERATE);

	/**
	 * Returns an Optional of this enum value (a mode) whose name is the given String.
	 * Returns an empty Optional if no enum value matches.
	 *
	 * @param name	the name of the Mode.
	 * @return an Optional of the enum value matchinf the given name.
	 */
	public static Optional<Mode> of(String name) {
		return Arrays.stream(values()).filter(m -> m.name().equals(name)).findAny();
	}

	/**
	 * Add a mode to an EnumSet of modes while checking for conflicts between modes.
	 * Throws an ArgumentsException in case of conflict.
	 *
	 * @param modes	EnumSet to add the mode to.
	 * @param mode	the mode to add.
	 */
	public static void addTo(Set<Mode> modes, Mode mode) {
		modes.stream().filter(mode::conflictsWith).findAny()
		.ifPresent(conflict -> {
			if (conflict == Mode.RUN)
				modes.remove(Mode.RUN);
			else
				throw new ArgumentsException("Mode " + mode.name().toLowerCase() + " and " + conflict.name().toLowerCase() + " are conflicting.");
		});
		modes.add(mode);
	}

	/**
	 * Checks if this mode conflicts with the given one.
	 *
	 * @param mode	the mode to check conflict with
	 * @return true if the modes conflict.
	 */
	private boolean conflictsWith(Mode mode) {
		return conflicts.contains(this) && conflicts.contains(mode);
	}
}
