package com.manycode.app.controller;

import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class ErrorMensaje {
	private String code;
	private List<Map<String, String>> messages;
}
