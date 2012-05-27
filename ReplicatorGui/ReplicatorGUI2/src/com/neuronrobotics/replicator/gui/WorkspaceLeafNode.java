package com.neuronrobotics.replicator.gui;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class WorkspaceLeafNode implements MutableTreeNode{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 883748291178354077L;
	
	private File theSTL, theGCode;
	
	private WorkspaceFolderNode parent;
		
	public WorkspaceLeafNode(WorkspaceFolderNode p,File theSTL, File theGCode){
		this.parent = p;
		if(this.parent!=null) parent.insert(this);
		this.theSTL = theSTL;
		this.theGCode = theGCode;
	}
	
	public File getTheSTLFile() {
		return theSTL;
	}

	public File getTheGCodeFile() {
		return theGCode;
	}
	
	/*
	public STLPreview getPreview(){
		return object.getPreview();
	}
*/
	public String toString(){
		return theSTL.getName();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Enumeration<TreeNode> children() {
		return null;
		/*return new Enumeration<TreeNode>(){

			@Override
			public boolean hasMoreElements() {
				return false;
			}

			@Override
			public TreeNode nextElement() {
				return null;
			}};*/
	}

	@Override
	public boolean getAllowsChildren() {
		return false;
	}

	@Override
	public TreeNode getChildAt(int arg0) {
		return null;
	}

	@Override
	public int getChildCount() {
		return 0;
	}

	@Override
	public int getIndex(TreeNode arg0) {
		return -1;
	}

	@Override
	public TreeNode getParent() {
		return parent;
	}

	@Override
	public boolean isLeaf() {
		return true;
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		//nothing
		
	}

	@Override
	public void remove(int index) {
		//nothing		
	}

	@Override
	public void remove(MutableTreeNode node) {
		//do nothing		
	}

	@Override
	public void removeFromParent() {
		this.parent = null;		
	}

	@Override
	public void setParent(MutableTreeNode newParent) {
		this.parent = (WorkspaceFolderNode)newParent;		
	}
	
	public void setParent(WorkspaceFolderNode newParent){
		this.parent = newParent;
	}

	@Override
	public void setUserObject(Object object) {
		//nothing
		
	}
	
}
