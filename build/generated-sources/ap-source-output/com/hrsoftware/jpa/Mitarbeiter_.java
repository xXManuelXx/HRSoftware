package com.hrsoftware.jpa;

import com.hrsoftware.jpa.Abteilung;
import com.hrsoftware.jpa.Arbeitszeit;
import com.hrsoftware.jpa.Gehalt;
import com.hrsoftware.jpa.Info;
import com.hrsoftware.jpa.Krankheit;
import com.hrsoftware.jpa.Land;
import com.hrsoftware.jpa.MaHistorie;
import com.hrsoftware.jpa.MaSchulung;
import com.hrsoftware.jpa.Ort;
import com.hrsoftware.jpa.Position;
import com.hrsoftware.jpa.Skills;
import com.hrsoftware.jpa.Urlaub;
import com.hrsoftware.jpa.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-12T18:06:17")
@StaticMetamodel(Mitarbeiter.class)
public class Mitarbeiter_ { 

    public static volatile CollectionAttribute<Mitarbeiter, MaSchulung> maSchulungCollection;
    public static volatile SingularAttribute<Mitarbeiter, Integer> steuerklasse;
    public static volatile SingularAttribute<Mitarbeiter, Info> infoId;
    public static volatile SingularAttribute<Mitarbeiter, String> vorname;
    public static volatile SingularAttribute<Mitarbeiter, Gehalt> idGehalt;
    public static volatile SingularAttribute<Mitarbeiter, Position> positionsId;
    public static volatile CollectionAttribute<Mitarbeiter, Skills> skillsCollection;
    public static volatile SingularAttribute<Mitarbeiter, User> idUser;
    public static volatile SingularAttribute<Mitarbeiter, Abteilung> abteilungsId;
    public static volatile SingularAttribute<Mitarbeiter, Date> geburtsdatum;
    public static volatile SingularAttribute<Mitarbeiter, Integer> urlaubstage;
    public static volatile SingularAttribute<Mitarbeiter, String> nachname;
    public static volatile SingularAttribute<Mitarbeiter, Land> land;
    public static volatile SingularAttribute<Mitarbeiter, Arbeitszeit> arbeitszeit;
    public static volatile SingularAttribute<Mitarbeiter, Krankheit> krankheit;
    public static volatile SingularAttribute<Mitarbeiter, Integer> id;
    public static volatile SingularAttribute<Mitarbeiter, Ort> plz;
    public static volatile SingularAttribute<Mitarbeiter, MaHistorie> maHistorie;
    public static volatile SingularAttribute<Mitarbeiter, Urlaub> urlaub;

}