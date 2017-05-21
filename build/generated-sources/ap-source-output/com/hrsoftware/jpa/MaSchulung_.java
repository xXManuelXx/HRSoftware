package com.hrsoftware.jpa;

import com.hrsoftware.jpa.MaSchulungPK;
import com.hrsoftware.jpa.Mitarbeiter;
import com.hrsoftware.jpa.Schulung;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-17T08:26:08")
@StaticMetamodel(MaSchulung.class)
public class MaSchulung_ { 

    public static volatile SingularAttribute<MaSchulung, Mitarbeiter> mitarbeiter;
    public static volatile SingularAttribute<MaSchulung, Schulung> schulung;
    public static volatile SingularAttribute<MaSchulung, Boolean> abgeschlossen;
    public static volatile SingularAttribute<MaSchulung, MaSchulungPK> maSchulungPK;

}