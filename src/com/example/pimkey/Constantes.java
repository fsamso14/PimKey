package com.example.pimkey;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Constantes {
	public static final String SETTINGS="settings";
	public static final String DOMAIN_PREF="domain preferences";
	public static final Set<String> DOMAIN_DEF = new HashSet<String>(Arrays.asList(new String[]{"Professional","Personnal","Hobby1","Hobby2",""})); 
	public static final String PROJECT_PREF="project preferences";
	public static final Set<String> PROJECT_DEF = new HashSet<String>(Arrays.asList(new String[]{"","Project 1","Project 2","Project 3"})); 
	public static final String DOC_PREF="document preferences";
	public static final Set<String> DOC_DEF = new HashSet<String>(Arrays.asList(new String[]{"","Contact","Doc","Text Note","Website"})); 
	public Constantes(){}
}
