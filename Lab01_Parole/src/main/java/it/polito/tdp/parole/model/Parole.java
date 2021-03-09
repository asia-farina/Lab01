package it.polito.tdp.parole.model;

import java.util.*;

public class Parole  {
	LinkedList<String> elenco=new LinkedList<String>();
		
	public Parole() {
		//TODO
	}
	
	public void addParola(String p) {
		//TODO
		elenco.add(p);
	}
	
	public List<String> getElenco() {
		//TODO
		return elenco;
	}
	
	public void reset() {
		// TODO
		elenco.clear();
	}
	
	public boolean delete (String p) {
		for (String s:elenco)
		{
			if (s.equals(p))
			{
				elenco.remove(p);
				return true;
			}
		}
		return false; 
	}
}
