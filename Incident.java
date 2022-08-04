package com.gpch.login.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "incident")
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "inc_id")
    private int id;
    
    @Column(name = "iname")
    @NotEmpty(message = "*Please provide an service name")
    private String iname;
    
    @Column(name = "itype")
    @NotEmpty(message = "*Please provide your Service Type")
    private String itype;
    
    @Column(name = "icontact")
    @NotEmpty(message = "*Please provide your Contact No")
    private String icontact;
    
    @Column(name = "iaddr")
    @NotEmpty(message = "*Please provide your address")
    private String iaddr;    
}
