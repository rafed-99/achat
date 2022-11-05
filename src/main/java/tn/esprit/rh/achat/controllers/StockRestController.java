package tn.esprit.rh.achat.controllers;


import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.rh.achat.dto.StockDTO;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.services.IStockService;

import java.util.List;

@RestController
@Api(tags = "Gestion des stocks")
@RequestMapping("/stock")
public class StockRestController {

	@Autowired
	IStockService stockService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/retrieve-all-stocks")
	@ResponseBody
	public List<Stock> getStocks() {
		return stockService.retrieveAllStocks();
	}


	@GetMapping("/retrieve-stock/{stock-id}")
	@ResponseBody
	public Stock retrieveStock(@PathVariable("stock-id") Long stockId) {
		return stockService.retrieveStock(stockId);
	}


	@PostMapping("/add-stock")
	@ResponseBody
	public Stock addStock(@RequestBody StockDTO s) {
		Stock persistentStock = modelMapper.map(s,  Stock.class);
		return  stockService.addStock( persistentStock);
	}


	@DeleteMapping("/remove-stock/{stock-id}")
	@ResponseBody
	public void removeStock(@PathVariable("stock-id") Long stockId) {
		stockService.deleteStock(stockId);
	}


	@PutMapping("/modify-stock")
	@ResponseBody
	public Stock modifyStock(@RequestBody StockDTO stock) {
		Stock persistentStock = modelMapper.map(stock,  Stock.class);
		return  stockService.updateStock( persistentStock);
	}

	/*
	 * Spring Scheduler : Comparer QteMin tolérée (à ne pa dépasser) avec
	 * Quantité du stock et afficher sur console la liste des produits inférieur
	 * au stock La fct schédulé doit obligatoirement etre sans paramètres et
	 * sans retour (void)
	 */

	 @Scheduled(fixedRate = 60000)
	 @Scheduled(fixedDelay = 60000)
	 @Scheduled(cron = "*/60 * * * * *")
	@GetMapping("/retrieveStatusStock")
	@ResponseBody
	public void retrieveStatusStock() {
		stockService.retrieveStatusStock();
	}

}