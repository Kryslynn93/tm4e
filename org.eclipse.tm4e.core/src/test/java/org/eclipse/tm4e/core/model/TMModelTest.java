/**
 * Copyright (c) 2022 Sebastian Thomschke and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.tm4e.core.model;

import static org.eclipse.tm4e.core.registry.IGrammarSource.*;
import static org.junit.jupiter.api.Assertions.*;

import org.eclipse.tm4e.core.Data;
import org.eclipse.tm4e.core.registry.Registry;
import org.junit.jupiter.api.Test;

class TMModelTest {

	@Test
	void testTokenizeWithTimeout() {
		final var grammar = new Registry().addGrammar(fromResource(Data.class, "TypeScript.tmLanguage.json"));

		final var lines = """
				function addNumbers(a: number, b: number) { // 1
				    return a + b;                           // 2
				}                                           // 3
				const sum = addNumbers(10, 15);             // 4
				console.log('Sum is: ' + sum);              // 5
			""".split("\\r?\\n");
		assertEquals(5, lines.length);

		final var modelLines = new AbstractModelLines() {
			@Override
			public int getNumberOfLines() {
				return lines.length;
			}

			@Override
			public String getLineText(final int lineIndex) throws Exception {
				return lines[lineIndex];
			}

			@Override
			public int getLineLength(final int lineIndex) throws Exception {
				return lines[lineIndex].length();
			}

			@Override
			public void dispose() {
			}
		};
		for (int i = 0; i < lines.length; i++) {
			modelLines.addLine(i);
		}

		final var tmModel = new TMModel(modelLines);
		try {
			tmModel.setGrammar(grammar);
			assertTrue(tmModel.isLineInvalid(0), "First line is expected to be invalid");

			for (int i = 0; i < lines.length; i++) {
				tmModel.forceTokenization(i);
				assertFalse(tmModel.isLineInvalid(i), "Line " + i + " is expected to be valid");
			}
		} finally {
			tmModel.dispose();
		}
	}
}