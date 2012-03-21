package com.neuronrobotics.replicator.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public abstract class STLWriter {
	
	
	STLObject theSTL;
	
	public STLWriter(STLObject stl){
		theSTL = stl;
	}
	
	public void writeSTLToFile(File f) throws IOException{
		FileWriter fw = new FileWriter(f);
		writeHeader(fw);
		for (STLFacet fac:theSTL.getFacetIterable()) writeFacet(fac,fw);
		writeFooter(fw);
		fw.close();
	}
	
	public abstract void writeHeader(FileWriter fw) throws IOException;
	
	public abstract void writeFacet(STLFacet fac, FileWriter fw) throws IOException;
	
	public abstract void writeFooter(FileWriter fw) throws IOException;
		
	
}
