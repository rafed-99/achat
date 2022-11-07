package tn.esprit.rh.achat;

import static org.junit.Assert.*;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.services.IOperateurService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j

public class OperateurServiceImplTest {
    @Autowired
    IOperateurService operateurService;

    @Test
    @Order(1)
    public void testRetrieveAllOperateurs() {
        List<Operateur> clients = operateurService.retrieveAllOperateurs();
        int expected = clients.size();
        Operateur o = new Operateur("drissi", "omar", "pwd");
        Operateur operateur= operateurService.addOperateur(o);
        assertEquals(expected + 1, operateurService.retrieveAllOperateurs().size());
        operateurService.deleteOperateur(operateur.getIdOperateur());
    }


    @Test
    @Order(2)
    public void testAddOperateur(){
        Operateur o = new Operateur("drissi","omar","pwd");
        Operateur operateur = operateurService.addOperateur(o);
        log.info("operateur " + operateur);
        assertNotNull(operateur.getIdOperateur());
        assertTrue(operateur.getNom().length() > 0);
        operateurService.deleteOperateur(operateur.getIdOperateur());
    }

    @Order(3)
    @Test
    public void testUpdateOperateur(){
        Operateur o = new Operateur("drissi","omar","pwddddddd");
        Operateur operateurUpdated = operateurService.updateOperateur(o);
        Assertions.assertEquals(o.getPassword(),operateurUpdated.getPassword());
    }


    @Test
    @Order(5)
    public void testDeleteOperateur(){
        Operateur o = new Operateur("drissi", "omar", "pwd");
        Operateur  operateur = operateurService.addOperateur(o);
        operateurService.deleteOperateur( operateur.getIdOperateur());
        assertNull(operateurService.retrieveOperateur(operateur.getIdOperateur()));
    }

}
