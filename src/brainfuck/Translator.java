package brainfuck;

/**
 * Translate an instruction in an other form.
 * @author Miaou
 * @see Instruction
 */

 public class Translator {
   /**
 	 * Write the instruction with the long syntax.
   *
   * @param instr	instruction to translate.
 	 */
   public void toLongSyntax(Instruction instr) {
     System.out.print(instr.getName());
   }

   /**
 	 * Write the instruction in the short syntax.
 	 *
 	 * @param instr	instruction to translate.
 	 */
   public void toShortSyntax(Instruction instr) {
     System.out.print(instr.getSymbol());
   }

   /**
 	 * Write the instruction in a sequence of hexadecimal numbers.
 	 *
 	 * @param instr	instruction to translate.
 	 */
   public void toColor(Instruction instr) {
     System.out.print(String.format("%X", instr.getColor()));
   }
 }
