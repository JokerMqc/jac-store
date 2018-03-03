/*
 * Copyright 2002-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yph.generator.util.sqlparse;

import java.util.ArrayList;
import java.util.List;

/**
 * copy from spring
 * 
 * Holds information about a parsed SQL statement.
 *
 *@modify diyvan
 */
public class ParsedSql {

	private String originalSql;

	private List<String> parameterNames = new ArrayList<String>();
	
	private List<String> parameterPlaceholders = new ArrayList<String>();

	private List<int[]> parameterIndexes = new ArrayList<int[]>();

	private int namedParameterCount;

	private int unnamedParameterCount;

	private int totalParameterCount;


	/**
	 * Create a new instance of the {@link ParsedSql} class.
	 * @param originalSql the SQL statement that is being (or is to be) parsed
	 */
	ParsedSql(String originalSql) {
		this.originalSql = originalSql;
	}

	/**
	 * Return the SQL statement that is being parsed.
	 */
	String getOriginalSql() {
		return this.originalSql;
	}


	/**
	 * Add a named parameter parsed from this SQL statement.
	 * @param parameterName the name of the parameter
	 * @param startIndex the start index in the original SQL String
	 * @param endIndex the end index in the original SQL String
	 */
	public void addNamedParameter(String parameterName,String parameterPlaceholder, int startIndex, int endIndex) {
		this.parameterNames.add(parameterName);
		this.parameterPlaceholders.add(parameterPlaceholder);
		this.parameterIndexes.add(new int[] {startIndex, endIndex});
	}

	/**
	 * Return all of the parameters (bind variables) in the parsed SQL statement.
	 * Repeated occurences of the same parameter name are included here.
	 */
	public List<String> getParameterNames() {
		return this.parameterNames;
	}
	
	
	/**
	 * Return all of the parameters placeholder in the parsed SQL statement.
	 * @return
	 */
	public List<String> getParameterPlaceholders() {
		return parameterPlaceholders;
	}

	/**
	 * Return the parameter indexes for the specified parameter.
	 * @param parameterPosition the position of the parameter
	 * (as index in the parameter names List)
	 * @return the start index and end index, combined into
	 * a int array of length 2
	 */
	public int[] getParameterIndexes(int parameterPosition) {
		return this.parameterIndexes.get(parameterPosition);
	}

	/**
	 * Set the count of named parameters in the SQL statement.
	 * Each parameter name counts once; repeated occurences do not count here.
	 */
	public void setNamedParameterCount(int namedParameterCount) {
		this.namedParameterCount = namedParameterCount;
	}

	/**
	 * Return the count of named parameters in the SQL statement.
	 * Each parameter name counts once; repeated occurences do not count here.
	 */
	public int getNamedParameterCount() {
		return this.namedParameterCount;
	}

	/**
	 * Set the count of all of the unnamed parameters in the SQL statement.
	 */
	public void setUnnamedParameterCount(int unnamedParameterCount) {
		this.unnamedParameterCount = unnamedParameterCount;
	}

	/**
	 * Return the count of all of the unnamed parameters in the SQL statement.
	 */
	public int getUnnamedParameterCount() {
		return this.unnamedParameterCount;
	}

	/**
	 * Set the total count of all of the parameters in the SQL statement.
	 * Repeated occurences of the same parameter name do count here.
	 */
	public void setTotalParameterCount(int totalParameterCount) {
		this.totalParameterCount = totalParameterCount;
	}

	/**
	 * Return the total count of all of the parameters in the SQL statement.
	 * Repeated occurences of the same parameter name do count here.
	 */
	public int getTotalParameterCount() {
		return this.totalParameterCount;
	}


	/**
	 * Exposes the original SQL String.
	 */
	@Override
	public String toString() {
		return this.originalSql;
	}

}
