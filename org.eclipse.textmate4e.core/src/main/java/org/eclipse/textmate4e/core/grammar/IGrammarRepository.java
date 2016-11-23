/**
 *  Copyright (c) 2015-2016 Angelo ZERR.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 * This code is an translation of code copyrighted by Microsoft Corporation, and initially licensed under MIT.
 *
 * Contributors:
 *  - Microsoft Corporation: Initial code, written in TypeScript, licensed under MIT license
 *  - Angelo Zerr <angelo.zerr@gmail.com> - translation and adaptation to Java
 */
package org.eclipse.textmate4e.core.grammar;

import org.eclipse.textmate4e.core.internal.types.IRawGrammar;

/**
 * TextMate grammar repository API.
 * 
 * @see https://github.com/Microsoft/vscode-textmate/blob/master/src/grammar.ts
 *
 */
public interface IGrammarRepository {

	/**
	 * Lookup a raw grammar.
	 */
	IRawGrammar lookup(String scopeName);
}
