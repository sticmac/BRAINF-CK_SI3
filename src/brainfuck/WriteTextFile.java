package brainfuck;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.io.FileOutputStream;

import brainfuck.virtualmachine.Machine;

/**
 * Opens a file and provide a Stream to read the data from.
 *
 * @author Nassim Bounouas 
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html">Files</a>
 */
public class WriteTextFile {
	/**
	 * File's path as a Path object for encapsulation.
	 */
	private File file;

	private FileOutputStream fos;
	/**
	 * Constructs a file reader with the given file path and check if the file exists.
	 *
	 * @param filename	File's path.
	 */
	public WriteTextFile(String filename){
		this.file = new File(filename);
		if(!this.file.exists()){	
			try{
				this.file.createNewFile();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void write(String str){
		try{
			this.fos = new FileOutputStream(this.file, true);
			fos.write(str.getBytes());
			fos.write('\n');		
			fos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
