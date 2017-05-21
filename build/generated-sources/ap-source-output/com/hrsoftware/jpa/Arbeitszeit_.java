package com.hrsoftware.jpa;

import com.hrsoftware.jpa.Mitarbeiter;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-17T08:26:08")
@StaticMetamodel(Arbeitszeit.class)
public class Arbeitszeit_ { 

    public static volatile SingularAttribute<Arbeitszeit, Date> anfang;
    public static volatile SingularAttribute<Arbeitszeit, Date> ende;
    public static volatile SingularAttribute<Arbeitszeit, Integer> id;
    public static volatile SingularAttribute<Arbeitszeit, Mitarbeiter> maId;

}