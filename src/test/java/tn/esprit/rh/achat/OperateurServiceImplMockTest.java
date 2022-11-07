package tn.esprit.rh.achat;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;

@SpringBootTest(classes =OperateurServiceImplMockTest.class)
@ExtendWith(MockitoExtension.class)

class OperateurServiceImplMockTest {
    @Mock
    OperateurRepository operateurRepo;
    @InjectMocks
    OperateurServiceImpl operateurService;


    Operateur user = new Operateur(1L,"Drissi","Omar","123");

    @SuppressWarnings("serial")
    ArrayList<Operateur> listOperateurs = new ArrayList<Operateur>() {
        {
            add(new Operateur(2L,"drissi", "ahmed", "456"));
            add(new Operateur(3L,"dri", "MOhamed", "789"));
        }
    };


    @Test
    @Order(4)
    void testRetrieveOperateur(){
        when(operateurRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(user));
        Operateur op1=operateurService.retrieveOperateur(1L);
        assertNotNull(op1);
    }
    @Test
    @Order(1)
    void testAddOperateur(){
        when(operateurRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(user));
        Operateur p =operateurService.retrieveOperateur(2L);
        Operateur u= operateurService.addOperateur(p);
        assertNotNull(u.getNom());
        assertNotNull(u.getPrenom());
        assertNotNull(u.getPassword());
    }
    @Test
    @Order(5)
    void testDeleteOperateur()  {
        Operateur op = new Operateur();
        op.setNom("drissi");
        op.setIdOperateur(1L);
        when(operateurRepo.findById(op.getIdOperateur())).thenReturn(Optional.of(op));
        Operateur o = operateurService.retrieveOperateur(1L);
        operateurService.deleteOperateur(o.getIdOperateur());
        verify(operateurRepo).deleteById(o.getIdOperateur());
    }
    @Test
    @Order(3)
    void testupdateOperateur( ) {
        when(operateurRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(user));
        Operateur pr =operateurService.retrieveOperateur(2L);
        pr.setPassword("pass");
        assertThat(pr.getPassword()).isEqualTo("pass");
    }

    @Test
    @Order(2)
    void  testretrieveAllOperateurs(){
        when(operateurRepo.findAll()).thenReturn(listOperateurs);
        List<Operateur> op =operateurService.retrieveAllOperateurs();
        assertNotNull(op);
    }
}
