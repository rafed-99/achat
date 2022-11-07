package tn.esprit.rh.achat;

import static org.junit.Assert.*;

import java.text.ParseException;

import java.util.List;


import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.services.IFournisseurService;

import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class FournisseurServiceImplTest {

    @Autowired
    IFournisseurService fournisseurService;

    @Test
    public void testAddFournisseur(){
        Fournisseur f = new Fournisseur("benyoussef", "achref");
        Fournisseur fournisseur = fournisseurService.addFournisseur(f);
        System.out.print("fournisseur " + fournisseur);
        assertNotNull(fournisseur.getIdFournisseur());
        assertTrue(fournisseur.getCode().length() > 0);
        fournisseurService.deleteFournisseur(fournisseur.getIdFournisseur());
    }


    @Test
    public void testDeleteFournisseur() {
        Fournisseur f = new Fournisseur("achref","benyoussef");
        Fournisseur fo = fournisseurService.addFournisseur(f);
        fournisseurService.deleteFournisseur(fo.getIdFournisseur());
        assertNull(fournisseurService.retrieveFournisseur(fo.getIdFournisseur()));
    }
    @Test
    public void testRetrieveAllFournisseurs() throws ParseException {
        List<Fournisseur> Fournisseurs = fournisseurService.retrieveAllFournisseurs();
        int expected =Fournisseurs.size();
        Fournisseur f = new Fournisseur("test", "test");
        Fournisseur fp= fournisseurService.addFournisseur(f);
        assertEquals(expected + 1, fournisseurService.retrieveAllFournisseurs().size());
        fournisseurService.deleteFournisseur(fp.getIdFournisseur());
    }
    @Test
    public void testUpdateFournisseur() {

        Fournisseur fournisseur = new Fournisseur("mohamed","dddd");
        Fournisseur f = fournisseurService.addFournisseur(fournisseur);
        f.setCode("ss");
        f.setLibelle("dd test");
        Fournisseur ufournisseur = fournisseurService.updateFournisseur(f);


        assertNotNull(ufournisseur.getIdFournisseur());
        assertSame("dd test", ufournisseur.getLibelle());

        fournisseurService.deleteFournisseur(f.getIdFournisseur());


    }
}
