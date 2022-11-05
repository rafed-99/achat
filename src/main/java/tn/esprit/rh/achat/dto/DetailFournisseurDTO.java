package tn.esprit.rh.achat.dto;

import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class DetailFournisseurDTO {
    private Long idDetailFournisseur;
    private String email;
    @Temporal(TemporalType.DATE)
    private Date dateDebutCollaboration;
    private String adresse;
    private String matricule;
}
