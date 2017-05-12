package com.hrsoftware.jpa;

import com.hrsoftware.jpa.Mitarbeiter;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-12T18:06:17")
@StaticMetamodel(Krankheit.class)
public class Krankheit_ { 

    public static volatile SingularAttribute<Krankheit, Date> anfang;
    public static volatile SingularAttribute<Krankheit, Date> ende;
    public static volatile SingularAttribute<Krankheit, Integer> id;
    public static volatile SingularAttribute<Krankheit, Boolean> attest;
    public static volatile SingularAttribute<Krankheit, Mitarbeiter> maId;

}