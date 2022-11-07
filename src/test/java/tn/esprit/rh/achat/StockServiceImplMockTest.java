package tn.esprit.rh.achat;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.IStockService;
import tn.esprit.rh.achat.services.StockServiceImpl;

import java.util.*;


import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;


@SpringBootTest(classes =StockServiceImplMockTest.class)
@ExtendWith(MockitoExtension.class)
class StockServiceImplMockTest {
    @Mock
    StockRepository stockRepository;

    @InjectMocks
    IStockService stockService = new StockServiceImpl ();






    Stock stock = new Stock("f1", 1,2);
    List<Stock> listStocks = new ArrayList<Stock>() {
        {
            add(new Stock("f1aa", 2,4));
            add(new Stock("f1f", 4,7));
        }
    };


    @Test
    void testRetrieveProduitByid() {

        when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(stock));
        Stock stockq = stockService.retrieveStock(1L);

        System.out.println(stockq);
        Assertions.assertNotNull(stockq);
    }
    @Test
    void testRetrieveAllProduit() {

        List<Stock> stocks = new ArrayList();
        stocks.add(new Stock());
        when(stockRepository.findAll()).thenReturn(stocks);
        List<Stock> expected = stockService.retrieveAllStocks();
        Assertions.assertEquals(expected, stocks);
        verify(stockRepository).findAll();

    }



    @Test
    void testCreateNewObject() {
        Stock obj = new Stock("new", 2,3);


        when(stockRepository.save(isA(Stock.class))).thenAnswer(invocation -> (Stock) invocation.getArguments()[0]);
        Stock returnedObj = stockService.addStock(obj);
        ArgumentCaptor<Stock> savedObjectArgument = ArgumentCaptor.forClass(Stock.class);
        verify(stockRepository, times(1)).save(savedObjectArgument.capture());
        verifyNoMoreInteractions(stockRepository);

        Stock savedRestObject = savedObjectArgument.getValue();
        Assertions.assertNotNull(savedRestObject);

    }

    @Test
    void testDeleteObject() {
        Stock produite = new Stock();
        produite.setLibelleStock("new test");
        produite.setIdStock(1L);
        when(stockRepository.findById(produite.getIdStock())).thenReturn(Optional.of(produite));
        Stock stockq = stockService.retrieveStock(1L);
        stockService.deleteStock(stockq.getIdStock());
        verify(stockRepository).deleteById(stockq.getIdStock());
    }

}
