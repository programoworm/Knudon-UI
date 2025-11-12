package com.webapi.knudon.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
public class GreetingService {

	public String getGreeting(String name) {
		return String.format("Hello %s!", name);
	}

	// public String executeInterpreter(String input) throws IOException {
	// 	// Save input to a temporary file
	// 	Path tempFile = Files.createTempFile("input", ".k");
	// 	System.out.println("Temporary file created at: " + tempFile.toAbsolutePath()); // Print the file path
	// 	Files.write(tempFile, input.getBytes(), StandardOpenOption.WRITE);

	// 	// Run interpreter.exe with the temporary file
	// 	ProcessBuilder processBuilder = new ProcessBuilder(
	// 		"c:\\Users\\rbkum\\OneDrive\\Documents\\GithubRepositories\\Knudon2.0\\knudon.exe", 
	// 		tempFile.toAbsolutePath().toString()
	// 	);
	// 	processBuilder.redirectErrorStream(true);
	// 	Process process = processBuilder.start();

	// 	// Capture the output
	// 	StringBuilder output = new StringBuilder();
	// 	try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
	// 		String line;
	// 		while ((line = reader.readLine()) != null) {
	// 			System.out.println(line); // Print the output to the console
	// 			output.append(line).append(System.lineSeparator());
	// 		}
	// 	}

	// 	// Wait for the process to complete and delete the temporary file
	// 	try {
	// 		process.waitFor();
	// 	} catch (InterruptedException e) {
	// 		Thread.currentThread().interrupt();
	// 	}
	// 	// Files.delete(tempFile);

	// 	return output.toString().trim();
	// }

	public String executeInterpreter(String input) throws IOException {
		// Define the interpreter path inside the backend container
		String interpreterPath = "/app/interpreter/knudon";
		String inputFilePath = "/app/interpreter/code.k";
	
		// Save input to a temporary file inside the backend container
		Path tempFile = Paths.get(inputFilePath);
		Files.write(tempFile, input.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);

		System.out.println("Saved input file to: " + tempFile.toAbsolutePath());
	
		// Run the interpreter inside the backend container
		ProcessBuilder processBuilder = new ProcessBuilder(
			interpreterPath, inputFilePath
		);
		processBuilder.redirectErrorStream(true);
		Process process = processBuilder.start();
	
		// Capture the output
		StringBuilder output = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				output.append(line).append(System.lineSeparator());
			}
		}
	
		// Wait for the process to complete
		try {
			process.waitFor();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	
		return output.toString().trim();
	}
	

}
