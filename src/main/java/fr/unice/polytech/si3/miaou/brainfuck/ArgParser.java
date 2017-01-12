package fr.unice.polytech.si3.miaou.brainfuck;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.EnumSet;
import java.util.Set;
import java.util.Arrays;
import java.util.Optional;

import fr.unice.polytech.si3.miaou.brainfuck.exceptions.ArgumentsException;

/**
 * Parser for the arguments passed through the call of the bfck executable.
 * It also stores the different parsed arguments into fields relative to their function.
 * e.g : if a filename is passed through bfck command, it is stored as a filename in this class.
 *
 * @author Julien Lemaire
 */
public class ArgParser {
	private static final List<String> fileArgs = Arrays.asList("-p", "-i", "-o", "--generate"); // Recognized filename options (program, in, out)
	private Map<String, String> paramValues;
	private Set<Mode> modes = EnumSet.of(Mode.RUN); //executing a file by default

	/**
	 * Main constructor of the <code>ArgParser</code> class.
	 * It takes an array of String, respresenting the arguments, and parse it to store them.
 	 *
	 * @param args Array of String containing all the arguments passed through the executable.
	 */
	public ArgParser(String[] args) {
		paramValues = new HashMap<>();

		//parsing arguments
		int i = 0;
		while (i < args.length) {
			Optional<Mode> mode = args[i].length() > 2 ? Mode.of(args[i].substring(2).toUpperCase()) : Optional.empty();

			mode.ifPresent(m -> Mode.addTo(modes, m));

			if (fileArgs.contains(args[i])) {
				paramValues.put(args[i], fetchNextArg(args, i));
				i++; // Skip filename
			} else if (!mode.isPresent()) {
				throw new ArgumentsException(args[i]+" is not a recognized option or argument.");
			}

			i++;
		}
	}

	private String fetchNextArg(String[] args, int i) {
		if (i+1 >= args.length || args[i+1].startsWith("-"))
			throw new ArgumentsException("No value for " + args[i] + " option.");
		return args[i+1];
	}

	/**
	 * Getter for the filename, if any.
	 *
	 * @return The filename if one was passed, else an empty string.
	 */
	public String getFilename() {
		String filename = paramValues.get("-p");
		if (filename == null || filename.isEmpty())
			throw new ArgumentsException("No program filename specified.");
		return filename;
	}

	/**
	 * Getter for the name of the input file.
	 *
	 * @return The name of the input file if one was passed, else null.
	 */
	public String getInput() {
		return paramValues.get("-i");
	}

	/**
	 * Getter for the name of the output file.
	 *
	 * @return The name of the output file if one was passed, else null.
	 */
	public String getOutput() {
		return paramValues.get("-o");
	}

	/**
	 * Getter for the language chosen.
	 *
	 * @return the name of the language to translate.
	 */
	public String getLanguage() {
		return paramValues.get("--generate");
	}

	/**
	 * Check if the given mode was activated.
	 *
	 * @param mode	mode to check for.
	 * @return true if the given mode is activated.
	 */
	public boolean isIn(Mode mode) {
		return modes.contains(mode);
	}

	/**
	 * Getter for the type of the file matching the filename entry.
	 * The file is treated as text by default.
	 *
	 * @return The type of the used file.
	 */
	public Type getType() {
		return getFilename().toLowerCase().endsWith(".bmp") ? Type.IMAGE : Type.TEXT;
	}
}
