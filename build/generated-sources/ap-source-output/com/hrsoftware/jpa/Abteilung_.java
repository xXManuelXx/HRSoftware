package com.hrsoftware.jpa;

import com.hrsoftware.jpa.Mitarbeiter;
import com.hrsoftware.jpa.Raum;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-12T18:06:17")
@StaticMetamodel(Abteilung.class)
public class Abteilung_ { 

    public static volatile SingularAttribute<Abteilung, Mitarbeiter> mitarbeiter;
    public static volatile SingularAttribute<Abteilung, Raum> raum;
    public static volatile SingularAttribute<Abteilung, Integer> id;
    public static volatile SingularAttribute<Abteilung, String> beschreibung;

}