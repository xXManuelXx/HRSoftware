package com.hrsoftware.jpa;

import com.hrsoftware.jpa.MaSchulung;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-12T18:06:17")
@StaticMetamodel(Schulung.class)
public class Schulung_ { 

    public static volatile CollectionAttribute<Schulung, MaSchulung> maSchulungCollection;
    public static volatile SingularAttribute<Schulung, Long> kosten;
    public static volatile SingularAttribute<Schulung, Integer> id;
    public static volatile SingularAttribute<Schulung, String> beschreibung;

}