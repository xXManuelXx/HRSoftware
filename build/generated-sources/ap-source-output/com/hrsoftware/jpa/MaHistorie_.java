package com.hrsoftware.jpa;

import com.hrsoftware.jpa.Mitarbeiter;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-12T18:06:17")
@StaticMetamodel(MaHistorie.class)
public class MaHistorie_ { 

    public static volatile SingularAttribute<MaHistorie, Date> anfang;
    public static volatile SingularAttribute<MaHistorie, Date> ende;
    public static volatile SingularAttribute<MaHistorie, Integer> id;
    public static volatile SingularAttribute<MaHistorie, Mitarbeiter> maId;

}