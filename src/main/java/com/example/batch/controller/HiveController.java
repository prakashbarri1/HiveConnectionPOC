package com.example.batch.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.batch.services.HiveService;

@RestController
@RequestMapping("/hive")
public class HiveController {
	@Autowired
	private HiveService hiveService;

	@GetMapping(value = "/{schema}/tables")
	public ResponseEntity<List<Map<String, Object>>> getTablesForSchema(@PathVariable String schema) {
		List<Map<String, Object>> rows = hiveService.getTables(schema);
		return new ResponseEntity<>(rows, HttpStatus.OK);
	}

	@GetMapping(value = "/schemas")
	public ResponseEntity<List<Map<String, Object>>> getSchemas() {
		List<Map<String, Object>> rows = hiveService.getSchemas();
		return new ResponseEntity<>(rows, HttpStatus.OK);
	}

	@GetMapping("/{schema}/preview/{table}")
	public ResponseEntity<List<Map<String, Object>>> previewTable(@PathVariable String schema,
			@PathVariable String table) {
		List<Map<String, Object>> rows = hiveService.getTablePreview(schema, table);
		return new ResponseEntity<>(rows, HttpStatus.OK);
	}
}
