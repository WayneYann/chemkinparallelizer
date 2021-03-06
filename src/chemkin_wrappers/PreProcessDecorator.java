package chemkin_wrappers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import util.ChemkinConstants;
import util.Paths;

/**
 * Decorator for {@link AbstractChemkinRoutine} that calls the routine "CKPreProcess" of Chemkin.
 * 
 * This Preprocessor checks whether the chemistry input file is error-free and writes diagnostics (.out) and 
 * link file (.asc).
 * 
 * The Preprocessor takes a file "CKPreprocess.input" as an argument with pointers to chemisty input file and 
 * filenames for output files.
 * @author Nick
 *
 */
public class PreProcessDecorator extends ChemkinRoutineDecorator {


	public PreProcessDecorator(AbstractChemkinRoutine routine){
		super.routine = routine;
	}

	@Override
	public String[] getKeyword() {
		routine.keywords = new String [3];
		routine.keywords[0] = Paths.getBinDirLocation()+"CKPreProcess";
		routine.keywords[1] = "-i";
		routine.keywords[2] = Paths.getWorkingDir()+ChemkinConstants.PREPROCESSINPUT;

		return routine.keywords;
	}

	@Override
	public void executeCKRoutine() {
		this.getKeyword();
		writeCKPreProcessInput();
		routine.executeCKRoutine();

	}
	/**
	 * Creates and writes a CKPreprocess.input file with directions to chemistry input file (chem.inp) 
	 * and output/link files of chemistry and transport.
	 * @param out TODO
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void writeCKPreProcessInput(){

		PrintWriter out;
		try {
			out = new PrintWriter(new FileWriter(Paths.getWorkingDir()+ChemkinConstants.PREPROCESSINPUT));	
			out.println("IN_CHEM_INPUT="+Paths.getWorkingDir()+Paths.chemistryInput);

			//chemistry output, link and species list:
			out.println("OUT_CHEM_OUTPUT="+Paths.getWorkingDir()+ChemkinConstants.CHEMOUT);
			out.println("OUT_CHEM_ASC="+Paths.getWorkingDir()+ChemkinConstants.CHEMASC);
			out.println("OUT_CHEM_SPECIES="+Paths.getWorkingDir()+ChemkinConstants.CHEMASU);

			//transport link files and log file:
			out.println("FIT_TRANSPORT_PROPERTIES=1");
			out.println("OUT_TRAN_OUTPUT="+Paths.getWorkingDir()+ChemkinConstants.TRANOUT);
			out.println("OUT_TRAN_ASC="+Paths.getWorkingDir()+ChemkinConstants.TRANASC);

			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
