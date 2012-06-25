package com.neuronrobotics.replicator.gui.stl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

import javax.media.j3d.*;
import javax.vecmath.*;

public class STLObject implements Iterable<STLFacet> {

	private String name;
	private ArrayList<STLFacet> theFacets;

	private Point3f center;
	private Point3f min, max;

	private ArrayList<STLFace> theFaces;
	private boolean facesAnalyzed;

	/*
	 * public STLObject(String name, ArrayList<STLFacet> facets, Point3f center,
	 * Point3f min, Point3f max) { this.name = name; this.theFacets=facets;
	 * this.center = center; this.min = min; this.max = max; this.theFaces = new
	 * ArrayList<STLFace>(); }
	 */

	public STLObject(String name, ArrayList<STLFacet> facets) {
		this.name = name;
		this.theFacets = facets;
		this.center = null;
		this.min = null;
		this.max = null;
		this.theFaces = new ArrayList<STLFace>();
		// this.calculateFaces(); //TODO take out after testing
	}

	public Iterable<STLFacet> getFacetIterable() {
		return (Iterable<STLFacet>) theFacets;
	}

	public int getFacetAmount() {
		return theFacets.size();
	}

	public Point3f getCenter() {
		if (center != null)
			return center;
		int numVertices = theFacets.size() * 3;
		float xSum = 0, ySum = 0, zSum = 0;
		for (STLFacet sf : theFacets) {
			xSum += sf.getVertex1().x / numVertices;
			xSum += sf.getVertex2().x / numVertices;
			xSum += sf.getVertex3().x / numVertices;
			ySum += sf.getVertex1().y / numVertices;
			ySum += sf.getVertex2().y / numVertices;
			ySum += sf.getVertex3().y / numVertices;
			zSum += sf.getVertex1().z / numVertices;
			zSum += sf.getVertex2().z / numVertices;
			zSum += sf.getVertex3().z / numVertices;
		}
		center = new Point3f(xSum, ySum, zSum);
		return center;
	}

	public float getXDistance() {
		if (max == null || min == null)
			setMaxMin();
		return max.x - min.x;
	}

	public float getYDistance() {
		if (max == null || min == null)
			setMaxMin();
		return max.y - min.y;
	}

	public float getZDistance() {
		if (max == null || min == null)
			setMaxMin();
		return max.z - min.z;
	}

	public String getName() {
		return name;
	}

	public Point3f getMax() {
		if (max == null)
			setMaxMin();
		return max;
	}

	private void setMaxMin() {
		
		max = null;
		min = null;
		for (STLFacet fac : this) {

			Point3f candMax = fac.getMax();
			Point3f candMin = fac.getMin();
			if (max != null) {
				max.set(Math.max(candMax.x, max.x), Math.max(candMax.y, max.y),
						Math.max(candMax.z, max.z));
				min.set(Math.min(candMin.x, min.x), Math.min(candMin.y, min.y),
						Math.min(candMin.z, min.z));
			} else {
				max = new Point3f(candMax);
				min = new Point3f(candMin);
			}
		}

	}

	public Point3f getMin() {
		if (min == null)
			setMaxMin();
		return min;
	}

	@Override
	public String toString() {
		String res = "";
		res += "Name: " + this.name + "\n";
		return res;
	}

	@Override
	public Iterator<STLFacet> iterator() {
		return theFacets.iterator();
	}

	public Iterator<STLFace> getFaceIterator() {
		if (!facesAnalyzed)
			calculateFaces();
		return theFaces.iterator();
	}

	public STLObject getTransformedSTLObject(Transform3D tran) {

		String newName = this.name + "Transformed";

		ArrayList<STLFacet> newFacetList = new ArrayList<STLFacet>();
		for (STLFacet f : this) {
			Point3f temp1 = f.getVertex1();
			tran.transform(temp1);
			Point3f temp2 = f.getVertex2();
			tran.transform(temp2);
			Point3f temp3 = f.getVertex3();
			tran.transform(temp3);
			newFacetList.add(new STLFacet(temp1, temp2, temp3));
		}

		Point3f newCenter = this.center;
		tran.transform(newCenter);
		Point3f newMin = this.min;
		tran.transform(newMin);
		Point3f newMax = this.max;
		tran.transform(newMax);

		return new STLObject(newName, newFacetList);// ,newCenter,newMin,newMax);
	}

	private void calculateFaces() {
		theFaces = new ArrayList<STLFace>();
		theFaces.addAll(STLFace.getSTLFace(this));
		facesAnalyzed = true;
	}

	public Collection<? extends STLFace> getFaces() {
		if (!facesAnalyzed)
			calculateFaces();
		return theFaces;
	}

}