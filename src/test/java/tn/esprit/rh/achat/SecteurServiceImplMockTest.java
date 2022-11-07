package tn.esprit.rh.achat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;
import tn.esprit.rh.achat.services.SecteurActiviteServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes =SecteurServiceImplMockTest.class)
@ExtendWith(MockitoExtension.class)

class SecteurServiceImplMockTest {
    @Mock
    SecteurActiviteRepository SecteurRepository;

    @InjectMocks
    SecteurActiviteServiceImpl SecteurService;

    @Test
    void testRetrieveSecteurActivite() {

        SecteurActivite s1 = new SecteurActivite(1L, "11111","BUREATIQUE",null);
        s1.setIdSecteurActivite(1L);

        when(SecteurRepository.findById(1L)).thenReturn(Optional.of(s1));
        SecteurService.retrieveSecteurActivite(1L);
        Assertions.assertNotNull(s1);



    }
    @Test
    void testAddSecteurActivite()
    {

        SecteurActivite s2 = new SecteurActivite(null,"22222","INFORMATIQUE",null);
        s2.setIdSecteurActivite(2L);

        SecteurService.addSecteurActivite(s2);
        verify(SecteurRepository, times(1)).save(s2);

    }
    @Test
    void testretrieveAllSecteurs()
    {
        List<SecteurActivite> SecteurList = new ArrayList<SecteurActivite>() {

            {
                add(new SecteurActivite(null,"33333","CASANIER",null));
                add(new SecteurActivite(null,"44444","ECOLIER",null));
                add(new SecteurActivite(null,"55555","AGRICOLE",null));
            }};
        when(SecteurService.retrieveAllSecteurActivite()).thenReturn(SecteurList);
        List<SecteurActivite> sList = SecteurService.retrieveAllSecteurActivite();
        assertEquals(3, sList.size());


    }

    @Test
    void testDeleteSecteur(){

        SecteurActivite se1 = new SecteurActivite (null,"33333","CASANIER",null);
        se1.setIdSecteurActivite(7L);

        Mockito.lenient().when(SecteurRepository.findById(se1.getIdSecteurActivite())).thenReturn(Optional.of(se1));

        SecteurService.deleteSecteurActivite(7L);
        verify(SecteurRepository).deleteById(se1.getIdSecteurActivite());



    }
}
