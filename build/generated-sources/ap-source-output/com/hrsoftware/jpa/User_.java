package com.hrsoftware.jpa;

import com.hrsoftware.jpa.Mitarbeiter;
import com.hrsoftware.jpa.Permission;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-12T18:06:17")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Mitarbeiter> mitarbeiter;
    public static volatile SingularAttribute<User, Permission> permissionId;
    public static volatile SingularAttribute<User, String> passwort;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> username;

}