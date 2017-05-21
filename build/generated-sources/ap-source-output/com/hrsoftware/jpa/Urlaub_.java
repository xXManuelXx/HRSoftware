package com.hrsoftware.jpa;

import com.hrsoftware.jpa.Mitarbeiter;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-17T08:26:08")
@StaticMetamodel(Urlaub.class)
public class Urlaub_ { 

    public static volatile SingularAttribute<Urlaub, Date> anfang;
    public static volatile SingularAttribute<Urlaub, Date> ende;
    public static volatile SingularAttribute<Urlaub, Integer> id;
    public static volatile SingularAttribute<Urlaub, Mitarbeiter> maId;
    public static volatile SingularAttribute<Urlaub, String> beschreibung;

}