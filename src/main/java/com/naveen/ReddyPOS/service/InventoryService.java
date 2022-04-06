package com.naveen.ReddyPOS.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naveen.ReddyPOS.model.Products;

@Service
public class InventoryService {

	private static final Logger logger = LoggerFactory.getLogger(OrdersService.class);

	public List<Products> getInventory() {
		logger.info("Started Fetching Inventory");

		List<Products> list = getInventoryFromJsonFile();

		if (list != null && list.size() > 1) {
			logger.info("Finished Fetching Inventory");
		} else {
			logger.error("Error Occured while Fetching Inventory");
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	private List<Products> getInventoryFromJsonFile() {

		List<Products> list = new ArrayList<Products>();

		try {
			JSONParser jsonParser = new JSONParser();

			ClassLoader classLoader = getClass().getClassLoader();

			JSONObject jsonObject = (JSONObject) jsonParser
					.parse(new FileReader(classLoader.getResource("Inventory.json").getFile()));

			JSONArray productsJsonArray = (JSONArray) jsonObject.get("products");

			ObjectMapper mapper = new ObjectMapper();

			list = mapper.readValue(productsJsonArray.toString(), List.class);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;
	}

}
