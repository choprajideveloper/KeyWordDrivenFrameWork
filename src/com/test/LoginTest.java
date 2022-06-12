package com.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.Engine.KeywordEngine;

public class LoginTest {

	public KeywordEngine engine;

	@Test

	public void LoginTestScenrio() throws IOException {

		engine = new KeywordEngine();

		engine.StartExecution("login");

	}
	
	@Test

	public void SignUpTest() throws IOException {

		engine = new KeywordEngine();

		engine.StartExecution("signup");

	}
}
