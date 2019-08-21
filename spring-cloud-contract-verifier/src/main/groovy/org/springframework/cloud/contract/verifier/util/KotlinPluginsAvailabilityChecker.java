/*
 * Copyright 2013-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.contract.verifier.util;

import org.springframework.util.ClassUtils;

/**
 * @author Tim Ysewyn
 * @since 2.2.0
 */
public final class KotlinPluginsAvailabilityChecker {

	private KotlinPluginsAvailabilityChecker() {
	}

	private static boolean mavenSupport = false;

	private static boolean gradleSupport = false;

	static {
		ClassLoader classLoader = KotlinPluginsAvailabilityChecker.class.getClassLoader();
		try {
			ClassUtils.forName(
					"org.springframework.cloud.contract.maven.verifier.GenerateTestsMojo",
					classLoader);
			ClassUtils.forName("org.jetbrains.kotlin.maven.KotlinTestCompileMojo",
					classLoader);
			mavenSupport = true;
		}
		catch (ClassNotFoundException ex) {
		}
		try {
			ClassUtils.forName(
					"org.springframework.cloud.contract.verifier.plugin.GenerateServerTestsTask",
					classLoader);
			ClassUtils.forName("org.jetbrains.kotlin.gradle.tasks.KotlinCompile",
					classLoader);
			gradleSupport = true;
		}
		catch (ClassNotFoundException ex) {
		}
	}

	public static boolean hasKotlinSupport() {
		return mavenSupport || gradleSupport;
	}

	public static boolean hasMavenSupport() {
		return mavenSupport;
	}

	public static boolean hasGradleSupport() {
		return gradleSupport;
	}

}
